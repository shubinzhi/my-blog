package com.blog.main.user.service;

import com.blog.common.entity.User;
import com.blog.main.user.dto.LoginDTO;
import com.blog.main.user.vo.LoginVO;
import com.github.yulichang.base.MPJBaseService;

/**
 * 用户 Service
 *
 * @author blog
 */
public interface UserService extends MPJBaseService<User> {

    /**
     * 用户登录
     */
    LoginVO login(LoginDTO dto);

    /**
     * 用户登出
     */
    void logout();

    /**
     * 获取当前用户信息
     */
    User getCurrentUser();

    /**
     * 检查用户名是否已存在
     */
    void checkUsernameUnique(String username, Long excludeId);
}
