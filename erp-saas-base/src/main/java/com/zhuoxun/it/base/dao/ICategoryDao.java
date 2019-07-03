package com.zhuoxun.it.base.dao;

import java.util.List;

import com.zhuoxun.it.base.entity.CategoryVO;
import com.zhuoxun.it.common.base.IBaseDao;

/**
 * Category实体数据访问持久层接口
 * 
 * @author liwen
 *
 */
public interface ICategoryDao extends IBaseDao<CategoryVO> {

    /**
     * 查询品类管理tree树状结构数据
     * 
     * @return
     */
    List<CategoryVO> findListTree();

}