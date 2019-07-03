package com.zhuoxun.it.iam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.zhuoxun.it.common.Result;
import com.zhuoxun.it.common.conditions.Wrapper;
import com.zhuoxun.it.common.enums.Active;
import com.zhuoxun.it.common.enums.TenantState;
import com.zhuoxun.it.common.enums.UserType;
import com.zhuoxun.it.common.utils.DateUtils;
import com.zhuoxun.it.common.utils.EncryptUtils;
import com.zhuoxun.it.common.utils.SpringContextUtils;
import com.zhuoxun.it.common.utils.TokenUtil;
import com.zhuoxun.it.common.validation.groups.Insert;
import com.zhuoxun.it.common.validation.groups.Update;
import com.zhuoxun.it.iam.entity.UserVO;
import com.zhuoxun.it.iam.service.IUserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * User实体控制层
 * 
 * @author liwen
 *
 */
@RestController
@Api(tags = "用户管理")
public class UserController {

    @Autowired
    IUserService iUserService;
    
    @Autowired
    TokenUtil tokenUtil;

    /**
     * 新增实体数据
     * 
     * @param entity
     *            业务实体
     * @return 返回新增影响的数据
     */
    @ApiOperation(value = "新增")
    @PostMapping("/v1/user/save")
    public Result<Integer> save(@RequestBody @Validated({Insert.class}) UserVO entity) {
        // 密码加密
        String pwd = EncryptUtils.md5Base64(entity.getPassword());
        entity.setPassword(pwd);
        entity.setUserType(UserType.System.getCode());
        return new Result<Integer>().success(iUserService.insert(entity));
    }

    /**
     * 根据实体ID删除数据，并持久化删除数据。
     * 
     * @param id
     *            实体ID
     * @return 返回删除影响的实体
     */
    @ApiOperation(value = "删除")
    @DeleteMapping("/v1/user/delete/{id}")
    public Result<Integer> delete(@PathVariable("id") String id) {
        return new Result<Integer>().success(iUserService.deleteById(id));
    }

    /**
     * 跟新实体数据并持久化保存操作
     * 
     * @param entity
     *            操作的业务实体对象
     * @return 返回影响的行
     */
    @ApiOperation(value = "更新")
    @PostMapping("/v1/user/update")
    public Result<Integer> update(@RequestBody @Validated({Update.class}) UserVO entity) {
        return new Result<Integer>().success(iUserService.update(entity));
    }

    /**
     * 根据实体ID查询实体对象并返回实体对象的详细信息
     * 
     * @param id
     *            实体对象对应的ID
     * @return 返回实体对象的相信信息
     */
    @ApiOperation(value = "详情")
    @GetMapping("/v1/user/single/{id}")
    public Result<UserVO> findById(@PathVariable("id") String id) {
        return new Result<UserVO>().success(iUserService.findById(id));
    }

    /**
     * 根据实体查询并返回实体对象集合
     * 
     * @param entity
     *            业务实体对象
     * @return 实体对象集合
     */
    @ApiOperation(value = "列表查询")
    @PostMapping("/v1/user/find/list")
    public Result<List<UserVO>> findList(@RequestBody UserVO entity) {
        return new Result<List<UserVO>>().success(iUserService.findList(entity));
    }

    /**
     * 分页操作
     * 
     * @param entity
     *            业务实体对象
     * @param pageNo
     *            起始页
     * @param pageSize
     *            显示数量
     * @return 返回实体的分页信息
     */
    @ApiOperation(value = "分页列表查询")
    @PostMapping("/v1/user/page/{pageNo}/{pageSize}")
    public Result<PageInfo<UserVO>> findPage(@RequestBody Wrapper<UserVO> entity, @PathVariable("pageNo") int pageNo,
        @PathVariable("pageSize") int pageSize) {
        return new Result<PageInfo<UserVO>>().success(iUserService.findPage(entity, pageNo, pageSize));
    }

    /**
     * 
     * @param entity
     *            用户实体对象
     * @param tenantId
     *            租户id
     * @return
     */
    @ApiOperation(value = "保存租户用户信息")
    @PostMapping("/v1/user/{tenantId}/save")
    public Result<Integer> saveTenantUser(@RequestBody UserVO entity, @PathVariable("tenantId") String tenantId) {
        return new Result<Integer>().success(iUserService.saveTenantUser(entity, tenantId));

    }

    /**
     * 修改状态
     * 
     * @param userId
     *            用户id
     * @param enable
     *            Y:启用，N：禁用 ，NA 过期
     * @return
     */
    @ApiOperation(value = "修改状态")
    @PostMapping("/v1/user/update/{userId}/{enable}")
    public Result<String> updateUserState(@PathVariable("userId") String userId, @PathVariable("enable") String enable) {
        UserVO user = iUserService.findById(userId);
        if (user == null) {
            return new Result<String>().fail("用户不存在");
        }
        if (Active.Y.name().equalsIgnoreCase(enable)) {
            user.setState(TenantState.Enable.getCode());
        } else if (Active.N.name().equalsIgnoreCase(enable)) {
            user.setState(TenantState.Disble.getCode());
        }
        iUserService.update(user);
        return new Result<String>().success();

    }
    @ApiOperation(value = "获取用户信息")
    @PostMapping("/v1/user/info")
    public Result<UserVO> getUserInfo(){
        JSONObject json = tokenUtil.getUserInfo(SpringContextUtils.getRequest());
        UserVO user = new UserVO();
        user.setId(json.getString("userId"));
        user.setUserAccount(json.getString("userAccount"));
        user.setPhone(json.getString("phone"));
        user.setRealName(json.getString("realName"));
        user.setSex(json.getString("sex"));
        user.setLastUpdateDate(DateUtils.strToDate(json.getString("lastUpdateDate")));
        return new Result<UserVO>().success(user);
    }
}
