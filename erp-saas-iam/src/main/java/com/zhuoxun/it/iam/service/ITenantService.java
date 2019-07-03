package com.zhuoxun.it.iam.service;

import java.util.List;

import com.zhuoxun.it.common.base.IBaseService;
import com.zhuoxun.it.iam.entity.TenantVO;
import com.zhuoxun.it.iam.entity.UserVO;

/**
 * Tenant实体业务逻辑处理层
 * 
 * @author liwen
 *
 */
public interface ITenantService extends IBaseService<TenantVO> {
    /**
     * 根据租户id查询所有的租户
     * 
     * @param tenantId
     * @return
     */
    List<UserVO> findUserByTenantId(String tenantId);

    /**
     * 是否禁用租户
     * 
     * @param tenantId
     * @param enable
     *            Y:启用，N：禁用 ，NA 过期
     * @return
     */
    Integer enable(String tenantId, String enable);

    /**
     * 移除租户下的用户
     * 
     * @param tenantId
     * @param userId
     * @return
     */
    Integer removeUser(String tenantId, String userId);
}