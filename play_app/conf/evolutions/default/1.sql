--- !Ups

-- MySQL dump 10.13  Distrib 5.7.17, for macos10.12 (x86_64)
--
-- Host: localhost    Database: ebiznes
-- ------------------------------------------------------
-- Server version	5.7.24

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
-- Table structure for table `Admin`
--

DROP TABLE IF EXISTS `Admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Admin` (
  `idAdmin` int(11) NOT NULL,
  `adminUsername` varchar(45) NOT NULL,
  `adminPassword` varchar(45) NOT NULL,
  PRIMARY KEY (`idAdmin`),
  UNIQUE KEY `adminUsername_UNIQUE` (`adminUsername`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Category`
--

DROP TABLE IF EXISTS `Category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Category` (
  `idCategory` int(11) NOT NULL,
  `categoryName` varchar(45) NOT NULL,
  `categoryUpper` int(11) DEFAULT NULL,
  PRIMARY KEY (`idCategory`),
  KEY `categoryUpper_idx` (`categoryUpper`),
  CONSTRAINT `categoryUpper` FOREIGN KEY (`categoryUpper`) REFERENCES `Category` (`idCategory`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Product`
--

DROP TABLE IF EXISTS `Product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Product` (
  `idProduct` int(11) NOT NULL,
  `productCategory` int(11) NOT NULL,
  `productPrice` int(11) NOT NULL,
  `productName` varchar(45) NOT NULL,
  `productDescription` varchar(255) DEFAULT NULL,
  `productPhoto` varchar(255) DEFAULT NULL,
  `productNotSaled` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`idProduct`),
  KEY `productCategory_idx` (`productCategory`),
  CONSTRAINT `productCategory` FOREIGN KEY (`productCategory`) REFERENCES `Category` (`idCategory`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Stock`
--

DROP TABLE IF EXISTS `Stock`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Stock` (
  `idStock` int(11) NOT NULL,
  `idProduct` int(11) NOT NULL,
  `quantity` varchar(45) NOT NULL DEFAULT '0',
  PRIMARY KEY (`idStock`),
  KEY `stock_id_product_idx` (`idProduct`),
  CONSTRAINT `stock_id_product` FOREIGN KEY (`idProduct`) REFERENCES `Product` (`idProduct`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Subtransaction`
--

DROP TABLE IF EXISTS `Subtransaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Subtransaction` (
  `idSubtransaction` int(11) NOT NULL,
  `idTransaction` int(11) NOT NULL,
  `idProduct` int(11) DEFAULT NULL,
  `subtransactionQuantity` int(11) DEFAULT NULL,
  PRIMARY KEY (`idSubtransaction`),
  KEY `idProduct_idx` (`idProduct`),
  KEY `idTransaction_idx` (`idTransaction`),
  CONSTRAINT `idProduct` FOREIGN KEY (`idProduct`) REFERENCES `Product` (`idProduct`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `idTransaction` FOREIGN KEY (`idTransaction`) REFERENCES `Transaction` (`idTransaction`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Transaction`
--

DROP TABLE IF EXISTS `Transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Transaction` (
  `idTransaction` int(11) NOT NULL,
  `idUser` int(11) DEFAULT NULL,
  `transactionDate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`idTransaction`),
  KEY `id_user_idx` (`idUser`),
  CONSTRAINT `id_user` FOREIGN KEY (`idUser`) REFERENCES `User` (`idUser`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `User`
--

DROP TABLE IF EXISTS `User`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `User` (
  `idUser` int(11) NOT NULL AUTO_INCREMENT,
  `userEmail` varchar(45) NOT NULL,
  `userPassword` varchar(45) NOT NULL,
  `userName` varchar(45) DEFAULT NULL,
  `userSurname` varchar(45) DEFAULT NULL,
  `userStreet` varchar(45) DEFAULT NULL,
  `userHomeNumber` varchar(45) DEFAULT NULL,
  `userCity` varchar(45) DEFAULT NULL,
  `userCountry` varchar(45) DEFAULT NULL,
  `userPostalCode` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idUser`),
  UNIQUE KEY `email_UNIQUE` (`userEmail`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-04-27 18:48:34

--- !Downs

DROP TABLE IF EXISTS `Admin`;
DROP TABLE IF EXISTS `Category`;
DROP TABLE IF EXISTS `Product`;
DROP TABLE IF EXISTS `Stock`;
DROP TABLE IF EXISTS `Subtransaction`;
DROP TABLE IF EXISTS `Transaction`;
DROP TABLE IF EXISTS `User`;
