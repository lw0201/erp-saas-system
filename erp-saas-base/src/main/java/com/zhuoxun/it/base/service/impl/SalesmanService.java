package com.zhuoxun.it.base.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhuoxun.it.base.dao.ISalesmanDao;
import com.zhuoxun.it.base.entity.SalesmanVO;
import com.zhuoxun.it.base.service.ISalesmanService;
import com.zhuoxun.it.common.base.impl.BaseService;
import com.zhuoxun.it.common.enums.Active;

/**
 * Salesman实体业务逻辑处理层
 * 
 * @author liwen
 *
 */
@Service
public class SalesmanService extends BaseService<SalesmanVO> implements ISalesmanService {
   
    @Autowired
    ISalesmanDao salesmanDao;
    
    /**
     * 启用，禁用
     */
    public Integer enable(String salesmanId, String enable) {
        SalesmanVO salesman = new SalesmanVO();
        salesman.setId(salesmanId);
        if (Active.Y.name().equalsIgnoreCase(enable)) {
            salesman.setState(Active.Y.name());
        } else if (Active.Y.name().equalsIgnoreCase(enable)) {
            salesman.setState(Active.N.name());
        }
        return salesmanDao.update(salesman);
    }

}
