/*
 Navicat Premium Data Transfer

 Source Server         : Java_Project
 Source Server Type    : MySQL
 Source Server Version : 50718
 Source Host           : localhost:3306
 Source Schema         : db_lbj_note

 Target Server Type    : MySQL
 Target Server Version : 50718
 File Encoding         : 65001

 Date: 22/01/2022 23:14:34
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_note
-- ----------------------------
DROP TABLE IF EXISTS `tb_note`;
CREATE TABLE `tb_note`  (
  `noteId` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
  `title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标题',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '内容',
  `typeId` int(11) NULL DEFAULT NULL COMMENT '外键，从属tb_note_type',
  `pubTime` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '发布时间',
  `lon` float NULL DEFAULT NULL COMMENT '经度',
  `lat` float NOT NULL COMMENT '纬度',
  PRIMARY KEY (`noteId`) USING BTREE,
  INDEX `fk_note_ref_type`(`typeId`) USING BTREE,
  CONSTRAINT `fk_note_ref_type` FOREIGN KEY (`typeId`) REFERENCES `tb_note_type` (`typeId`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_note
-- ----------------------------
INSERT INTO `tb_note` VALUES (1, '上海一日游', '<p><strong>这是测试内容</strong></p>', 2, '2021-11-23 05:10:35', 121.395, 31.2536);
INSERT INTO `tb_note` VALUES (2, '第一次测试', '<p>测试</p>', 4, '2021-11-23 05:05:54', 121.395, 31.2536);
INSERT INTO `tb_note` VALUES (3, '今日工作安排', '<p>测试</p>', 1, '2021-12-23 05:05:51', 121.518, 31.0632);
INSERT INTO `tb_note` VALUES (4, '第二次测试', '<p>测试</p>', 1, '2021-12-23 05:05:50', 121.364, 31.0662);
INSERT INTO `tb_note` VALUES (5, '第三次测试', '<p>测试</p>', 1, '2021-12-23 05:05:47', 120.075, 30.2741);
INSERT INTO `tb_note` VALUES (6, '第四次测试', '<p>测试</p>', 1, '2022-01-03 05:02:21', 120.105, 30.1777);
INSERT INTO `tb_note` VALUES (7, '出行计划', '<p>出行计划</p>', 2, '2022-01-03 05:02:24', 119.989, 30.1587);
INSERT INTO `tb_note` VALUES (8, '测试001', '<p>测试001</p>', 4, '2022-01-03 05:02:26', 120.629, 28.0021);
INSERT INTO `tb_note` VALUES (9, '测试002', '<p>测试002</p>', 4, '2022-01-03 05:02:28', 120.753, 27.8908);
INSERT INTO `tb_note` VALUES (11, '重复', '<p>重复</p>', 3, '2022-01-05 02:22:49', 120.681, 28.0628);
INSERT INTO `tb_note` VALUES (12, '测试地址', '<p>测试</p>', 1, '2022-01-05 03:42:18', 121.488, 31.2492);
INSERT INTO `tb_note` VALUES (13, '测试003', '<p>测试003</p>', 1, '2022-01-05 03:45:32', 116.413, 39.9109);
INSERT INTO `tb_note` VALUES (14, 'Java测试', '<p>Java测试~~~</p>', 16, '2022-01-05 03:52:25', 121.488, 31.2492);
INSERT INTO `tb_note` VALUES (16, '测试004美食', '<p style=\"text-align: center;\">测试004美食</p>', 3, '2022-01-18 19:19:38', 104.205, 35.8673);
INSERT INTO `tb_note` VALUES (17, '三亚三天四晚游', '<p>来三亚游泳！</p>', 2, '2022-01-19 11:43:44', 104.205, 35.8673);
INSERT INTO `tb_note` VALUES (18, '云R记', '<p>项目进行中</p>', 16, '2022-01-19 17:41:41', 104.205, 35.8673);
INSERT INTO `tb_note` VALUES (19, 'Java笔记', '<p style=\"text-align: center;\">jre&nbsp;</p><p style=\"text-align: center;\">jvm</p><p style=\"text-align: center;\">jdk</p><p>idea</p>', 16, '2022-01-19 20:07:15', 104.205, 35.8673);
INSERT INTO `tb_note` VALUES (20, '测试Test', '<p style=\"text-align: center;\">测试Test</p>', 9, '2022-01-20 22:27:01', 104.205, 35.8673);
INSERT INTO `tb_note` VALUES (21, '大数据', '<p>大数据专业在读!!</p>', 17, '2022-01-20 22:43:25', 104.205, 35.8673);

-- ----------------------------
-- Table structure for tb_note_type
-- ----------------------------
DROP TABLE IF EXISTS `tb_note_type`;
CREATE TABLE `tb_note_type`  (
  `typeId` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键，自动增长',
  `typeName` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '类别名，在同一个用户下唯一',
  `userId` int(11) NULL DEFAULT NULL COMMENT '从属用户',
  PRIMARY KEY (`typeId`) USING BTREE,
  INDEX `fk_type_ref_user`(`userId`) USING BTREE,
  CONSTRAINT `fk_type_ref_user` FOREIGN KEY (`userId`) REFERENCES `tb_user` (`userId`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_note_type
-- ----------------------------
INSERT INTO `tb_note_type` VALUES (1, '私人日记', 1);
INSERT INTO `tb_note_type` VALUES (2, '旅游攻略', 1);
INSERT INTO `tb_note_type` VALUES (3, '美食', 1);
INSERT INTO `tb_note_type` VALUES (4, '测试', 1);
INSERT INTO `tb_note_type` VALUES (9, 'test001', 1);
INSERT INTO `tb_note_type` VALUES (14, '私人日记1', 3);
INSERT INTO `tb_note_type` VALUES (15, '旅游攻略', 3);
INSERT INTO `tb_note_type` VALUES (16, 'Java', 1);
INSERT INTO `tb_note_type` VALUES (17, 'BigData', 1);

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user`  (
  `userId` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键，自动增长',
  `uname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `upwd` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `nick` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `head` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `mood` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '心情',
  PRIMARY KEY (`userId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES (1, 'admin', 'e10adc3949ba59abbe56e057f20f883e', 'wangwu', 'coding.jpg', 'hello java');
INSERT INTO `tb_user` VALUES (2, 'zhangsan', 'e10adc3949ba59abbe56e057f20f883e', 'zhangsan', 'jay.jpg', 'Hello');
INSERT INTO `tb_user` VALUES (3, 'lisi', 'e10adc3949ba59abbe56e057f20f883e', 'lisi', 'coding.jpg', 'Hello Code');

SET FOREIGN_KEY_CHECKS = 1;
