package com.blog.main.article.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blog.common.entity.Article;
import com.blog.common.entity.ArticleTag;
import com.blog.common.entity.Category;
import com.blog.common.entity.Tag;
import com.blog.common.exception.BizError;
import com.blog.common.exception.BizException;
import com.blog.main.article.dto.ArticlePageDTO;
import com.blog.main.article.dto.ArticleSaveDTO;
import com.blog.main.article.mapper.ArticleMapper;
import com.blog.main.article.mapper.ArticleTagMapper;
import com.blog.main.article.service.ArticleService;
import com.blog.main.article.vo.ArticleDetailVO;
import com.blog.main.article.vo.ArticlePageVO;
import com.blog.main.tag.mapper.TagMapper;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 文章 Service 实现
 *
 * @author blog
 */
@Service
@RequiredArgsConstructor
public class ArticleServiceImpl extends MPJBaseServiceImpl<ArticleMapper, Article> implements ArticleService {

    private final ArticleTagMapper articleTagMapper;
    private final TagMapper tagMapper;

    @Override
    public Page<ArticlePageVO> pageArticle(Page<Article> page, ArticlePageDTO dto) {
        MPJLambdaWrapper<Article> wrapper = new MPJLambdaWrapper<Article>()
                .selectAll(Article.class)
                .selectAs(Category::getName, ArticlePageVO::getCategoryName)
                .leftJoin(Category.class, Category::getId, Article::getCategoryId)
                .like(StringUtils.hasText(dto.getKeyword()), Article::getTitle, dto.getKeyword())
                .eq(dto.getCategoryId() != null, Article::getCategoryId, dto.getCategoryId())
                .eq(dto.getStatus() != null, Article::getStatus, dto.getStatus())
                .orderByDesc(Article::getIsTop)
                .orderByDesc(Article::getCreateTime);

        Page<ArticlePageVO> result = baseMapper.selectJoinPage(page, ArticlePageVO.class, wrapper);

        // 填充标签
        result.getRecords().forEach(this::fillTags);
        return result;
    }

    @Override
    public ArticleDetailVO getArticleDetail(Long id) {
        MPJLambdaWrapper<Article> wrapper = new MPJLambdaWrapper<Article>()
                .selectAll(Article.class)
                .selectAs(Category::getName, ArticleDetailVO::getCategoryName)
                .leftJoin(Category.class, Category::getId, Article::getCategoryId)
                .eq(Article::getId, id);

        ArticleDetailVO vo = baseMapper.selectJoinOne(ArticleDetailVO.class, wrapper);
        if (vo == null) {
            throw new BizException(BizError.ARTICLE_NOT_FOUND);
        }

        // 填充标签
        fillDetailTags(vo);
        return vo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long saveArticle(ArticleSaveDTO dto) {
        Article article = new Article();
        article.setId(dto.getId());
        article.setTitle(dto.getTitle());
        article.setSummary(dto.getSummary());
        article.setContent(dto.getContent());
        article.setCoverImage(dto.getCoverImage());
        article.setCategoryId(dto.getCategoryId());
        article.setIsTop(dto.getIsTop() != null ? dto.getIsTop() : 0);
        article.setStatus(dto.getStatus() != null ? dto.getStatus() : 0);

        if (dto.getId() == null) {
            // 新增
            article.setAuthorId(StpUtil.getLoginIdAsLong());
            article.setViewCount(0);
            baseMapper.insert(article);
        } else {
            // 更新
            baseMapper.updateById(article);
            // 删除旧标签关联
            articleTagMapper.delete(new LambdaQueryWrapper<ArticleTag>()
                    .eq(ArticleTag::getArticleId, dto.getId()));
        }

        // 保存标签关联
        if (!CollectionUtils.isEmpty(dto.getTagIds())) {
            List<ArticleTag> articleTags = dto.getTagIds().stream()
                    .map(tagId -> {
                        ArticleTag at = new ArticleTag();
                        at.setArticleId(article.getId());
                        at.setTagId(tagId);
                        return at;
                    })
                    .collect(Collectors.toList());
            articleTags.forEach(articleTagMapper::insert);
        }

        return article.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteArticle(Long id) {
        baseMapper.deleteById(id);
        articleTagMapper.delete(new LambdaQueryWrapper<ArticleTag>()
                .eq(ArticleTag::getArticleId, id));
    }

    @Override
    public void publishArticle(Long id, Integer status) {
        Article article = new Article();
        article.setId(id);
        article.setStatus(status);
        baseMapper.updateById(article);
    }

    @Override
    public void incrementViewCount(Long id) {
        baseMapper.update(null, new LambdaQueryWrapper<Article>()
                .eq(Article::getId, id)
                .setSql("view_count = view_count + 1"));
    }

    private void fillTags(ArticlePageVO vo) {
        List<Tag> tags = getTagsByArticleId(vo.getId());
        vo.setTags(tags.stream().map(t -> {
            ArticlePageVO.TagVO tagVO = new ArticlePageVO.TagVO();
            tagVO.setId(t.getId());
            tagVO.setName(t.getName());
            tagVO.setColor(t.getColor());
            return tagVO;
        }).collect(Collectors.toList()));
    }

    private void fillDetailTags(ArticleDetailVO vo) {
        List<Tag> tags = getTagsByArticleId(vo.getId());
        vo.setTags(tags.stream().map(t -> {
            ArticleDetailVO.TagVO tagVO = new ArticleDetailVO.TagVO();
            tagVO.setId(t.getId());
            tagVO.setName(t.getName());
            tagVO.setColor(t.getColor());
            return tagVO;
        }).collect(Collectors.toList()));
    }

    private List<Tag> getTagsByArticleId(Long articleId) {
        List<ArticleTag> articleTags = articleTagMapper.selectList(
                new LambdaQueryWrapper<ArticleTag>().eq(ArticleTag::getArticleId, articleId));
        if (CollectionUtils.isEmpty(articleTags)) {
            return new ArrayList<>();
        }
        List<Long> tagIds = articleTags.stream().map(ArticleTag::getTagId).collect(Collectors.toList());
        return tagMapper.selectBatchIds(tagIds);
    }
}
