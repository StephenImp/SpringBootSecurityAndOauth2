/*
Navicat MySQL Data Transfer

Source Server         : mozi
Source Server Version : 50720
Source Host           : rm-bp1o892eutomay3940o.mysql.rds.aliyuncs.com:3306
Source Database       : attendance_system

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-11-15 18:46:53
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for permisson
-- ----------------------------
DROP TABLE IF EXISTS `permisson`;
CREATE TABLE `permisson` (
  `p_id` int(20) NOT NULL,
  `p_name` varchar(255) DEFAULT NULL,
  `p_descritpion` varchar(255) DEFAULT NULL,
  `p_url` varchar(255) DEFAULT NULL,
  `u_role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`p_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of permisson
-- ----------------------------
INSERT INTO `permisson` VALUES ('1', 'test1', '测试1', '/test/connect111', 'ROLE_SUPER_ADMIN');
INSERT INTO `permisson` VALUES ('2', 'test2', '测试1', '/test/test2', 'ROLE_SUPER_ADMIN');
INSERT INTO `permisson` VALUES ('3', 'test3', '测试3', '/test/test3', 'ROLE_SUPER_ADMIN');
INSERT INTO `permisson` VALUES ('4', 'test3', '测试3', '/test/test2', 'ROLE_SUPER_ADMIN');
INSERT INTO `permisson` VALUES ('5', 'test4', '测试hello', '/hello', 'ROLE_USER');
