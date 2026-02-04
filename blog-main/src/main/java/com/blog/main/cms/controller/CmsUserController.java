package com.blog.main.cms.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.blog.common.entity.User;
import com.blog.common.vo.R;
import com.blog.main.user.dto.LoginDTO;
import com.blog.main.user.service.UserService;
import com.blog.main.user.vo.LoginVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * CMS - 用户认证
 *
 * @author blog
 */
@Tag(name = "CMS - 用户认证")
@RestController
@RequestMapping("/cms/user")
@RequiredArgsConstructor
public class CmsUserController {

    private final UserService userService;

    @Operation(summary = "用户登录")
    @PostMapping("/login")
    public R<LoginVO> login(@Valid @RequestBody LoginDTO dto) {
        return R.success(userService.login(dto));
    }

    @Operation(summary = "用户登出")
    @PostMapping("/logout")
    @SaCheckLogin
    public R<Void> logout() {
        userService.logout();
        return R.success();
    }

    @Operation(summary = "获取当前用户信息")
    @GetMapping("/info")
    @SaCheckLogin
    public R<User> info() {
        return R.success(userService.getCurrentUser());
    }
}
