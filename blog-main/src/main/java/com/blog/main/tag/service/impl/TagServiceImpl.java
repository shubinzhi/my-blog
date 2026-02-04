package com.blog.main.tag.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.blog.common.entity.Tag;
import com.blog.main.tag.mapper.TagMapper;
import com.blog.main.tag.service.TagService;
import com.github.yulichang.base.MPJBaseServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 标签 Service 实现
 *
 * @author blog
 */
@Service
public class TagServiceImpl extends MPJBaseServiceImpl<TagMapper, Tag> implements TagService {

    @Override
    public List<Tag> listAll() {
        return list(new LambdaQueryWrapper<Tag>().orderByDesc(Tag::getCreateTime));
    }

    @Override
    public Long saveTag(Tag tag) {
        if (tag.getId() == null) {
            baseMapper.insert(tag);
        } else {
            baseMapper.updateById(tag);
        }
        return tag.getId();
    }

    @Override
    public void deleteTag(Long id) {
        baseMapper.deleteById(id);
    }
}
