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

import com.github.pagehelper.PageInfo;
import com.zhuoxun.it.common.Result;
import com.zhuoxun.it.common.conditions.Wrapper;
import com.zhuoxun.it.common.validation.groups.Insert;
import com.zhuoxun.it.common.validation.groups.Update;
import com.zhuoxun.it.iam.entity.MenuVO;
import com.zhuoxun.it.iam.service.IMenuService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Menu实体控制层
 * 
 * @author liwen
 *
 */
@RestController
@Api(tags = "菜单管理")
public class MenuController {

    @Autowired
    IMenuService iMenuService;

    /**
     * 新增实体数据
     * 
     * @param entity
     *            业务实体
     * @return 返回新增影响的数据
     */
    @ApiOperation(value = "新增")
    @PostMapping("/v1/menu/save")
    public Result<Integer> save(@RequestBody @Validated({Insert.class}) MenuVO entity) {
        return new Result<Integer>().success(iMenuService.insert(entity));
    }

    /**
     * 根据实体ID删除数据，并持久化删除数据。
     * 
     * @param id
     *            实体ID
     * @return 返回删除影响的实体
     */
    @ApiOperation(value = "删除")
    @DeleteMapping("/v1/menu/delete/{id}")
    public Result<Integer> delete(@PathVariable("id") String id) {
        return new Result<Integer>().success(iMenuService.deleteById(id));
    }

    /**
     * 跟新实体数据并持久化保存操作
     * 
     * @param entity
     *            操作的业务实体对象
     * @return 返回影响的行
     */
    @ApiOperation(value = "更新")
    @PostMapping("/v1/menu/update")
    public Result<Integer> update(@RequestBody @Validated({Update.class}) MenuVO entity) {
        return new Result<Integer>().success(iMenuService.update(entity));
    }

    /**
     * 根据实体ID查询实体对象并返回实体对象的详细信息
     * 
     * @param id
     *            实体对象对应的ID
     * @return 返回实体对象的相信信息
     */
    @ApiOperation(value = "详情")
    @GetMapping("/v1/menu/single/{id}")
    public Result<MenuVO> findById(@PathVariable("id") String id) {
        return new Result<MenuVO>().success(iMenuService.findById(id));
    }

    /**
     * 根据实体查询并返回实体对象集合
     * 
     * @param entity
     *            业务实体对象
     * @return 实体对象集合
     */
    @ApiOperation(value = "列表查询")
    @PostMapping("/v1/menu/find/list")
    public Result<List<MenuVO>> findList(@RequestBody MenuVO entity) {
        return new Result<List<MenuVO>>().success(iMenuService.findList(entity));
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
    @PostMapping("/v1/menu/page/{pageNo}/{pageSize}")
    public Result<PageInfo<MenuVO>> findPage(@RequestBody Wrapper<MenuVO> entity, @PathVariable("pageNo") int pageNo,
        @PathVariable("pageSize") int pageSize) {
        return new Result<PageInfo<MenuVO>>().success(iMenuService.findPage(entity, pageNo, pageSize));
    }

}
