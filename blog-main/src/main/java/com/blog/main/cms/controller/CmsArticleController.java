package com.blog.main.cms.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blog.common.dto.PageRequest;
import com.blog.common.entity.Article;
import com.blog.common.vo.R;
import com.blog.main.article.dto.ArticlePageDTO;
import com.blog.main.article.dto.ArticleSaveDTO;
import com.blog.main.article.service.ArticleService;
import com.blog.main.article.vo.ArticleDetailVO;
import com.blog.main.article.vo.ArticlePageVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * CMS - 文章管理
 *
 * @author blog
 */
@Tag(name = "CMS - 文章管理")
@RestController
@RequestMapping("/cms/article")
@RequiredArgsConstructor
@SaCheckLogin
public class CmsArticleController {

    private final ArticleService articleService;

    @Operation(summary = "分页查询文章")
    @PostMapping("/page")
    public R<Page<ArticlePageVO>> page(@RequestBody PageRequest<ArticlePageDTO> request) {
        ArticlePageDTO dto = request.getData() != null ? request.getData() : new ArticlePageDTO();
        Page<ArticlePageVO> page = articleService.pageArticle(request.toPage(), dto);
        return R.success(page);
    }

    @Operation(summary = "获取文章详情")
    @GetMapping("/{id}")
    public R<ArticleDetailVO> detail(@PathVariable Long id) {
        return R.success(articleService.getArticleDetail(id));
    }

    @Operation(summary = "新增/更新文章")
    @PostMapping
    public R<Long> save(@Valid @RequestBody ArticleSaveDTO dto) {
        return R.success(articleService.saveArticle(dto));
    }

    @Operation(summary = "删除文章")
    @DeleteMapping("/{id}")
    public R<Void> delete(@PathVariable Long id) {
        articleService.deleteArticle(id);
        return R.success();
    }

    @Operation(summary = "发布/下架文章")
    @PutMapping("/{id}/publish")
    public R<Void> publish(@PathVariable Long id, @RequestParam Integer status) {
        articleService.publishArticle(id, status);
        return R.success();
    }
}
