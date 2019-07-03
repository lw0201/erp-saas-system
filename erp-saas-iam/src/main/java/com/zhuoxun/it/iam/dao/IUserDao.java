package com.zhuoxun.it.iam.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zhuoxun.it.common.base.IBaseDao;
import com.zhuoxun.it.common.conditions.Wrapper;
import com.zhuoxun.it.iam.entity.UserVO;

/**
 * User实体数据访问持久层接口
 * 
 * @author liwen
 *
 */
public interface IUserDao extends IBaseDao<UserVO> {

    /**
     * 根据租户id查询用户
     * 
     * @param tenantId
     * @return
     */
    List<UserVO> findUserByTenantId(String tenantId);
    
    
    List<UserVO> queryByLoginWrapper(@Param("wp") Wrapper<UserVO> wrapper);
}