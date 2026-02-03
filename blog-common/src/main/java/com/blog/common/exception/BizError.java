package com.blog.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 业务错误枚举
 *
 * @author blog
 */
@Getter
@AllArgsConstructor
public enum BizError {

    // ========== 通用错误 ==========
    SYSTEM_ERROR(500, "系统繁忙，请稍后重试"),
    PARAM_ERROR(400, "参数错误"),
    UNAUTHORIZED(401, "未登录或登录已过期"),
    FORBIDDEN(403, "无权限访问"),
    NOT_FOUND(404, "资源不存在"),

    // ========== 用户模块 ==========
    USER_NOT_FOUND(1001, "用户不存在"),
    USER_PASSWORD_ERROR(1002, "用户名或密码错误"),
    USER_DISABLED(1003, "用户已被禁用"),
    USER_EXISTS(1004, "用户名已存在"),

    // ========== 文章模块 ==========
    ARTICLE_NOT_FOUND(2001, "文章不存在"),
    ARTICLE_ALREADY_PUBLISHED(2002, "文章已发布"),

    // ========== 分类模块 ==========
    CATEGORY_NOT_FOUND(3001, "分类不存在"),
    CATEGORY_HAS_ARTICLES(3002, "分类下存在文章，无法删除"),

    // ========== 标签模块 ==========
    TAG_NOT_FOUND(4001, "标签不存在"),

    // ========== 评论模块 ==========
    COMMENT_NOT_FOUND(5001, "评论不存在"),
    ;

    private final Integer code;
    private final String message;
}
