package com.blog.main.category.service;

import com.blog.common.entity.Category;
import com.github.yulichang.base.MPJBaseService;

import java.util.List;

/**
 * 分类 Service
 *
 * @author blog
 */
public interface CategoryService extends MPJBaseService<Category> {

    /**
     * 获取所有分类
     */
    List<Category> listAll();

    /**
     * 保存分类
     */
    Long saveCategory(Category category);

    /**
     * 删除分类
     */
    void deleteCategory(Long id);
}
