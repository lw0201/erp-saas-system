package com.zhuoxun.it.base.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhuoxun.it.base.dao.IAttributeDao;
import com.zhuoxun.it.base.entity.AttributeVO;
import com.zhuoxun.it.base.service.IAttributeService;
import com.zhuoxun.it.common.base.impl.BaseService;
import com.zhuoxun.it.common.enums.Active;
import com.zhuoxun.it.common.exception.ApplicationException;
import com.zhuoxun.it.common.utils.ObjectUtils;

/**
 * Attribute实体业务逻辑处理层
 * 
 * @author liwen
 *
 */
@Service
public class AttributeService extends BaseService<AttributeVO> implements IAttributeService {

    @Autowired
    IAttributeDao attributeDao;


    public AttributeVO exists(String name, String tenantId) {
        AttributeVO existsEntity = new AttributeVO();
        existsEntity.setName(name);
        existsEntity.setTenantId(tenantId);
        return attributeDao.query(existsEntity);
    }

    public int insert(AttributeVO entity) {
        if (ObjectUtils.isNotEmpty(exists(entity.getName(), entity.getTenantId()))) {
            throw new ApplicationException("该品类名称已经存在。");
        }
        return attributeDao.insert(entity);
    }

    public int update(AttributeVO entity) {
        if (ObjectUtils.isNotEmpty(exists(entity.getName(), entity.getTenantId()))) {
            throw new ApplicationException("该品类名称已经存在。");
        }
        return attributeDao.update(entity);
    }
    /**
     * 启用,禁用
     */
    public Integer enable(String attributeId, String enable) {
        AttributeVO vo = new AttributeVO();
        vo.setId(attributeId);
        if (Active.Y.name().equalsIgnoreCase(enable)) {
            vo.setState(Active.Y.name());
        } else if (Active.N.name().equalsIgnoreCase(enable)) {
            vo.setState(Active.N.name());
        }
        return attributeDao.update(vo);
    }

}
