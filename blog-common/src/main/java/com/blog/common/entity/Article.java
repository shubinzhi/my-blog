package com.blog.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 文章实体
 *
 * @author blog
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("article")
@Schema(description = "文章")
public class Article extends BaseEntity {

    @Schema(description = "标题")
    private String title;

    @Schema(description = "摘要")
    private String summary;

    @Schema(description = "内容 (Markdown)")
    private String content;

    @Schema(description = "封面图 URL")
    private String coverImage;

    @Schema(description = "分类 ID")
    private Long categoryId;

    @Schema(description = "作者 ID")
    private Long authorId;

    @Schema(description = "状态: 0-草稿 1-已发布 2-已下架")
    private Integer status;

    @Schema(description = "是否置顶: 0-否 1-是")
    private Integer isTop;

    @Schema(description = "浏览量")
    private Integer viewCount;

    @Schema(description = "点赞数")
    private Integer likeCount;
}
