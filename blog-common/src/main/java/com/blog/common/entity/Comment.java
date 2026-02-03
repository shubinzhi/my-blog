package com.blog.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 评论实体
 *
 * @author blog
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("comment")
@Schema(description = "评论")
public class Comment extends BaseEntity {

    @Schema(description = "文章 ID")
    private Long articleId;

    @Schema(description = "评论者 ID")
    private Long userId;

    @Schema(description = "评论内容")
    private String content;

    @Schema(description = "父评论 ID (NULL 表示一级评论)")
    private Long parentId;

    @Schema(description = "回复目标用户 ID")
    private Long replyUserId;

    @Schema(description = "状态: 0-待审核 1-已通过 2-已拒绝")
    private Integer status;
}
