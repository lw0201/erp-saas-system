package com.zhuoxun.it.base.service;

import com.zhuoxun.it.base.entity.CategoryFieldVO;
import com.zhuoxun.it.common.base.IBaseService;

/**
 * CategoryField实体业务逻辑处理层
 * 
 * @author liwen
 *
 */
public interface ICategoryFieldService extends IBaseService<CategoryFieldVO> {

    /**
     * 上移
     * 
     * @param id
     * @return 返回影响的数据行
     */
    public int moveUp(String id);

    /**
     * 下移
     * 
     * @param id
     * @return 返回影响的数据行
     */
    public int moveDown(String id);

}