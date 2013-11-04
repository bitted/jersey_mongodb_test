/*
Navicat MySQL Data Transfer

Source Server         : 10.5.12.61
Source Server Version : 50045
Source Host           : 10.5.12.61:3306
Source Database       : showcase4

Target Server Type    : MYSQL
Target Server Version : 50045
File Encoding         : 65001

Date: 2013-11-01 11:45:57
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `ss_role`
-- ----------------------------
DROP TABLE IF EXISTS `ss_role`;
CREATE TABLE `ss_role` (
  `id` int(20) unsigned zerofill NOT NULL auto_increment,
  `name` varchar(255) NOT NULL,
  `permissions` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ss_role
-- ----------------------------
INSERT INTO `ss_role` VALUES ('00000000000000000001', 'Admin', 'user:view,user:edit');
INSERT INTO `ss_role` VALUES ('00000000000000000002', 'User', 'user:view');

-- ----------------------------
-- Table structure for `ss_team`
-- ----------------------------
DROP TABLE IF EXISTS `ss_team`;
CREATE TABLE `ss_team` (
  `id` int(20) unsigned zerofill NOT NULL auto_increment,
  `name` varchar(255) NOT NULL,
  `master_id` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ss_team
-- ----------------------------
INSERT INTO `ss_team` VALUES ('00000000000000000001', 'Dolphin', '1');

-- ----------------------------
-- Table structure for `ss_user`
-- ----------------------------
DROP TABLE IF EXISTS `ss_user`;
CREATE TABLE `ss_user` (
  `id` int(20) unsigned zerofill NOT NULL auto_increment,
  `login_name` varchar(255) NOT NULL,
  `name` varchar(64) default NULL,
  `password` varchar(255) default NULL,
  `salt` varchar(64) default NULL,
  `email` varchar(128) default NULL,
  `status` varchar(32) default NULL,
  `team_id` bigint(20) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ss_user
-- ----------------------------
INSERT INTO `ss_user` VALUES ('00000000000000000001', 'admin', 'Admin', '691b14d79bf0fa2215f155235df5e670b64394cc', '7efbd59d9741d34f', 'admin@springside.org.cn', 'enabled', '1');
INSERT INTO `ss_user` VALUES ('00000000000000000002', 'user', 'Calvin', '2488aa0c31c624687bd9928e0a5d29e7d1ed520b', '6d65d24122c30500', 'user@springside.org.cn', 'enabled', '1');
INSERT INTO `ss_user` VALUES ('00000000000000000003', 'user2', 'Jack', '2488aa0c31c624687bd9928e0a5d29e7d1ed520b', '6d65d24122c30500', 'jack@springside.org.cn', 'enabled', '1');
INSERT INTO `ss_user` VALUES ('00000000000000000004', 'user3', 'Kate', '2488aa0c31c624687bd9928e0a5d29e7d1ed520b', '6d65d24122c30500', 'kate@springside.org.cn', 'enabled', '1');
INSERT INTO `ss_user` VALUES ('00000000000000000005', 'user4', 'Sawyer', '2488aa0c31c624687bd9928e0a5d29e7d1ed520b', '6d65d24122c30500', 'sawyer@springside.org.cn', 'enabled', '1');
INSERT INTO `ss_user` VALUES ('00000000000000000006', 'user5', 'Ben', '2488aa0c31c624687bd9928e0a5d29e7d1ed520b', '6d65d24122c30500', 'ben@springside.org.cn', 'enabled', '1');

-- ----------------------------
-- Table structure for `ss_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `ss_user_role`;
CREATE TABLE `ss_user_role` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY  (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ss_user_role
-- ----------------------------
INSERT INTO `ss_user_role` VALUES ('1', '1');
INSERT INTO `ss_user_role` VALUES ('1', '2');
INSERT INTO `ss_user_role` VALUES ('2', '2');
INSERT INTO `ss_user_role` VALUES ('3', '2');
INSERT INTO `ss_user_role` VALUES ('4', '2');
INSERT INTO `ss_user_role` VALUES ('5', '2');
INSERT INTO `ss_user_role` VALUES ('6', '2');
