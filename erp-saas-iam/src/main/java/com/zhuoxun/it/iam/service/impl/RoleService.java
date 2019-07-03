package com.zhuoxun.it.iam.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextException;
import org.springframework.stereotype.Service;

import com.zhuoxun.it.common.base.impl.BaseService;
import com.zhuoxun.it.common.conditions.Criteria;
import com.zhuoxun.it.common.conditions.Wrapper;
import com.zhuoxun.it.common.utils.ObjectUtils;
import com.zhuoxun.it.iam.dao.IRoleDao;
import com.zhuoxun.it.iam.entity.RoleVO;
import com.zhuoxun.it.iam.service.IRoleService;

/**
 * Role实体业务逻辑处理层
 * 
 * @author liwen
 *
 */
@Service
public class RoleService extends BaseService<RoleVO> implements IRoleService {


    @Autowired
    IRoleDao roleDao;
    /**
     * 新增租户下的角色时，需要判断角色代码和角色是否重复
     */
    public int insert(RoleVO entity) {
        RoleVO existsRoleName = roleDao.queryByWrapper(new Wrapper<RoleVO>()
            .where(new Criteria().eq("tenantId", entity.getTenantId()).eq("roleName", entity.getRoleName())));
       
        RoleVO existsRoleCode = roleDao.queryByWrapper(new Wrapper<RoleVO>()
            .where(new Criteria().eq("tenantId", entity.getTenantId()).eq("roleCode", entity.getRoleCode())));
        
        if (ObjectUtils.isNotEmpty(existsRoleCode)) {
            throw new ApplicationContextException("角色编码已经存在");
        }
        
        if (ObjectUtils.isNotEmpty(existsRoleName)) {
            throw new ApplicationContextException("角色名称已经存在");
        }
        // 如果不重复就保存
        return roleDao.insert(entity);
    }

    /*
     * 更新租户下的角色管理时，需要判断去重
     */
    public int update(RoleVO entity) {
        RoleVO existsRoleName = roleDao.queryByWrapper(new Wrapper<RoleVO>()
            .where(new Criteria().eq("tenantId", entity.getTenantId()).eq("roleName", entity.getRoleName())));

        RoleVO existsRoleCode = roleDao.queryByWrapper(new Wrapper<RoleVO>()
            .where(new Criteria().eq("tenantId", entity.getTenantId()).eq("roleCode", entity.getRoleCode())));

        if (ObjectUtils.isEmpty(existsRoleCode)) {
            throw new ApplicationContextException("角色编码已经存在");
        }

        if (ObjectUtils.isEmpty(existsRoleName)) {
            throw new ApplicationContextException("角色名称已经存在");
        }
        // 如果不重复就保存
        return roleDao.update(entity);
    }



}
