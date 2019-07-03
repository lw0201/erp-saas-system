package com.zhuoxun.it.base.service;

import com.zhuoxun.it.base.entity.AttributeVO;
import com.zhuoxun.it.common.base.IBaseService;

/**
 * Attribute实体业务逻辑处理层
 * 
 * @author liwen
 *
 */
public interface IAttributeService extends IBaseService<AttributeVO> {

    /**
     * 启用,禁用
     * 
     * @param attributeId
     *            属性id
     * @return
     */

    Integer enable(String attributeId, String enable);
}