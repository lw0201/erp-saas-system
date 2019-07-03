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
import com.zhuoxun.it.common.enums.UserType;
import com.zhuoxun.it.common.exception.ApplicationException;
import com.zhuoxun.it.common.handler.BusinessCode;
import com.zhuoxun.it.common.utils.SpringContextUtils;
import com.zhuoxun.it.common.utils.TokenUtil;
import com.zhuoxun.it.common.validation.groups.Insert;
import com.zhuoxun.it.common.validation.groups.Update;
import com.zhuoxun.it.iam.entity.DeptVO;
import com.zhuoxun.it.iam.entity.TenantUserVO;
import com.zhuoxun.it.iam.service.IDeptService;
import com.zhuoxun.it.iam.service.ITenantUserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Dept实体控制层
 * 
 * @author liwen
 *
 */
@RestController
@Api(tags = "部门管理")
public class DeptController {

    @Autowired
    IDeptService iDeptService;

    @Autowired
    private TokenUtil tokenUtil;

    @Autowired
    private ITenantUserService iTenantUserService;

    private String getTenantId() {
        JSONObject userJson = tokenUtil.getUserInfo(SpringContextUtils.getRequest());
        if (UserType.Tenant.getCode() == Integer.parseInt(userJson.getString("userType"))) {
            return userJson.getString("tenantId");
        } else {
            TenantUserVO query = new TenantUserVO();
            query.setUserId(userJson.getString("userId"));
            TenantUserVO result = iTenantUserService.query(query);
            if (result == null) {
                throw new ApplicationException("租户不存在");
            }
            return result.getTenantId();
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
    @PostMapping("/v1/dept/save")
    public Result<Integer> save(@RequestBody @Validated({Insert.class}) DeptVO entity) {
        entity.setTenantId(getTenantId());
        return new Result<Integer>().success(iDeptService.insert(entity));
    }

    /**
     * 根据实体ID删除数据，并持久化删除数据。
     * 
     * @param id
     *            实体ID
     * @return 返回删除影响的实体
     */
    @ApiOperation(value = "删除")
    @DeleteMapping("/v1/dept/delete/{id}")
    public Result<Integer> delete(@PathVariable("id") String id) {
        DeptVO vo = iDeptService.findById(id);
        if(vo == null) {
            throw new ApplicationException(BusinessCode.NOT_FOUND_DATA,"数据不存在");
        }
        if(vo.getDeptLevel().intValue() == 0) {
            throw new ApplicationException("最高部门不可删除");
        }
        //TODO
        return new Result<Integer>().success(iDeptService.deleteById(id));
    }

    /**
     * 跟新实体数据并持久化保存操作
     * 
     * @param entity
     *            操作的业务实体对象
     * @return 返回影响的行
     */
    @ApiOperation(value = "更新")
    @PostMapping("/v1/dept/update")
    public Result<Integer> update(@RequestBody @Validated({Update.class}) DeptVO entity) {
        return new Result<Integer>().success(iDeptService.update(entity));
    }

    /**
     * 根据实体ID查询实体对象并返回实体对象的详细信息
     * 
     * @param id
     *            实体对象对应的ID
     * @return 返回实体对象的相信信息
     */
    @ApiOperation(value = "详情")
    @GetMapping("/v1/dept/single/{id}")
    public Result<DeptVO> findById(@PathVariable("id") String id) {
        return new Result<DeptVO>().success(iDeptService.findById(id));
    }

    /**
     * 根据实体查询并返回实体对象集合
     * 
     * @param entity
     *            业务实体对象
     * @return 实体对象集合
     */
    @ApiOperation(value = "列表查询")
    @PostMapping("/v1/dept/find/list")
    public Result<List<DeptVO>> findList(@RequestBody DeptVO entity) {
        entity.setTenantId(getTenantId());
        return new Result<List<DeptVO>>().success(iDeptService.findList(entity));
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
    @PostMapping("/v1/dept/page/{pageNo}/{pageSize}")
    public Result<PageInfo<DeptVO>> findPage(@RequestBody Wrapper<DeptVO> entity, @PathVariable("pageNo") int pageNo,
        @PathVariable("pageSize") int pageSize) {
        return new Result<PageInfo<DeptVO>>().success(iDeptService.findPage(entity, pageNo, pageSize));
    }

}
