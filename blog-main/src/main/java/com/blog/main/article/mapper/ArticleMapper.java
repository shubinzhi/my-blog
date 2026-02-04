package com.blog.main.article.mapper;

import com.blog.common.entity.Article;
import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 文章 Mapper
 *
 * @author blog
 */
@Mapper
public interface ArticleMapper extends MPJBaseMapper<Article> {
}
