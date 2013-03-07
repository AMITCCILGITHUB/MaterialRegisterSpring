CREATE DATABASE  IF NOT EXISTS `metalplantsspring` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `metalplantsspring`;
-- MySQL dump 10.13  Distrib 5.5.16, for Win32 (x86)
--
-- Host: localhost    Database: metalplantsspring
-- ------------------------------------------------------
-- Server version	5.5.27

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
-- Table structure for table `menu_role_master`
--

DROP TABLE IF EXISTS `menu_role_master`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `menu_role_master` (
  `MENU_CODE` int(11) NOT NULL,
  `CODE` int(11) NOT NULL,
  KEY `FK2F81A76B4AD27525` (`MENU_CODE`),
  KEY `FK2F81A76BDFC609F` (`CODE`),
  CONSTRAINT `FK2F81A76B4AD27525` FOREIGN KEY (`MENU_CODE`) REFERENCES `menu_master` (`MENU_CODE`),
  CONSTRAINT `FK2F81A76BDFC609F` FOREIGN KEY (`CODE`) REFERENCES `validation_master` (`CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menu_role_master`
--

LOCK TABLES `menu_role_master` WRITE;
/*!40000 ALTER TABLE `menu_role_master` DISABLE KEYS */;
INSERT INTO `menu_role_master` VALUES (1000,1000),(1001,1001),(1001,1000);
/*!40000 ALTER TABLE `menu_role_master` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `heatchartsheet_code_sequence`
--

DROP TABLE IF EXISTS `heatchartsheet_code_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `heatchartsheet_code_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `heatchartsheet_code_sequence`
--

LOCK TABLES `heatchartsheet_code_sequence` WRITE;
/*!40000 ALTER TABLE `heatchartsheet_code_sequence` DISABLE KEYS */;
INSERT INTO `heatchartsheet_code_sequence` VALUES (1000);
/*!40000 ALTER TABLE `heatchartsheet_code_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `heatchart_sheet`
--

DROP TABLE IF EXISTS `heatchart_sheet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `heatchart_sheet` (
  `HEATCHARTSHEET_CODE` int(11) NOT NULL,
  `CREATED_BY` varchar(255) DEFAULT NULL,
  `CREATED_DATE` date DEFAULT NULL,
  `PART_NAME` varchar(255) DEFAULT NULL,
  `PART_NUMBER` varchar(255) DEFAULT NULL,
  `SEQUENCE_NUMBER` int(11) DEFAULT NULL,
  `HEATCHART_SHEET_NUMBER` int(11) DEFAULT NULL,
  `SPECIFIED_GRADE` varchar(255) DEFAULT NULL,
  `SPECIFIED_SIZE` varchar(255) DEFAULT NULL,
  `STATUS` varchar(255) DEFAULT NULL,
  `HEATCHART_CODE` int(11) DEFAULT NULL,
  `MATERIAL_CODE` int(11) DEFAULT NULL,
  `TEST_CODE` int(11) DEFAULT NULL,
  PRIMARY KEY (`HEATCHARTSHEET_CODE`),
  UNIQUE KEY `HEATCHARTSHEET_CODE` (`HEATCHARTSHEET_CODE`),
  KEY `FKD66A858ED2DDC217` (`HEATCHART_CODE`),
  KEY `FKD66A858E568BDF9D` (`TEST_CODE`),
  KEY `FKD66A858EAFBB68A5` (`MATERIAL_CODE`),
  CONSTRAINT `FKD66A858E568BDF9D` FOREIGN KEY (`TEST_CODE`) REFERENCES `material_test` (`MATERIALTEST_CODE`),
  CONSTRAINT `FKD66A858EAFBB68A5` FOREIGN KEY (`MATERIAL_CODE`) REFERENCES `material_master` (`MATERIAL_CODE`),
  CONSTRAINT `FKD66A858ED2DDC217` FOREIGN KEY (`HEATCHART_CODE`) REFERENCES `heatchart_master` (`HEATCHART_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `heatchart_sheet`
--

LOCK TABLES `heatchart_sheet` WRITE;
/*!40000 ALTER TABLE `heatchart_sheet` DISABLE KEYS */;
/*!40000 ALTER TABLE `heatchart_sheet` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `validation_master`
--

DROP TABLE IF EXISTS `validation_master`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `validation_master` (
  `CODE` int(11) NOT NULL,
  `CREATED_BY` varchar(255) DEFAULT NULL,
  `CREATED_DATE` date DEFAULT NULL,
  `DISPLAY_TEXT` varchar(255) DEFAULT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `STATUS` varchar(255) DEFAULT NULL,
  `TYPE` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`CODE`),
  UNIQUE KEY `CODE` (`CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `validation_master`
--

LOCK TABLES `validation_master` WRITE;
/*!40000 ALTER TABLE `validation_master` DISABLE KEYS */;
INSERT INTO `validation_master` VALUES (1000,'SYSTEM','2013-02-27','Administrator','Admin','TRUE','ROLE'),(1001,'SYSTEM','2013-02-27','Viewer','Viewer','TRUE','ROLE');
/*!40000 ALTER TABLE `validation_master` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `material_code_sequence`
--

DROP TABLE IF EXISTS `material_code_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `material_code_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `material_code_sequence`
--

LOCK TABLES `material_code_sequence` WRITE;
/*!40000 ALTER TABLE `material_code_sequence` DISABLE KEYS */;
INSERT INTO `material_code_sequence` VALUES (1000);
/*!40000 ALTER TABLE `material_code_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `material_test`
--

DROP TABLE IF EXISTS `material_test`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `material_test` (
  `MATERIALTEST_CODE` int(11) NOT NULL,
  `CREATED_BY` varchar(255) DEFAULT NULL,
  `CREATED_DATE` date DEFAULT NULL,
  `EQUIPMENTS` varchar(255) DEFAULT NULL,
  `FAILURE_REASON` varchar(255) DEFAULT NULL,
  `REMARKS` varchar(255) DEFAULT NULL,
  `REPORT_DATE` varchar(255) DEFAULT NULL,
  `REPORT_NUMBER` varchar(255) DEFAULT NULL,
  `SAMPLE_ID` varchar(255) DEFAULT NULL,
  `STATUS` varchar(255) DEFAULT NULL,
  `WITNESSED_BY` varchar(255) DEFAULT NULL,
  `CODE` int(11) DEFAULT NULL,
  `MATERIAL_CODE` int(11) DEFAULT NULL,
  PRIMARY KEY (`MATERIALTEST_CODE`),
  UNIQUE KEY `MATERIALTEST_CODE` (`MATERIALTEST_CODE`),
  KEY `FK7702C0AADFC609F` (`CODE`),
  KEY `FK7702C0AAAFBB68A5` (`MATERIAL_CODE`),
  CONSTRAINT `FK7702C0AAAFBB68A5` FOREIGN KEY (`MATERIAL_CODE`) REFERENCES `material_master` (`MATERIAL_CODE`),
  CONSTRAINT `FK7702C0AADFC609F` FOREIGN KEY (`CODE`) REFERENCES `validation_master` (`CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `material_test`
--

LOCK TABLES `material_test` WRITE;
/*!40000 ALTER TABLE `material_test` DISABLE KEYS */;
/*!40000 ALTER TABLE `material_test` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `heatchart_code_sequence`
--

DROP TABLE IF EXISTS `heatchart_code_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `heatchart_code_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `heatchart_code_sequence`
--

LOCK TABLES `heatchart_code_sequence` WRITE;
/*!40000 ALTER TABLE `heatchart_code_sequence` DISABLE KEYS */;
INSERT INTO `heatchart_code_sequence` VALUES (1000);
/*!40000 ALTER TABLE `heatchart_code_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `materialtest_code_sequence`
--

DROP TABLE IF EXISTS `materialtest_code_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `materialtest_code_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `materialtest_code_sequence`
--

LOCK TABLES `materialtest_code_sequence` WRITE;
/*!40000 ALTER TABLE `materialtest_code_sequence` DISABLE KEYS */;
INSERT INTO `materialtest_code_sequence` VALUES (1000);
/*!40000 ALTER TABLE `materialtest_code_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `heatchart_master`
--

DROP TABLE IF EXISTS `heatchart_master`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `heatchart_master` (
  `HEATCHART_CODE` int(11) NOT NULL,
  `HEATCHART_NUMBER` varchar(255) DEFAULT NULL,
  `CREATED_BY` varchar(255) DEFAULT NULL,
  `CREATED_DATE` date DEFAULT NULL,
  `CUSTOMER` varchar(255) DEFAULT NULL,
  `DRAWING_NUMBER` varchar(255) DEFAULT NULL,
  `EQUIPMENT` varchar(255) DEFAULT NULL,
  `PO_DETAILS` varchar(255) DEFAULT NULL,
  `STATUS` varchar(255) DEFAULT NULL,
  `SURVEYOR` varchar(255) DEFAULT NULL,
  `TAG_NUMBER` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`HEATCHART_CODE`),
  UNIQUE KEY `HEATCHART_CODE` (`HEATCHART_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `heatchart_master`
--

LOCK TABLES `heatchart_master` WRITE;
/*!40000 ALTER TABLE `heatchart_master` DISABLE KEYS */;
/*!40000 ALTER TABLE `heatchart_master` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `code_number_sequence`
--

DROP TABLE IF EXISTS `code_number_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `code_number_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `code_number_sequence`
--

LOCK TABLES `code_number_sequence` WRITE;
/*!40000 ALTER TABLE `code_number_sequence` DISABLE KEYS */;
INSERT INTO `code_number_sequence` VALUES (1000);
/*!40000 ALTER TABLE `code_number_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_code_sequence`
--

DROP TABLE IF EXISTS `user_code_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_code_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_code_sequence`
--

LOCK TABLES `user_code_sequence` WRITE;
/*!40000 ALTER TABLE `user_code_sequence` DISABLE KEYS */;
INSERT INTO `user_code_sequence` VALUES (1000);
/*!40000 ALTER TABLE `user_code_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `code_master`
--

DROP TABLE IF EXISTS `code_master`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `code_master` (
  `CODE_NUMBER` int(11) NOT NULL,
  `CODE_DATA_TYPE` varchar(255) DEFAULT NULL,
  `CODE_DESC` varchar(255) DEFAULT NULL,
  `CODE_NAME` varchar(255) DEFAULT NULL,
  `CODE_VALUE` varchar(255) DEFAULT NULL,
  `CREATED_BY` varchar(255) DEFAULT NULL,
  `CREATED_DATE` date DEFAULT NULL,
  `STATUS` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`CODE_NUMBER`),
  UNIQUE KEY `CODE_NUMBER` (`CODE_NUMBER`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `code_master`
--

LOCK TABLES `code_master` WRITE;
/*!40000 ALTER TABLE `code_master` DISABLE KEYS */;
/*!40000 ALTER TABLE `code_master` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `menu_master`
--

DROP TABLE IF EXISTS `menu_master`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `menu_master` (
  `MENU_CODE` int(11) NOT NULL,
  `CREATED_BY` varchar(255) DEFAULT NULL,
  `CREATED_DATE` date DEFAULT NULL,
  `DISPLAY_TEXT` varchar(255) DEFAULT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `MENU_ORDER` int(11) DEFAULT NULL,
  `STATUS` varchar(255) DEFAULT NULL,
  `PARENT_MENU` int(11) DEFAULT NULL,
  PRIMARY KEY (`MENU_CODE`),
  UNIQUE KEY `MENU_CODE` (`MENU_CODE`),
  KEY `FK73C31542E25A402C` (`PARENT_MENU`),
  CONSTRAINT `FK73C31542E25A402C` FOREIGN KEY (`PARENT_MENU`) REFERENCES `menu_master` (`MENU_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menu_master`
--

LOCK TABLES `menu_master` WRITE;
/*!40000 ALTER TABLE `menu_master` DISABLE KEYS */;
INSERT INTO `menu_master` VALUES (1000,'SYSTEM','2013-02-27','File','File',1,'TRUE',NULL),(1001,'SYSTEM','2013-02-27','Edit','Edit',2,'TRUE',NULL);
/*!40000 ALTER TABLE `menu_master` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `validation_code_sequence`
--

DROP TABLE IF EXISTS `validation_code_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `validation_code_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `validation_code_sequence`
--

LOCK TABLES `validation_code_sequence` WRITE;
/*!40000 ALTER TABLE `validation_code_sequence` DISABLE KEYS */;
INSERT INTO `validation_code_sequence` VALUES (1000);
/*!40000 ALTER TABLE `validation_code_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `id_gen`
--

DROP TABLE IF EXISTS `id_gen`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `id_gen` (
  `GEN_NAME` varchar(255) DEFAULT NULL,
  `GEN_VAL` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `id_gen`
--

LOCK TABLES `id_gen` WRITE;
/*!40000 ALTER TABLE `id_gen` DISABLE KEYS */;
/*!40000 ALTER TABLE `id_gen` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_master`
--

DROP TABLE IF EXISTS `user_master`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_master` (
  `USER_CODE` int(11) NOT NULL,
  `CREATED_BY` varchar(255) DEFAULT NULL,
  `CREATED_DATE` date DEFAULT NULL,
  `PASSWORD` varchar(255) DEFAULT NULL,
  `STATUS` varchar(255) DEFAULT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `ROLE` int(11) DEFAULT NULL,
  PRIMARY KEY (`USER_CODE`),
  UNIQUE KEY `USER_CODE` (`USER_CODE`),
  KEY `FK8E16D56E033328` (`ROLE`),
  CONSTRAINT `FK8E16D56E033328` FOREIGN KEY (`ROLE`) REFERENCES `validation_master` (`CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_master`
--

LOCK TABLES `user_master` WRITE;
/*!40000 ALTER TABLE `user_master` DISABLE KEYS */;
INSERT INTO `user_master` VALUES (1000,'SYSTEM','2013-02-27','1','TRUE','Admin',1000),(1001,'SYSTEM','2013-02-27','1','TRUE','Amit',1001);
/*!40000 ALTER TABLE `user_master` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `material_master`
--

DROP TABLE IF EXISTS `material_master`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `material_master` (
  `MATERIAL_CODE` int(11) NOT NULL,
  `CREATED_BY` varchar(255) DEFAULT NULL,
  `CREATED_DATE` date DEFAULT NULL,
  `CT_NUMBER` varchar(255) DEFAULT NULL,
  `HEAT_NUMBER` varchar(255) DEFAULT NULL,
  `PLATE_NUMBER` varchar(255) DEFAULT NULL,
  `QUANTITY` int(11) DEFAULT NULL,
  `SIZE` varchar(255) DEFAULT NULL,
  `STATUS` varchar(255) DEFAULT NULL,
  `CODE` int(11) DEFAULT NULL,
  PRIMARY KEY (`MATERIAL_CODE`),
  UNIQUE KEY `MATERIAL_CODE` (`MATERIAL_CODE`),
  KEY `FKB52AFC7ADFC609F` (`CODE`),
  CONSTRAINT `FKB52AFC7ADFC609F` FOREIGN KEY (`CODE`) REFERENCES `validation_master` (`CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `material_master`
--

LOCK TABLES `material_master` WRITE;
/*!40000 ALTER TABLE `material_master` DISABLE KEYS */;
/*!40000 ALTER TABLE `material_master` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'metalplantsspring'
--

--
-- Dumping routines for database 'metalplantsspring'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2013-03-06 19:04:21
