package com.blog.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户实体
 *
 * @author blog
 */
@Data
@TableName("user")
@Schema(description = "用户")
public class User implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.ASSIGN_ID)
    @Schema(description = "主键 ID")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;

    @Schema(description = "用户名")
    private String username;

    @Schema(description = "密码 (加密)")
    private String password;

    @Schema(description = "昵称")
    private String nickname;

    @Schema(description = "邮箱")
    private String email;

    @Schema(description = "头像 URL")
    private String avatar;

    @Schema(description = "角色: 0-访客 1-用户 2-管理员")
    private Integer role;

    @Schema(description = "状态: 0-禁用 1-正常")
    private Integer status;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @Schema(description = "最后登录时间")
    private LocalDateTime lastLoginTime;
}
