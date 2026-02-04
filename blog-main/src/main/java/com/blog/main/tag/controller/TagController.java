package com.blog.main.tag.controller;

import com.blog.common.entity.Tag;
import com.blog.common.vo.R;
import com.blog.main.tag.service.TagService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 博客前台 - 标签 API
 *
 * @author blog
 */
@io.swagger.v3.oas.annotations.tags.Tag(name = "博客前台 - 标签")
@RestController
@RequestMapping("/api/tag")
@RequiredArgsConstructor
public class TagController {

    private final TagService tagService;

    @Operation(summary = "获取标签列表")
    @GetMapping("/list")
    public R<List<Tag>> list() {
        return R.success(tagService.listAll());
    }
}
