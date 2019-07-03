package com.zhuoxun.it.base.service;

import com.zhuoxun.it.base.entity.AreaVO;
import com.zhuoxun.it.common.base.IBaseService;

/**
 * Area实体业务逻辑处理层
 * 
 * @author liwen
 *
 */
public interface IAreaService extends IBaseService<AreaVO> {

    /**
     * @param id
     *            区域id
     * @param enable
     *            标识符
     * @return
     */
    Integer enable(String id, String enable);

}