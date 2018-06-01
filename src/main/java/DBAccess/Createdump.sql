CREATE DATABASE  IF NOT EXISTS `Carport` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `Carport`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 138.68.68.197    Database: Carport
-- ------------------------------------------------------
-- Server version	5.7.22-0ubuntu0.16.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `lineitem`
--

DROP TABLE IF EXISTS `lineitem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lineitem` (
  `idlineitem` int(11) NOT NULL AUTO_INCREMENT,
  `length` int(11) NOT NULL,
  `qty` int(11) NOT NULL,
  `unit` varchar(45) NOT NULL,
  `description_use` varchar(100) NOT NULL,
  `material_idmaterial` int(11) NOT NULL,
  `order_idorder` int(11) NOT NULL,
  PRIMARY KEY (`idlineitem`),
  KEY `fk_material_has_order_order1_idx` (`order_idorder`),
  KEY `fk_material_has_order_material_idx` (`material_idmaterial`),
  CONSTRAINT `fk_material_has_order_material` FOREIGN KEY (`material_idmaterial`) REFERENCES `material` (`idmaterial`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_material_has_order_order1` FOREIGN KEY (`order_idorder`) REFERENCES `order` (`idorder`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lineitem`
--

LOCK TABLES `lineitem` WRITE;
/*!40000 ALTER TABLE `lineitem` DISABLE KEYS */;
/*!40000 ALTER TABLE `lineitem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `material`
--

DROP TABLE IF EXISTS `material`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `material` (
  `idmaterial` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `MSRP` double NOT NULL,
  PRIMARY KEY (`idmaterial`),
  UNIQUE KEY `idmateriale_UNIQUE` (`idmaterial`),
  UNIQUE KEY `navn_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `material`
--

LOCK TABLES `material` WRITE;
/*!40000 ALTER TABLE `material` DISABLE KEYS */;
INSERT INTO `material` VALUES (1,'97x97mm. trykimp. stolpe',100),(2,'45x195mm. spærtræ ubh.',100),(3,'25x200mm. trykimp. bræt',45),(4,'25x125mm. trykimp. bræt',99),(5,'19x100mm. trykimp. bræt',22),(6,'Plastmo Ecolite blåtonet',5),(7,'38x73mm. lægte ubh.',30),(8,'45x95mm. reglar ub.',34),(9,'Hulbånd 1x20mm. 10m.',49),(10,'Stalddørsgreb 50x75',26),(11,'T hængsel 390mm.',26),(12,'universal 190mm. venstre',25),(13,'universal 190mm. højre',25),(14,'Plastmo bundskruer 200stk.',1),(15,'4,5x60mm. skruer 200stk.',1),(16,'5,0x40mm beslagsskruer 250stk.',1),(17,'Bræddebolt 10x120mm.',5),(18,'Firkantskriver 40x40x11mm.',5),(19,'4,5x70mm. skruer 400stk.',200),(20,'4,5x50mm. skruer 300stk.',150),(21,'Vinkelbeslag 35',7),(22,'25x150mm. trykimp. bræt',70),(23,'Færdigskåret byg-selv spær',100),(24,'25x50mm. trykimp. bræt',100),(25,'38x73mm. taglægte T1',100),(26,'B&C Dobbelt -s sort',100),(27,'B&C Rygstens sort',100),(28,'B&C Rygstensbeslag',100),(29,'B&C toplægte holder',100),(30,'B&C tagstensbindere og nakkekroge',100),(31,'5,0x100mm. skruer 100stk.',100);
/*!40000 ALTER TABLE `material` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order` (
  `idorder` int(11) NOT NULL AUTO_INCREMENT,
  `length` int(11) DEFAULT NULL,
  `width` int(11) DEFAULT NULL,
  `inclination` varchar(45) DEFAULT NULL,
  `angle` int(11) DEFAULT NULL,
  `roof_material` varchar(45) DEFAULT NULL,
  `shed` varchar(45) DEFAULT NULL,
  `shed_length` int(11) DEFAULT NULL,
  `shed_width` int(11) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  `zipcode` varchar(45) DEFAULT NULL,
  `phonenumber` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `comment` varchar(45) DEFAULT NULL,
  `price` int(11) DEFAULT NULL,
  `salesprice` int(11) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idorder`),
  UNIQUE KEY `idorder_UNIQUE` (`idorder`)
) ENGINE=InnoDB AUTO_INCREMENT=93 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
INSERT INTO `order` VALUES (85,240,240,'Fladt tag',0,'ingen','Uden skur',0,0,'Bo','Jensen','2990 Nivå','88888888','bo@jensen.dk','Mindste mål fladt tag uden skur',4856,0,'Afsendt'),(86,240,240,'Med rejsning',35,'eternittag b6','Uden skur',0,0,'Bo Jensen','Jensensvej 10','2300 Skurby','88888888','bo@jensen.dk','Mindste mål rejsning intet skur',22090,0,'Afsendt'),(87,240,240,'Fladt tag',0,'ingen','shed',150,210,'Bo Jensen','Skurvej 10','Skurby 10','88888888','bo@jensen.dk','Mindste mål fladt tag med skur',4856,0,'Behandlet'),(89,750,720,'Fladt tag',0,'ingen','Med skur',300,660,'Bo Jensen','Storthusvej 3','2990 NivÃ¥','88888888','bo@jensen.dk','Maks mål fladt tag fullshed',16062,0,'Behandler'),(90,510,450,'Fladt tag',0,'ingen','Uden skur',210,150,'Adam Saidane','EgedalsvÃ¦nge 26, 2. mf.','2980','42156035','adamsaidane@hotmail.com','Mellem mål mindste mål skur',4856,0,'Afventer behandling');
/*!40000 ALTER TABLE `order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `iduser` int(11) NOT NULL AUTO_INCREMENT,
  `empno` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  PRIMARY KEY (`iduser`),
  UNIQUE KEY `idbruger_UNIQUE` (`iduser`),
  UNIQUE KEY `medarbejdernr_UNIQUE` (`empno`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'a01','admin');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-06-01 17:00:30
