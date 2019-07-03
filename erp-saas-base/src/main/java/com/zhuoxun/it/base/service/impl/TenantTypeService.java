package com.zhuoxun.it.base.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhuoxun.it.base.dao.ITenantTypeDao;
import com.zhuoxun.it.base.entity.TenantTypeVO;
import com.zhuoxun.it.base.service.ITenantTypeService;
import com.zhuoxun.it.common.base.impl.BaseService;
import com.zhuoxun.it.common.exception.ApplicationException;
import com.zhuoxun.it.common.utils.ObjectUtils;

/**
 * TenantType实体业务逻辑处理层
 * 
 * @author liwen
 *
 */
@Service
public class TenantTypeService extends BaseService<TenantTypeVO> implements ITenantTypeService {

    @Autowired
    private ITenantTypeDao tenantTypeDao;

    // 保存时,需要判断企业类型名称是否相同
    public int insert(TenantTypeVO entity) {
        TenantTypeVO tenantType = new TenantTypeVO();
        tenantType.setName(entity.getName());
        TenantTypeVO existsName = tenantTypeDao.query(tenantType);
        if (ObjectUtils.isNotEmpty(existsName)) {
            throw new ApplicationException("企业名称已存在。");
        }
        return tenantTypeDao.insert(entity);

    }
}
