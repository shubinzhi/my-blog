package com.blog.main.article.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

/**
 * 文章保存 DTO
 *
 * @author blog
 */
@Data
@Schema(description = "文章保存请求")
public class ArticleSaveDTO {

    @Schema(description = "文章 ID（更新时必填）")
    private Long id;

    @NotBlank(message = "标题不能为空")
    @Schema(description = "标题")
    private String title;

    @Schema(description = "摘要")
    private String summary;

    @NotBlank(message = "内容不能为空")
    @Schema(description = "内容 (Markdown)")
    private String content;

    @Schema(description = "封面图 URL")
    private String coverImage;

    @Schema(description = "分类 ID")
    private Long categoryId;

    @Schema(description = "标签 ID 列表")
    private List<Long> tagIds;

    @Schema(description = "是否置顶: 0-否 1-是")
    private Integer isTop;

    @Schema(description = "状态: 0-草稿 1-已发布")
    private Integer status;
}
