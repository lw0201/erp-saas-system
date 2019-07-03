package com.zhuoxun.it.base.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
import com.zhuoxun.it.base.entity.AttributeVO;
import com.zhuoxun.it.base.entity.TenantUserVO;
import com.zhuoxun.it.base.service.IAttributeService;
import com.zhuoxun.it.common.Option;
import com.zhuoxun.it.common.Result;
import com.zhuoxun.it.common.conditions.Wrapper;
import com.zhuoxun.it.common.enums.AttributeTypes;
import com.zhuoxun.it.common.enums.UserType;
import com.zhuoxun.it.common.exception.ApplicationException;
import com.zhuoxun.it.common.utils.SpringContextUtils;
import com.zhuoxun.it.common.utils.TokenUtil;
import com.zhuoxun.it.common.validation.groups.Insert;
import com.zhuoxun.it.common.validation.groups.Update;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Attribute实体控制层
 * 
 * @author liwen
 *
 */
@RestController
@Api(tags = "属性管理")
public class AttributeController {

    private static Logger logger = LoggerFactory.getLogger(StorehouseController.class);

    @Autowired
    IAttributeService iAttributeService;

    @Autowired
    private TokenUtil tokenUtil;

    @Autowired
    private IUserService iuserService;

    @SuppressWarnings({"unchecked", "rawtypes"})
    private String getTenantId() {
        JSONObject userJson = tokenUtil.getUserInfo(SpringContextUtils.getRequest());
        if (UserType.System.getCode() == Integer.parseInt(userJson.getString("userType"))) {
            return "0";
        } else if (UserType.Tenant.getCode() == Integer.parseInt(userJson.getString("userType"))) {
            return userJson.getString("tenantId");
        } else {
            TenantUserVO json = new TenantUserVO();
            json.setUserId(userJson.getString("userId"));
            Result<TenantUserVO> result = iuserService.query(json);
            if (result == null) {
                throw new ApplicationException("租户不存在");
            }
            Map<String, Object> map = (Map)result.getData();
            logger.info("fegin调用result_map============{}", map);
            return map.get("tenantId").toString();
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
    @PostMapping("/v1/attribute/save")
    public Result<Integer> save(@RequestBody @Validated({Insert.class}) AttributeVO entity) {
        entity.setTenantId(getTenantId());
        return new Result<Integer>().success(iAttributeService.insert(entity));
    }

    /**
     * 根据实体ID删除数据，并持久化删除数据。
     * 
     * @param id
     *            实体ID
     * @return 返回删除影响的实体
     */
    @ApiOperation(value = "删除")
    @DeleteMapping("/v1/attribute/delete/{id}")
    public Result<Integer> delete(@PathVariable("id") String id) {
        return new Result<Integer>().success(iAttributeService.deleteById(id));
    }

    /**
     * 跟新实体数据并持久化保存操作
     * 
     * @param entity
     *            操作的业务实体对象
     * @return 返回影响的行
     */
    @ApiOperation(value = "更新")
    @PostMapping("/v1/attribute/update")
    public Result<Integer> update(@RequestBody @Validated({Update.class}) AttributeVO entity) {
        return new Result<Integer>().success(iAttributeService.update(entity));
    }

    /**
     * 根据实体ID查询实体对象并返回实体对象的详细信息
     * 
     * @param id
     *            实体对象对应的ID
     * @return 返回实体对象的相信信息
     */
    @ApiOperation(value = "详情")
    @GetMapping("/v1/attribute/single/{id}")
    public Result<AttributeVO> findById(@PathVariable("id") String id) {
        return new Result<AttributeVO>().success(iAttributeService.findById(id));
    }

    /**
     * 根据实体查询并返回实体对象集合
     * 
     * @param entity
     *            业务实体对象
     * @return 实体对象集合
     */
    @ApiOperation(value = "列表查询")
    @PostMapping("/v1/attribute/find/list")
    public Result<List<AttributeVO>> findList(@RequestBody AttributeVO entity) {
        return new Result<List<AttributeVO>>().success(iAttributeService.findList(entity));
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
    @PostMapping("/v1/attribute/page/{pageNo}/{pageSize}")
    public Result<PageInfo<AttributeVO>> findPage(@RequestBody Wrapper<AttributeVO> entity,
        @PathVariable("pageNo") int pageNo, @PathVariable("pageSize") int pageSize) {
        return new Result<PageInfo<AttributeVO>>().success(iAttributeService.findPage(entity, pageNo, pageSize));
    }

    /**
     * 启用 禁用
     * 
     */
    @ApiOperation(value = "启用，禁用")
    @PostMapping("/v1/attribute/enable/{attributeId}/{enable}")
    public Result<Integer> update(@PathVariable("attributeId") String attributeId,
        @PathVariable("enable") String enable) {
        return new Result<Integer>().success(iAttributeService.enable(attributeId, enable));
    }

    /**
     * 属性类型
     */
    @ApiOperation(value = "获取所有类型")
    @GetMapping("/v1/attribute/get/types")
    public Result<List<Option>> getTypes() {
        List<Option> options = new ArrayList<>();
        for (AttributeTypes el : AttributeTypes.values()) {
            Option op = new Option(el.getName(),el.getValue());
            options.add(op);
        }
        return new Result<List<Option>>().success(options);
    }

}
