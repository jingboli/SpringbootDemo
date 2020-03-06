/*
 Navicat MySQL Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80015
 Source Host           : localhost:3306
 Source Schema         : microservice

 Target Server Type    : MySQL
 Target Server Version : 80015
 File Encoding         : 65001

 Date: 06/03/2020 12:38:00
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user`  (
  `id` int(10) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT,
  `username` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES (0000000001, '张三', '北京市宣武区大栅栏');
INSERT INTO `tb_user` VALUES (0000000002, '李四', '北京市西城区城隍根');
INSERT INTO `tb_user` VALUES (0000000003, '王麻子', '北京市昌平区十三陵');
INSERT INTO `tb_user` VALUES (0000000004, '王五', '北京市石景山区首钢');
INSERT INTO `tb_user` VALUES (0000000005, '郭德纲', '天津市河北区天津大学');
INSERT INTO `tb_user` VALUES (0000000006, '于谦', '北京市亦庄开发区');
INSERT INTO `tb_user` VALUES (0000000007, '小岳岳', '北京市海淀区清华大学');

SET FOREIGN_KEY_CHECKS = 1;
