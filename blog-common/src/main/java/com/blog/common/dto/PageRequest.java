package com.blog.common.dto;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 通用分页请求封装
 *
 * @param <T> 查询条件 DTO 类型
 * @author blog
 */
@Data
@Schema(description = "分页请求")
public class PageRequest<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "页码", example = "1")
    private Integer page = 1;

    @Schema(description = "每页条数", example = "10")
    private Integer size = 10;

    @Valid
    @Schema(description = "查询条件")
    private T data;

    /**
     * 转换为 MyBatis-Plus Page 对象
     *
     * @param <E> 实体类型
     * @return Page 对象
     */
    public <E> Page<E> toPage() {
        // 防止参数异常
        int pageNo = (page == null || page < 1) ? 1 : page;
        int pageSize = (size == null || size < 1) ? 10 : Math.min(size, 100);
        return new Page<>(pageNo, pageSize);
    }
}
