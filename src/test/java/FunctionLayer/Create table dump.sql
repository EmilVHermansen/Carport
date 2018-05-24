DROP TABLE IF EXISTS lineitem;
DROP TABLE IF EXISTS material;
DROP TABLE IF EXISTS `order`;
DROP TABLE IF EXISTS `user`;

DROP TABLE IF EXISTS lineitemTest;
DROP TABLE IF EXISTS materialTest;
DROP TABLE IF EXISTS orderTest;
DROP TABLE IF EXISTS userTest;

CREATE TABLE CarportTest.lineitem LIKE Carport.lineitem;
CREATE TABLE CarportTest.material LIKE Carport.material;
CREATE TABLE CarportTest.`order` LIKE Carport.`order`;
CREATE TABLE CarportTest.`user` LIKE Carport.`user`;

CREATE TABLE lineitemTest LIKE CarportTest.lineitem;
CREATE TABLE materialTest LIKE CarportTest.material;
CREATE TABLE orderTest LIKE CarportTest.`order`;
CREATE TABLE userTest LIKE CarportTest.`user`;