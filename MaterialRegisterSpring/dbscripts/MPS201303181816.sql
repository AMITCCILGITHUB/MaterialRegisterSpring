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
  KEY `FK2F81A76B17FECC1C` (`CODE`),
  CONSTRAINT `FK2F81A76B17FECC1C` FOREIGN KEY (`CODE`) REFERENCES `role_master` (`ROLE_CODE`),
  CONSTRAINT `FK2F81A76B4AD27525` FOREIGN KEY (`MENU_CODE`) REFERENCES `menu_master` (`MENU_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menu_role_master`
--

LOCK TABLES `menu_role_master` WRITE;
/*!40000 ALTER TABLE `menu_role_master` DISABLE KEYS */;
INSERT INTO `menu_role_master` VALUES (1001,1001),(1002,1001),(1003,1001),(1004,1001),(1005,1001),(1006,1001),(1002,1002),(1003,1002),(1005,1002),(1006,1002),(1002,1003),(1005,1003),(1007,1001),(1008,1001),(1009,1001),(1010,1001),(1011,1001),(1012,1001),(1008,1002),(1009,1002),(1012,1002),(1008,1003),(1013,1001),(1014,1001),(1015,1001),(1016,1001),(1016,1002),(1015,1002),(1014,1002),(1014,1003),(1016,1003);
/*!40000 ALTER TABLE `menu_role_master` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_master`
--

DROP TABLE IF EXISTS `role_master`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role_master` (
  `ROLE_CODE` int(11) NOT NULL,
  `CREATED_BY` varchar(255) DEFAULT NULL,
  `CREATED_DATE` date DEFAULT NULL,
  `DISPLAY_TEXT` varchar(255) DEFAULT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `STATUS` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ROLE_CODE`),
  UNIQUE KEY `ROLE_CODE` (`ROLE_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_master`
--

LOCK TABLES `role_master` WRITE;
/*!40000 ALTER TABLE `role_master` DISABLE KEYS */;
INSERT INTO `role_master` VALUES (1001,'SYSTEM','2013-03-18','Administrator','Admin','TRUE'),(1002,'SYSTEM','2013-03-18','Maker','Maker','TRUE'),(1003,'SYSTEM','2013-03-18','Checker','Checker','TRUE');
/*!40000 ALTER TABLE `role_master` ENABLE KEYS */;
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
  CONSTRAINT `FKD66A858EAFBB68A5` FOREIGN KEY (`MATERIAL_CODE`) REFERENCES `material_master` (`MATERIAL_CODE`),
  CONSTRAINT `FKD66A858E568BDF9D` FOREIGN KEY (`TEST_CODE`) REFERENCES `material_test` (`MATERIALTEST_CODE`),
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
INSERT INTO `code_master` VALUES (1001,'INTEGER','Default Year','Default_Year','2013','SYSTEM','2013-03-18','TRUE');
/*!40000 ALTER TABLE `code_master` ENABLE KEYS */;
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
  CONSTRAINT `FK7702C0AADFC609F` FOREIGN KEY (`CODE`) REFERENCES `validation_master` (`VALIDATION_CODE`)
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
  PRIMARY KEY (`MENU_CODE`),
  UNIQUE KEY `MENU_CODE` (`MENU_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menu_master`
--

LOCK TABLES `menu_master` WRITE;
/*!40000 ALTER TABLE `menu_master` DISABLE KEYS */;
INSERT INTO `menu_master` VALUES (1001,'SYSTEM','2013-03-18','Add User','Add User',1001,'TRUE'),(1002,'SYSTEM','2013-03-18','View User','View User',1002,'TRUE'),(1003,'SYSTEM','2013-03-18','Edit User','Edit User',1003,'TRUE'),(1004,'SYSTEM','2013-03-18','Add Material','Add Material',1004,'TRUE'),(1005,'SYSTEM','2013-03-18','View Material','View Material',1005,'TRUE'),(1006,'SYSTEM','2013-03-18','Edit Material','Edit Material',1006,'TRUE'),(1007,'SYSTEM','2013-03-18','Add HeatChart','Add HeatChart',1007,'TRUE'),(1008,'SYSTEM','2013-03-18','View HeatChart','View HeatChart',1008,'TRUE'),(1009,'SYSTEM','2013-03-18','Edit HeatChart','Edit HeatChart',1009,'TRUE'),(1010,'SYSTEM','2013-03-18','Database Backup','Database Backup',1010,'TRUE'),(1011,'SYSTEM','2013-03-18','Settings','Settings',1011,'TRUE'),(1012,'SYSTEM','2013-03-18','Change Password','Change Password',1012,'TRUE'),(1013,'SYSTEM','2013-03-18','Add Validation','Add Validation',1013,'TRUE'),(1014,'SYSTEM','2013-03-18','View Validation','View Validation',1014,'TRUE'),(1015,'SYSTEM','2013-03-18','Edit Validation','Edit Validation',1015,'TRUE'),(1016,'SYSTEM','2013-03-18','View Material Register','View Material Register',1016,'TRUE');
/*!40000 ALTER TABLE `menu_master` ENABLE KEYS */;
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
-- Table structure for table `validation_master`
--

DROP TABLE IF EXISTS `validation_master`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `validation_master` (
  `VALIDATION_CODE` int(11) NOT NULL,
  `CREATED_BY` varchar(255) DEFAULT NULL,
  `CREATED_DATE` date DEFAULT NULL,
  `DISPLAY_TEXT` varchar(255) DEFAULT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `STATUS` varchar(255) DEFAULT NULL,
  `TYPE` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`VALIDATION_CODE`),
  UNIQUE KEY `VALIDATION_CODE` (`VALIDATION_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `validation_master`
--

LOCK TABLES `validation_master` WRITE;
/*!40000 ALTER TABLE `validation_master` DISABLE KEYS */;
/*!40000 ALTER TABLE `validation_master` ENABLE KEYS */;
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
  KEY `FK8E16D5618059EA5` (`ROLE`),
  CONSTRAINT `FK8E16D5618059EA5` FOREIGN KEY (`ROLE`) REFERENCES `role_master` (`ROLE_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_master`
--

LOCK TABLES `user_master` WRITE;
/*!40000 ALTER TABLE `user_master` DISABLE KEYS */;
INSERT INTO `user_master` VALUES (1001,'SYSTEM','2013-03-18','1','TRUE','Admin',1001),(1002,'SYSTEM','2013-03-18','1','TRUE','Maker',1002),(1003,'SYSTEM','2013-03-18','1','TRUE','Checker',1003);
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
  CONSTRAINT `FKB52AFC7ADFC609F` FOREIGN KEY (`CODE`) REFERENCES `validation_master` (`VALIDATION_CODE`)
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

-- Dump completed on 2013-03-18 18:17:12
