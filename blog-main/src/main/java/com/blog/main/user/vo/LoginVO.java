package com.blog.main.user.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 登录响应 VO
 *
 * @author blog
 */
@Data
@Schema(description = "登录响应")
public class LoginVO {

    @Schema(description = "Token")
    private String token;

    @Schema(description = "用户昵称")
    private String nickname;

    @Schema(description = "头像")
    private String avatar;
}
