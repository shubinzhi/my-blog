package com.blog.main.category.controller;

import com.blog.common.entity.Category;
import com.blog.common.vo.R;
import com.blog.main.category.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 博客前台 - 分类 API
 *
 * @author blog
 */
@Tag(name = "博客前台 - 分类")
@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @Operation(summary = "获取分类列表")
    @GetMapping("/list")
    public R<List<Category>> list() {
        return R.success(categoryService.listAll());
    }
}
