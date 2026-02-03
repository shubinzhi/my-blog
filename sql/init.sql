-- =============================================
-- 个人博客系统数据库初始化脚本
-- =============================================

-- 创建数据库
CREATE DATABASE IF NOT EXISTS `my_blog` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE `my_blog`;

-- =============================================
-- 1. 用户表
-- =============================================
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
    `id` BIGINT NOT NULL COMMENT '主键 ID',
    `username` VARCHAR(50) NOT NULL COMMENT '用户名',
    `password` VARCHAR(100) NOT NULL COMMENT '密码 (加密)',
    `nickname` VARCHAR(50) DEFAULT NULL COMMENT '昵称',
    `avatar` VARCHAR(255) DEFAULT NULL COMMENT '头像 URL',
    `status` TINYINT NOT NULL DEFAULT 1 COMMENT '状态: 0-禁用 1-正常',
    `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `last_login_time` TIMESTAMP DEFAULT NULL COMMENT '最后登录时间',
    `is_deleted` TINYINT NOT NULL DEFAULT 0 COMMENT '逻辑删除: 0-未删除 1-已删除',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户表';

-- =============================================
-- 2. 角色表
-- =============================================
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
    `id` BIGINT NOT NULL COMMENT '主键 ID',
    `code` VARCHAR(50) NOT NULL COMMENT '角色编码',
    `name` VARCHAR(50) NOT NULL COMMENT '角色名称',
    `description` VARCHAR(200) DEFAULT NULL COMMENT '描述',
    `sort` INT NOT NULL DEFAULT 0 COMMENT '排序',
    `status` TINYINT NOT NULL DEFAULT 1 COMMENT '状态: 0-禁用 1-正常',
    `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted` TINYINT NOT NULL DEFAULT 0 COMMENT '逻辑删除: 0-未删除 1-已删除',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='角色表';

-- =============================================
-- 3. 菜单表
-- =============================================
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
    `id` BIGINT NOT NULL COMMENT '主键 ID',
    `parent_id` BIGINT DEFAULT 0 COMMENT '父菜单 ID (0 表示顶级菜单)',
    `name` VARCHAR(50) NOT NULL COMMENT '菜单名称',
    `path` VARCHAR(200) DEFAULT NULL COMMENT '路由路径',
    `component` VARCHAR(200) DEFAULT NULL COMMENT '组件路径',
    `icon` VARCHAR(50) DEFAULT NULL COMMENT '菜单图标',
    `type` TINYINT NOT NULL DEFAULT 1 COMMENT '类型: 1-目录 2-菜单 3-按钮',
    `permission` VARCHAR(100) DEFAULT NULL COMMENT '权限标识',
    `sort` INT NOT NULL DEFAULT 0 COMMENT '排序',
    `visible` TINYINT NOT NULL DEFAULT 1 COMMENT '是否可见: 0-隐藏 1-显示',
    `status` TINYINT NOT NULL DEFAULT 1 COMMENT '状态: 0-禁用 1-正常',
    `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted` TINYINT NOT NULL DEFAULT 0 COMMENT '逻辑删除: 0-未删除 1-已删除',
    PRIMARY KEY (`id`),
    KEY `idx_parent_id` (`parent_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='菜单表';

-- =============================================
-- 4. 用户-角色关联表
-- =============================================
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
    `id` BIGINT NOT NULL COMMENT '主键 ID',
    `user_id` BIGINT NOT NULL COMMENT '用户 ID',
    `role_id` BIGINT NOT NULL COMMENT '角色 ID',
    PRIMARY KEY (`id`),
    KEY `idx_role_id` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户-角色关联表';

-- =============================================
-- 5. 角色-菜单关联表
-- =============================================
DROP TABLE IF EXISTS `role_menu`;
CREATE TABLE `role_menu` (
    `id` BIGINT NOT NULL COMMENT '主键 ID',
    `role_id` BIGINT NOT NULL COMMENT '角色 ID',
    `menu_id` BIGINT NOT NULL COMMENT '菜单 ID',
    PRIMARY KEY (`id`),
    KEY `idx_menu_id` (`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='角色-菜单关联表';

-- =============================================
-- 6. 分类表
-- =============================================
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
    `id` BIGINT NOT NULL COMMENT '主键 ID',
    `name` VARCHAR(50) NOT NULL COMMENT '分类名称',
    `description` VARCHAR(200) DEFAULT NULL COMMENT '描述',
    `sort` INT NOT NULL DEFAULT 0 COMMENT '排序',
    `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted` TINYINT NOT NULL DEFAULT 0 COMMENT '逻辑删除: 0-未删除 1-已删除',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='分类表';

-- =============================================
-- 7. 标签表
-- =============================================
DROP TABLE IF EXISTS `tag`;
CREATE TABLE `tag` (
    `id` BIGINT NOT NULL COMMENT '主键 ID',
    `name` VARCHAR(50) NOT NULL COMMENT '标签名称',
    `color` VARCHAR(20) DEFAULT '#409EFF' COMMENT '标签颜色',
    `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted` TINYINT NOT NULL DEFAULT 0 COMMENT '逻辑删除: 0-未删除 1-已删除',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='标签表';

-- =============================================
-- 8. 文章表
-- =============================================
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article` (
    `id` BIGINT NOT NULL COMMENT '主键 ID',
    `title` VARCHAR(200) NOT NULL COMMENT '标题',
    `summary` VARCHAR(500) DEFAULT NULL COMMENT '摘要',
    `content` LONGTEXT COMMENT '内容 (Markdown)',
    `cover_image` VARCHAR(255) DEFAULT NULL COMMENT '封面图 URL',
    `category_id` BIGINT DEFAULT NULL COMMENT '分类 ID',
    `author_id` BIGINT NOT NULL COMMENT '作者 ID',
    `status` TINYINT NOT NULL DEFAULT 0 COMMENT '状态: 0-草稿 1-已发布 2-已下架',
    `is_top` TINYINT NOT NULL DEFAULT 0 COMMENT '是否置顶: 0-否 1-是',
    `view_count` INT NOT NULL DEFAULT 0 COMMENT '浏览量',
    `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted` TINYINT NOT NULL DEFAULT 0 COMMENT '逻辑删除: 0-未删除 1-已删除',
    PRIMARY KEY (`id`),
    KEY `idx_category_id` (`category_id`),
    KEY `idx_author_id` (`author_id`),
    KEY `idx_status` (`status`),
    KEY `idx_create_time` (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='文章表';

-- =============================================
-- 9. 文章-标签关联表
-- =============================================
DROP TABLE IF EXISTS `article_tag`;
CREATE TABLE `article_tag` (
    `id` BIGINT NOT NULL COMMENT '主键 ID',
    `article_id` BIGINT NOT NULL COMMENT '文章 ID',
    `tag_id` BIGINT NOT NULL COMMENT '标签 ID',
    PRIMARY KEY (`id`),
    KEY `idx_tag_id` (`tag_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='文章-标签关联表';
