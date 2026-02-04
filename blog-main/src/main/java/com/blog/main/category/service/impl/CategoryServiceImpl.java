package com.blog.main.category.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.blog.common.entity.Category;
import com.blog.main.category.mapper.CategoryMapper;
import com.blog.main.category.service.CategoryService;
import com.github.yulichang.base.MPJBaseServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 分类 Service 实现
 *
 * @author blog
 */
@Service
public class CategoryServiceImpl extends MPJBaseServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Override
    public List<Category> listAll() {
        return list(new LambdaQueryWrapper<Category>().orderByAsc(Category::getSort));
    }

    @Override
    public Long saveCategory(Category category) {
        if (category.getId() == null) {
            baseMapper.insert(category);
        } else {
            baseMapper.updateById(category);
        }
        return category.getId();
    }

    @Override
    public void deleteCategory(Long id) {
        baseMapper.deleteById(id);
    }
}
