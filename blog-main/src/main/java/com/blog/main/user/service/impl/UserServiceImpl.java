package com.blog.main.user.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.crypto.digest.BCrypt;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.blog.common.entity.User;
import com.blog.common.exception.BizError;
import com.blog.common.exception.BizException;
import com.blog.main.user.dto.LoginDTO;
import com.blog.main.user.mapper.UserMapper;
import com.blog.main.user.service.UserService;
import com.blog.main.user.vo.LoginVO;
import com.github.yulichang.base.MPJBaseServiceImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * 用户 Service 实现
 *
 * @author blog
 */
@Service
public class UserServiceImpl extends MPJBaseServiceImpl<UserMapper, User> implements UserService {

    @Override
    public LoginVO login(LoginDTO dto) {
        User user = getOne(new LambdaQueryWrapper<User>()
                .eq(User::getUsername, dto.getUsername()));

        if (user == null) {
            throw new BizException(BizError.USER_PASSWORD_ERROR);
        }

        if (!BCrypt.checkpw(dto.getPassword(), user.getPassword())) {
            throw new BizException(BizError.USER_PASSWORD_ERROR);
        }

        if (user.getStatus() != 1) {
            throw new BizException(BizError.USER_DISABLED);
        }

        // 更新最后登录时间
        user.setLastLoginTime(LocalDateTime.now());
        updateById(user);

        // 登录
        StpUtil.login(user.getId());

        LoginVO vo = new LoginVO();
        vo.setToken(StpUtil.getTokenValue());
        vo.setNickname(user.getNickname());
        vo.setAvatar(user.getAvatar());
        return vo;
    }

    @Override
    public void logout() {
        StpUtil.logout();
    }

    @Override
    public User getCurrentUser() {
        return getById(StpUtil.getLoginIdAsLong());
    }

    @Override
    public void checkUsernameUnique(String username, Long excludeId) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<User>()
                .eq(User::getUsername, username)
                .ne(excludeId != null, User::getId, excludeId);
        if (count(wrapper) > 0) {
            throw new BizException(BizError.USER_EXISTS);
        }
    }
}
