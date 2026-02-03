package com.blog.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 文章-标签关联实体
 *
 * @author blog
 */
@Data
@TableName("article_tag")
@Schema(description = "文章-标签关联")
public class ArticleTag implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.ASSIGN_ID)
    @Schema(description = "主键 ID")
    private Long id;

    @Schema(description = "文章 ID")
    private Long articleId;

    @Schema(description = "标签 ID")
    private Long tagId;
}
