package com.zhuoxun.it.base.service;

import com.zhuoxun.it.base.entity.LookupVO;
import com.zhuoxun.it.common.base.IBaseService;

/**
 * Lookup实体业务逻辑处理层
 * 
 * @author liwen
 *
 */
public interface ILookupService extends IBaseService<LookupVO> {

    /**
     * 启用,禁用
     * 
     * @param lookupId
     *            数字字典id
     * @return
     */

    Integer enable(String lookupId, String enable);
}