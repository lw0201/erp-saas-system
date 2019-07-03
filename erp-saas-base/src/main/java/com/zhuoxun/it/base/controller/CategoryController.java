package com.zhuoxun.it.base.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.zhuoxun.it.base.entity.CategoryVO;
import com.zhuoxun.it.base.service.ICategoryService;
import com.zhuoxun.it.common.Result;
import com.zhuoxun.it.common.conditions.Wrapper;
import com.zhuoxun.it.common.validation.groups.Insert;
import com.zhuoxun.it.common.validation.groups.Update;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Category实体控制层
 * 
 * @author liwen
 *
 */
@RestController
@Api(tags = "品类管理")
public class CategoryController {

    @Autowired
    ICategoryService iCategoryService;

    /**
     * 新增实体数据
     * 
     * @param entity
     *            业务实体
     * @return 返回新增影响的数据
     */
    @ApiOperation(value = "新增")
    @PostMapping("/v1/category/save")
    public Result<Integer> save(@RequestBody @Validated({Insert.class}) CategoryVO entity) {
        return new Result<Integer>().success(iCategoryService.insert(entity));
    }

    /**
     * 根据实体ID删除数据，并持久化删除数据。
     * 
     * @param id
     *            实体ID
     * @return 返回删除影响的实体
     */
    @ApiOperation(value = "删除")
    @DeleteMapping("/v1/category/delete/{id}")
    public Result<Integer> delete(@PathVariable("id") String id) {
        return new Result<Integer>().success(iCategoryService.deleteById(id));
    }

    /**
     * 跟新实体数据并持久化保存操作
     * 
     * @param entity
     *            操作的业务实体对象
     * @return 返回影响的行
     */
    @ApiOperation(value = "更新")
    @PostMapping("/v1/category/update")
    public Result<Integer> update(@RequestBody @Validated({Update.class}) CategoryVO entity) {
        return new Result<Integer>().success(iCategoryService.update(entity));
    }

    /**
     * 根据实体ID查询实体对象并返回实体对象的详细信息
     * 
     * @param id
     *            实体对象对应的ID
     * @return 返回实体对象的相信信息
     */
    @ApiOperation(value = "详情")
    @GetMapping("/v1/category/single/{id}")
    public Result<CategoryVO> findById(@PathVariable("id") String id) {
        return new Result<CategoryVO>().success(iCategoryService.findById(id));
    }

    /**
     * 根据实体查询并返回实体对象集合
     * 
     * @param entity
     *            业务实体对象
     * @return 实体对象集合
     */
    @ApiOperation(value = "列表查询")
    @PostMapping("/v1/category/find/list")
    public Result<List<CategoryVO>> findList(@RequestBody CategoryVO entity) {
        return new Result<List<CategoryVO>>().success(iCategoryService.findList(entity));
    }

    /**
     * 根据实体查询并返回实体对象集合
     * 
     * @param entity
     *            业务实体对象
     * @return 实体对象集合
     */
    @ApiOperation(value = "列表查询")
    @GetMapping("/v1/category/find/tree")
    public Result<List<CategoryVO>> findListTree() {
        return new Result<List<CategoryVO>>().success(iCategoryService.findListTree());
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
    @PostMapping("/v1/category/page/{pageNo}/{pageSize}")
    public Result<PageInfo<CategoryVO>> findPage(@RequestBody Wrapper<CategoryVO> entity,
        @PathVariable("pageNo") int pageNo, @PathVariable("pageSize") int pageSize) {
        return new Result<PageInfo<CategoryVO>>().success(iCategoryService.findPage(entity, pageNo, pageSize));
    }

    /**
     * 
     * @param categoryId
     *            品类id
     * @param enable
     * 
     * @return
     */
    @ApiOperation(value = "启用,禁用")
    @PostMapping("/v1/category/enable/{categoryId}/{enable}")
    public Result<Integer> enable(@PathVariable("categoryId") String categoryId,
        @PathVariable("enable") String enable) {
        return new Result<Integer>().success(iCategoryService.enable(categoryId, enable));

    }

}
