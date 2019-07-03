package com.zhuoxun.it.base.service;

import com.zhuoxun.it.base.entity.SalesmanVO;
import com.zhuoxun.it.common.base.IBaseService;

/**
 * Salesman实体业务逻辑处理层
 * 
 * @author liwen
 *
 */
public interface ISalesmanService extends IBaseService<SalesmanVO> {

    /**
     * 
     * @param salesmanId
     *            业务员id
     * @param enable
     *            启用，禁用
     * @return
     */
    public Integer enable(String salesmanId, String enable);

}