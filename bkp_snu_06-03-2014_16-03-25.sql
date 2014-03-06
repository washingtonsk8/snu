-- MySQL dump 10.13  Distrib 5.6.15, for Win64 (x86_64)
--
-- Host: localhost    Database: snu
-- ------------------------------------------------------
-- Server version	5.6.15

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
-- Current Database: `snu`
--

/*!40000 DROP DATABASE IF EXISTS `snu`*/;

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `snu` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `snu`;

--
-- Table structure for table `documentomusica_listainvertida`
--

DROP TABLE IF EXISTS `documentomusica_listainvertida`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `documentomusica_listainvertida` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `frequencia_token` int(11) DEFAULT NULL,
  `documentomusica_id` bigint(20) DEFAULT NULL,
  `musica_id` bigint(20) DEFAULT NULL,
  `vocabulo_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKCE3E87CD22451D7` (`vocabulo_id`),
  CONSTRAINT `FKCE3E87CD22451D7` FOREIGN KEY (`vocabulo_id`) REFERENCES `documentomusica_vocabulario` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=284 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `documentomusica_listainvertida`
--

LOCK TABLES `documentomusica_listainvertida` WRITE;
/*!40000 ALTER TABLE `documentomusica_listainvertida` DISABLE KEYS */;
INSERT INTO `documentomusica_listainvertida` VALUES (1,1,1,1,1),(2,1,1,1,2),(3,1,1,1,3),(4,1,1,1,4),(5,1,1,1,5),(6,3,1,1,6),(7,1,1,1,7),(8,1,1,1,8),(9,1,1,1,9),(10,1,1,1,10),(11,1,1,1,11),(12,1,1,1,12),(13,3,1,1,13),(14,1,1,1,14),(15,1,1,1,15),(16,1,1,1,16),(17,1,1,1,17),(18,1,1,1,18),(19,1,1,1,19),(20,1,1,1,20),(21,3,1,1,21),(22,2,1,1,22),(23,1,1,1,23),(24,1,1,1,24),(25,1,1,1,25),(26,1,1,1,26),(27,1,1,1,27),(28,1,1,1,28),(29,1,1,1,29),(30,1,1,1,30),(31,1,1,1,31),(32,1,1,1,32),(33,1,1,1,33),(34,1,1,1,34),(35,1,1,1,35),(36,2,1,1,36),(37,1,1,1,37),(38,2,1,1,38),(39,4,1,1,39),(40,1,1,1,40),(41,1,1,1,41),(42,1,1,1,42),(43,11,1,1,43),(44,1,1,1,44),(45,1,1,1,45),(46,1,1,1,46),(47,1,1,1,47),(48,4,1,1,48),(49,2,1,1,49),(50,1,1,1,50),(51,1,1,1,51),(52,1,1,1,52),(53,1,1,1,53),(54,2,1,1,54),(55,2,1,1,55),(56,1,1,1,56),(57,1,1,1,57),(58,2,1,1,58),(59,2,1,1,59),(60,1,1,1,60),(61,1,1,1,61),(62,2,1,1,62),(63,1,1,1,63),(64,1,1,1,64),(65,1,1,1,65),(66,1,1,1,66),(67,1,1,1,67),(68,10,2,2,68),(69,2,2,2,4),(70,1,2,2,5),(71,1,2,2,69),(72,1,2,2,70),(73,9,2,2,9),(74,1,2,2,71),(75,4,2,2,72),(76,1,2,2,73),(77,2,2,2,74),(78,2,2,2,75),(79,1,2,2,76),(80,2,2,2,77),(81,1,2,2,78),(82,2,2,2,79),(83,3,2,2,80),(84,2,2,2,81),(85,4,2,2,82),(86,1,2,2,83),(87,1,2,2,22),(88,3,2,2,84),(89,1,2,2,24),(90,1,2,2,27),(91,3,2,2,85),(92,2,2,2,86),(93,2,2,2,87),(94,1,2,2,88),(95,1,2,2,89),(96,1,2,2,36),(97,4,2,2,90),(98,1,2,2,91),(99,1,2,2,92),(100,8,2,2,39),(101,2,2,2,93),(102,4,2,2,43),(103,2,2,2,94),(104,1,2,2,95),(105,1,2,2,96),(106,3,2,2,97),(107,4,2,2,48),(108,2,2,2,49),(109,1,2,2,98),(110,1,2,2,99),(111,1,2,2,52),(112,4,2,2,54),(113,2,2,2,100),(114,8,2,2,56),(115,1,2,2,58),(116,1,2,2,101),(117,1,2,2,102),(118,7,2,2,103),(119,2,2,2,104),(120,1,2,2,105),(161,1,3,3,1),(162,2,3,3,106),(163,1,3,3,2),(164,1,3,3,107),(165,1,3,3,32),(166,1,3,3,87),(167,1,3,3,108),(168,1,3,3,109),(169,1,3,3,110),(170,1,3,3,111),(171,1,3,3,6),(172,1,3,3,112),(173,1,3,3,113),(174,1,3,3,114),(175,1,3,3,115),(176,1,3,3,116),(177,1,3,3,117),(178,1,3,3,119),(179,1,3,3,118),(180,1,3,3,120),(181,1,3,3,121),(182,1,3,3,122),(183,1,3,3,123),(184,1,3,3,11),(185,1,3,3,124),(186,1,3,3,12),(187,1,3,3,125),(188,1,3,3,50),(189,1,3,3,51),(190,1,3,3,126),(191,1,3,3,52),(192,1,3,3,21),(193,1,3,3,56),(194,1,3,3,127),(195,1,3,3,128),(196,3,3,3,129),(197,1,3,3,58),(198,1,3,3,130),(199,1,3,3,131),(200,1,7,7,132),(201,1,7,7,5),(202,2,7,7,111),(203,1,7,7,8),(204,1,7,7,133),(205,3,7,7,114),(206,1,7,7,134),(207,1,7,7,135),(208,1,7,7,136),(209,5,7,7,137),(210,1,7,7,138),(211,1,7,7,139),(212,1,7,7,19),(213,2,7,7,140),(214,1,7,7,141),(215,1,7,7,142),(216,1,7,7,128),(217,1,7,7,143),(218,1,7,7,144),(219,3,7,7,145),(220,1,7,7,146),(221,1,7,7,147),(222,2,7,7,32),(223,1,7,7,148),(224,1,7,7,149),(225,2,7,7,110),(226,1,7,7,150),(227,1,7,7,151),(228,1,7,7,152),(229,4,7,7,153),(230,3,7,7,116),(231,1,7,7,39),(232,1,7,7,154),(233,1,7,7,155),(234,3,7,7,156),(235,1,7,7,157),(236,1,7,7,43),(237,1,7,7,45),(238,1,7,7,47),(239,1,7,7,158),(240,1,7,7,48),(241,1,7,7,159),(242,1,7,7,160),(243,1,7,7,50),(244,3,7,7,161),(245,2,7,7,126),(246,1,7,7,162),(247,1,7,7,163),(248,1,7,7,56),(249,2,7,7,164),(250,1,7,7,165),(251,2,7,7,166),(252,1,7,7,101),(253,3,7,7,167),(254,1,7,7,168),(255,2,7,7,169),(256,1,8,8,170),(257,2,NULL,NULL,1),(258,3,NULL,NULL,171),(259,10,NULL,NULL,172),(260,1,NULL,NULL,5),(261,1,NULL,NULL,151),(262,1,NULL,NULL,173),(263,2,NULL,NULL,174),(264,1,NULL,NULL,175),(265,1,NULL,NULL,176),(266,1,NULL,NULL,177),(267,1,NULL,NULL,45),(268,3,NULL,NULL,178),(269,1,NULL,NULL,96),(270,3,NULL,NULL,179),(271,2,NULL,NULL,81),(272,1,NULL,NULL,50),(273,1,NULL,NULL,21),(274,1,NULL,NULL,180),(275,3,NULL,NULL,181),(276,1,NULL,NULL,56),(277,3,NULL,NULL,59),(278,1,NULL,NULL,182),(279,1,NULL,NULL,166),(280,3,NULL,NULL,84),(281,3,NULL,NULL,183),(282,3,NULL,NULL,66),(283,3,NULL,NULL,30);
/*!40000 ALTER TABLE `documentomusica_listainvertida` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `documentomusica_vocabulario`
--

DROP TABLE IF EXISTS `documentomusica_vocabulario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `documentomusica_vocabulario` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `token` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=184 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `documentomusica_vocabulario`
--

LOCK TABLES `documentomusica_vocabulario` WRITE;
/*!40000 ALTER TABLE `documentomusica_vocabulario` DISABLE KEYS */;
INSERT INTO `documentomusica_vocabulario` VALUES (1,'tem'),(2,'aqu'),(3,'tenh'),(4,'derram'),(5,'no'),(6,'te'),(7,'ande'),(8,'long'),(9,'foi'),(10,'beij'),(11,'lug'),(12,'pod'),(13,'sou'),(14,'saudad'),(15,'soment'),(16,'mostr'),(17,'sol'),(18,'ansios'),(19,'olhos'),(20,'viv'),(21,'senhor'),(22,'fal'),(23,'tard'),(24,'hoj'),(25,'acolh'),(26,'temp'),(27,'refra'),(28,'rest'),(29,'got'),(30,'salt'),(31,'volt'),(32,'ond'),(33,'pergunt'),(34,'estrad'),(35,'olhand'),(36,'nad'),(37,'corr'),(38,'ver'),(39,'meu'),(40,'desist'),(41,'gast'),(42,'estou'),(43,'me'),(44,'mar'),(45,'coraca'),(46,'cont'),(47,'vou'),(48,'eu'),(49,'mim'),(50,'alegr'),(51,'pai'),(52,'intr'),(53,'amou'),(54,'quer'),(55,'sei'),(56,'amor'),(57,'rasg'),(58,'encontr'),(59,'num'),(60,'abrac'),(61,'decid'),(62,'esper'),(63,'muit'),(64,'meus'),(65,'bens'),(66,'ja'),(67,'x'),(68,'dor'),(69,'estiv'),(70,'sai'),(71,'jardim'),(72,'amad'),(73,'maos'),(74,'chag'),(75,'deix'),(76,'sair'),(77,'sempr'),(78,'final'),(79,'ª'),(80,'fer'),(81,'so'),(82,'sint'),(83,'mago'),(84,'jesus'),(85,'tao'),(86,'ououoh'),(87,'ser'),(88,'vi'),(89,'pra'),(90,'voc'),(91,'diant'),(92,'lind'),(93,'cruz'),(94,'del'),(95,'mal'),(96,'minh'),(97,'sang'),(98,'nunc'),(99,'ped'),(100,'saiss'),(101,'venc'),(102,'receb'),(103,'cur'),(104,'part'),(105,'entregu'),(106,'sorr'),(107,'maravilh'),(108,'for'),(109,'dois'),(110,'lanc'),(111,'deus'),(112,'ama'),(113,'diz'),(114,'olh'),(115,'unid'),(116,'par'),(117,'abr'),(118,'gent'),(119,'repent'),(120,'filh'),(121,'brot'),(122,'ve'),(123,'fund'),(124,'tristez'),(125,'alg'),(126,'mund'),(127,'brac'),(128,'tras'),(129,'bom'),(130,'la'),(131,'esta'),(132,'nasc'),(133,'cha'),(134,'daqu'),(135,'experiment'),(136,'estam'),(137,'vid'),(138,'mort'),(139,'faz'),(140,'dia'),(141,'agon'),(142,'tron'),(143,'escond'),(144,'ressuscit'),(145,'nov'),(146,'cabec'),(147,'perda'),(148,'compreend'),(149,'rend'),(150,'levant'),(151,'noss'),(152,'voe'),(153,'ceu'),(154,'entreg'),(155,'cert'),(156,'alt'),(157,'voo'),(158,'arrast'),(159,'depend'),(160,'ergu'),(161,'crist'),(162,'busca'),(163,'alcand'),(164,'vej'),(165,'irma'),(166,'vem'),(167,'pro'),(168,'venh'),(169,'busqu'),(170,'dfagdfgadf'),(171,'muralh'),(172,'alelu'),(173,'roch'),(174,'sentiment'),(175,'verdadeir'),(176,'salvaca'),(177,'paz'),(178,'conhec'),(179,'poss'),(180,'conden'),(181,'trop'),(182,'precios'),(183,'pis');
/*!40000 ALTER TABLE `documentomusica_vocabulario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `integrante`
--

DROP TABLE IF EXISTS `integrante`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `integrante` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `data_entrada` date DEFAULT NULL,
  `data_nascimento` date DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `endereco` varchar(255) DEFAULT NULL,
  `funcao_primaria` varchar(255) DEFAULT NULL,
  `funcao_secundaria` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `sexo` varchar(255) DEFAULT NULL,
  `telefone_celular` varchar(255) DEFAULT NULL,
  `telefone_comercial` varchar(255) DEFAULT NULL,
  `telefone_residencial` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `integrante`
--

LOCK TABLES `integrante` WRITE;
/*!40000 ALTER TABLE `integrante` DISABLE KEYS */;
INSERT INTO `integrante` VALUES (1,NULL,NULL,'','Rua Ipanema, 18 - Urca, BH','GUITARRISTA_SOLO',NULL,'Washington Luis de Souza Ramos','MASCULINO','','',''),(2,NULL,NULL,'manola@exemplo.com','Rua das Manolas','BAIXISTA',NULL,'Manola Teste','FEMININO','','',''),(3,'2012-01-01','1990-10-31','jakeilane@hotmail.com','Rua Angra dos Reis, 281 - Estrela Dalva, Contagem','CANTOR',NULL,'Jakeilane da Silva Rocha','FEMININO','(31) 85506510','','(31) 33974618'),(4,NULL,NULL,'','Rua de Teste para o Manolo','GUITARRISTA_BASE','GUITARRISTA_SOLO','Manolo do Teste','MASCULINO','','',''),(5,NULL,NULL,'','Teste','GUITARRISTA_SOLO',NULL,'Teste','MASCULINO','','',''),(6,'2008-04-20','1991-10-15','luanachaves19@gmail.com','Rua Dores de Campos, 131 casa 02, Santa Teresinha','BAIXISTA',NULL,'Luana Ferreira Chaves','FEMININO','(31)85507069','','(31)30474104'),(7,'2014-02-28','2007-01-31','','Teste','VIOLONISTA',NULL,'Jaquelino','MASCULINO','34534535','34534534','34534543'),(8,NULL,'2013-08-07','marina@exemplo.com','Endereço','GUITARRISTA_SOLO',NULL,'Marina','FEMININO','53453453245','3452345','435236234');
/*!40000 ALTER TABLE `integrante` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `missa`
--

DROP TABLE IF EXISTS `missa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `missa` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `data_acontecimento` date NOT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `descricao_email` longtext,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `missa`
--

LOCK TABLES `missa` WRITE;
/*!40000 ALTER TABLE `missa` DISABLE KEYS */;
INSERT INTO `missa` VALUES (1,'2014-03-01','',NULL),(2,'2014-03-01','','Boa noite,\n\nsegue abaixo a relação de músicas para a missa do dia 01/03/2014.\n\nAção de Graças:\n	Anjos de Resgate - Foi Por Você\n\nAtenciosamente,\n\nMinistério de Música'),(3,'2014-03-24','Missa 1 do Tempo Comum','Boa noite,\n\nsegue abaixo a relação de músicas para a missa do dia 24/03/2014.\n\nEntrada:\n	Anjos de Resgate - Foi Por Você\nAto Penitencial:\n	Vida Reluz - Sorrir é bom demais\nGlória:\n	Anjos de Resgate - gfjhgh\nAclamação ao Evangelho:\n	Celina Borges - Busque o alto\n\nAtenciosamente,\n\nMinistério de Música Nova Unção'),(4,'2014-03-16','Missa 2','Boa noite,\n\nsegue abaixo a relação de músicas para a missa do dia 16/03/2014.\n\nEspeciais:\n	Vida Reluz - Sorrir é bom demais\n	Celina Borges - Busque o alto\n\n\nAtenciosamente,\n\nMinistério de Música Nova Unção'),(5,'2014-03-16','gagddsfsd','Boa tarde,\n\nsegue abaixo a relação de músicas para a missa do dia 16/03/2014.\n\nEntrada:\n	Anjos de Resgate - Foi Por Você\nAclamação ao Evangelho:\n	Vida Reluz - Sorrir é bom demais\nOfertório:\n	Celina Borges - fgadgadfgd\nComunhão:\n	Anjos de Resgate - gfjhgh\nEspeciais:\n	Celina Borges - Busque o alto\n\n\nAtenciosamente,\n\nMinistério de Música Nova Unção.'),(6,'2014-03-23','Missa do 4º Domingo Comum','Bom dia,\n\nsegue abaixo a relação de músicas para a missa do dia 23/03/2014.\n\nEntrada:\n	Anjos de Resgate - Foi Por Você\nAto Penitencial:\n	Vida Reluz - Sorrir é bom demais\nGlória:\n	Anjos de Resgate - gfjhgh\nAclamação ao Evangelho:\n	Celina Borges - Busque o alto\nOfertório:\n	Celina Borges - fgadgadfgd\nPaz:\n	Novo Autor - Busque o Alto\nSanto:\n	Anjos de Resgate - Busque o Alto\nComunhão:\n	Anjos de Resgate - Busque o Alto\nAção de Graças:\n	Novo Autor - Teste\nFinal:\n	Vida Reluz - Busque o Alto\nEspeciais:\n	Desconhecido - Teste 2\n	Removido - Removida\n\n\nAtenciosamente,\n\nMinistério de Música Nova Unção.');
/*!40000 ALTER TABLE `missa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `missas_musicas`
--

DROP TABLE IF EXISTS `missas_musicas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `missas_musicas` (
  `missa_id` bigint(20) NOT NULL,
  `musica_id` bigint(20) NOT NULL,
  PRIMARY KEY (`missa_id`,`musica_id`),
  KEY `FK620B8006495641E1` (`musica_id`),
  KEY `FK620B8006BA374EA6` (`missa_id`),
  CONSTRAINT `FK620B8006495641E1` FOREIGN KEY (`musica_id`) REFERENCES `musica` (`id`),
  CONSTRAINT `FK620B8006BA374EA6` FOREIGN KEY (`missa_id`) REFERENCES `missa` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `missas_musicas`
--

LOCK TABLES `missas_musicas` WRITE;
/*!40000 ALTER TABLE `missas_musicas` DISABLE KEYS */;
/*!40000 ALTER TABLE `missas_musicas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `musica`
--

DROP TABLE IF EXISTS `musica`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `musica` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `afinacao` varchar(255) DEFAULT NULL,
  `link_video` varchar(255) DEFAULT NULL,
  `tom` varchar(255) DEFAULT NULL,
  `autor_id` bigint(20) DEFAULT NULL,
  `documentomusica_id` bigint(20) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK8A0BC2FCEB682C53` (`autor_id`),
  KEY `FK8A0BC2FC61BD7213` (`documentomusica_id`),
  CONSTRAINT `FK8A0BC2FC61BD7213` FOREIGN KEY (`documentomusica_id`) REFERENCES `musica_documento` (`id`),
  CONSTRAINT `FK8A0BC2FCEB682C53` FOREIGN KEY (`autor_id`) REFERENCES `musica_autores` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `musica`
--

LOCK TABLES `musica` WRITE;
/*!40000 ALTER TABLE `musica` DISABLE KEYS */;
INSERT INTO `musica` VALUES (2,'EADGBE','http://www.youtube.com/watch?v=0jRLzJQA0FM','G',3,2,'Foi Por Você'),(3,'EADGBE','','C',4,3,'Sorrir é bom demais'),(6,'EADGBE','','C_SUSTENIDO',3,6,'gfjhgh'),(7,'EADGBE','','G',5,7,'Busque o alto'),(8,'EADGBE','','C_SUSTENIDO',5,8,'fgadgadfgd'),(9,'EADGBE','','C',3,9,'Busque o Alto'),(10,'EADGBE','','C_SUSTENIDO',3,10,'Busque o Alto'),(11,'EADGBE','','D_SUSTENIDO',4,11,'Busque o Alto'),(12,'EADGBE','','D_SUSTENIDO',6,12,'Busque o Alto'),(13,'EADGBE','','G',6,13,'Teste'),(14,'DGCFAD','','G_SUSTENIDO',7,14,'Teste 2'),(15,'EADGBE','','G_SUSTENIDO',8,15,'Removida'),(16,'EADGBE','','F',9,17,'Musica teste');
/*!40000 ALTER TABLE `musica` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `musica_associacoes`
--

DROP TABLE IF EXISTS `musica_associacoes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `musica_associacoes` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `tom` varchar(255) DEFAULT NULL,
  `integrante_id` bigint(20) DEFAULT NULL,
  `musica_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4D5D02A4495641E1` (`musica_id`),
  KEY `FK4D5D02A428D0375E` (`integrante_id`),
  CONSTRAINT `FK4D5D02A428D0375E` FOREIGN KEY (`integrante_id`) REFERENCES `integrante` (`id`),
  CONSTRAINT `FK4D5D02A4495641E1` FOREIGN KEY (`musica_id`) REFERENCES `musica` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `musica_associacoes`
--

LOCK TABLES `musica_associacoes` WRITE;
/*!40000 ALTER TABLE `musica_associacoes` DISABLE KEYS */;
INSERT INTO `musica_associacoes` VALUES (1,'C',3,3),(2,'G',1,7),(3,'D_SUSTENIDO',3,7),(4,'C_SUSTENIDO',1,12),(5,'A',3,12),(6,'C_SUSTENIDO',6,12),(7,'E',7,12),(8,'C',3,13),(9,'F_SUSTENIDO',2,13),(10,'A',1,14),(11,'C',4,14),(12,'F',6,15),(13,'A',3,15),(14,'C_SUSTENIDO',3,16),(15,'F_SUSTENIDO',6,16),(16,'A',7,16);
/*!40000 ALTER TABLE `musica_associacoes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `musica_autores`
--

DROP TABLE IF EXISTS `musica_autores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `musica_autores` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `musica_autores`
--

LOCK TABLES `musica_autores` WRITE;
/*!40000 ALTER TABLE `musica_autores` DISABLE KEYS */;
INSERT INTO `musica_autores` VALUES (3,'Anjos de Resgate'),(4,'Vida Reluz'),(5,'Celina Borges'),(6,'Novo Autor'),(7,'Desconhecido'),(8,'Removido'),(9,'Jackie Autora');
/*!40000 ALTER TABLE `musica_autores` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `musica_documento`
--

DROP TABLE IF EXISTS `musica_documento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `musica_documento` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `conteudo` longtext,
  `frequencia_maxima_token` int(11) DEFAULT NULL,
  `quantidade_tokens` int(11) DEFAULT NULL,
  `introducao` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `musica_documento`
--

LOCK TABLES `musica_documento` WRITE;
/*!40000 ALTER TABLE `musica_documento` DISABLE KEYS */;
INSERT INTO `musica_documento` VALUES (2,'     @G             @D/F#      @Em7\nEncontrei-me com Jesus no jardim\n      @G           @D/F#    @Em7\nNunca vi nada tão lindo assim\n       @Am          @Am7/G       @D/F#\nMinhas dores entreguei em suas mãos\n    @Am        @Am7/G     @D/F#  @C/D\nE Jesus foi falando pra mim:\n       @G         @D/F#   @Em7\n\"Das feridas que eu recebi,\n      @G     @D/F#       @Em7\nNão saíram sangue, nem dor\n        @Am         @Am7/G     @D/F#\nFoi por isso que o mal eu venci\n       @Am         @Am7/G   @D/F#\nPorque delas só saía     amor,\n             @G        @D9/F#\nFoi sempre o meu amor\n    @G\nFoi por você\n             @G/F                     @C/E\nQue eu me deixei ser tão chagado e ferido\n         @Cm7/Eb                @G\nPor isso sinta-se   amado e querido\n         @C#m5-/7               @Am7    @C/D\nPois é o meu amor que cura sua dor,  ououoh\n    @G\nFoi por você\n        @G7/F                       @C/E\nQue, na cruz,  meu sangue foi derramado\n         @Cm7/Eb                @G/D\nPor isso sinta-se   querido e amado\n         @Em7                   @Am7\nPois é o meu amor que cura sua dor,\n              @C   @G/B  @Bb  @F  @Ab  @Eb @D7/11 @D7\nque cura sua dor”\n         @G     @D/F#     @Em7\nEntão, Jesus pediu-me assim\n       @G              @D/F#      @Em7 (@Em @Em/F# @Em/G @Em/B)\nQue as mágoas que estivessem em mim\n    @Am          @Am7/G     @D/F#\nQue delas não saísse mais dor\n             @Am         @Am7/G  @D/F#\nDe hoje em diante, só saísse  amor\n           @G             @D9/F#\nQue seja sempre assim\n     @G\nFoi por você\n             @G/F                     @C/E\nQue eu me deixei ser tão chagado e ferido\n         @Cm7/Eb                @G\nPor isso sinta-se   amado e querido\n         @C#m5-/7               @Am7    @C/D\nPois é o meu amor que cura sua dor,  ououoh\n    @G\nFoi por você\n        @G7/F                       @C/E\nQue, na cruz,  meu sangue foi derramado\n         @Cm7/Eb                @G/D\nPor isso sinta-se   querido e amado\n         @Em7                   @Am7\nPois é o meu amor que cura sua dor,\n              @C     @G/B\nque cura sua dor”\n\n(intro)\n\n@E|-10/12-15-10----------10--10-10-------------------------------------------\n@B|-------------12-12-13-----------13-12-13~~--10-10-12-13--13-13-12-10-12~~-\n\n\n@E|------------------8-10-------------------------------\n@B|-8-8-10-12--12-12------10~~--10-8-7-8/10~~-8~~-------\n\n\n(refrão)\n\n(1ª Parte)\n\n@E|-10/12-12-10-10----------10--10-10--------------------------------------------\n@B|----------------12-12-13-----------13-12-13~~--10-10-12-13--13-13-12-10-12~~--\n\n\n\n@E|------------------8-10-------------\n@B|-8-8-10-12--12-12------10~~--------\n\n\n(2ª Parte)\n\n@E|-10/12-12-10-10----------10--10-10--------------------------------------------\n@B|----------------12-12-13-----------13-12-13~~--10-10-12-13--13-13-12-10-12~~--\n\n\n\n@E|------------------------------------------------\n@B|-8-8-10-12--12-12-10-8-10~~--10-8-7-8/10~~-8~~--\n\n\n(final)\n\n@E|------------------------------\n@B|-8-8-10-12--12-10-8-7----8~~--\n\n\n    @Am7               @C/D      @G\nÉ o meu amor      que cura sua dor”',10,129,NULL),(3,'                 @C7+			           @Dm7\nComo é bom a gente se encontrar aqui neste lugar\n	@F9	           @C		      @Dm7	@G4/7    @G7\nOnde dois ou mais estão unidos, o Senhor está também\n\n          @Em7	     @Am7		@F7+	      @Fm7\nDe repente brota lá do fundo, algo que o mundo não tem....\n      @C7+		@Am7		 @Dm7	      @F/G     	     @C7+     @F/G\nA alegria de poder dizer: Que maravilha é ser o filho de Deus!\n\n	   @Em7 @Am7       @Em7     @Am7\nAbre seus braços ao Pai de Amor\n   @Dm  @Em  @D/F#  @F/G\nE vê que ele te ama\n	        @Em7 @Am7       @Em7         @Am7\nLança fora a tristeza e não olhe para trás\n   @Dm  @Em  @D/F#  @F/G\nSorrir é bom demais\n		   Intro\nSorrir é bom demais\n',3,42,'@C7+   @F/G (2x)'),(6,'@B @C @D',0,0,'@A @B @C'),(7,'    @G                               @Bm \nUma vida nova em Cristo venha experimentar \n     @C                           @D \nE de coração rendido, dia a dia se entregar \n      @G                         @Bm \nCom os olhos para o alto daqui menos depender \n       @C              \nPara o mundo estamos mortos \n      @C#º                  @D \nNossa vida escondida está em Deus \n  \nNasci pro céu... \n @G              @Am7            @C              @D \nOlhe, olhe mais longe, além do mundo, voe pro céu \n @G               @Am7               @C \nBusque, busque o alto, pôr sobre a vida \n     @Cm                  @G      @Am7, @C, @D \nVeja o trono, onde está Deus! \n  \n(Alçando vôo se lançar) \n       @G                         @Bm \nVem do céu essa alegria que me faz compreender \n      @C                 @D \nVejo além da agonia é certo eu vou vencer \n    @G                            @Bm \nSe levante para Cristo, não se arraste mais ao chão \n      @C                       @C#º \nMeu irmão cabeça erguida, Ele trás a nova vida \n   @D \nO amor e o perdão, olhe pro céu... \n    @C                 @D        @Bm            @E7 \nSe portanto ressuscitastes, buscai tudo que está no alto \n @Am7          @D                @C         @Cm        @G   @Am7, @C, @D \nOnde Cristo é tudo em todos, vem à vida nova se lançar.\n',5,83,'@G @Am @C @D'),(8,'dfagdfgadf',1,1,'adf'),(9,NULL,NULL,NULL,NULL),(10,NULL,NULL,NULL,NULL),(11,NULL,NULL,NULL,NULL),(12,NULL,NULL,NULL,NULL),(13,NULL,NULL,NULL,NULL),(14,NULL,NULL,NULL,NULL),(15,NULL,NULL,NULL,NULL),(16,'@G           @G7                          \n@A Alegria está no Coração,\n    @C                  @G\nde quem já conhece a JESUS.\n@G            @Em\n@A Verdadeira Paz só tem aquele\n    @A              @D7\nque já conhece a JESUS.\n\n\n@G                  @G7           @C            @Cm/F\nO Sentimento mais Precioso que vêm de nosso SENHOR.\n     @G           @D                       @G    @C  @D\nÉ o Amor que só tem quem já conhece a JESUS.\n        @G                   @G7\nPosso Pisar numa Tropa e Saltar as Muralhas \n   @C       @Cm/F\nAleluia, Aleluia!\n        @G                   @G7\nPosso Pisar numa Tropa e Saltar as Muralhas \n   @C       @Cm/F\nAleluia, Aleluia!\n@G                @G7\nEle é a Rocha da minha Salvação,\n     @C             @Cm/F\ncom Ele não há 8mais Condenação.\n        @G                   @G7\nPosso Pisar numa Tropa e Saltar as Muralhas \n   @C       @Cm/F\nAleluia, Aleluia!\n   @G         @C  @G      @G  @Em     @A  @D7\nAleluia,  Aleluia,  Aleluia,  Aleluia.\n\nO Sentimento... \n',10,57,'@G   @D   @G   @C @D'),(17,'@G           @G7                          \n@A Alegria está no Coração,\n    @C                  @G\nde quem já conhece a JESUS.\n@G            @Em\n@A Verdadeira Paz só tem aquele\n    @A              @D7\nque já conhece a JESUS.\n\n\n@G                  @G7           @C            @Cm/F\nO Sentimento mais Precioso que vêm de nosso SENHOR.\n     @G           @D                       @G    @C  @D\nÉ o Amor que só tem quem já conhece a JESUS.\n        @G                   @G7\nPosso Pisar numa Tropa e Saltar as Muralhas \n   @C       @Cm/F\nAleluia, Aleluia!\n        @G                   @G7\nPosso Pisar numa Tropa e Saltar as Muralhas \n   @C       @Cm/F\nAleluia, Aleluia!\n@G                @G7\nEle é a Rocha da minha Salvação,\n     @C             @Cm/F\ncom Ele não há 8mais Condenação.\n        @G                   @G7\nPosso Pisar numa Tropa e Saltar as Muralhas \n   @C       @Cm/F\nAleluia, Aleluia!\n   @G         @C  @G      @G  @Em     @A  @D7\nAleluia,  Aleluia,  Aleluia,  Aleluia.\n\nO Sentimento... \n',10,57,'@G   @D   @G   @C @D');
/*!40000 ALTER TABLE `musica_documento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `musica_leiturasassociadas`
--

DROP TABLE IF EXISTS `musica_leiturasassociadas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `musica_leiturasassociadas` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(255) DEFAULT NULL,
  `musica_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK9A1FA4FF495641E1` (`musica_id`),
  CONSTRAINT `FK9A1FA4FF495641E1` FOREIGN KEY (`musica_id`) REFERENCES `musica` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `musica_leiturasassociadas`
--

LOCK TABLES `musica_leiturasassociadas` WRITE;
/*!40000 ALTER TABLE `musica_leiturasassociadas` DISABLE KEYS */;
INSERT INTO `musica_leiturasassociadas` VALUES (1,'Leitura 1',14),(2,'Leitura 2',14),(3,'Leitura 3',14),(4,'Mt231',15),(5,'Mt39471',15);
/*!40000 ALTER TABLE `musica_leiturasassociadas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `musica_tipos`
--

DROP TABLE IF EXISTS `musica_tipos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `musica_tipos` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `valor` varchar(255) DEFAULT NULL,
  `musica_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKC039B89C495641E1` (`musica_id`),
  CONSTRAINT `FKC039B89C495641E1` FOREIGN KEY (`musica_id`) REFERENCES `musica` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `musica_tipos`
--

LOCK TABLES `musica_tipos` WRITE;
/*!40000 ALTER TABLE `musica_tipos` DISABLE KEYS */;
INSERT INTO `musica_tipos` VALUES (4,'ACAO_DE_GRACAS',2),(5,'PALESTRA',2),(6,'ADORACAO',2),(7,'ENTRADA',3),(8,'PAZ',3),(9,'FINAL',3),(13,'ATO_PENITENCIAL',6),(14,'ENTRADA',7),(15,'FINAL',7),(16,'ATO_PENITENCIAL',8),(17,'ACAO_DE_GRACAS',9),(18,'ENTRADA',10),(19,'ATO_PENITENCIAL',11),(20,'ACAO_DE_GRACAS',12),(21,'PALESTRA',12),(22,'COMUNHAO',13),(23,'FINAL',13),(24,'ENTRADA',14),(25,'FINAL',14),(26,'ATO_PENITENCIAL',15),(27,'GLORIA',15),(28,'ACLAMACAO',15),(29,'GLORIA',16),(30,'ACLAMACAO',16);
/*!40000 ALTER TABLE `musica_tipos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys`
--

DROP TABLE IF EXISTS `sys`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `template_descricao_email` longtext,
  `versao` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys`
--

LOCK TABLES `sys` WRITE;
/*!40000 ALTER TABLE `sys` DISABLE KEYS */;
INSERT INTO `sys` VALUES (1,'<saudação diária>,\n\nsegue abaixo a relação de músicas para a missa do dia <data>.\n\n<músicas>\n\nAtenciosamente,\n\nMinistério de Música Nova Unção.',1);
/*!40000 ALTER TABLE `sys` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-03-06 16:03:27
