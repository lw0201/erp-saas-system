package com.zhuoxun.it.base.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.zhuoxun.it.base.entity.SalesmanVO;
import com.zhuoxun.it.base.service.ISalesmanService;
import com.zhuoxun.it.common.Result;
import com.zhuoxun.it.common.conditions.Wrapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Salesman实体控制层
 * 
 * @author liwen
 *
 */
@RestController
@Api(tags = "业务员管理")
public class SalesmanController {

    @Autowired
    ISalesmanService iSalesmanService;

    /**
     * 新增实体数据
     * 
     * @param entity
     *            业务实体
     * @return 返回新增影响的数据
     */
    @ApiOperation(value = "新增")
    @PostMapping("/v1/salesman/save")
    public Result<Integer> save(@RequestBody SalesmanVO entity) {
        return new Result<Integer>().success(iSalesmanService.insert(entity));
    }

    /**
     * 根据实体ID删除数据，并持久化删除数据。
     * 
     * @param id
     *            实体ID
     * @return 返回删除影响的实体
     */
    @ApiOperation(value = "删除")
    @DeleteMapping("/v1/salesman/delete/{id}")
    public Result<Integer> delete(@PathVariable("id") String id) {
        return new Result<Integer>().success(iSalesmanService.deleteById(id));
    }

    /**
     * 跟新实体数据并持久化保存操作
     * 
     * @param entity
     *            操作的业务实体对象
     * @return 返回影响的行
     */
    @ApiOperation(value = "更新")
    @PostMapping("/v1/salesman/update")
    public Result<Integer> update(@RequestBody SalesmanVO entity) {
        return new Result<Integer>().success(iSalesmanService.update(entity));
    }

    /**
     * 根据实体ID查询实体对象并返回实体对象的详细信息
     * 
     * @param id
     *            实体对象对应的ID
     * @return 返回实体对象的相信信息
     */
    @ApiOperation(value = "详情")
    @GetMapping("/v1/salesman/single/{id}")
    public Result<SalesmanVO> findById(@PathVariable("id") String id) {
        return new Result<SalesmanVO>().success(iSalesmanService.findById(id));
    }

    /**
     * 根据实体查询并返回实体对象集合
     * 
     * @param entity
     *            业务实体对象
     * @return 实体对象集合
     */
    @ApiOperation(value = "列表查询")
    @PostMapping("/v1/salesman/find/list")
    public Result<List<SalesmanVO>> findList(@RequestBody SalesmanVO entity) {
        return new Result<List<SalesmanVO>>().success(iSalesmanService.findList(entity));
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
    @PostMapping("/v1/salesman/page/{pageNo}/{pageSize}")
    public Result<PageInfo<SalesmanVO>> findPage(@RequestBody Wrapper<SalesmanVO> entity, @PathVariable("pageNo") int pageNo,
        @PathVariable("pageSize") int pageSize) {
        return new Result<PageInfo<SalesmanVO>>().success(iSalesmanService.findPage(entity, pageNo, pageSize));
    }

    /**
     * @param salesmanId
     *            业务员id
     * @param enable
     *            标识符
     * @return
     */
    @ApiOperation(value = "启用、禁用")
    @PostMapping("/v1/salesman/enable/{salesmanId}/{enable}")
    public Result<Integer> enable(@PathVariable("salesmanId") String salesmanId,
        @PathVariable("enable") String enable) {
        return new Result<Integer>().success(iSalesmanService.enable(salesmanId, salesmanId));

    }
}
