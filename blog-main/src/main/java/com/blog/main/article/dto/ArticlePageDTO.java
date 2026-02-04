package com.blog.main.article.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 文章分页查询 DTO
 *
 * @author blog
 */
@Data
@Schema(description = "文章分页查询条件")
public class ArticlePageDTO {

    @Schema(description = "关键词")
    private String keyword;

    @Schema(description = "分类 ID")
    private Long categoryId;

    @Schema(description = "标签 ID")
    private Long tagId;

    @Schema(description = "状态: 0-草稿 1-已发布 2-已下架")
    private Integer status;
}
