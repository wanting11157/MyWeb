/*
 Navicat Premium Data Transfer

 Source Server         : 2
 Source Server Type    : MySQL
 Source Server Version : 80021
 Source Host           : localhost:3306
 Source Schema         : myweb1

 Target Server Type    : MySQL
 Target Server Version : 80021
 File Encoding         : 65001

 Date: 15/10/2020 10:17:25
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '课程姓名',
  `teacher_id` int(0) NULL DEFAULT NULL COMMENT '教师id',
  `teacher_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '教师姓名',
  `college` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '学院',
  `team` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '学期',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES (1, '1', 1, '1', '1', '1', '2020-10-06 18:08:21', '2020-10-19 18:08:24', NULL);
INSERT INTO `course` VALUES (2, '2', 0, NULL, NULL, NULL, NULL, NULL, '2');
INSERT INTO `course` VALUES (3, '2', 0, '2', '2', '2', NULL, NULL, '2');
INSERT INTO `course` VALUES (4, '3', 0, '3', '3', '33', NULL, NULL, '3');
INSERT INTO `course` VALUES (5, '3', 0, '3', '3', '33', NULL, NULL, '3');
INSERT INTO `course` VALUES (6, '2', 0, '2', '2', 'y88y8', NULL, NULL, '2');

-- ----------------------------
-- Table structure for score
-- ----------------------------
DROP TABLE IF EXISTS `score`;
CREATE TABLE `score`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `course_id` int(0) NOT NULL,
  `score` int(0) NULL DEFAULT NULL,
  `student_id` int(0) NOT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of score
-- ----------------------------
INSERT INTO `score` VALUES (1, 1, 1, 1, NULL, NULL, NULL);
INSERT INTO `score` VALUES (2, 1, 1, 1, NULL, NULL, NULL);
INSERT INTO `score` VALUES (3, 1, 6, 2, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sexs
-- ----------------------------
DROP TABLE IF EXISTS `sexs`;
CREATE TABLE `sexs`  (
  `id` int(0) NOT NULL,
  `sex` char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sexs
-- ----------------------------
INSERT INTO `sexs` VALUES (0, '男');
INSERT INTO `sexs` VALUES (1, '女');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '姓名',
  `major` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '专业',
  `haoma` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '学号',
  `sex` tinyint(0) NULL DEFAULT NULL COMMENT '性别',
  `college` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '学院',
  `password` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '生成时间',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '1', '1', 'yiyiy', 1, '6868', '1', NULL, '2020-10-11 17:29:56', '1');
INSERT INTO `user` VALUES (2, 'fwefew', 'wcw', 'dwd', 1, 'fewf', 'fewafew', NULL, NULL, 'fewafew');
INSERT INTO `user` VALUES (3, '2', '2', '2', 0, '3', '3', NULL, NULL, '3');
INSERT INTO `user` VALUES (4, '2', '2', '7', 0, '5', '3', NULL, NULL, '3');
INSERT INTO `user` VALUES (5, '1', '1', '1423232', 1, '1', '1', NULL, NULL, '1');
INSERT INTO `user` VALUES (6, 't', 't', 't', 1, 't', 't', NULL, NULL, 't');

SET FOREIGN_KEY_CHECKS = 1;
