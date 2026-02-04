package com.blog.main.article.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blog.common.dto.PageRequest;
import com.blog.common.entity.Article;
import com.blog.common.vo.R;
import com.blog.main.article.dto.ArticlePageDTO;
import com.blog.main.article.service.ArticleService;
import com.blog.main.article.vo.ArticleDetailVO;
import com.blog.main.article.vo.ArticlePageVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 博客前台 - 文章 API
 *
 * @author blog
 */
@Tag(name = "博客前台 - 文章")
@RestController
@RequestMapping("/api/article")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @Operation(summary = "分页查询文章")
    @PostMapping("/page")
    public R<Page<ArticlePageVO>> page(@RequestBody PageRequest<ArticlePageDTO> request) {
        ArticlePageDTO dto = request.getData() != null ? request.getData() : new ArticlePageDTO();
        dto.setStatus(1); // 前台只查询已发布的文章
        Page<ArticlePageVO> page = articleService.pageArticle(request.toPage(), dto);
        return R.success(page);
    }

    @Operation(summary = "获取文章详情")
    @GetMapping("/{id}")
    public R<ArticleDetailVO> detail(@PathVariable Long id) {
        // 增加浏览量
        articleService.incrementViewCount(id);
        ArticleDetailVO vo = articleService.getArticleDetail(id);
        return R.success(vo);
    }
}
