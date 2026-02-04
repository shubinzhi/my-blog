package com.blog.main.tag.service;

import com.blog.common.entity.Tag;
import com.github.yulichang.base.MPJBaseService;

import java.util.List;

/**
 * 标签 Service
 *
 * @author blog
 */
public interface TagService extends MPJBaseService<Tag> {

    /**
     * 获取所有标签
     */
    List<Tag> listAll();

    /**
     * 保存标签
     */
    Long saveTag(Tag tag);

    /**
     * 删除标签
     */
    void deleteTag(Long id);
}
