/*
Navicat MySQL Data Transfer

Source Server         : 120.77.253.118:3307
Source Server Version : 50724
Source Host           : 120.77.253.118:3308
Source Database       : erp

Target Server Type    : MYSQL
Target Server Version : 50724
File Encoding         : 65001

Date: 2019-05-30 16:20:03
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for dept
-- ----------------------------
DROP TABLE IF EXISTS `dept`;
CREATE TABLE `dept` (
  `id` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '主键ID',
  `dept_code` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '部门编码',
  `dept_name` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '部门名称',
  `tenant_id` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '租户ID',
  `address` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '部门地址',
  `describe` varchar(150) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '描述',
  `created_by` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '创建人',
  `created_date` datetime DEFAULT NULL COMMENT '创建时间',
  `last_update_by` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '最后修改人',
  `last_update_date` datetime DEFAULT NULL COMMENT '最后修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uq_name_tenan` (`dept_name`,`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='部门表';

-- ----------------------------
-- Table structure for lookup
-- ----------------------------
DROP TABLE IF EXISTS `lookup`;
CREATE TABLE `lookup` (
  `id` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '主键ID',
  `lookup_code` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '字典编码',
  `lookup_name` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '字典名称',
  `describe` varchar(150) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '描述',
  `tenant_id` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '租户ID',
  `created_by` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '创建人',
  `created_date` datetime DEFAULT NULL COMMENT '创建时间',
  `last_update_by` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '最后修改人',
  `last_update_date` datetime DEFAULT NULL COMMENT '最后修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uq_code_tenant` (`lookup_code`,`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='数据字典';

-- ----------------------------
-- Table structure for lookup_config
-- ----------------------------
DROP TABLE IF EXISTS `lookup_config`;
CREATE TABLE `lookup_config` (
  `id` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '主键ID',
  `key` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '字典名称',
  `value` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '字典编码',
  `state` char(1) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '状态{Y:启用,N:停用}',
  `looup_id` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '数据字典ID',
  `created_by` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '创建人',
  `created_date` datetime DEFAULT NULL COMMENT '创建时间',
  `last_update_by` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '最后修改人',
  `last_update_date` datetime DEFAULT NULL COMMENT '最后修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='数据字典配置表';

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `id` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '主键ID',
  `pid` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `menu_name` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '菜单名称',
  `menu_type` char(1) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '菜单类型{0:菜单,1:子菜单,2:按钮}',
  `icon` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '图片',
  `component` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '组件',
  `path` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '路径',
  `sort` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '排序',
  `tenant_id` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '租户ID',
  `created_by` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '创建人',
  `created_date` datetime DEFAULT NULL COMMENT '创建时间',
  `last_update_by` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '最后修改人',
  `last_update_date` datetime DEFAULT NULL COMMENT '最后修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='菜单管理';

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '主键ID',
  `role_code` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '角色编码',
  `role_name` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '角色名称',
  `tenant_id` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '租户ID',
  `describe` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '描述',
  `created_by` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '创建人',
  `created_date` datetime DEFAULT NULL COMMENT '创建时间',
  `last_update_by` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '最后修改人',
  `last_update_date` datetime DEFAULT NULL COMMENT '最后修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='角色';

-- ----------------------------
-- Table structure for tenant
-- ----------------------------
DROP TABLE IF EXISTS `tenant`;
CREATE TABLE `tenant` (
  `id` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '主键ID',
  `tenant_code` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '企业编码,系统自动生成',
  `tenant_name` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '企业名称',
  `tenant_type` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '企业类型',
  `province` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '省',
  `city` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '市',
  `area` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '区',
  `address` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '详细地址',
  `credit_code` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '统一信用代码',
  `user_name` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '联系人',
  `phone` varchar(15) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '电话号码',
  `effective_date` datetime DEFAULT NULL COMMENT '有效日期',
  `state` datetime DEFAULT NULL COMMENT '状态{正常、禁用、已过期}',
  `created_by` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '创建人',
  `created_date` datetime DEFAULT NULL COMMENT '创建时间',
  `last_update_by` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '最后修改人',
  `last_update_date` datetime DEFAULT NULL COMMENT '最后修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uq_tenant_name` (`tenant_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='租户';

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `tenant_role`;
CREATE TABLE `tenant_role` (
  `id` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '主键ID',
  `tenant_id` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '租户ID',
  `role_id` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '角色ID',
  `created_by` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '创建人',
  `created_date` datetime DEFAULT NULL COMMENT '创建时间',
  `last_update_by` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '最后修改人',
  `last_update_date` datetime DEFAULT NULL COMMENT '最后修改时间',
  PRIMARY KEY (`id`),
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='租户角色关系';

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '主键ID',
  `user_account` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户账号,系统自动生成',
  `real_name` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户的真实姓名',
  `phone` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户手机号码',
  `password` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户密码',
  `sex` char(1) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '性別',
  `birth_date` datetime DEFAULT NULL COMMENT '出生日期',
  `created_by` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '创建人',
  `created_date` datetime DEFAULT NULL COMMENT '创建时间',
  `last_update_by` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '最后修改人',
  `last_update_date` datetime DEFAULT NULL COMMENT '最后修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uq_user_account` (`user_account`),
  UNIQUE KEY `uq_phone` (`phone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

