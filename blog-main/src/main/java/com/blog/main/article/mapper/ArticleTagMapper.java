package com.blog.main.article.mapper;

import com.blog.common.entity.ArticleTag;
import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 文章-标签关联 Mapper
 *
 * @author blog
 */
@Mapper
public interface ArticleTagMapper extends MPJBaseMapper<ArticleTag> {
}
