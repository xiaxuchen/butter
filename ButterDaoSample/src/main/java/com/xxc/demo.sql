-- MySQL dump 10.13  Distrib 5.7.24, for Win64 (x86_64)
--
-- Host: localhost    Database: demo
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
-- Table structure for table `msg`
--

DROP TABLE IF EXISTS `msg`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `msg` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(500) NOT NULL,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `username` char(16) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `username` (`username`),
  CONSTRAINT `msg_ibfk_1` FOREIGN KEY (`username`) REFERENCES `user` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `msg`
--

LOCK TABLES `msg` WRITE;
/*!40000 ALTER TABLE `msg` DISABLE KEYS */;
INSERT INTO `msg` VALUES (4,'xzc is best','2019-06-03 06:59:47','xzc'),(5,'xzc is best','2019-06-03 07:37:13','xxc'),(6,'xzc is best','2019-06-03 07:37:21','xzc'),(7,'1234576','2019-06-03 12:40:07','pg'),(8,'hello world','2019-06-03 12:50:49','xxc'),(9,'hello world','2019-06-03 12:52:11','xxc'),(10,'hello world','2019-06-03 13:09:39','xxc'),(11,'hello world','2019-06-03 13:18:49','xxc'),(12,'hello world','2019-06-03 13:18:50','xxc'),(13,'hello world','2019-06-03 13:19:33','xxc'),(14,'hello world','2019-06-03 13:19:33','xxc'),(15,'hello world','2019-06-03 13:21:49','xxc'),(16,'hello world','2019-06-03 13:21:49','xxc'),(17,'hello world','2019-06-03 13:35:34','xxc'),(18,'hello world','2019-06-03 13:35:34','xxc'),(19,'hello world','2019-06-04 07:03:40','xxc'),(20,'hello world','2019-06-04 07:03:40','xxc'),(21,'hello world','2019-06-04 07:05:25','xxc'),(22,'hello world','2019-06-04 07:05:25','xxc'),(23,'hello world','2019-06-04 07:06:36','xxc'),(24,'hello world','2019-06-04 07:06:36','xxc'),(25,'hello world','2019-06-04 07:06:51','xxc'),(26,'hello world','2019-06-04 07:06:51','xxc'),(27,'hello world','2019-06-04 07:10:50','xxc'),(28,'hello world','2019-06-04 07:10:50','xxc'),(29,'hello world','2019-06-04 07:12:43','xxc'),(30,'hello world','2019-06-04 07:12:44','xxc'),(31,'hello world','2019-06-04 07:14:12','xxc'),(32,'hello world','2019-06-04 07:14:12','xxc');
/*!40000 ALTER TABLE `msg` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `username` char(16) NOT NULL,
  `pwd` char(12) NOT NULL,
  `sex` enum('man','woman') DEFAULT NULL,
  `phone` int(11) DEFAULT NULL,
  PRIMARY KEY (`username`),
  UNIQUE KEY `phone` (`phone`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('p','1234576',NULL,NULL),('pg','1234576',NULL,NULL),('pxp','1234576',NULL,NULL),('pxpx','1234576',NULL,NULL),('xf','123456',NULL,NULL),('xjp','123456',NULL,NULL),('xxc','123456','man',NULL),('xzc','123456',NULL,NULL);
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

-- Dump completed on 2019-06-04 15:16:31
