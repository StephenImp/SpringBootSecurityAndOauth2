/*
Navicat MySQL Data Transfer

Source Server         : mozi
Source Server Version : 50720
Source Host           : rm-bp1o892eutomay3940o.mysql.rds.aliyuncs.com:3306
Source Database       : attendance_system

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-11-15 18:46:33
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `u_id` bigint(20) NOT NULL,
  `u_password` varchar(255) DEFAULT NULL,
  `u_username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`u_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('1', '$2a$10$7OCL0BmVqNX0lUdPJ9mP2uwGPJLdQ6.6UoKDQkVSenYRHZFe7.v.m', 'wpw');
INSERT INTO `users` VALUES ('2', '$2a$10$QWo1JTFnloveVkZbxq2acOXH2jSThb9jcxZEW1K8mBBt5rLqoTrFm', 'cl');
