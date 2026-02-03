package com.blog.common.exception;

import lombok.Getter;

/**
 * 业务异常
 *
 * @author blog
 */
@Getter
public class BizException extends RuntimeException {

    /**
     * 错误码
     */
    private final Integer code;

    /**
     * 错误信息
     */
    private final String message;

    public BizException(String message) {
        super(message);
        this.code = 500;
        this.message = message;
    }

    public BizException(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public BizException(BizError error) {
        super(error.getMessage());
        this.code = error.getCode();
        this.message = error.getMessage();
    }
}
