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
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
INSERT INTO `order` VALUES (1,510,480,'Med rejsning',20,'betontagsten','shed',270,150,'anders','emilvej','2300','53570037','adammail@hej.dk','Nu kan man opdatere',5,1000000,'Afsendt'),(2,300,600,'ja',0,'Tagsten','nej',0,0,'Jens','Jensvej 12','2800 Kgs. Lyngby','12345678','jens@jens.dk','',20000,0,'Afsendt'),(3,300,600,'ja',0,'Tagsten','nej',0,0,'Jens','Jensvej 12','2800 Kgs. Lyngby','12345678','jens@jens.dk','',20000,0,'Behandler'),(4,300,600,'ja',0,'Tagsten','nej',0,0,'Jens','Jensvej 12','2800 Kgs. Lyngby','12345678','jens@jens.dk','',20000,0,'Annulleret'),(5,324,235,'shed',0,'betontagsten','shed',22,64,'adfgadg','asdgasdg','asdgasdg','asdgasdg','asdgasdg','asdgasdg',235,0,'Behandler'),(6,432,234,'shed',0,'betontagsten','shed',100,100,'cgasdg','asdfasdf','asdfasdf','asdfasdf','asdfasdf','asdfasdf',234,0,'asdfasdf'),(7,234,235,'shed',0,'betontagsten','shed',34,100,'defg','sdfg','sdfg','asfhd','dfh','adfh',235,0,'dfh'),(8,345,235,'shed',0,'betontagsten','shed',100,100,'asdgtarg','asdgasdg','asdgasdg','qwerqewr','qwerqwer','qweqwere',235,235,'Annulleret'),(9,345,235,'shed',0,'betontagsten','shed',100,100,'asdgtarg','asdgasdg','asdgasdg','qwerqewr','qwerqwer','qweqwere',235,0,'qwerqwer'),(10,234,234,'shed',0,'betontagsten','shed',25,0,'sdfgsdfg','sdfgsdfg','sdfgsfdgsdfgs','sdfgsdfg','sdfgsdfg','sdfgsdfg',234,0,'sdfgsdfg'),(11,234,234,'shed',0,'betontagsten','shed',25,0,'sdfgsdfg','sdfgsdfg','sdfgsfdgsdfgs','sdfgsdfg','sdfgsdfg','sdfgsdfg',234,0,'sdfgsdfg'),(12,234,234,'shed',0,'ingen','shed',0,0,'sdfgwfd','asdga','asdg','asdga','asdg','asdg',234,0,'asdg'),(13,234,234,'shed',0,'ingen','shed',0,0,'sdfgwfd','asdga','asdg','asdga','asdg','asdg',234,0,'asdg'),(14,234,234,'shed',0,'ingen','shed',0,0,'sdfgwfd','asdga','asdg','asdga','asdg','asdg',234,0,'asdg'),(15,234,234,'shed',0,'ingen','shed',0,0,'sdfgwfd','asdga','asdg','asdga','asdg','asdg',234,0,'asdg'),(16,234,234,'shed',0,'ingen','shed',0,0,'sdfgwfd','asdga','asdg','asdga','asdg','asdg',234,0,'asdg'),(17,123,123,'shed',0,'ingen','shed',0,0,'sdfgsdfg','sdfgsfg','sdfgsgf','sdfggsf','sdfsdg','sdfgsfg',123,0,'Afsendt'),(18,123,123,'shed',0,'ingen','shed',0,0,'sdfgsdfg','sdfgsfg','sdfgsgf','sdfggsf','sdfsdg','sdfgsfg',123,0,'sdfsdg'),(19,123,123,'shed',0,'ingen','shed',0,0,'JKH','HG','JHF','JHF','sdfg','wdfg',123,0,'sdfg'),(20,123,123,'shed',0,'ingen','shed',0,0,'JKH','HG','JHF','JHF','sdfg','wdfg',123,0,'sdfg'),(21,123,123,'shed',0,'ingen','shed',0,0,'JKH','HG','JHF','JHF','sdfg','',123,0,'sdfg'),(22,123,123,'shed',0,'ingen','shed',0,0,'JKH','HG','JHF','JHF','sdfg','',123,0,'sdfg'),(23,123,123,'shed',0,'ingen','shed',0,0,'JKH','HG','JHF','JHF','sdfg','',123,0,'sdfg'),(24,123,123,'shed',0,'ingen','shed',0,0,'JKH','HG','JHF','JHF','sdfg','wdfg',123,0,'sdfg'),(25,123,123,'shed',0,'ingen','shed',0,0,'JKH','HG','JHF','JHF','sdfg','',123,0,'Behandler'),(26,240,240,'shed',0,'eternittag b6','shed',0,0,'Ã¦kjhÃ¦kj','Ã¦jkh','Ã¦kjjh','kj','Ã¦kjh','Ã¦jkh',240,0,'Ã¦kjh'),(27,780,750,'noShed',0,'eternittag b6','noShed',0,0,'testmax','kjg','kjg','kjg','kjg','kjg',750,0,'kjg'),(28,780,600,'shed',0,'eternittag b6','shed',210,530,'FOGEKSEMPEL','kjhf','jlhg','jlhg','jlh','gjhg',600,0,'Behandler'),(31,780,600,'shed',0,'eternittag b6','shed',530,0,'BoMtest','sakldÃ¦akd','sdklaÃ¦','35324523','easdkÃ¦ald','',600,0,'easdkÃ¦ald'),(32,500,500,'noShed',0,'eternittag b6','noShed',0,0,'testorderid','adsfg','jhg','jkhg','kjh','kjh',500,0,'kjh'),(33,600,600,'noShed',0,'eternittag b6','noShed',0,0,'Test','hej','hej','hej','hej','hej',600,0,'hej'),(34,500,500,'noShed',0,'eternittag b6','noShed',0,0,'test','kjg','kjh','kjh','kjh','kjh',500,0,'kjh'),(35,780,750,'shed',0,'eternittag b6','shed',690,720,'TESTBIGSHED','kjghkj','kjgkjgh','kjgkjgh','kjgkjg','kjhkjg',750,0,'kjgkjg'),(36,600,540,'shed',0,'eternittag b6','shed',180,540,'fddglgjk','jjlkjklj','jkljlj','jlkjlkjkl','jkljlk','jkl',540,0,'jkljlk'),(37,240,240,'inclination',0,'eternittag b6','shed',390,90,'test','kjh','kjh','kjh','kjh','lkh',240,0,'kjh'),(38,720,360,'Med rejsning',20,'betontagsten','shed',210,330,'testInclination','asjdfgbÃ¦jkdasbg','kÃ¦sjdb','kÃ¦j','Ã¦kjh','kÃ¦jh',360,0,'Ã¦kjh'),(39,720,360,'Med rejsning',20,'eternittag b6','shed',210,330,'Test med vinkel','saldkasdlÃ¦','kÃ¦klÃ¦','khj','hjk','',720,0,'hjk'),(40,720,360,'Med rejsning',20,'eternittag b6','shed',210,330,'Test med vinkel v2','adkÃ¦l','klÃ¦','klÃ¦','klÃ¦','klÃ¦',360,0,'klÃ¦'),(41,720,630,'Med rejsning',30,'betontagsten','shed',420,600,'PriceTest1','kjhkj','kjgkj','kjgkjg','kjgkj','',77453,0,'kjgkj'),(42,540,390,'Med rejsning',0,'ingen','shed',0,0,'dividermednulTest1','lkhlkjh','jlkhjlkh','lkjhkj','kjhkjlh','',44765,0,'kjhkjlh'),(43,360,420,'Fladt tag',25,'betontagsten','noShed',0,0,'status test 2','kjhk','kjhkjh','kjhkj','hjkhjk','hjkk',7169,0,'Afventer behandling'),(44,780,750,'Med rejsning',0,'eternittag b6','shed',0,0,'Adam','Gaden 26','2980 Byen','21212121','adam@adam.adam','Hejsa',750,0,'adam@adam.adam'),(45,780,300,'Med rejsning',0,'eternittag b6','shed',240,240,'Adam','Gaden 27b','2980 Byen','21212424','adam@business.dk','',300,0,'adam@business.dk'),(46,240,300,'Med rejsning',0,'ingen','noShed',0,0,'Jens','Gadevej 33','2980 Byen','21958320','jens@jysk.dk','',300,0,'jens@jysk.dk'),(47,450,600,'Med rejsning',30,'eternittag b6','shed',210,330,'Test','hej','hej','hej','hej','',51135,47000,'Afventer behandling'),(48,690,630,'Fladt tag',0,'betontagsten','shed',510,360,'TEstFLat','kjgh','kjh','kjh','kjh','kjh',30822,0,'Afventer behandling'),(49,240,240,'Med rejsning',30,'betontagsten','shed',30,30,'testLille','kj','kjh','kjh','kjh','',24959,0,'Afventer behandling'),(50,570,450,'Med rejsning',15,'eternittag b6','shed',570,300,'Alexander','nede i gyden','2400 KÃ¸benhavn NV','Hvad prÃ¸ver du pÃ¥','nÃ¦ nej','Det er mit nye hjem',63120,0,'Afventer behandling'),(51,600,540,'Fladt tag',0,'ingen','noShed',0,390,'kjbh','kj','kjb','kjb','kjb','',11859,0,'Afventer behandling'),(52,720,720,'Med rejsning',35,'betontagsten','shed',270,150,'Unittest','test','test','test','test','SÃ¥ virker det sgu!!!!',28375,28375,'Afsendt'),(53,720,720,'Med rejsning',35,'eternittag b6','shed',720,200,'Adam Jensen','Jensvej 222','2984','242424242','næh','yea',10000,10000,'Afsendt'),(54,720,720,'Med rejsning',35,'betontagsten','shed',270,270,'DataTest','jh','jhg','jhg','jhf','jhg',20000,30000,'Afventer behandling'),(55,240,240,'Fladt tag',0,'ingen','noShed',0,0,'quicktest','teste','tes','klÃ¦dal','hj','',4856,NULL,'Afventer behandling'),(56,750,240,'Fladt tag',0,'ingen','noShed',0,0,'dette er en test','9:19','4600','5353535','353535','3423423',10611,NULL,'Afventer behandling'),(57,240,240,'Fladt tag',0,'ingen','noShed',0,0,'Adam Test','Byengen 24','2980 kokkedal','213212321','hej@test.dk','',4856,NULL,'Afventer behandling'),(58,240,240,'Fladt tag',0,'ingen','noShed',0,0,'jh','gjhg','jhg','jhg','jhg','jhg',4856,NULL,'Afventer behandling'),(59,240,240,'Fladt tag',0,'ingen','noShed',0,0,'kjg','jhg','jhg','jhg','jhg','',4856,0,'Afventer behandling'),(60,690,630,'Med rejsning',30,'betontagsten','shed',150,120,'jhgf','jhg','jhg','jhg','jhg','jhg',62750,0,'Afventer behandling'),(61,690,630,'Med rejsning',30,'betontagsten','shed',150,120,'jhgf','jhg','jhg','jhg','jhg','jhg',62750,0,'Afventer behandling');
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

-- Dump completed on 2018-05-24 13:30:12
