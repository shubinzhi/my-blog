package com.blog.common.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 实体基类
 * <p>所有 DO 实体都必须继承此类</p>
 *
 * @author blog
 */
@Data
public abstract class BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键 ID
     */
    @TableId(type = IdType.ASSIGN_ID)
    @Schema(description = "主键 ID")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @Schema(description = "更新时间")
    private LocalDateTime updateTime;

    /**
     * 逻辑删除标识 (0-未删除 1-已删除)
     */
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    @Schema(description = "是否删除", hidden = true)
    private Integer isDeleted;
}
