package com.zhuoxun.it.iam.service;

import com.zhuoxun.it.common.base.IBaseService;
import com.zhuoxun.it.iam.entity.TenantUserVO;

/**
 * TenantUser实体业务逻辑处理层
 * 
 * @author liwen
 *
 */
public interface ITenantUserService extends IBaseService<TenantUserVO> {

    String resetPassword(String tenantId);

}