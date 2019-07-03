package com.zhuoxun.it.base.service.impl;

import java.io.Serializable;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhuoxun.it.base.dao.ILookupDao;
import com.zhuoxun.it.base.entity.LookupConfigVO;
import com.zhuoxun.it.base.entity.LookupVO;
import com.zhuoxun.it.base.service.ILookupConfigService;
import com.zhuoxun.it.base.service.ILookupService;
import com.zhuoxun.it.common.base.impl.BaseService;
import com.zhuoxun.it.common.conditions.Criteria;
import com.zhuoxun.it.common.conditions.Wrapper;
import com.zhuoxun.it.common.enums.Active;

/**
 * Lookup实体业务逻辑处理层
 * 
 * @author liwen
 *
 */
@Service
public class LookupService extends BaseService<LookupVO> implements ILookupService {

    @Autowired
    ILookupConfigService iLookupConfigService;

    @Autowired
    ILookupDao iLookupDao;

    @Override
    public LookupVO findById(Serializable id) {
        LookupVO lookUp = super.findById(id);
        LookupConfigVO config = new LookupConfigVO();
        config.setLooupId(lookUp.getId());
        lookUp.setLookupConfigs(iLookupConfigService.findList(config));
        return lookUp;
    }

    @Override
    public int insert(LookupVO entity) {
        int result = super.insert(entity);
        LookupVO query = new LookupVO();
        query.setLookupCode(entity.getLookupCode());
        query.setTenantId(entity.getTenantId());
        LookupVO vo = this.query(query);
        if (CollectionUtils.isNotEmpty(entity.getLookupConfigs())) {
            for (LookupConfigVO lookups : entity.getLookupConfigs()) {
                lookups.setLooupId(vo.getId());
                lookups.setCreatedBy(vo.getCreatedBy());
                lookups.setLastUpdateBy(vo.getLastUpdateBy());
            }
            iLookupConfigService.inserts(entity.getLookupConfigs());
        }
        return result;
    }

    @Override
    public int update(LookupVO entity) {
        int result = super.update(entity);
        LookupVO query = new LookupVO();
        query.setLookupCode(entity.getLookupCode());
        query.setTenantId(entity.getTenantId());
        LookupVO vo = this.query(query);
        if (CollectionUtils.isNotEmpty(entity.getLookupConfigs())) {
            for (LookupConfigVO lookups : entity.getLookupConfigs()) {
                lookups.setLooupId(vo.getId());
                lookups.setLastUpdateBy(vo.getLastUpdateBy());
                lookups.setCreatedBy(vo.getCreatedBy());
            }
            iLookupConfigService
                .delete(new Wrapper<LookupConfigVO>().where(new Criteria().eq("looupId", entity.getId())));
            iLookupConfigService.inserts(entity.getLookupConfigs());
        }
        return result;
    }

    @Override
    public Integer enable(String lookupId, String enable) {
        LookupVO vo = new LookupVO();
        vo.setId(lookupId);
        if (Active.Y.name().equalsIgnoreCase(enable)) {
            vo.setState(Active.Y.name());
        } else if (Active.N.name().equalsIgnoreCase(enable)) {
            vo.setState(Active.N.name());
        }
        return iLookupDao.update(vo);
    }
}
