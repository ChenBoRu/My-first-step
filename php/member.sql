-- phpMyAdmin SQL Dump
-- version 2.10.3
-- http://www.phpmyadmin.net
-- 
-- 主機: localhost
-- 建立日期: Jan 12, 2010, 09:07 AM
-- 伺服器版本: 5.0.51
-- PHP 版本: 5.2.6

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";

-- 
-- 資料庫: `member`
-- 

-- --------------------------------------------------------

-- 
-- 資料表格式： `member`
-- 

CREATE TABLE `member` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(20) collate utf8_unicode_ci NOT NULL COMMENT '姓名',
  `account` varchar(20) collate utf8_unicode_ci NOT NULL COMMENT '帳號',
  `password` varchar(20) collate utf8_unicode_ci NOT NULL COMMENT '密碼',
  `sex` char(2) collate utf8_unicode_ci NOT NULL COMMENT '性別',
  `birthday` date default NULL COMMENT '生日',
  `email` varchar(100) collate utf8_unicode_ci default NULL COMMENT '電子郵件',
  `url` varchar(100) collate utf8_unicode_ci default NULL COMMENT '個人網頁',
  `telephone` varchar(20) collate utf8_unicode_ci default NULL COMMENT '電話',
  `address` varchar(100) collate utf8_unicode_ci default NULL COMMENT '地址',
  `contact` char(1) collate utf8_unicode_ci NOT NULL default '1' COMMENT '聯絡方式',
  `memo` varchar(255) collate utf8_unicode_ci default NULL COMMENT '備註',
  PRIMARY KEY  (`id`),
  UNIQUE KEY `account` (`account`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=19 ;

-- 
-- 列出以下資料庫的數據： `member`
-- 

INSERT INTO `member` VALUES (16, '11111', 'aaaaa', '11111', '男', '1977-08-13', '3333@333.22', '44444', '22222', '33333', '1', NULL);
INSERT INTO `member` VALUES (18, '11111', 'ccccc', '11111', '男', '1962-04-05', '444@44.22', '55555', '22222', '333333', '3', '66666');
