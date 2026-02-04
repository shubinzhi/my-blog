package com.blog.main.article.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blog.common.entity.Article;
import com.blog.main.article.dto.ArticlePageDTO;
import com.blog.main.article.dto.ArticleSaveDTO;
import com.blog.main.article.vo.ArticleDetailVO;
import com.blog.main.article.vo.ArticlePageVO;
import com.github.yulichang.base.MPJBaseService;

/**
 * 文章 Service
 *
 * @author blog
 */
public interface ArticleService extends MPJBaseService<Article> {

    /**
     * 分页查询文章（前台）
     */
    Page<ArticlePageVO> pageArticle(Page<Article> page, ArticlePageDTO dto);

    /**
     * 获取文章详情
     */
    ArticleDetailVO getArticleDetail(Long id);

    /**
     * 保存文章（新增/更新）
     */
    Long saveArticle(ArticleSaveDTO dto);

    /**
     * 删除文章
     */
    void deleteArticle(Long id);

    /**
     * 发布/下架文章
     */
    void publishArticle(Long id, Integer status);

    /**
     * 增加浏览量
     */
    void incrementViewCount(Long id);
}
