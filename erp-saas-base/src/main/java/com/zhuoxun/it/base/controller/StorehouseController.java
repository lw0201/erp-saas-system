package com.zhuoxun.it.base.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.zhuo.xun.it.base.client.IUserService;
import com.zhuoxun.it.base.entity.StorehouseVO;
import com.zhuoxun.it.base.entity.TenantUserVO;
import com.zhuoxun.it.base.service.IStorehouseService;
import com.zhuoxun.it.common.Result;
import com.zhuoxun.it.common.conditions.Criteria;
import com.zhuoxun.it.common.conditions.Criterion;
import com.zhuoxun.it.common.conditions.Operation;
import com.zhuoxun.it.common.conditions.Wrapper;
import com.zhuoxun.it.common.enums.Active;
import com.zhuoxun.it.common.enums.UserType;
import com.zhuoxun.it.common.exception.ApplicationException;
import com.zhuoxun.it.common.utils.SpringContextUtils;
import com.zhuoxun.it.common.utils.TokenUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Storehouse实体控制层
 * 
 * @author liwen
 *
 */
@RestController
@Api(tags = "仓库管理")
public class StorehouseController {

    private static Logger logger = LoggerFactory.getLogger(StorehouseController.class);

    @Autowired
    IStorehouseService iStorehouseService;

    @Autowired
    private TokenUtil tokenUtil;

    @Autowired
    private IUserService iuserService;

    private String getTenantId() {
        JSONObject userJson = tokenUtil.getUserInfo(SpringContextUtils.getRequest());
        if (UserType.Tenant.getCode() == Integer.parseInt(userJson.getString("userType"))) {
            return userJson.getString("tenantId");
        } else {

            TenantUserVO json = new TenantUserVO();
            json.setUserId(userJson.getString("userId"));
            Result<TenantUserVO> result = iuserService.query(json);
            if(result == null) {
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
    @PostMapping("/v1/storehouse/save")
    public Result<Integer> save(@RequestBody StorehouseVO entity) {
        entity.setTenantId(getTenantId());
        return new Result<Integer>().success(iStorehouseService.insert(entity));
    }

    /**
     * 根据实体ID删除数据，并持久化删除数据。
     * 
     * @param id
     *            实体ID
     * @return 返回删除影响的实体
     */
    @ApiOperation(value = "删除")
    @DeleteMapping("/v1/storehouse/delete/{id}")
    public Result<Integer> delete(@PathVariable("id") String id) {
      //TODO
        return new Result<Integer>().success(iStorehouseService.deleteById(id));
    }

    /**
     * 跟新实体数据并持久化保存操作
     * 
     * @param entity
     *            操作的业务实体对象
     * @return 返回影响的行
     */
    @ApiOperation(value = "更新")
    @PostMapping("/v1/storehouse/update")
    public Result<Integer> update(@RequestBody StorehouseVO entity) {
        return new Result<Integer>().success(iStorehouseService.update(entity));
    }

    /**
     * 根据实体ID查询实体对象并返回实体对象的详细信息
     * 
     * @param id
     *            实体对象对应的ID
     * @return 返回实体对象的相信信息
     */
    @ApiOperation(value = "详情")
    @GetMapping("/v1/storehouse/single/{id}")
    public Result<StorehouseVO> findById(@PathVariable("id") String id) {
        return new Result<StorehouseVO>().success(iStorehouseService.findById(id));
    }

    /**
     * 根据实体查询并返回实体对象集合
     * 
     * @param entity
     *            业务实体对象
     * @return 实体对象集合
     */
    @ApiOperation(value = "列表查询")
    @PostMapping("/v1/storehouse/find/list")
    public Result<List<StorehouseVO>> findList(@RequestBody StorehouseVO entity) {
        entity.setTenantId(getTenantId());
        return new Result<List<StorehouseVO>>().success(iStorehouseService.findList(entity));
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
    @PostMapping("/v1/storehouse/page/{pageNo}/{pageSize}")
    public Result<PageInfo<StorehouseVO>> findPage(@RequestBody Wrapper<StorehouseVO> entity,
        @PathVariable("pageNo") int pageNo, @PathVariable("pageSize") int pageSize) {
        List<Criteria> list = entity.getCriterias();
        for (Criteria criteria : list) {
            List<Criterion> inn = criteria.getCriterions();
            inn.add(new Criterion("tenantId", Operation.eq, getTenantId()));
        }
        return new Result<PageInfo<StorehouseVO>>().success(iStorehouseService.findPage(entity, pageNo, pageSize));
    }

    @ApiOperation(value = "启用,禁用")
    @PostMapping("/v1/storehouse/enable/{id}/{enable}")
    public Result<String> enable(@PathVariable("id") String id, @PathVariable("enable") String enable) {
        StorehouseVO vo = iStorehouseService.findById(id);
        if (vo == null) {
            return new Result<String>().fail("仓库不存在");
        }
        if (Active.Y.getName().equalsIgnoreCase(enable)) {
            vo.setState(Active.Y.getName());
        } else {
            vo.setState(Active.N.getName());
        }
        iStorehouseService.update(vo);
        return new Result<String>().success();
    }

}
