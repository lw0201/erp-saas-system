package com.zhuoxun.it.iam.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhuoxun.it.common.base.impl.BaseService;
import com.zhuoxun.it.common.exception.ApplicationException;
import com.zhuoxun.it.common.handler.BusinessCode;
import com.zhuoxun.it.common.utils.EncryptUtils;
import com.zhuoxun.it.common.utils.StringUtils;
import com.zhuoxun.it.iam.dao.IUserDao;
import com.zhuoxun.it.iam.entity.TenantUserVO;
import com.zhuoxun.it.iam.entity.UserVO;
import com.zhuoxun.it.iam.service.ITenantUserService;

/**
 * TenantUser实体业务逻辑处理层
 * 
 * @author liwen
 *
 */
@Service
public class TenantUserService extends BaseService<TenantUserVO> implements ITenantUserService {
    
    @Autowired
    private IUserDao userDao;

    /**
     * 重置租户密码
     */
    @Override
    public String resetPassword(String tenantId) {
        String newpwd = StringUtils.getRandomString(6);
        TenantUserVO entity = new TenantUserVO();
        entity.setTenantId(tenantId);
        TenantUserVO vo = this.query(entity );
        if (vo == null) {
            throw new ApplicationException(BusinessCode.NOT_FOUND_DATA, "租户不存在");
        }
        UserVO user = userDao.findById(vo.getUserId());
        if (user == null) {
            throw new ApplicationException(BusinessCode.NOT_FOUND_DATA, "用户不存在");
        }
        user.setPassword(EncryptUtils.md5Base64(newpwd));
        userDao.update(user);
        return newpwd;
    }
}
