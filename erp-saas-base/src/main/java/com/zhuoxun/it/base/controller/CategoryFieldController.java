package com.zhuoxun.it.base.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.zhuo.xun.it.base.client.IUserService;
import com.zhuoxun.it.base.entity.CategoryFieldVO;
import com.zhuoxun.it.base.entity.TenantUserVO;
import com.zhuoxun.it.base.service.ICategoryFieldService;
import com.zhuoxun.it.common.Result;
import com.zhuoxun.it.common.conditions.Wrapper;
import com.zhuoxun.it.common.enums.UserType;
import com.zhuoxun.it.common.exception.ApplicationException;
import com.zhuoxun.it.common.utils.SpringContextUtils;
import com.zhuoxun.it.common.utils.TokenUtil;
import com.zhuoxun.it.common.validation.groups.Insert;
import com.zhuoxun.it.common.validation.groups.Update;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * CategoryField实体控制层
 * 
 * @author liwen
 *
 */
@RestController
@Api(tags = "品类字段")
public class CategoryFieldController {

    private static Logger logger = LoggerFactory.getLogger(CategoryFieldController.class);

    @Autowired
    ICategoryFieldService iCategoryFieldService;

    @Autowired
    private TokenUtil tokenUtil;

    @Autowired
    private IUserService iuserService;

    // 登录，判断时租户登录，还是系统用户登录，返回不同的值
    private String getTenantId() {
        JSONObject userJson = tokenUtil.getUserInfo(SpringContextUtils.getRequest());
        if (UserType.System.getCode() == Integer.parseInt(userJson.getString("userType"))) {
            return "1";
        } else if (UserType.Tenant.getCode() == Integer.parseInt(userJson.getString("userType"))) {
            return userJson.getString("tenantId");
        } else {
            TenantUserVO json = new TenantUserVO();
            json.setUserId(userJson.getString("userId"));
            Result<TenantUserVO> result = iuserService.query(json);
            if (result == null) {
                throw new ApplicationException("租户不存在");
            }
            TenantUserVO map = result.getData();
            logger.info("fegin调用result_map============{}", map);
            return map.getTenantId();
        }
    }

    /**
     * 新增实体数据
     * 
     * @param entity
     *            业务实体
     * @return 返回新增影响的数据
     */
    @ApiOperation(value = "新增")
    @PostMapping("/v1/categoryfield/save")
    public Result<Integer> save(@RequestBody @Validated({Insert.class}) CategoryFieldVO entity) {
        entity.setSource(getTenantId());
        return new Result<Integer>().success(iCategoryFieldService.insert(entity));
    }

    /**
     * 根据实体ID删除数据，并持久化删除数据。
     * 
     * @param id
     *            实体ID
     * @return 返回删除影响的实体
     */
    @ApiOperation(value = "删除")
    @DeleteMapping("/v1/categoryfield/delete/{id}")
    public Result<Integer> delete(@PathVariable("id") String id) {
        return new Result<Integer>().success(iCategoryFieldService.deleteById(id));
    }

    /**
     * 跟新实体数据并持久化保存操作
     * 
     * @param entity
     *            操作的业务实体对象
     * @return 返回影响的行
     */
    @ApiOperation(value = "更新")
    @PostMapping("/v1/categoryfield/update")
    public Result<Integer> update(@RequestBody @Validated({Update.class}) CategoryFieldVO entity) {
        return new Result<Integer>().success(iCategoryFieldService.update(entity));
    }

    /**
     * 根据实体ID查询实体对象并返回实体对象的详细信息
     * 
     * @param id
     *            实体对象对应的ID
     * @return 返回实体对象的相信信息
     */
    @ApiOperation(value = "详情")
    @GetMapping("/v1/categoryfield/single/{id}")
    public Result<CategoryFieldVO> findById(@PathVariable("id") String id) {
        return new Result<CategoryFieldVO>().success(iCategoryFieldService.findById(id));
    }

    /**
     * 根据实体查询并返回实体对象集合
     * 
     * @param entity
     *            业务实体对象
     * @return 实体对象集合
     */
    @ApiOperation(value = "列表查询")
    @PostMapping("/v1/categoryfield/find/list")
    public Result<List<CategoryFieldVO>> findList(@RequestBody CategoryFieldVO entity) {
        return new Result<List<CategoryFieldVO>>().success(iCategoryFieldService.findList(entity));
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
    @PostMapping("/v1/categoryfield/page/{pageNo}/{pageSize}")
    public Result<PageInfo<CategoryFieldVO>> findPage(@RequestBody Wrapper<CategoryFieldVO> entity,
        @PathVariable("pageNo") int pageNo, @PathVariable("pageSize") int pageSize) {
        return new Result<PageInfo<CategoryFieldVO>>()
            .success(iCategoryFieldService.findPage(entity, pageNo, pageSize));
    }

    /**
     * 上移
     * 
     * @param entity
     *            操作的业务实体对象
     * @return 返回影响的行
     */
    @ApiOperation(value = "上移")
    @PostMapping("/v1/categoryfield/moveup/{id}")
    public Result<Integer> moveUp(@PathVariable("id") String id) {
        return new Result<Integer>().success(iCategoryFieldService.moveUp(id));
    }

    /**
     * 下移
     * 
     * @param entity
     *            操作的业务实体对象
     * @return 返回影响的行
     */
    @ApiOperation(value = "下移")
    @PostMapping("/v1/categoryfield/movedown/{id}")
    public Result<Integer> moveDown(@PathVariable("id") String id) {
        return new Result<Integer>().success(iCategoryFieldService.moveDown(id));
    }

}
