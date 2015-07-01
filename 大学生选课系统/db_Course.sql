CREATE DATABASE  IF NOT EXISTS `db_course` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `db_course`;
-- MySQL dump 10.13  Distrib 5.6.13, for Win32 (x86)
--
-- Host: localhost    Database: db_course
-- ------------------------------------------------------
-- Server version	5.5.17

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
-- Table structure for table `tb_chooseclass`
--

DROP TABLE IF EXISTS `tb_chooseclass`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_chooseclass` (
  `ChooseClassNo` int(11) NOT NULL,
  `ChooseClassName` varchar(20) NOT NULL,
  `CNo` int(11) NOT NULL,
  PRIMARY KEY (`ChooseClassNo`),
  KEY `CNo_ChooseClass_idx` (`CNo`),
  CONSTRAINT `CNo_ChooseClass` FOREIGN KEY (`CNo`) REFERENCES `tb_course` (`CNo`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_chooseclass`
--

LOCK TABLES `tb_chooseclass` WRITE;
/*!40000 ALTER TABLE `tb_chooseclass` DISABLE KEYS */;
INSERT INTO `tb_chooseclass` VALUES (1111,'javaweb',1),(2222,'javaweb2',2),(3333,'操作系统',3);
/*!40000 ALTER TABLE `tb_chooseclass` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_course`
--

DROP TABLE IF EXISTS `tb_course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_course` (
  `CNo` int(11) NOT NULL,
  `CName` varchar(20) NOT NULL,
  `TNo` varchar(20) NOT NULL,
  PRIMARY KEY (`CNo`),
  KEY `TNo_Course_idx` (`TNo`),
  CONSTRAINT `TNo_Course` FOREIGN KEY (`TNo`) REFERENCES `tb_teacher` (`TNo`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_course`
--

LOCK TABLES `tb_course` WRITE;
/*!40000 ALTER TABLE `tb_course` DISABLE KEYS */;
INSERT INTO `tb_course` VALUES (1,'javaweb','1111'),(2,'javaweb2','1112'),(3,'操作系统','1113');
/*!40000 ALTER TABLE `tb_course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_manager`
--

DROP TABLE IF EXISTS `tb_manager`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_manager` (
  `ManagerNo` varchar(20) NOT NULL,
  `ManagerName` varchar(10) NOT NULL,
  `ManagerSex` char(2) NOT NULL,
  `PassWord` varchar(20) NOT NULL,
  PRIMARY KEY (`ManagerNo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_manager`
--

LOCK TABLES `tb_manager` WRITE;
/*!40000 ALTER TABLE `tb_manager` DISABLE KEYS */;
INSERT INTO `tb_manager` VALUES ('admin','admin','男','admin');
/*!40000 ALTER TABLE `tb_manager` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_sc`
--

DROP TABLE IF EXISTS `tb_sc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_sc` (
  `SNo` varchar(20) NOT NULL,
  `ChooseClassNo` int(11) NOT NULL,
  `CNo` int(11) NOT NULL,
  KEY `ChooseClassNo_SC_idx` (`ChooseClassNo`),
  KEY `CNo_SC_idx` (`CNo`),
  KEY `SNo_SC` (`SNo`),
  CONSTRAINT `SNo_SC` FOREIGN KEY (`SNo`) REFERENCES `tb_student` (`SNo`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `ChooseClassNo_SC` FOREIGN KEY (`ChooseClassNo`) REFERENCES `tb_chooseclass` (`ChooseClassNo`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `CNo_SC` FOREIGN KEY (`CNo`) REFERENCES `tb_course` (`CNo`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_sc`
--

LOCK TABLES `tb_sc` WRITE;
/*!40000 ALTER TABLE `tb_sc` DISABLE KEYS */;
INSERT INTO `tb_sc` VALUES ('20121521',1111,1);
/*!40000 ALTER TABLE `tb_sc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_speclass`
--

DROP TABLE IF EXISTS `tb_speclass`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_speclass` (
  `SClassNo` int(11) NOT NULL,
  `SClassName` varchar(30) NOT NULL,
  PRIMARY KEY (`SClassNo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_speclass`
--

LOCK TABLES `tb_speclass` WRITE;
/*!40000 ALTER TABLE `tb_speclass` DISABLE KEYS */;
INSERT INTO `tb_speclass` VALUES (1201,'软件1201'),(1202,'软件1202'),(1203,'软件1203');
/*!40000 ALTER TABLE `tb_speclass` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_student`
--

DROP TABLE IF EXISTS `tb_student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_student` (
  `SNo` varchar(20) NOT NULL,
  `SName` varchar(20) NOT NULL,
  `SSex` char(2) DEFAULT NULL,
  `PassWord` char(4) NOT NULL,
  `SClassNo` int(11) NOT NULL,
  PRIMARY KEY (`SNo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_student`
--

LOCK TABLES `tb_student` WRITE;
/*!40000 ALTER TABLE `tb_student` DISABLE KEYS */;
INSERT INTO `tb_student` VALUES ('20121511','林虎','男','6666',1201),('20121512','曹恢武','男','6666',1201),('20121513','黄毅伟','男','6666',1201),('20121514','邓黎洋','男','6666',1201),('20121515','黄彩艳','女','6666',1201),('20121516','林丽娜','女','6666',1201),('20121521','李佳璇','男','6666',1201),('20121556','李四','男','6666',1202),('20121557','王五','女','6666',1203);
/*!40000 ALTER TABLE `tb_student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_teacher`
--

DROP TABLE IF EXISTS `tb_teacher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_teacher` (
  `TNo` varchar(20) NOT NULL,
  `TName` varchar(20) NOT NULL,
  `TSex` char(2) DEFAULT NULL,
  `PassWord` char(4) NOT NULL,
  PRIMARY KEY (`TNo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_teacher`
--

LOCK TABLES `tb_teacher` WRITE;
/*!40000 ALTER TABLE `tb_teacher` DISABLE KEYS */;
INSERT INTO `tb_teacher` VALUES ('1111','苏波','男','8888'),('1112','吕益平','男','8888'),('1113','李佳航','女','8888');
/*!40000 ALTER TABLE `tb_teacher` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-06-21 18:24:04
