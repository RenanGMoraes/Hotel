-- MySQL dump 10.13  Distrib 5.5.28, for Win32 (x86)
--
-- Host: localhost    Database: hotel
-- ------------------------------------------------------
-- Server version	5.5.28

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
-- Table structure for table `estadia`
--

DROP TABLE IF EXISTS `estadia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `estadia` (
  `pk_idEstadia` tinyint(4) NOT NULL AUTO_INCREMENT,
  `fk_idReserva` tinyint(4) NOT NULL,
  `dataEntrada` date NOT NULL,
  `dataSaida` date DEFAULT NULL,
  `valorTotalServicos` decimal(5,2) DEFAULT '0.00',
  PRIMARY KEY (`pk_idEstadia`),
  UNIQUE KEY `fk_idReserva` (`fk_idReserva`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estadia`
--

LOCK TABLES `estadia` WRITE;
/*!40000 ALTER TABLE `estadia` DISABLE KEYS */;
INSERT INTO `estadia` VALUES (1,2,'2010-10-09',NULL,0.00);
/*!40000 ALTER TABLE `estadia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hospedes`
--

DROP TABLE IF EXISTS `hospedes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hospedes` (
  `id` tinyint(4) NOT NULL AUTO_INCREMENT,
  `nome` varchar(50) NOT NULL,
  `cpf` varchar(14) NOT NULL,
  `telefone` varchar(16) NOT NULL,
  `email` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `cpf` (`cpf`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hospedes`
--

LOCK TABLES `hospedes` WRITE;
/*!40000 ALTER TABLE `hospedes` DISABLE KEYS */;
INSERT INTO `hospedes` VALUES (1,'Alecs','4235346123','11951737124','alecs@gmail.com'),(2,'Amem','7891346123','11996718951','amem@gmail.com'),(3,'Gambino','8987656989','11990962751','redbone@gmail.com'),(4,'Helena','1232542353','11243252313','wantme@gmail.com'),(5,'Closed','9865678912','11432534121','mind@gmail.com'),(6,'Jurema','9876544524','11872615361','futuro@gmail.com'),(7,'Mariazinha','8562781094','17951737124','mariazinha@gmail.com'),(8,'Barbara','8987656251','11990962718','barbara@gmail.com'),(10,'Testador','1','1','1');
/*!40000 ALTER TABLE `hospedes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `quartos`
--

DROP TABLE IF EXISTS `quartos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `quartos` (
  `id` tinyint(4) NOT NULL AUTO_INCREMENT,
  `tipo` enum('SOLTEIRO','DUPLO_SOLTEIRO','CASAL') NOT NULL,
  `disponivel` tinyint(1) NOT NULL,
  `valor` decimal(10,2) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `quartos`
--

LOCK TABLES `quartos` WRITE;
/*!40000 ALTER TABLE `quartos` DISABLE KEYS */;
INSERT INTO `quartos` VALUES (1,'SOLTEIRO',0,100.00),(2,'DUPLO_SOLTEIRO',0,100.00),(4,'SOLTEIRO',0,100.00),(5,'CASAL',1,200.00),(6,'CASAL',1,100.00),(7,'CASAL',1,100.00),(8,'DUPLO_SOLTEIRO',0,100.00),(9,'DUPLO_SOLTEIRO',1,100.00),(10,'DUPLO_SOLTEIRO',1,50.00);
/*!40000 ALTER TABLE `quartos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservas`
--

DROP TABLE IF EXISTS `reservas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reservas` (
  `pk_idReserva` tinyint(4) NOT NULL DEFAULT '0',
  `fk_idHospede` tinyint(4) NOT NULL DEFAULT '0',
  `fk_idQuarto` tinyint(4) NOT NULL DEFAULT '0',
  `dataEntrada` date NOT NULL,
  `dataSaida` date NOT NULL,
  PRIMARY KEY (`pk_idReserva`,`fk_idHospede`,`fk_idQuarto`),
  KEY `fk_idQuarto` (`fk_idQuarto`),
  KEY `fk_idHospede` (`fk_idHospede`),
  CONSTRAINT `reservas_ibfk_1` FOREIGN KEY (`fk_idQuarto`) REFERENCES `quartos` (`id`),
  CONSTRAINT `reservas_ibfk_2` FOREIGN KEY (`fk_idHospede`) REFERENCES `hospedes` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservas`
--

LOCK TABLES `reservas` WRITE;
/*!40000 ALTER TABLE `reservas` DISABLE KEYS */;
INSERT INTO `reservas` VALUES (1,1,1,'2019-05-21','2019-05-31'),(2,2,2,'2010-10-10','2010-10-10');
/*!40000 ALTER TABLE `reservas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary table structure for view `tabelareservas`
--

DROP TABLE IF EXISTS `tabelareservas`;
/*!50001 DROP VIEW IF EXISTS `tabelareservas`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `tabelareservas` (
  `pk_idReserva` tinyint NOT NULL,
  `cpfHospede` tinyint NOT NULL,
  `fk_idQuarto` tinyint NOT NULL,
  `dataEntrada` tinyint NOT NULL,
  `dataSaida` tinyint NOT NULL
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Final view structure for view `tabelareservas`
--

/*!50001 DROP TABLE IF EXISTS `tabelareservas`*/;
/*!50001 DROP VIEW IF EXISTS `tabelareservas`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `tabelareservas` AS select `r`.`pk_idReserva` AS `pk_idReserva`,`h`.`cpf` AS `cpfHospede`,`r`.`fk_idQuarto` AS `fk_idQuarto`,`r`.`dataEntrada` AS `dataEntrada`,`r`.`dataSaida` AS `dataSaida` from (`reservas` `r` join `hospedes` `h`) where (`r`.`fk_idHospede` = `h`.`id`) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-06-09 20:20:10
