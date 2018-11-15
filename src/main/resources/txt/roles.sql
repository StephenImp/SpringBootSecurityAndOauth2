/*
Navicat MySQL Data Transfer

Source Server         : mozi
Source Server Version : 50720
Source Host           : rm-bp1o892eutomay3940o.mysql.rds.aliyuncs.com:3306
Source Database       : attendance_system

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-11-15 18:46:43
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for roles
-- ----------------------------
DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles` (
  `r_id` bigint(20) NOT NULL,
  `r_flag` varchar(255) DEFAULT NULL,
  `r_name` varchar(255) DEFAULT NULL,
  `u_id` int(20) DEFAULT NULL,
  PRIMARY KEY (`r_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of roles
-- ----------------------------
INSERT INTO `roles` VALUES ('1', 'ROLE_USER', '普通用户', '1');
INSERT INTO `roles` VALUES ('2', 'ROLE_ADMIN', '管理员', '1');
INSERT INTO `roles` VALUES ('3', 'ROLE_SUPER_ADMIN', '超级管理员', '1');
INSERT INTO `roles` VALUES ('4', 'ROLE_USER', '普通用户', '2');
