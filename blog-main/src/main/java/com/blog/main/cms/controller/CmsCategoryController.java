package com.blog.main.cms.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.blog.common.entity.Category;
import com.blog.common.vo.R;
import com.blog.main.category.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * CMS - 分类管理
 *
 * @author blog
 */
@Tag(name = "CMS - 分类管理")
@RestController
@RequestMapping("/cms/category")
@RequiredArgsConstructor
@SaCheckLogin
public class CmsCategoryController {

    private final CategoryService categoryService;

    @Operation(summary = "获取分类列表")
    @GetMapping("/list")
    public R<List<Category>> list() {
        return R.success(categoryService.listAll());
    }

    @Operation(summary = "新增/更新分类")
    @PostMapping
    public R<Long> save(@RequestBody Category category) {
        return R.success(categoryService.saveCategory(category));
    }

    @Operation(summary = "删除分类")
    @DeleteMapping("/{id}")
    public R<Void> delete(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return R.success();
    }
}
