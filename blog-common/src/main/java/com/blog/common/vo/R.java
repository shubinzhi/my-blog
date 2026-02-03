package com.blog.common.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 统一响应封装
 *
 * @param <T> 数据类型
 * @author blog
 */
@Data
@Schema(description = "统一响应结果")
public class R<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "状态码 (200=成功)")
    private Integer code;

    @Schema(description = "响应消息")
    private String msg;

    @Schema(description = "响应数据")
    private T data;

    // ============ 构造方法 ============

    public R() {
    }

    public R(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    // ============ 静态方法 ============

    /**
     * 成功响应 (无数据)
     */
    public static <T> R<T> success() {
        return new R<>(200, "操作成功", null);
    }

    /**
     * 成功响应 (带数据)
     */
    public static <T> R<T> success(T data) {
        return new R<>(200, "操作成功", data);
    }

    /**
     * 成功响应 (自定义消息)
     */
    public static <T> R<T> success(String msg, T data) {
        return new R<>(200, msg, data);
    }

    /**
     * 失败响应
     */
    public static <T> R<T> fail(String msg) {
        return new R<>(500, msg, null);
    }

    /**
     * 失败响应 (自定义状态码)
     */
    public static <T> R<T> fail(Integer code, String msg) {
        return new R<>(code, msg, null);
    }

    /**
     * 判断是否成功
     */
    public boolean isSuccess() {
        return this.code != null && this.code == 200;
    }
}
