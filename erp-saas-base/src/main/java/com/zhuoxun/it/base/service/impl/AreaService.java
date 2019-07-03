package com.zhuoxun.it.base.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextException;
import org.springframework.stereotype.Service;

import com.zhuoxun.it.base.dao.IAreaDao;
import com.zhuoxun.it.base.entity.AreaVO;
import com.zhuoxun.it.base.service.IAreaService;
import com.zhuoxun.it.common.base.impl.BaseService;
import com.zhuoxun.it.common.enums.Active;
import com.zhuoxun.it.common.utils.ObjectUtils;

/**
 * Area实体业务逻辑处理层
 * 
 * @author liwen
 *
 */
@Service
public class AreaService extends BaseService<AreaVO> implements IAreaService {


    @Autowired
    IAreaDao areaDao;


    /**
     * 判断是否存在相同的名字
     * 
     * @param name
     * @return
     */
    public AreaVO exists(String name, String teanntId) {
        AreaVO existsEntity = new AreaVO();
        existsEntity.setName(name);
        existsEntity.setTenantId(teanntId);
        return areaDao.query(existsEntity);
    }

    /**
     * 新增去重
     */
    public int insert(AreaVO entity) {
        if (ObjectUtils.isNotEmpty(exists(entity.getName(), entity.getTenantId()))) {
            throw new ApplicationContextException("区域已存在");
        }
        return areaDao.insert(entity);
    }

    /**
     * 更新去重判断
     */
    public int update(AreaVO entity) {
        if (ObjectUtils.isNotEmpty(exists(entity.getName(), entity.getTenantId()))) {
            throw new ApplicationContextException("区域已存在");
        }
        return areaDao.update(entity);
    }
    /**
     * 启用,禁用
     */
    public Integer enable(String id, String enable) {
        AreaVO area = new AreaVO();
        area.setId(id);
        if (Active.Y.name().equalsIgnoreCase(enable)) {
            area.setState(Active.Y.name());
        } else if (Active.N.name().equalsIgnoreCase(enable)) {
            area.setState(Active.N.name());
        }
        return areaDao.update(area);
    }

}
