/*
 Navicat Premium Data Transfer

 Source Server         : windows
 Source Server Type    : MySQL
 Source Server Version : 80026 (8.0.26)
 Source Host           : localhost:3306
 Source Schema         : lab

 Target Server Type    : MySQL
 Target Server Version : 80026 (8.0.26)
 File Encoding         : 65001

 Date: 26/05/2025 16:35:42
*/
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for announcement
-- ----------------------------
DROP TABLE IF EXISTS `announcement`;
CREATE TABLE `announcement`  (
                                 `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
                                 `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '公告标题',
                                 `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '公告内容',
                                 `uid` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '发布者id（比赛创建者或超级管理员）',
                                 `type` tinyint NOT NULL DEFAULT 0 COMMENT '公告类型：0系统公告，1比赛公告',
                                 `cid` bigint NOT NULL DEFAULT 0 COMMENT '比赛id，仅当 type = 1（比赛公告）时有效',
                                 `gmt_create` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                 `gmt_modified` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
                                 `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
                                 `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
                                 `is_deleted` tinyint(1) NULL DEFAULT 0,
                                 PRIMARY KEY (`id`) USING BTREE,
                                 INDEX `idx_type`(`type` ASC) USING BTREE,
                                 INDEX `idx_cid`(`cid` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '公告表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
                             `id` int NOT NULL AUTO_INCREMENT,
                             `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                             `gmt_create` datetime NULL DEFAULT CURRENT_TIMESTAMP,
                             `gmt_modified` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                             `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                             `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                             `is_deleted` tinyint(1) NULL DEFAULT 0,
                             PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for code_template
-- ----------------------------
DROP TABLE IF EXISTS `code_template`;
CREATE TABLE `code_template`  (
                                  `id` int NOT NULL AUTO_INCREMENT,
                                  `pid` bigint UNSIGNED NOT NULL,
                                  `lid` bigint UNSIGNED NOT NULL,
                                  `code` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
                                  `status` tinyint(1) NULL DEFAULT 0,
                                  `gmt_create` datetime NULL DEFAULT CURRENT_TIMESTAMP,
                                  `gmt_modified` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                  `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                  `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                  `is_deleted` tinyint(1) NULL DEFAULT 0,
                                  PRIMARY KEY (`id`) USING BTREE,
                                  INDEX `lid`(`lid` ASC) USING BTREE,
                                  INDEX `pid`(`pid` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
                            `id` int NOT NULL AUTO_INCREMENT,
                            `cid` bigint UNSIGNED NULL DEFAULT NULL COMMENT 'null表示无引用比赛',
                            `did` int NULL DEFAULT NULL COMMENT 'null表示无引用讨论',
                            `content` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '评论内容',
                            `from_uid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '评论者id',
                            `from_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '评论者用户名',
                            `from_avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '评论组头像地址',
                            `from_role` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '评论者角色',
                            `like_num` int NULL DEFAULT 0 COMMENT '点赞数量',
                            `status` int NULL DEFAULT 0 COMMENT '是否封禁或逻辑删除该评论',
                            `gmt_create` datetime NULL DEFAULT CURRENT_TIMESTAMP,
                            `gmt_modified` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                            PRIMARY KEY (`id`) USING BTREE,
                            INDEX `cid`(`cid` ASC) USING BTREE,
                            INDEX `from_avatar`(`from_avatar` ASC) USING BTREE,
                            INDEX `uid`(`from_uid` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for comment_like
-- ----------------------------
DROP TABLE IF EXISTS `comment_like`;
CREATE TABLE `comment_like`  (
                                 `id` int NOT NULL AUTO_INCREMENT,
                                 `uid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
                                 `cid` int NOT NULL,
                                 `gmt_create` datetime NULL DEFAULT CURRENT_TIMESTAMP,
                                 `gmt_modified` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                 PRIMARY KEY (`id`) USING BTREE,
                                 INDEX `cid`(`cid` ASC) USING BTREE,
                                 INDEX `uid`(`uid` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for contest
-- ----------------------------
DROP TABLE IF EXISTS `contest`;
CREATE TABLE `contest`  (
                            `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
                            `uid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '比赛创建者id',
                            `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '比赛标题',
                            `type` int NOT NULL DEFAULT 0 COMMENT '0为acm赛制，1为比分赛制',
                            `description` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '比赛说明',
                            `auth` int NOT NULL COMMENT '0为公开赛，1为私有赛（访问有密码），2为保护赛（提交有密码）',
                            `pwd` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '比赛密码',
                            `start_time` datetime NULL DEFAULT NULL COMMENT '开始时间',
                            `end_time` datetime NULL DEFAULT NULL COMMENT '结束时间',
                            `duration` bigint NULL DEFAULT NULL COMMENT '比赛时长(s)',
                            `seal_rank` tinyint(1) NULL DEFAULT 0 COMMENT '是否开启封榜',
                            `seal_rank_time` datetime NULL DEFAULT NULL COMMENT '封榜起始时间，一直到比赛结束，不刷新榜单',
                            `auto_real_rank` tinyint(1) NULL DEFAULT 1 COMMENT '比赛结束是否自动解除封榜,自动转换成真实榜单',
                            `status` int NULL DEFAULT NULL COMMENT '-1为未开始，0为进行中，1为已结束',
                            `visible` tinyint(1) NULL DEFAULT 1 COMMENT '是否可见',
                            `open_print` tinyint(1) NULL DEFAULT 0 COMMENT '是否打开打印功能',
                            `open_account_limit` tinyint(1) NULL DEFAULT 0 COMMENT '是否开启账号限制',
                            `account_limit_rule` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '账号限制规则',
                            `rank_show_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'username' COMMENT '排行榜显示（username、nickname、realname）',
                            `open_rank` tinyint(1) NULL DEFAULT 0 COMMENT '是否开放比赛榜单',
                            `oi_rank_score_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'Recent' COMMENT 'oi排行榜得分方式，Recent、Highest',
                            `is_group` tinyint(1) NULL DEFAULT 0,
                            `gid` bigint UNSIGNED NULL DEFAULT NULL,
                            `allow_end_submit` tinyint(1) NULL DEFAULT 0 COMMENT '是否允许比赛结束后进行提交',
                            `gmt_create` datetime NULL DEFAULT CURRENT_TIMESTAMP,
                            `gmt_modified` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                            `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                            `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                            `is_deleted` tinyint(1) NULL DEFAULT 0,
                            PRIMARY KEY (`id`) USING BTREE,
                            INDEX `uid`(`uid` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for contest_problem
-- ----------------------------
DROP TABLE IF EXISTS `contest_problem`;
CREATE TABLE `contest_problem`  (
                                    `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
                                    `display_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '该题目在比赛中的顺序id',
                                    `cid` bigint UNSIGNED NOT NULL COMMENT '比赛id',
                                    `pid` bigint UNSIGNED NOT NULL COMMENT '题目id',
                                    `display_title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '该题目在比赛中的标题，默认为原名字',
                                    `color` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '气球颜色',
                                    `gmt_create` datetime NULL DEFAULT CURRENT_TIMESTAMP,
                                    `gmt_modified` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                    `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                    `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                    `is_deleted` tinyint(1) NULL DEFAULT 0,
                                    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for contest_record
-- ----------------------------
DROP TABLE IF EXISTS `contest_record`;
CREATE TABLE `contest_record`  (
                                   `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
                                   `cid` bigint UNSIGNED NULL DEFAULT NULL COMMENT '比赛id',
                                   `uid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户id',
                                   `pid` bigint UNSIGNED NULL DEFAULT NULL COMMENT '题目id',
                                   `cpid` bigint UNSIGNED NULL DEFAULT NULL COMMENT '比赛中的题目的id',
                                   `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
                                   `realname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '真实姓名',
                                   `display_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '比赛中展示的id',
                                   `submit_id` bigint UNSIGNED NOT NULL COMMENT '提交id，用于可重判',
                                   `status` int NULL DEFAULT NULL COMMENT '提交结果，0表示未AC通过不罚时，1表示AC通过，-1为未AC通过算罚时',
                                   `submit_time` datetime NOT NULL COMMENT '具体提交时间',
                                   `time` bigint UNSIGNED NULL DEFAULT NULL COMMENT '相对时间，为提交时间减去比赛时间',
                                   `score` int NULL DEFAULT NULL COMMENT 'OI比赛的得分',
                                   `use_time` int NULL DEFAULT NULL COMMENT '运行耗时',
                                   `first_blood` tinyint(1) NULL DEFAULT 0 COMMENT '是否为一血AC(废弃)',
                                   `checked` tinyint(1) NULL DEFAULT 0 COMMENT 'AC是否已校验',
                                   `gmt_create` datetime NULL DEFAULT CURRENT_TIMESTAMP,
                                   `gmt_modified` datetime NULL DEFAULT CURRENT_TIMESTAMP,
                                   `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                   `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                   `is_deleted` tinyint(1) NULL DEFAULT 0,
                                   PRIMARY KEY (`id`) USING BTREE,
                                   INDEX `cpid`(`cpid` ASC) USING BTREE,
                                   INDEX `index_cid`(`cid` ASC) USING BTREE,
                                   INDEX `index_time`(`time` ASC) USING BTREE,
                                   INDEX `pid`(`pid` ASC) USING BTREE,
                                   INDEX `submit_id`(`submit_id` ASC) USING BTREE,
                                   INDEX `uid`(`uid` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for contest_register
-- ----------------------------
DROP TABLE IF EXISTS `contest_register`;
CREATE TABLE `contest_register`  (
                                     `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
                                     `cid` bigint UNSIGNED NOT NULL COMMENT '比赛id',
                                     `uid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户id',
                                     `status` int NULL DEFAULT 0 COMMENT '默认为0表示正常，1为失效。',
                                     `gmt_create` datetime NULL DEFAULT CURRENT_TIMESTAMP,
                                     `gmt_modified` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                     PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for discussion
-- ----------------------------
DROP TABLE IF EXISTS `discussion`;
CREATE TABLE `discussion`  (
                               `id` int NOT NULL AUTO_INCREMENT,
                               `category_id` int NOT NULL COMMENT '分类id',
                               `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '讨论标题',
                               `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '讨论简介',
                               `content` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '讨论内容',
                               `pid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '关联题目id',
                               `uid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '发表者id',
                               `author` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '发表者用户名',
                               `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发表讨论者头像',
                               `role` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'user' COMMENT '发表者角色',
                               `view_num` int NULL DEFAULT 0 COMMENT '浏览数量',
                               `like_num` int NULL DEFAULT 0 COMMENT '点赞数量',
                               `top_priority` tinyint(1) NULL DEFAULT 0 COMMENT '优先级，是否置顶',
                               `comment_num` int NULL DEFAULT 0 COMMENT '评论数量',
                               `status` int NULL DEFAULT 0 COMMENT '是否封禁该讨论',
                               `gid` bigint UNSIGNED NULL DEFAULT NULL,
                               `gmt_create` datetime NULL DEFAULT CURRENT_TIMESTAMP,
                               `gmt_modified` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                               PRIMARY KEY (`id`) USING BTREE,
                               INDEX `category_id`(`category_id` ASC) USING BTREE,
                               INDEX `pid`(`pid` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for discussion_like
-- ----------------------------
DROP TABLE IF EXISTS `discussion_like`;
CREATE TABLE `discussion_like`  (
                                    `id` int NOT NULL AUTO_INCREMENT,
                                    `uid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
                                    `did` int NOT NULL,
                                    `gmt_create` datetime NULL DEFAULT CURRENT_TIMESTAMP,
                                    `gmt_modified` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                    PRIMARY KEY (`id`) USING BTREE,
                                    INDEX `did`(`did` ASC) USING BTREE,
                                    INDEX `uid`(`uid` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for discussion_report
-- ----------------------------
DROP TABLE IF EXISTS `discussion_report`;
CREATE TABLE `discussion_report`  (
                                      `id` int NOT NULL AUTO_INCREMENT,
                                      `did` int NULL DEFAULT NULL COMMENT '讨论id',
                                      `reporter` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '举报者的用户名',
                                      `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '举报内容',
                                      `status` tinyint(1) NULL DEFAULT 0 COMMENT '是否已读',
                                      `gmt_create` datetime NULL DEFAULT CURRENT_TIMESTAMP,
                                      `gmt_modified` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                      PRIMARY KEY (`id`) USING BTREE,
                                      INDEX `did`(`did` ASC) USING BTREE,
                                      INDEX `reporter`(`reporter` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for file
-- ----------------------------
DROP TABLE IF EXISTS `file`;
CREATE TABLE `file`  (
                         `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
                         `uid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户id',
                         `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文件名',
                         `suffix` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文件后缀格式',
                         `folder_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文件所在文件夹的路径',
                         `file_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件绝对路径',
                         `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件所属类型，例如avatar',
                         `delete` tinyint(1) NULL DEFAULT 0 COMMENT '是否删除',
                         `gid` bigint UNSIGNED NULL DEFAULT NULL,
                         `gmt_create` datetime NULL DEFAULT CURRENT_TIMESTAMP,
                         `gmt_modified` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                         `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                         `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                         `is_deleted` tinyint(1) NULL DEFAULT 0,
                         PRIMARY KEY (`id`) USING BTREE,
                         INDEX `uid`(`uid` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for group
-- ----------------------------
DROP TABLE IF EXISTS `group`;
CREATE TABLE `group`  (
                          `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
                          `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像地址',
                          `name` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '团队名称',
                          `short_name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '团队简称，创建题目时题号自动添加的前缀',
                          `brief` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '团队简介',
                          `description` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '团队介绍',
                          `owner` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '团队创建者用户名',
                          `uid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '团队创建者用户id',
                          `visible` tinyint(1) NULL DEFAULT 1 COMMENT '是否可见',
                          `status` tinyint(1) NULL DEFAULT 0 COMMENT '是否封禁',
                          `code` varchar(6) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邀请码',
                          `gmt_create` datetime NULL DEFAULT CURRENT_TIMESTAMP,
                          `gmt_modified` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                          PRIMARY KEY (`id`) USING BTREE,
                          UNIQUE INDEX `name`(`name` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for group_member
-- ----------------------------
DROP TABLE IF EXISTS `group_member`;
CREATE TABLE `group_member`  (
                                 `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
                                 `gid` bigint UNSIGNED NOT NULL COMMENT '团队id',
                                 `uid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户id',
                                 `auth` int NULL DEFAULT 1 COMMENT '1未审批，2拒绝，3普通成员，4团队管理员，5团队拥有者',
                                 `reason` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '申请理由',
                                 `gmt_create` datetime NULL DEFAULT CURRENT_TIMESTAMP,
                                 `gmt_modified` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                 PRIMARY KEY (`id`) USING BTREE,
                                 INDEX `gid`(`gid` ASC) USING BTREE,
                                 INDEX `uid`(`uid` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for judge
-- ----------------------------
DROP TABLE IF EXISTS `judge`;
CREATE TABLE `judge`  (
                          `submit_id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
                          `pid` bigint UNSIGNED NOT NULL COMMENT '题目id',
                          `display_pid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '题目展示id',
                          `uid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户id',
                          `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
                          `submit_time` datetime NOT NULL COMMENT '提交的时间',
                          `status` int NULL DEFAULT NULL COMMENT '结果码具体参考文档',
                          `share` tinyint(1) NULL DEFAULT 0 COMMENT '0为仅自己可见，1为全部人可见。',
                          `error_message` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '错误提醒（编译错误）',
                          `time` int NULL DEFAULT NULL COMMENT '运行时间(ms)',
                          `memory` int NULL DEFAULT NULL COMMENT '运行内存（kb）',
                          `score` int NULL DEFAULT NULL COMMENT 'IO判题则不为空',
                          `length` int NULL DEFAULT NULL COMMENT '代码长度',
                          `code` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '代码',
                          `language` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '代码语言',
                          `gid` bigint UNSIGNED NULL DEFAULT NULL COMMENT '团队id，不为团队内提交则为null',
                          `cid` bigint UNSIGNED NOT NULL DEFAULT 0 COMMENT '比赛id，非比赛题目默认为0',
                          `cpid` bigint UNSIGNED NULL DEFAULT 0 COMMENT '比赛中题目排序id，非比赛题目默认为0',
                          `judger` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '判题机ip',
                          `ip` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '提交者所在ip',
                          `version` int NOT NULL DEFAULT 0 COMMENT '乐观锁',
                          `oi_rank_score` int NULL DEFAULT 0 COMMENT 'oi排行榜得分',
                          `is_manual` tinyint(1) NULL DEFAULT 0 COMMENT '是否为人工评测',
                          `gmt_create` datetime NULL DEFAULT CURRENT_TIMESTAMP,
                          `gmt_modified` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                          PRIMARY KEY (`submit_id`, `pid`, `display_pid`, `uid`, `cid`) USING BTREE,
                          INDEX `pid`(`pid` ASC) USING BTREE,
                          INDEX `uid`(`uid` ASC) USING BTREE,
                          INDEX `username`(`username` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for judge_case
-- ----------------------------
DROP TABLE IF EXISTS `judge_case`;
CREATE TABLE `judge_case`  (
                               `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
                               `submit_id` bigint UNSIGNED NOT NULL COMMENT '提交判题id',
                               `uid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户id',
                               `pid` bigint UNSIGNED NOT NULL COMMENT '题目id',
                               `case_id` bigint NULL DEFAULT NULL COMMENT '测试样例id',
                               `status` int NULL DEFAULT NULL COMMENT '具体看结果码',
                               `time` int NULL DEFAULT NULL COMMENT '测试该样例所用时间ms',
                               `memory` int NULL DEFAULT NULL COMMENT '测试该样例所用空间KB',
                               `score` int NULL DEFAULT NULL COMMENT 'IO得分',
                               `group_num` int NULL DEFAULT NULL COMMENT 'subtask分组的组号',
                               `seq` int NULL DEFAULT NULL COMMENT '排序',
                               `mode` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'default' COMMENT 'default,subtask_lowest,subtask_average',
                               `input_data` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '样例输入，比赛不可看',
                               `output_data` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '样例输出，比赛不可看',
                               `user_output` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '用户样例输出，比赛不可看',
                               `gmt_create` datetime NULL DEFAULT CURRENT_TIMESTAMP,
                               `gmt_modified` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                               PRIMARY KEY (`id`, `submit_id`, `uid`, `pid`) USING BTREE,
                               INDEX `case_id`(`case_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for judge_server
-- ----------------------------
DROP TABLE IF EXISTS `judge_server`;
CREATE TABLE `judge_server`  (
                                 `id` int NOT NULL AUTO_INCREMENT,
                                 `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '判题服务名字',
                                 `ip` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '判题机ip',
                                 `port` int NOT NULL COMMENT '判题机端口号',
                                 `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'ip:port',
                                 `cpu_core` int NULL DEFAULT 0 COMMENT '判题机所在服务器cpu核心数',
                                 `task_number` int NOT NULL DEFAULT 0 COMMENT '当前判题数',
                                 `max_task_number` int NOT NULL COMMENT '判题并发最大数',
                                 `status` int NULL DEFAULT 0 COMMENT '0可用，1不可用',
                                 `gmt_create` datetime NULL DEFAULT CURRENT_TIMESTAMP,
                                 `gmt_modified` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                 PRIMARY KEY (`id`) USING BTREE,
                                 INDEX `index_judge_url`(`url` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for language
-- ----------------------------
DROP TABLE IF EXISTS `language`;
CREATE TABLE `language`  (
                             `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
                             `content_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '语言类型',
                             `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '语言描述',
                             `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '语言名字',
                             `compile_command` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '编译指令',
                             `template` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '模板',
                             `code_template` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '语言默认代码模板',
                             `is_spj` tinyint(1) NULL DEFAULT 0 COMMENT '是否可作为特殊判题的一种语言',
                             `ext` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '扩展信息(可能有用)',
                             `seq` int NULL DEFAULT 0 COMMENT '语言排序',
                             `gmt_create` datetime NULL DEFAULT CURRENT_TIMESTAMP,
                             `gmt_modified` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                             `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                             `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                             `is_deleted` tinyint(1) NULL DEFAULT 0,
                             PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for msg_remind
-- ----------------------------
DROP TABLE IF EXISTS `msg_remind`;
CREATE TABLE `msg_remind`  (
                               `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
                               `action` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '动作类型，如点赞讨论帖Like_Post、点赞评论Like_Discuss、评论Discuss、回复Reply等',
                               `source_id` int UNSIGNED NULL DEFAULT NULL COMMENT '消息来源id，讨论id或比赛id',
                               `source_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '事件源类型：\'Discussion\'、\'Contest\'等',
                               `source_content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '事件源的内容，比如回复的内容，评论的帖子标题等等',
                               `quote_id` int UNSIGNED NULL DEFAULT NULL COMMENT '事件引用上一级评论或回复id',
                               `quote_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '事件引用上一级的类型：Comment、Reply',
                               `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '事件所发生的地点链接 url',
                               `state` tinyint(1) NULL DEFAULT 0 COMMENT '是否已读',
                               `sender_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作者的id',
                               `recipient_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '接受消息的用户id',
                               `gmt_create` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                               `gmt_modified` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
                               PRIMARY KEY (`id`) USING BTREE,
                               INDEX `recipient_id`(`recipient_id` ASC) USING BTREE,
                               INDEX `sender_id`(`sender_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission`  (
                               `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
                               `permission_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '权限名称',
                               `key_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '权限关键词(权限认证使用此字段)',
                               `status` tinyint(1) NULL DEFAULT 1 COMMENT '状态 (0：关闭，1：开启)',
                               `gmt_create` datetime(6) NULL DEFAULT NULL,
                               `gmt_modified` datetime(6) NULL DEFAULT NULL,
                               `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
                               `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
                               `is_deleted` tinyint(1) NULL DEFAULT 0,
                               `parent_id` bigint NULL DEFAULT 0 COMMENT '父级权限id',
                               `icon` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '图标',
                               PRIMARY KEY (`id`) USING BTREE,
                               UNIQUE INDEX `key_name`(`key_name` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for problem
-- ----------------------------
DROP TABLE IF EXISTS `problem`;
CREATE TABLE `problem`  (
                            `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
                            `problem_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '问题的自定义ID 例如（XDZNOJ-1000）',
                            `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '题目',
                            `author` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '未知' COMMENT '作者',
                            `type` int NOT NULL DEFAULT 0 COMMENT '0为ACM,1为OI',
                            `time_limit` int NULL DEFAULT 1000 COMMENT '单位ms',
                            `memory_limit` int NULL DEFAULT 65535 COMMENT '单位kb',
                            `stack_limit` int NULL DEFAULT 128 COMMENT '单位mb',
                            `description` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '描述',
                            `input` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '输入描述',
                            `output` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '输出描述',
                            `examples` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '题面样例',
                            `difficulty` int NULL DEFAULT 0 COMMENT '题目难度,0简单，1中等，2困难',
                            `hint` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '备注,提醒',
                            `auth` int NULL DEFAULT 1 COMMENT '默认为1公开，2为私有，3为比赛题目',
                            `io_score` int NULL DEFAULT 100 COMMENT '当该题目为io题目时的分数',
                            `code_share` tinyint(1) NULL DEFAULT 1 COMMENT '该题目对应的相关提交代码，用户是否可用分享',
                            `judge_mode` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'default' COMMENT '题目评测模式,default、function',
                            `function_code` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '函数程序代码',
                            `function_language` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '特判程序或交互程序代码的语言',
                            `is_remove_end_blank` tinyint(1) NULL DEFAULT 1 COMMENT '是否默认去除用户代码的文末空格',
                            `open_case_result` tinyint(1) NULL DEFAULT 1 COMMENT '是否默认开启该题目的测试样例结果查看',
                            `is_upload_case` tinyint(1) NULL DEFAULT 1 COMMENT '题目测试数据是否是上传文件的',
                            `case_version` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '题目测试数据的版本号',
                            `is_group` tinyint(1) NULL DEFAULT 0,
                            `gid` bigint UNSIGNED NULL DEFAULT NULL,
                            `apply_public_progress` int NULL DEFAULT NULL COMMENT '申请公开的进度：null为未申请，1为申请中，2为申请通过，3为申请拒绝',
                            `gmt_create` datetime NULL DEFAULT CURRENT_TIMESTAMP,
                            `gmt_modified` datetime NULL DEFAULT CURRENT_TIMESTAMP,
                            `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                            `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                            `is_deleted` tinyint(1) NULL DEFAULT 0,
                            PRIMARY KEY (`id`) USING BTREE,
                            INDEX `problem_id`(`problem_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for problem_case
-- ----------------------------
DROP TABLE IF EXISTS `problem_case`;
CREATE TABLE `problem_case`  (
                                 `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键id',
                                 `pid` bigint UNSIGNED NOT NULL COMMENT '题目id',
                                 `input` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '测试样例的输入',
                                 `output` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '测试样例的输出',
                                 `score` int NULL DEFAULT NULL COMMENT '该测试样例的IO得分',
                                 `status` int NULL DEFAULT 0 COMMENT '0可用，1不可用',
                                 `group_num` int NULL DEFAULT 1 COMMENT 'subtask分组的编号',
                                 `gmt_create` datetime NULL DEFAULT CURRENT_TIMESTAMP,
                                 `gmt_modified` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                 `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                 `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                 `is_deleted` tinyint(1) NULL DEFAULT 0,
                                 PRIMARY KEY (`id`) USING BTREE,
                                 INDEX `pid`(`pid` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for problem_language
-- ----------------------------
DROP TABLE IF EXISTS `problem_language`;
CREATE TABLE `problem_language`  (
                                     `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
                                     `pid` bigint UNSIGNED NOT NULL,
                                     `lid` bigint UNSIGNED NOT NULL,
                                     `gmt_create` datetime NULL DEFAULT CURRENT_TIMESTAMP,
                                     `gmt_modified` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                     PRIMARY KEY (`id`) USING BTREE,
                                     INDEX `pid`(`pid` ASC) USING BTREE,
                                     INDEX `lid`(`lid` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for problem_tag
-- ----------------------------
DROP TABLE IF EXISTS `problem_tag`;
CREATE TABLE `problem_tag`  (
                                `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
                                `pid` bigint UNSIGNED NOT NULL,
                                `tid` bigint UNSIGNED NOT NULL,
                                `gmt_create` datetime NULL DEFAULT CURRENT_TIMESTAMP,
                                `gmt_modified` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                PRIMARY KEY (`id`) USING BTREE,
                                INDEX `pid`(`pid` ASC) USING BTREE,
                                INDEX `tid`(`tid` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for reply
-- ----------------------------
DROP TABLE IF EXISTS `reply`;
CREATE TABLE `reply`  (
                          `id` int NOT NULL AUTO_INCREMENT,
                          `comment_id` int NOT NULL COMMENT '被回复的评论id',
                          `from_uid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '发起回复的用户id',
                          `from_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '发起回复的用户名',
                          `from_avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发起回复的用户头像地址',
                          `from_role` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发起回复的用户角色',
                          `to_uid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '被回复的用户id',
                          `to_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '被回复的用户名',
                          `to_avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '被回复的用户头像地址',
                          `content` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '回复的内容',
                          `status` int NULL DEFAULT 0 COMMENT '是否封禁或逻辑删除该回复',
                          `gmt_create` datetime NULL DEFAULT CURRENT_TIMESTAMP,
                          `gmt_modified` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                          PRIMARY KEY (`id`) USING BTREE,
                          INDEX `comment_id`(`comment_id` ASC) USING BTREE,
                          INDEX `from_avatar`(`from_avatar` ASC) USING BTREE,
                          INDEX `to_avatar`(`to_avatar` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
                         `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
                         `role` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色标识，例如 admin、tourist、user',
                         `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色描述',
                         `status` int NULL DEFAULT 0 COMMENT '是否可用, 0 可用, 1 不可用',
                         `gmt_create` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                         `gmt_modified` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
                         `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                         `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                         `is_deleted` tinyint(1) NULL DEFAULT 0,
                         PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission`  (
                                    `id` bigint NOT NULL AUTO_INCREMENT,
                                    `role_id` bigint NOT NULL,
                                    `permission_id` bigint NOT NULL,
                                    `gmt_create` datetime(6) NULL DEFAULT NULL COMMENT '创建时间',
                                    `gmt_modified` datetime(6) NULL DEFAULT NULL COMMENT '修改时间',
                                    `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建者',
                                    `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改者',
                                    `is_deleted` tinyint(1) NULL DEFAULT 0 COMMENT '逻辑删除',
                                    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for session
-- ----------------------------
DROP TABLE IF EXISTS `session`;
CREATE TABLE `session`  (
                            `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
                            `uid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
                            `user_agent` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                            `ip` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                            `gmt_create` datetime NULL DEFAULT CURRENT_TIMESTAMP,
                            `gmt_modified` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                            PRIMARY KEY (`id`) USING BTREE,
                            INDEX `uid`(`uid` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_notice_publish
-- ----------------------------
DROP TABLE IF EXISTS `sys_notice_publish`;
CREATE TABLE `sys_notice_publish`  (
                                       `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
                                       `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标题',
                                       `content` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '内容',
                                       `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发给哪些用户类型',
                                       `state` tinyint(1) NULL DEFAULT 0 COMMENT '是否已拉取给用户',
                                       `recipient_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '接受通知的用户id',
                                       `admin_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发送通知的管理员id',
                                       `gmt_create` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                       `gmt_modified` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
                                       `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                       `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                       `is_deleted` tinyint(1) NULL DEFAULT 0,
                                       PRIMARY KEY (`id`) USING BTREE,
                                       INDEX `admin_id`(`admin_id` ASC) USING BTREE,
                                       INDEX `recipient_id`(`recipient_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_notice_receive
-- ----------------------------
DROP TABLE IF EXISTS `sys_notice_receive`;
CREATE TABLE `sys_notice_receive`  (
                                       `id` int NOT NULL AUTO_INCREMENT,
                                       `notice_id` int NOT NULL COMMENT '对应 sys_notice_publish 的主键',
                                       `recipient_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '接受通知的用户id',
                                       `type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'Sys 系统通知 / Mine 我的消息',
                                       `state` tinyint(1) NULL DEFAULT 0 COMMENT '是否已读',
                                       `gmt_create` datetime NULL DEFAULT CURRENT_TIMESTAMP,
                                       `gmt_modified` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                       `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                                       `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                                       `is_deleted` tinyint(1) NULL DEFAULT 0,
                                       PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tag
-- ----------------------------
DROP TABLE IF EXISTS `tag`;
CREATE TABLE `tag`  (
                        `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
                        `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '标签名字',
                        `color` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标签颜色',
                        `gid` bigint UNSIGNED NULL DEFAULT NULL,
                        `gmt_create` datetime NULL DEFAULT CURRENT_TIMESTAMP,
                        `gmt_modified` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                        `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                        `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                        `is_deleted` tinyint(1) NULL DEFAULT 0,
                        PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for training
-- ----------------------------
DROP TABLE IF EXISTS `training`;
CREATE TABLE `training`  (
                             `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
                             `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '训练题单名称',
                             `description` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '训练题单简介',
                             `auth` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '训练题单权限类型：Public、Private',
                             `private_pwd` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '训练题单权限为Private时的密码',
                             `rank` int NULL DEFAULT 0 COMMENT '编号，升序',
                             `status` tinyint(1) NULL DEFAULT 1 COMMENT '是否可用',
                             `is_group` tinyint(1) NULL DEFAULT 0,
                             `cid` bigint NOT NULL COMMENT '分类id',
                             `gid` bigint UNSIGNED NULL DEFAULT NULL,
                             `gmt_create` datetime NULL DEFAULT CURRENT_TIMESTAMP,
                             `gmt_modified` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                             `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                             `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                             `is_deleted` tinyint(1) NULL DEFAULT 0,
                             PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for training_category
-- ----------------------------
DROP TABLE IF EXISTS `training_category`;
CREATE TABLE `training_category`  (
                                      `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
                                      `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                      `color` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                      `gid` bigint UNSIGNED NULL DEFAULT NULL,
                                      `gmt_create` datetime NULL DEFAULT CURRENT_TIMESTAMP,
                                      `gmt_modified` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                      PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for training_problem
-- ----------------------------
DROP TABLE IF EXISTS `training_problem`;
CREATE TABLE `training_problem`  (
                                     `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
                                     `tid` bigint UNSIGNED NOT NULL COMMENT '训练id',
                                     `pid` bigint UNSIGNED NOT NULL COMMENT '题目id',
                                     `rank` int NULL DEFAULT 0,
                                     `display_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
                                     `gmt_create` datetime NULL DEFAULT CURRENT_TIMESTAMP,
                                     `gmt_modified` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                     PRIMARY KEY (`id`) USING BTREE,
                                     INDEX `display_id`(`display_id` ASC) USING BTREE,
                                     INDEX `pid`(`pid` ASC) USING BTREE,
                                     INDEX `tid`(`tid` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for training_record
-- ----------------------------
DROP TABLE IF EXISTS `training_record`;
CREATE TABLE `training_record`  (
                                    `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
                                    `tid` bigint UNSIGNED NOT NULL,
                                    `tpid` bigint UNSIGNED NOT NULL,
                                    `pid` bigint UNSIGNED NOT NULL,
                                    `uid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
                                    `submit_id` bigint UNSIGNED NOT NULL,
                                    `gmt_create` datetime NULL DEFAULT CURRENT_TIMESTAMP,
                                    `gmt_modified` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                    PRIMARY KEY (`id`) USING BTREE,
                                    INDEX `pid`(`pid` ASC) USING BTREE,
                                    INDEX `submit_id`(`submit_id` ASC) USING BTREE,
                                    INDEX `tid`(`tid` ASC) USING BTREE,
                                    INDEX `tpid`(`tpid` ASC) USING BTREE,
                                    INDEX `uid`(`uid` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for training_register
-- ----------------------------
DROP TABLE IF EXISTS `training_register`;
CREATE TABLE `training_register`  (
                                      `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
                                      `tid` bigint UNSIGNED NOT NULL COMMENT '训练id',
                                      `uid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户id',
                                      `status` tinyint(1) NULL DEFAULT 1 COMMENT '是否可用',
                                      `gmt_create` datetime NULL DEFAULT CURRENT_TIMESTAMP,
                                      `gmt_modified` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                      PRIMARY KEY (`id`) USING BTREE,
                                      INDEX `tid`(`tid` ASC) USING BTREE,
                                      INDEX `uid`(`uid` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user_acproblem
-- ----------------------------
DROP TABLE IF EXISTS `user_acproblem`;
CREATE TABLE `user_acproblem`  (
                                   `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
                                   `uid` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户ID，关联 user_info 表的 uuid',
                                   `pid` bigint NOT NULL COMMENT '题目ID，关联题目表',
                                   `submit_id` bigint NOT NULL COMMENT '提交ID，关联提交记录表',
                                   `gmt_create` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                   `gmt_modified` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
                                   `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                                   `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                                   `is_deleted` tinyint(1) NULL DEFAULT 0,
                                   PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户AC题目记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info`  (
                              `uuid` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'uuid用户id',
                              `username` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '登录账号',
                              `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '登录密码',
                              `nickname` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户昵称',
                              `school` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '学校',
                              `course` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '专业',
                              `number` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '学号',
                              `realname` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '真实名字',
                              `email` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
                              `gender` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '性别',
                              `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像图片地址',
                              `signature` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '个性签名',
                              `blog` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '博客地址',
                              `github` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'github地址',
                              `title_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '称号、头衔',
                              `title_color` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '称号、头衔的背景颜色',
                              `status` tinyint NULL DEFAULT 0 COMMENT '0可用，1不可用',
                              `gmt_create` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                              `gmt_modified` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
                              `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
                              `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新者',
                              `is_deleted` tinyint(1) NULL DEFAULT 0 COMMENT '逻辑删除(0：未删除 1：已删除)',
                              PRIMARY KEY (`uuid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
                              `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
                              `uid` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户ID',
                              `role_id` bigint NOT NULL COMMENT '角色ID',
                              `gmt_create` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                              `gmt_modified` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
                              `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                              `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                              `is_deleted` tinyint(1) NULL DEFAULT 0,
                              PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户与角色关联表' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
