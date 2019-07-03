package com.zhuoxun.it.base.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhuoxun.it.base.dao.ICategoryFieldDao;
import com.zhuoxun.it.base.entity.CategoryFieldVO;
import com.zhuoxun.it.base.service.ICategoryFieldService;
import com.zhuoxun.it.common.base.impl.BaseService;
import com.zhuoxun.it.common.exception.ApplicationException;
import com.zhuoxun.it.common.utils.ObjectUtils;

/**
 * CategoryField实体业务逻辑处理层
 * 
 * @author liwen
 *
 */
@Service
public class CategoryFieldService extends BaseService<CategoryFieldVO> implements ICategoryFieldService {

    @Autowired
    ICategoryFieldDao iCategoryFieldDao;

    @Override
    public int insert(CategoryFieldVO entity) {
        CategoryFieldVO existsEntity = new CategoryFieldVO();
        existsEntity.setSource(entity.getSource());
        existsEntity.setDisplyName(entity.getDisplyName());
        if (ObjectUtils.isNotEmpty(existsEntity)) {
            throw new ApplicationException("显示名称已经重复");
        }
        int maxSort = iCategoryFieldDao.maxSort(entity.getCategoryId());
        entity.setSort(maxSort + 1);
        return super.insert(entity);
    }

    @Override
    public int deleteById(Serializable id) {
        int resultInt = 0;
        CategoryFieldVO field = iCategoryFieldDao.findById(id);
        resultInt = iCategoryFieldDao.deleteById(id);
        if (field != null) {
            CategoryFieldVO category = new CategoryFieldVO();
            category.setCategoryId(field.getCategoryId());
            List<CategoryFieldVO> colls = iCategoryFieldDao.findList(category);
            int index = 0;
            for (CategoryFieldVO categoryFieldVO : colls) {
                index++;
                if (categoryFieldVO.getSort() > index) {
                    CategoryFieldVO vo = new CategoryFieldVO();
                    vo.setSort(index);
                    vo.setId(categoryFieldVO.getId());
                    iCategoryFieldDao.update(vo);
                }
            }
        }
        return resultInt;
    }

    @Override
    public int moveUp(String id) {
        int rowSize = 0;
        CategoryFieldVO field = iCategoryFieldDao.findById(id);
        if (null == field) {
            throw new ApplicationException("数据可能被删除。");
        }
        CategoryFieldVO entity = new CategoryFieldVO();
        entity.setCategoryId(field.getCategoryId());
        entity.setSort(field.getSort() - 1);
        CategoryFieldVO upEntity = iCategoryFieldDao.query(entity);
        if (upEntity == null) {
            throw new ApplicationException("已经是最顶层，不能上移。");
        } else {
            entity.setSort(field.getSort());
            entity.setId(upEntity.getId());
            iCategoryFieldDao.update(entity);
            CategoryFieldVO currentEntity = new CategoryFieldVO();
            currentEntity.setId(id);
            currentEntity.setSort(upEntity.getSort());
            rowSize = iCategoryFieldDao.update(currentEntity);
        }
        return rowSize;
    }

    @Override
    public int moveDown(String id) {
        int rowSize = 0;
        CategoryFieldVO field = iCategoryFieldDao.findById(id);
        if (null == field) {
            throw new ApplicationException("数据可能被删除。");
        }
        CategoryFieldVO entity = new CategoryFieldVO();
        entity.setCategoryId(field.getCategoryId());
        entity.setSort(field.getSort() + 1);
        CategoryFieldVO downEntity = iCategoryFieldDao.query(entity);
        if (downEntity == null) {
            throw new ApplicationException("已经是最低层，不能下移。");
        } else {
            entity.setSort(field.getSort());
            entity.setId(downEntity.getId());
            iCategoryFieldDao.update(entity);
            CategoryFieldVO currentEntity = new CategoryFieldVO();
            currentEntity.setId(id);
            currentEntity.setSort(downEntity.getSort());
            rowSize = iCategoryFieldDao.update(currentEntity);
        }
        return rowSize;
    }

}
