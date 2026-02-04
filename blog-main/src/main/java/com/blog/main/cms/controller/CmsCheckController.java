package com.blog.main.cms.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.blog.common.entity.Category;
import com.blog.common.entity.Tag;
import com.blog.common.entity.User;
import com.blog.common.vo.R;
import com.blog.main.category.service.CategoryService;
import com.blog.main.tag.service.TagService;
import com.blog.main.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * CMS - 唯一性校验接口
 *
 * @author blog
 */
@io.swagger.v3.oas.annotations.tags.Tag(name = "CMS - 唯一性校验")
@RestController
@RequestMapping("/cms/check")
@RequiredArgsConstructor
public class CmsCheckController {

    private final UserService userService;
    private final CategoryService categoryService;
    private final TagService tagService;

    @Operation(summary = "校验用户名是否可用")
    @GetMapping("/username")
    public R<Boolean> checkUsername(
            @RequestParam String username,
            @RequestParam(required = false) Long excludeId) {
        long count = userService.count(new LambdaQueryWrapper<User>()
                .eq(User::getUsername, username)
                .ne(excludeId != null, User::getId, excludeId));
        return R.success(count == 0);
    }

    @Operation(summary = "校验分类名称是否可用")
    @GetMapping("/category-name")
    public R<Boolean> checkCategoryName(
            @RequestParam String name,
            @RequestParam(required = false) Long excludeId) {
        long count = categoryService.count(new LambdaQueryWrapper<Category>()
                .eq(Category::getName, name)
                .ne(excludeId != null, Category::getId, excludeId));
        return R.success(count == 0);
    }

    @Operation(summary = "校验标签名称是否可用")
    @GetMapping("/tag-name")
    public R<Boolean> checkTagName(
            @RequestParam String name,
            @RequestParam(required = false) Long excludeId) {
        long count = tagService.count(new LambdaQueryWrapper<Tag>()
                .eq(Tag::getName, name)
                .ne(excludeId != null, Tag::getId, excludeId));
        return R.success(count == 0);
    }
}
