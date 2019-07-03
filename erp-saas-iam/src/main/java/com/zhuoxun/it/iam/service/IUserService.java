package com.zhuoxun.it.iam.service;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.zhuoxun.it.common.base.IBaseService;
import com.zhuoxun.it.iam.entity.UserVO;

/**
 * User实体业务逻辑处理层
 * 
 * @author liwen
 *
 */
public interface IUserService extends IBaseService<UserVO> {

    // LoginVO login(UserVO user, HttpServletResponse response);

    Map<String, Object> login(UserVO user, HttpServletResponse response, int userTpye);

    /**
     * 保存用户和租户信息
     * 
     * @param user
     * @param tenantId
     * @return
     */
    int saveTenantUser(UserVO user, String tenantId);


}