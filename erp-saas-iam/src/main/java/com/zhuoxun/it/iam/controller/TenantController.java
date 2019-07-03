package com.zhuoxun.it.iam.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.zhuoxun.it.common.Result;
import com.zhuoxun.it.common.conditions.Wrapper;
import com.zhuoxun.it.common.exception.ApplicationException;
import com.zhuoxun.it.common.utils.BeanUtils;
import com.zhuoxun.it.common.utils.EncryptUtils;
import com.zhuoxun.it.common.validation.groups.Insert;
import com.zhuoxun.it.common.validation.groups.Update;
import com.zhuoxun.it.iam.entity.TenantUserVO;
import com.zhuoxun.it.iam.entity.TenantVO;
import com.zhuoxun.it.iam.entity.UserVO;
import com.zhuoxun.it.iam.service.ITenantService;
import com.zhuoxun.it.iam.service.ITenantUserService;
import com.zhuoxun.it.iam.service.IUserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Tenant实体控制层
 * 
 * @author liwen
 *
 */
@RestController
@Api(tags = "租户管理")
public class TenantController {

    @Autowired
    ITenantService iTenantService;

    @Autowired
    private ITenantUserService iTenantUserService;

    @Autowired
    private IUserService userService;

    /**
     * 新增实体数据
     * 
     * @param entity
     *            业务实体
     * @return 返回新增影响的数据
     */
    @ApiOperation(value = "新增")
    @PostMapping("/v1/tenant/save")
    public Result<Integer> save(@RequestBody @Validated({Insert.class}) TenantVO entity) {
        return new Result<Integer>().success(iTenantService.insert(entity));
    }

    /**
     * 根据实体ID删除数据，并持久化删除数据。
     * 
     * @param id
     *            实体ID
     * @return 返回删除影响的实体
     */
    @ApiOperation(value = "删除")
    @DeleteMapping("/v1/tenant/delete/{id}")
    public Result<Integer> delete(@PathVariable("id") String id) {
        return new Result<Integer>().success(iTenantService.deleteById(id));
    }

    /**
     * 跟新实体数据并持久化保存操作
     * 
     * @param entity
     *            操作的业务实体对象
     * @return 返回影响的行
     */
    @ApiOperation(value = "更新")
    @PostMapping("/v1/tenant/update")
    public Result<Integer> update(@RequestBody @Validated({Update.class}) TenantVO entity) {
        return new Result<Integer>().success(iTenantService.update(entity));
    }

    /**
     * 根据实体ID查询实体对象并返回实体对象的详细信息
     * 
     * @param id
     *            实体对象对应的ID
     * @return 返回实体对象的相信信息
     */
    @ApiOperation(value = "详情")
    @GetMapping("/v1/tenant/single/{id}")
    public Result<Object> findById(@PathVariable("id") String id) {
        TenantUserVO entity = new TenantUserVO();
        entity.setTenantId(id);
        TenantUserVO tuserVO = iTenantUserService.query(entity);
        if (tuserVO == null) {
            throw new ApplicationException("租户不存在");
        }
        UserVO user = userService.findById(tuserVO.getUserId());
        if (user == null) {
            throw new ApplicationException("租户不存在");
        }
        TenantVO tenant = iTenantService.findById(id);
        if (tenant == null) {
            throw new ApplicationException("租户不存在");
        }
        Map<String, Object> beanMap = BeanUtils.beanToMap(tenant);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("userAccount", user.getUserAccount());
        result.putAll(beanMap);
        return new Result<Object>().success(result);
    }

    /**
     * 根据实体查询并返回实体对象集合
     * 
     * @param entity
     *            业务实体对象
     * @return 实体对象集合
     */
    @ApiOperation(value = "列表查询")
    @PostMapping("/v1/tenant/find/list")
    public Result<List<TenantVO>> findList(@RequestBody TenantVO entity) {
        return new Result<List<TenantVO>>().success(iTenantService.findList(entity));
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
    @PostMapping("/v1/tenant/page/{pageNo}/{pageSize}")
    public Result<PageInfo<TenantVO>> findPage(@RequestBody Wrapper<TenantVO> entity,
        @PathVariable("pageNo") int pageNo, @PathVariable("pageSize") int pageSize) {
        return new Result<PageInfo<TenantVO>>().success(iTenantService.findPage(entity, pageNo, pageSize));
    }

    /**
     * 启用、禁用
     * 
     * @param tenantId
     * @param enable
     *            Y:启用，N：禁用 ，NA 过期
     * @return
     */
    @ApiOperation(value = "启用、禁用")
    @PostMapping("/v1/tenant/enable/{tenantId}/{enable}")
    public Result<Integer> enable(@PathVariable("tenantId") String tenantId, @PathVariable("enable") String enable) {
        return new Result<Integer>().success(iTenantService.enable(tenantId, enable));
    }

    /**
     * 
     * @param tenantId
     *            租户id
     * @return 用户信息
     */
    @ApiOperation(value = "查询租户下的用户")
    @GetMapping("/v1/tenant/find/user/{tenantId}")
    public Result<List<UserVO>> findTenatUser(@PathVariable("tenantId") String tenantId) {
        return new Result<List<UserVO>>().success(iTenantService.findUserByTenantId(tenantId));
    }

    /**
     * 重置密码
     * 
     * @param tenantId
     *            租户id
     * @return
     */
    @ApiOperation(value = "重置密码")
    @PostMapping("/v1/tenant/resetpassword/{tenantId}")
    public Result<String> resetPassword(@PathVariable("tenantId") String tenantId, @RequestBody TenantVO tenantVO) {
        TenantUserVO entity = new TenantUserVO();
        entity.setTenantId(tenantId);
        TenantUserVO vo = iTenantUserService.query(entity);
        if (vo == null) {
            return new Result<String>().fail("该数据不存在");
        }
        UserVO user = userService.findById(vo.getUserId());
        if (user == null) {
            return new Result<String>().fail("该数据不存在");
        }
        user.setPassword(EncryptUtils.md5Base64(tenantVO.getPassword()));
        userService.update(user);
        return new Result<String>().success();
    }

    /**
     * @param id
     * @return
     */
    @ApiOperation(value = "移除租户下的用户")
    @DeleteMapping("/v1/tenant/romove/user/{tenantId}/{userId}")
    public Result<Integer> deleteUser(@PathVariable("tenantId") String tenantId,
        @PathVariable("userId") String userId) {
        return new Result<Integer>().success(iTenantService.removeUser(tenantId, userId));
    }
}
