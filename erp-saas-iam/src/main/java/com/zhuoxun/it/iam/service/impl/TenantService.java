package com.zhuoxun.it.iam.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhuoxun.it.common.base.impl.BaseService;
import com.zhuoxun.it.common.conditions.Criteria;
import com.zhuoxun.it.common.conditions.Wrapper;
import com.zhuoxun.it.common.enums.Active;
import com.zhuoxun.it.common.enums.TenantState;
import com.zhuoxun.it.common.enums.UserType;
import com.zhuoxun.it.common.exception.ApplicationException;
import com.zhuoxun.it.common.utils.EncryptUtils;
import com.zhuoxun.it.common.utils.ObjectUtils;
import com.zhuoxun.it.iam.dao.ITenantDao;
import com.zhuoxun.it.iam.dao.ITenantUserDao;
import com.zhuoxun.it.iam.dao.IUserDao;
import com.zhuoxun.it.iam.entity.TenantUserVO;
import com.zhuoxun.it.iam.entity.TenantVO;
import com.zhuoxun.it.iam.entity.UserVO;
import com.zhuoxun.it.iam.service.ITenantService;
import com.zhuoxun.it.iam.service.ITenantUserService;
import com.zhuoxun.it.iam.service.IUserService;

/**
 * Tenant实体业务逻辑处理层
 * 
 * @author liwen
 *
 */
@Service
public class TenantService extends BaseService<TenantVO> implements ITenantService {

    @Autowired
    IUserService iUserService;

    @Autowired
    ITenantUserService iTenantUserService;

    @Autowired
    ITenantDao iTenantDao;

    ITenantUserDao iTenantUserDao;
    @Autowired
    private IUserDao userDao;

    @Transactional
    public int insert(TenantVO entity) {
        // 根据传入的租户的code,name.是否存在租户
//        TenantVO existsTenantCode = iTenantDao
//            .queryByWrapper(new Wrapper<TenantVO>().where(new Criteria().eq("creditCode", entity.getTenantCode())));
//        if (ObjectUtils.isNotEmpty(existsTenantCode)) {
//            throw new ApplicationException("企业信用代码已存在。");
//        }
        TenantVO existsTenantName = iTenantDao
            .queryByWrapper(new Wrapper<TenantVO>().where(new Criteria().eq("tenantName", entity.getTenantName())));
        if (ObjectUtils.isNotEmpty(existsTenantName)) {
            throw new ApplicationException("企业名称已存在。");
        }
        UserVO existsUser = new UserVO();
        existsUser.setPhone(entity.getPhone());
        /** 用户存在的情况下不新增用户,不存在的情况下新增用户 */
        UserVO exists = iUserService.query(existsUser);
        TenantUserVO tenantUser = new TenantUserVO();
        if (exists == null) {
            UserVO user = new UserVO();
            user.setUserAccount("ZX");
            user.setUserType(UserType.Tenant.getCode());
            user.setRealName(entity.getUserName());
            user.setPhone(entity.getPhone());
            user.setPassword(EncryptUtils.md5Base64(entity.getPassword()));
            user.setCreatedBy(entity.getCreatedBy());
            user.setLastUpdateBy(entity.getCreatedBy());
            iUserService.insert(user);
            tenantUser.setUserId(user.getId());
        } else {
            tenantUser.setUserId(exists.getId());
        }
        tenantUser.setCreatedBy(entity.getCreatedBy());
        tenantUser.setLastUpdateBy(entity.getCreatedBy());
        int resultRow = iTenantDao.insert(entity);
        tenantUser.setTenantId(entity.getId());
        /** 新增用戶跟租戶的关系 */
        iTenantUserService.insert(tenantUser);
        return resultRow;
    }

    /**
     * 根据租户id查询所有用户
     */
    @Override
    public List<UserVO> findUserByTenantId(String tenantId) {
        return userDao.findUserByTenantId(tenantId);
    }

    /**
     * 是否禁用租户
     */
    @Override
    public Integer enable(String tenantId, String enable) {
        Integer result = 0;
        TenantVO vo = new TenantVO();
        vo.setId(tenantId);
        if (Active.Y.name().equalsIgnoreCase(enable)) {
            vo.setState(TenantState.Enable.getCode());
            result = iTenantDao.update(vo);
        } else if (Active.N.name().equalsIgnoreCase(enable)) {
            vo.setState(TenantState.Disble.getCode());
            result = iTenantDao.update(vo);
        }
        return result;
    }

    /**
     * 移除租户下的用户
     */
    public Integer removeUser(String tenantId, String userId) {
        Integer result = iTenantUserService
            .delete(new Wrapper<TenantUserVO>().where(new Criteria().eq("tenantId", tenantId).eq("userId", userId)));
        return result;
    }

}
