package com.blog.main.user.mapper;

import com.blog.common.entity.User;
import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户 Mapper
 *
 * @author blog
 */
@Mapper
public interface UserMapper extends MPJBaseMapper<User> {
}
