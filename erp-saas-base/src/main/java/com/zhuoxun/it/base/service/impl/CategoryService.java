package com.zhuoxun.it.base.service.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextException;
import org.springframework.stereotype.Service;

import com.zhuoxun.it.base.dao.ICategoryDao;
import com.zhuoxun.it.base.dao.ICategoryFieldDao;
import com.zhuoxun.it.base.entity.CategoryFieldVO;
import com.zhuoxun.it.base.entity.CategoryVO;
import com.zhuoxun.it.base.service.ICategoryService;
import com.zhuoxun.it.common.base.impl.BaseService;
import com.zhuoxun.it.common.enums.Active;
import com.zhuoxun.it.common.exception.ApplicationException;
import com.zhuoxun.it.common.utils.ObjectUtils;

/**
 * Category实体业务逻辑处理层
 * 
 * @author liwen
 *
 */
@Service
public class CategoryService extends BaseService<CategoryVO> implements ICategoryService {

    @Autowired
    ICategoryFieldDao icategoryFieldDao;

    @Autowired
    ICategoryDao categoryDao;

    /**
     * 保存品类类型
     */
    public int insert(CategoryVO entity) {
        if (ObjectUtils.isNotEmpty(exists(entity.getName()))) {
            throw new ApplicationException("该品类名称已经存在。");
        }
        return categoryDao.insert(entity);
    }

    public CategoryVO exists(String name) {
        CategoryVO existsEntity = new CategoryVO();
        existsEntity.setName(name);
        return categoryDao.query(existsEntity);
    }

    /**
     * 更新品类
     */
    public int update(CategoryVO entity) {
        if (ObjectUtils.isNotEmpty(exists(entity.getName()))) {
            throw new ApplicationException("该品类名称已经存在。");
        }
        return categoryDao.update(entity);
    }

    /**
     * 启用，和禁用
     */
    @Override
    public Integer enable(String categoryId, String enable) {
        CategoryVO cvo = categoryDao.findById(categoryId);
        List<CategoryVO> level2 = null;
        if (StringUtils.isBlank(cvo.getPid()) || StringUtils.equalsIgnoreCase("0", cvo.getPid())) {
            if (Active.Y.name().equalsIgnoreCase(enable)) {
                cvo.setState(Active.Y.name());
                return categoryDao.update(cvo);
            }
            level2 = categoryDao.findList(new CategoryVO(categoryId, Active.Y.name())); // 查询启动的二级品类
            if (CollectionUtils.isNotEmpty(level2) && Active.N.name().equalsIgnoreCase(enable)) {
                throw new ApplicationContextException("二级品类中存在上架商品，无法下架一级品类");
            } else if (CollectionUtils.isEmpty(level2) && Active.N.name().equalsIgnoreCase(enable)) {
                cvo.setState(Active.N.name());
                return categoryDao.update(cvo);
            }
        } else {
            CategoryVO secoundLevel = categoryDao.findById(cvo.getPid());
            if (Active.Y.name().equalsIgnoreCase(enable)) { // 如果二级品类全部时禁用突然启动一个，那么父类也要启动状态
                cvo.setState(Active.Y.name());
                if (org.apache.commons.lang3.StringUtils.equalsIgnoreCase(Active.N.name(), secoundLevel.getState())) {
                    secoundLevel.setState(Active.Y.name());
                    categoryDao.update(secoundLevel);
                }
            } else if (Active.N.name().equalsIgnoreCase(enable)) {
                cvo.setState(Active.N.name());
            }
            return categoryDao.update(cvo);
        }
        return 0;
    }

    /**
     * 编辑时带出所有的关联信息
     */
    public CategoryVO findById(Serializable id) {
        CategoryFieldVO category = new CategoryFieldVO();
        category.setCategoryId(id.toString());
        List<CategoryFieldVO> categoryFields = icategoryFieldDao.findList(category);
        CategoryVO entity = categoryDao.findById(id);
        if (CollectionUtils.isNotEmpty(categoryFields)) {
            entity.setCategoryFields(categoryFields);
        }
        return entity;
    }

    @Override
    public List<CategoryVO> findListTree() {
        return categoryDao.findListTree();
    }

}
