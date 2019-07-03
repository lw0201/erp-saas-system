package com.zhuoxun.it.base.dao;

import com.zhuoxun.it.base.entity.CategoryFieldVO;
import com.zhuoxun.it.common.base.IBaseDao;

/**
 * CategoryField实体数据访问持久层接口
 * 
 * @author liwen
 *
 */
public interface ICategoryFieldDao extends IBaseDao<CategoryFieldVO> {

    /**
     * 查询最大的序列
     * 
     * @param entity
     * @return
     */
    public Integer maxSort(String categoryId);

}