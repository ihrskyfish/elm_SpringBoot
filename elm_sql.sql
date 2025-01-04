/*
 Navicat Premium Data Transfer

 Source Server         : navicat
 Source Server Type    : MySQL
 Source Server Version : 80200 (8.2.0)
 Source Host           : localhost:3306
 Source Schema         : elm

 Target Server Type    : MySQL
 Target Server Version : 80200 (8.2.0)
 File Encoding         : 65001

 Date: 07/12/2023 13:21:27
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for merchant
-- ----------------------------
DROP TABLE IF EXISTS `merchant`;
CREATE TABLE `merchant` (
  `merchantId` int unsigned NOT NULL AUTO_INCREMENT COMMENT '商家编号',
  `merchantName` varchar(40) NOT NULL COMMENT '商家名称',
  `merchantAddress` varchar(50) DEFAULT NULL COMMENT '商家地址',
  `merchantExplain` varchar(40) DEFAULT NULL COMMENT '商家介绍',
  `merchantImg` mediumtext NOT NULL COMMENT '商家图片（base64）',
  `orderTypeId` int NOT NULL COMMENT '点餐分类',
  `starPrice` decimal(5,2) DEFAULT '0.00' COMMENT '起送费',
  `deliveryPrice` decimal(5,2) DEFAULT '0.00' COMMENT '配送费',
  `remarks` varchar(40) DEFAULT NULL COMMENT '备注',
  `views` int(10) unsigned zerofill DEFAULT NULL COMMENT 'no use' ,
  `orderQuantity` int DEFAULT NULL,
  `isDelete` tinyint NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`merchantId`)
) ENGINE=InnoDB AUTO_INCREMENT=10014 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Table structure for cart
-- ----------------------------
DROP TABLE IF EXISTS `cart`;
CREATE TABLE `cart` (
  `cartId` int NOT NULL AUTO_INCREMENT COMMENT '无意义编号',
  `foodId` int NOT NULL COMMENT '食品编号',
  `merchantId` int unsigned NOT NULL COMMENT '所属商家编号',
  `userId` varchar(20) NOT NULL COMMENT '所属用户编号',
  `quantity` int NOT NULL COMMENT '同一类型食品的购买数量',
  `isDelete` tinyint NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`cartId`)
) ENGINE=InnoDB AUTO_INCREMENT=176 DEFAULT CHARSET=utf8mb3;
alter table cart
    add constraint cart_user_userId_fk
        foreign key (userId) references user (userId);
;





-- ----------------------------
-- Table structure for deliveryaddress
-- ----------------------------
DROP TABLE IF EXISTS `deliveryaddress`;
CREATE TABLE `deliveryaddress` (
  `daId` int NOT NULL AUTO_INCREMENT COMMENT '送货地址编号',
  `contactName` varchar(20) NOT NULL COMMENT '联系人姓名',
  `contactSex` int NOT NULL COMMENT '联系人性别',
  `contactTel` varchar(20) NOT NULL COMMENT '联系人电话',
  `address` varchar(100) NOT NULL COMMENT '送货地址',
  `userId` varchar(20) NOT NULL COMMENT '所属用户编号',
  `isDelete` tinyint NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`daId`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb3;
alter table deliveryaddress
    add constraint deliveryaddress_user_userId_fk
        foreign key (userId) references user (userId);



-- ----------------------------
-- Table structure for food
-- ----------------------------
DROP TABLE IF EXISTS `food`;
CREATE TABLE `food` (
  `foodId` int NOT NULL AUTO_INCREMENT COMMENT '食品编号',
  `foodName` varchar(30) NOT NULL COMMENT '食品名称',
  `foodExplain` varchar(30) NOT NULL COMMENT '食品介绍',
  `foodImg` mediumtext NOT NULL COMMENT '食品图片',
  `foodPrice` decimal(5,2) NOT NULL COMMENT '食品价格',
  `merchantId` int unsigned NOT NULL COMMENT '所属商家编号',
  `remarks` varchar(40) DEFAULT NULL COMMENT '备注',
  `isDelete` tinyint NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`foodId`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb3;
alter table food
    add constraint food_merchant_merchantId_fk
        foreign key (merchantId) references merchant (merchantId) ;


-- ----------------------------
-- Table structure for orderdetailet
-- ----------------------------
DROP TABLE IF EXISTS `orderdetailet`;
CREATE TABLE `orderdetailet` (
  `odId` int NOT NULL AUTO_INCREMENT COMMENT '订单明细编号',
  `orderId` int NOT NULL COMMENT '所属订单编号',
  `foodId` int NOT NULL COMMENT '食品编号',
  `quantity` int NOT NULL COMMENT '数量',
  `isDelete` tinyint NOT NULL DEFAULT '0' COMMENT '是否删除',
  `merchantId` int unsigned DEFAULT NULL,
  PRIMARY KEY (`odId`)
) ENGINE=InnoDB AUTO_INCREMENT=109 DEFAULT CHARSET=utf8mb3;
alter table orderdetailet
    add constraint orderdetailet_merchant_merchantId_fk
        foreign key (merchantId) references merchant (merchantId) ;

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `orderId` int NOT NULL AUTO_INCREMENT COMMENT '订单编号',
  `userId` varchar(20) NOT NULL COMMENT '用户编号',
  `merchantId` int  unsigned  NULL COMMENT '商家编号',
  `orderDate` varchar(20) NOT NULL COMMENT '订购日期',
  `orderTotal` decimal(7,2) NOT NULL DEFAULT '0.00' COMMENT '订单总价',
  `daId` int NOT NULL COMMENT '送货地址编号',
  `orderState` int NOT NULL DEFAULT '0' COMMENT '订单状态（0：未支付； 1：已支付）',
  `isDelete` tinyint NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`orderId`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8mb3;
alter table orders
    add constraint orders_user_userId_fk
        foreign key (userId) references user (userId);
alter table orders
    add constraint orderdetailet_merchant_merchantId_fk
        foreign key (merchantId) references merchant (merchantId) ;



-- ----------------------------
-- Table structure for point
-- ----------------------------
DROP TABLE IF EXISTS `point`;
CREATE TABLE `point` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '积分编号',
  `userId` varchar(20) NOT NULL COMMENT '用户编号',
  `balance` int NOT NULL COMMENT '钱包余额',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=78 DEFAULT CHARSET=utf8mb3;
alter table point
    add constraint point_user_userId_fk
        foreign key (userId) references user (userId);



-- ----------------------------
-- Table structure for pointTurnover
-- ----------------------------
DROP TABLE IF EXISTS `pointTurnover`;
CREATE TABLE `pointTurnover` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '虚拟钱包编号',
  `pointId` bigint NOT NULL COMMENT '积分账户编号',
  `userId` varchar(20) NOT NULL COMMENT '用户编号',
  `state` varchar(20) NOT NULL COMMENT '积分流水状态',
  `amount` int NOT NULL COMMENT '消耗积分数',
  `total` int NOT NULL COMMENT '获取积分数',
  `balance` int NOT NULL COMMENT '本次获取的剩余积分数',
  `createTime` varchar(20) NOT NULL COMMENT '创建日期',
  `isDelete` tinyint NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=75 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Table structure for transactionFlow
-- ----------------------------
DROP TABLE IF EXISTS `transactionFlow`;
CREATE TABLE `transactionFlow` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '虚拟钱包编号',
  `virtualWalletId` bigint NOT NULL COMMENT '订单编号',
  `userId` varchar(20) NOT NULL COMMENT '用户编号',
  `state` varchar(20) NOT NULL COMMENT '流水状态',
  `amount` int NOT NULL COMMENT '流水金额',
  `createTime` varchar(20) NOT NULL COMMENT '创建日期',
  `isDelete` tinyint NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=75 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `userId` varchar(20) NOT NULL COMMENT '用户编号',
  `password` varchar(20) NOT NULL COMMENT '密码',
  `userName` varchar(20) NOT NULL COMMENT '用户名称',
  `userSex` int NOT NULL DEFAULT '1' COMMENT '用户性别（1：男； 0：女）',
  `userImg` mediumtext COMMENT '用户头像',
  `delTag` int NOT NULL DEFAULT '1' COMMENT '删除标记（1：正常； 0：删除）',
  `isDelete` tinyint NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Table structure for virtualWallet
-- ----------------------------
DROP TABLE IF EXISTS `virtualWallet`;
CREATE TABLE `virtualWallet` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '虚拟钱包编号',
  `userId` varchar(20) NOT NULL COMMENT '用户编号',
  `balance` int NOT NULL COMMENT '钱包余额',
  `createTime` varchar(20) NOT NULL COMMENT '创建日期',
  `isDelete` tinyint NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=75 DEFAULT CHARSET=utf8mb3;
-- add a foreign key
alter table virtualWallet
    add constraint virtualWallet_user_userId_fk
        foreign key (userId) references user (userId);


SET FOREIGN_KEY_CHECKS = 1;
