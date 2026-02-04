package com.blog.main.cms.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.blog.common.entity.Tag;
import com.blog.common.vo.R;
import com.blog.main.tag.service.TagService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * CMS - 标签管理
 *
 * @author blog
 */
@io.swagger.v3.oas.annotations.tags.Tag(name = "CMS - 标签管理")
@RestController
@RequestMapping("/cms/tag")
@RequiredArgsConstructor
@SaCheckLogin
public class CmsTagController {

    private final TagService tagService;

    @Operation(summary = "获取标签列表")
    @GetMapping("/list")
    public R<List<Tag>> list() {
        return R.success(tagService.listAll());
    }

    @Operation(summary = "新增/更新标签")
    @PostMapping
    public R<Long> save(@RequestBody Tag tag) {
        return R.success(tagService.saveTag(tag));
    }

    @Operation(summary = "删除标签")
    @DeleteMapping("/{id}")
    public R<Void> delete(@PathVariable Long id) {
        tagService.deleteTag(id);
        return R.success();
    }
}
