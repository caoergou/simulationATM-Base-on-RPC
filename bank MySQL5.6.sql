/*
 Navicat Premium Data Transfer

 Source Server         : Local Server
 Source Server Type    : MySQL
 Source Server Version : 50528
 Source Host           : localhost:3306
 Source Schema         : bank

 Target Server Type    : MySQL
 Target Server Version : 50528
 File Encoding         : 65001

 Date: 24/04/2020 10:44:24
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for banklog
-- ----------------------------
DROP TABLE IF EXISTS `banklog`;
CREATE TABLE `banklog`  (
  `record_datetime` datetime NOT NULL,
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `outamount` decimal(10, 2) NOT NULL,
  `beforeamount` decimal(10, 2) NOT NULL,
  PRIMARY KEY (`record_datetime`, `username`, `outamount`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for bankuser
-- ----------------------------
DROP TABLE IF EXISTS `bankuser`;
CREATE TABLE `bankuser`  (
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_money` decimal(10, 2) NOT NULL,
  PRIMARY KEY (`username`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of bankuser
-- ----------------------------
INSERT INTO `bankuser` VALUES ('123456', '123456', 2023.00);
INSERT INTO `bankuser` VALUES ('123457', '123456', 3000.00);

-- ----------------------------
-- Table structure for user_record
-- ----------------------------
DROP TABLE IF EXISTS `user_record`;
CREATE TABLE `user_record`  (
  `record_datetime` datetime NOT NULL,
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `record_type` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `record_money` decimal(10, 2) NOT NULL,
  `goalname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`record_datetime`, `record_type`, `goalname`) USING BTREE,
  INDEX `username`(`username`) USING BTREE,
  CONSTRAINT `user_record_ibfk_1` FOREIGN KEY (`username`) REFERENCES `bankuser` (`username`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user_record
-- ----------------------------
INSERT INTO `user_record` VALUES ('2020-04-21 10:29:04', '123456', '存款', 2000.00, '123456');
INSERT INTO `user_record` VALUES ('2020-04-21 10:29:22', '123456', '取款', 2000.00, '123456');
INSERT INTO `user_record` VALUES ('2020-04-21 12:12:17', '123456', '取款', 2000.00, '123456');
INSERT INTO `user_record` VALUES ('2020-04-22 11:44:18', '123456', '取款', 1111.00, '123456');
INSERT INTO `user_record` VALUES ('2020-04-22 14:58:32', '123456', '存款', 1.00, '123456');
INSERT INTO `user_record` VALUES ('2020-04-22 14:58:36', '123456', '存款', 10.00, '123456');
INSERT INTO `user_record` VALUES ('2020-04-23 09:00:12', '123456', '存款', 123.00, '123456');

SET FOREIGN_KEY_CHECKS = 1;
