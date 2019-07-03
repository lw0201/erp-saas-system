package com.zhuoxun.it.base.service;

import java.util.List;

import com.zhuoxun.it.base.entity.CategoryVO;
import com.zhuoxun.it.common.base.IBaseService;

/**
 * Category实体业务逻辑处理层
 * 
 * @author liwen
 *
 */
public interface ICategoryService extends IBaseService<CategoryVO> {
    /**
     * 启用,禁用
     * 
     * @param icategoryId
     *            品类id
     * @return
     */

    Integer enable(String categoryId, String enable);

    /**
     * 品类查询
     * 
     * @return List<CategoryVO> 品类集合
     */
    List<CategoryVO> findListTree();

}