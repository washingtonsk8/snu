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
) ENGINE=InnoDB AUTO_INCREMENT=783 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `documentomusica_listainvertida`
--

LOCK TABLES `documentomusica_listainvertida` WRITE;
/*!40000 ALTER TABLE `documentomusica_listainvertida` DISABLE KEYS */;
INSERT INTO `documentomusica_listainvertida` VALUES (1,1,1,1,1),(2,1,1,1,2),(3,1,1,1,3),(4,1,1,1,4),(5,1,1,1,5),(6,3,1,1,6),(7,1,1,1,7),(8,1,1,1,8),(9,1,1,1,9),(10,1,1,1,10),(11,1,1,1,11),(12,1,1,1,12),(13,3,1,1,13),(14,1,1,1,14),(15,1,1,1,15),(16,1,1,1,16),(17,1,1,1,17),(18,1,1,1,18),(19,1,1,1,19),(20,1,1,1,20),(21,3,1,1,21),(22,2,1,1,22),(23,1,1,1,23),(24,1,1,1,24),(25,1,1,1,25),(26,1,1,1,26),(27,1,1,1,27),(28,1,1,1,28),(29,1,1,1,29),(30,1,1,1,30),(31,1,1,1,31),(32,1,1,1,32),(33,1,1,1,33),(34,1,1,1,34),(35,1,1,1,35),(36,2,1,1,36),(37,1,1,1,37),(38,2,1,1,38),(39,4,1,1,39),(40,1,1,1,40),(41,1,1,1,41),(42,1,1,1,42),(43,11,1,1,43),(44,1,1,1,44),(45,1,1,1,45),(46,1,1,1,46),(47,1,1,1,47),(48,4,1,1,48),(49,2,1,1,49),(50,1,1,1,50),(51,1,1,1,51),(52,1,1,1,52),(53,1,1,1,53),(54,2,1,1,54),(55,2,1,1,55),(56,1,1,1,56),(57,1,1,1,57),(58,2,1,1,58),(59,2,1,1,59),(60,1,1,1,60),(61,1,1,1,61),(62,2,1,1,62),(63,1,1,1,63),(64,1,1,1,64),(65,1,1,1,65),(66,1,1,1,66),(67,1,1,1,67),(68,10,2,2,68),(69,2,2,2,4),(70,1,2,2,5),(71,1,2,2,69),(72,1,2,2,70),(73,9,2,2,9),(74,1,2,2,71),(75,4,2,2,72),(76,1,2,2,73),(77,2,2,2,74),(78,2,2,2,75),(79,1,2,2,76),(80,2,2,2,77),(81,1,2,2,78),(82,2,2,2,79),(83,3,2,2,80),(84,2,2,2,81),(85,4,2,2,82),(86,1,2,2,83),(87,1,2,2,22),(88,3,2,2,84),(89,1,2,2,24),(90,1,2,2,27),(91,3,2,2,85),(92,2,2,2,86),(93,2,2,2,87),(94,1,2,2,88),(95,1,2,2,89),(96,1,2,2,36),(97,4,2,2,90),(98,1,2,2,91),(99,1,2,2,92),(100,8,2,2,39),(101,2,2,2,93),(102,4,2,2,43),(103,2,2,2,94),(104,1,2,2,95),(105,1,2,2,96),(106,3,2,2,97),(107,4,2,2,48),(108,2,2,2,49),(109,1,2,2,98),(110,1,2,2,99),(111,1,2,2,52),(112,4,2,2,54),(113,2,2,2,100),(114,8,2,2,56),(115,1,2,2,58),(116,1,2,2,101),(117,1,2,2,102),(118,7,2,2,103),(119,2,2,2,104),(120,1,2,2,105),(161,1,3,3,1),(162,2,3,3,106),(163,1,3,3,2),(164,1,3,3,107),(165,1,3,3,32),(166,1,3,3,87),(167,1,3,3,108),(168,1,3,3,109),(169,1,3,3,110),(170,1,3,3,111),(171,1,3,3,6),(172,1,3,3,112),(173,1,3,3,113),(174,1,3,3,114),(175,1,3,3,115),(176,1,3,3,116),(177,1,3,3,117),(178,1,3,3,119),(179,1,3,3,118),(180,1,3,3,120),(181,1,3,3,121),(182,1,3,3,122),(183,1,3,3,123),(184,1,3,3,11),(185,1,3,3,124),(186,1,3,3,12),(187,1,3,3,125),(188,1,3,3,50),(189,1,3,3,51),(190,1,3,3,126),(191,1,3,3,52),(192,1,3,3,21),(193,1,3,3,56),(194,1,3,3,127),(195,1,3,3,128),(196,3,3,3,129),(197,1,3,3,58),(198,1,3,3,130),(199,1,3,3,131),(200,1,7,7,132),(201,1,7,7,5),(202,2,7,7,111),(203,1,7,7,8),(204,1,7,7,133),(205,3,7,7,114),(206,1,7,7,134),(207,1,7,7,135),(208,1,7,7,136),(209,5,7,7,137),(210,1,7,7,138),(211,1,7,7,139),(212,1,7,7,19),(213,2,7,7,140),(214,1,7,7,141),(215,1,7,7,142),(216,1,7,7,128),(217,1,7,7,143),(218,1,7,7,144),(219,3,7,7,145),(220,1,7,7,146),(221,1,7,7,147),(222,2,7,7,32),(223,1,7,7,148),(224,1,7,7,149),(225,2,7,7,110),(226,1,7,7,150),(227,1,7,7,151),(228,1,7,7,152),(229,4,7,7,153),(230,3,7,7,116),(231,1,7,7,39),(232,1,7,7,154),(233,1,7,7,155),(234,3,7,7,156),(235,1,7,7,157),(236,1,7,7,43),(237,1,7,7,45),(238,1,7,7,47),(239,1,7,7,158),(240,1,7,7,48),(241,1,7,7,159),(242,1,7,7,160),(243,1,7,7,50),(244,3,7,7,161),(245,2,7,7,126),(246,1,7,7,162),(247,1,7,7,163),(248,1,7,7,56),(249,2,7,7,164),(250,1,7,7,165),(251,2,7,7,166),(252,1,7,7,101),(253,3,7,7,167),(254,1,7,7,168),(255,2,7,7,169),(256,1,8,8,170),(257,2,NULL,NULL,1),(258,3,NULL,NULL,171),(259,10,NULL,NULL,172),(260,1,NULL,NULL,5),(261,1,NULL,NULL,151),(262,1,NULL,NULL,173),(263,2,NULL,NULL,174),(264,1,NULL,NULL,175),(265,1,NULL,NULL,176),(266,1,NULL,NULL,177),(267,1,NULL,NULL,45),(268,3,NULL,NULL,178),(269,1,NULL,NULL,96),(270,3,NULL,NULL,179),(271,2,NULL,NULL,81),(272,1,NULL,NULL,50),(273,1,NULL,NULL,21),(274,1,NULL,NULL,180),(275,3,NULL,NULL,181),(276,1,NULL,NULL,56),(277,3,NULL,NULL,59),(278,1,NULL,NULL,182),(279,1,NULL,NULL,166),(280,3,NULL,NULL,84),(281,3,NULL,NULL,183),(282,3,NULL,NULL,66),(283,3,NULL,NULL,30),(284,1,19,18,184),(285,2,19,18,2),(286,1,19,18,185),(287,1,19,18,108),(288,1,19,18,186),(289,4,19,18,5),(290,1,19,18,187),(291,1,19,18,188),(292,1,19,18,189),(293,1,19,18,190),(294,1,19,18,191),(295,1,19,18,192),(296,1,19,18,193),(297,1,19,18,194),(298,1,19,18,195),(299,1,19,18,196),(300,1,19,18,76),(301,1,19,18,197),(302,1,19,18,198),(303,1,19,18,199),(304,2,19,18,137),(305,1,19,18,12),(306,1,19,18,200),(307,1,19,18,201),(308,1,19,18,78),(309,1,19,18,202),(310,1,19,18,203),(311,1,19,18,204),(312,1,19,18,205),(313,2,19,18,206),(314,1,19,18,207),(315,1,19,18,208),(316,1,19,18,20),(317,1,19,18,209),(318,3,19,18,210),(319,1,19,18,143),(320,2,19,18,211),(321,1,19,18,212),(322,2,19,18,213),(323,1,19,18,214),(324,2,19,18,215),(325,1,19,18,216),(326,1,19,18,217),(327,1,19,18,218),(328,3,19,18,219),(329,1,19,18,26),(330,1,19,18,220),(331,3,19,18,221),(332,1,19,18,222),(333,1,19,18,223),(334,1,19,18,224),(335,1,19,18,225),(336,2,19,18,226),(337,3,19,18,227),(338,1,19,18,228),(339,1,19,18,229),(340,1,19,18,230),(341,1,19,18,231),(342,1,19,18,151),(343,1,19,18,232),(344,2,19,18,233),(345,5,19,18,234),(346,1,19,18,235),(347,1,19,18,236),(348,3,19,18,43),(349,1,19,18,237),(350,1,19,18,238),(351,1,19,18,46),(352,1,19,18,239),(353,1,19,18,240),(354,1,19,18,241),(355,1,19,18,242),(356,1,19,18,243),(357,1,19,18,244),(358,3,19,18,50),(359,1,19,18,245),(360,1,19,18,246),(361,1,19,18,247),(362,1,19,18,248),(363,1,19,18,249),(364,1,19,18,166),(365,3,19,18,250),(366,1,19,18,251),(367,2,19,18,252),(368,1,19,18,253),(369,1,19,18,254),(370,5,19,18,63),(371,2,19,18,255),(372,1,19,18,104),(373,1,19,18,256),(374,1,19,18,257),(375,1,19,18,258),(376,1,19,18,259),(377,1,19,18,260),(378,1,19,18,261),(379,1,19,18,262),(380,1,19,18,263),(381,1,19,18,264),(382,1,19,18,265),(383,3,19,18,266),(384,1,19,18,267),(385,1,19,18,268),(386,1,19,18,269),(387,1,19,18,270),(388,1,19,18,271),(389,1,19,18,272),(390,1,19,18,75),(391,1,19,18,273),(392,1,19,18,274),(393,1,19,18,275),(394,1,19,18,11),(395,3,19,18,276),(396,1,19,18,277),(397,1,19,18,278),(398,1,19,18,279),(399,1,19,18,139),(400,2,19,18,81),(401,1,19,18,280),(402,1,19,18,281),(403,1,19,18,282),(404,3,19,18,283),(405,2,19,18,284),(406,4,19,18,22),(407,1,19,18,285),(408,2,19,18,286),(409,2,19,18,287),(410,1,19,18,288),(411,1,19,18,289),(412,2,19,18,290),(413,3,19,18,291),(414,1,19,18,292),(415,1,19,18,293),(416,1,19,18,294),(417,2,19,18,295),(418,1,19,18,296),(419,1,19,18,297),(420,1,19,18,298),(421,1,19,18,299),(422,1,19,18,300),(423,2,19,18,301),(424,3,19,18,31),(425,1,19,18,302),(426,1,19,18,32),(427,2,19,18,87),(428,1,19,18,303),(429,1,19,18,304),(430,1,19,18,305),(431,1,19,18,36),(432,1,19,18,306),(433,1,19,18,307),(434,2,19,18,113),(435,1,19,18,308),(436,1,19,18,309),(437,4,19,18,116),(438,2,19,18,310),(439,1,19,18,311),(440,1,19,18,312),(441,1,19,18,313),(442,2,19,18,314),(443,1,19,18,315),(444,1,19,18,316),(445,1,19,18,317),(446,1,19,18,318),(447,1,19,18,319),(448,1,19,18,126),(449,1,19,18,320),(450,1,19,18,321),(451,1,19,18,322),(452,1,19,18,55),(453,1,19,18,129),(454,1,19,18,323),(455,1,19,18,324),(456,1,19,18,325),(457,1,19,18,326),(458,1,19,18,327),(459,1,19,18,328),(460,1,19,18,329),(461,1,19,18,330),(462,1,19,18,331),(463,1,19,18,332),(464,2,19,18,333),(465,1,19,18,334),(466,1,19,18,335),(467,1,NULL,NULL,336),(468,1,NULL,NULL,337),(469,1,NULL,NULL,338),(470,1,NULL,NULL,339),(471,3,NULL,NULL,340),(472,2,NULL,NULL,341),(473,1,NULL,NULL,342),(474,1,NULL,NULL,343),(475,1,NULL,NULL,344),(476,1,NULL,NULL,345),(477,1,NULL,NULL,115),(478,1,NULL,NULL,346),(479,2,NULL,NULL,194),(480,1,NULL,NULL,347),(481,1,NULL,NULL,348),(482,1,NULL,NULL,12),(483,2,NULL,NULL,77),(484,1,NULL,NULL,349),(485,2,NULL,NULL,350),(486,2,NULL,NULL,351),(487,1,NULL,NULL,352),(488,4,NULL,NULL,353),(489,2,NULL,NULL,354),(490,2,NULL,NULL,355),(491,1,NULL,NULL,356),(492,1,NULL,NULL,226),(493,1,NULL,NULL,148),(494,1,NULL,NULL,357),(495,2,NULL,NULL,358),(496,1,NULL,NULL,359),(497,1,NULL,NULL,360),(498,1,NULL,NULL,361),(499,1,NULL,NULL,38),(500,1,NULL,NULL,362),(501,1,NULL,NULL,363),(502,2,NULL,NULL,364),(503,1,NULL,NULL,365),(504,1,NULL,NULL,366),(505,1,NULL,NULL,367),(506,2,NULL,NULL,368),(507,1,NULL,NULL,369),(508,1,NULL,NULL,44),(509,1,NULL,NULL,370),(510,1,NULL,NULL,371),(511,1,NULL,NULL,372),(512,1,NULL,NULL,373),(513,1,NULL,NULL,374),(514,1,NULL,NULL,48),(515,1,NULL,NULL,375),(516,1,NULL,NULL,376),(517,1,NULL,NULL,377),(518,1,NULL,NULL,378),(519,1,NULL,NULL,379),(520,1,NULL,NULL,380),(521,1,NULL,NULL,381),(522,1,NULL,NULL,382),(523,7,NULL,NULL,63),(524,1,NULL,NULL,131),(525,1,NULL,NULL,383),(526,1,NULL,NULL,384),(527,1,NULL,NULL,260),(528,2,NULL,NULL,385),(529,1,NULL,NULL,8),(530,1,NULL,NULL,386),(531,1,NULL,NULL,387),(532,1,NULL,NULL,388),(533,1,NULL,NULL,389),(534,2,NULL,NULL,81),(535,1,NULL,NULL,390),(536,1,NULL,NULL,391),(537,1,NULL,NULL,392),(538,1,NULL,NULL,393),(539,1,NULL,NULL,394),(540,1,NULL,NULL,395),(541,1,NULL,NULL,396),(542,1,NULL,NULL,397),(543,1,NULL,NULL,398),(544,1,NULL,NULL,399),(545,3,NULL,NULL,87),(546,1,NULL,NULL,400),(547,3,NULL,NULL,401),(548,1,NULL,NULL,36),(549,1,NULL,NULL,110),(550,3,NULL,NULL,402),(551,1,NULL,NULL,37),(552,1,NULL,NULL,403),(553,3,NULL,NULL,404),(554,2,NULL,NULL,116),(555,2,NULL,NULL,39),(556,1,NULL,NULL,310),(557,1,NULL,NULL,405),(558,1,NULL,NULL,406),(559,1,NULL,NULL,407),(560,1,NULL,NULL,408),(561,1,NULL,NULL,409),(562,1,NULL,NULL,125),(563,2,NULL,NULL,317),(564,1,NULL,NULL,410),(565,1,NULL,NULL,411),(566,1,NULL,NULL,412),(567,1,NULL,NULL,320),(568,1,NULL,NULL,54),(569,2,NULL,NULL,413),(570,1,NULL,NULL,414),(571,1,NULL,NULL,132),(572,3,NULL,NULL,415),(573,1,NULL,NULL,108),(574,4,NULL,NULL,5),(575,1,NULL,NULL,416),(576,3,NULL,NULL,417),(577,1,NULL,NULL,418),(578,1,NULL,NULL,419),(579,1,NULL,NULL,173),(580,1,NULL,NULL,420),(581,5,NULL,NULL,421),(582,1,NULL,NULL,422),(583,1,NULL,NULL,423),(584,1,NULL,NULL,424),(585,1,NULL,NULL,425),(586,2,NULL,NULL,426),(587,2,NULL,NULL,427),(588,1,NULL,NULL,428),(589,1,NULL,NULL,429),(590,1,NULL,NULL,136),(591,1,NULL,NULL,430),(592,1,NULL,NULL,431),(593,1,NULL,NULL,432),(594,1,NULL,NULL,205),(595,1,NULL,NULL,17),(596,4,NULL,NULL,433),(597,1,NULL,NULL,434),(598,1,NULL,NULL,435),(599,1,NULL,NULL,436),(600,1,NULL,NULL,437),(601,2,NULL,NULL,438),(602,4,NULL,NULL,218),(603,2,NULL,NULL,85),(604,1,NULL,NULL,439),(605,1,NULL,NULL,440),(606,6,NULL,NULL,227),(607,1,NULL,NULL,441),(608,1,NULL,NULL,442),(609,1,NULL,NULL,443),(610,1,NULL,NULL,231),(611,4,NULL,NULL,151),(612,1,NULL,NULL,444),(613,1,NULL,NULL,445),(614,1,NULL,NULL,446),(615,1,NULL,NULL,447),(616,1,NULL,NULL,43),(617,1,NULL,NULL,448),(618,3,NULL,NULL,449),(619,1,NULL,NULL,98),(620,2,NULL,NULL,450),(621,3,NULL,NULL,451),(622,1,NULL,NULL,452),(623,1,NULL,NULL,453),(624,1,NULL,NULL,454),(625,1,NULL,NULL,455),(626,1,NULL,NULL,456),(627,1,NULL,NULL,457),(628,6,NULL,NULL,1),(629,3,NULL,NULL,458),(630,1,NULL,NULL,459),(631,1,NULL,NULL,460),(632,2,NULL,NULL,263),(633,1,NULL,NULL,461),(634,1,NULL,NULL,265),(635,1,NULL,NULL,462),(636,1,NULL,NULL,463),(637,1,NULL,NULL,464),(638,1,NULL,NULL,268),(639,1,NULL,NULL,465),(640,1,NULL,NULL,75),(641,4,NULL,NULL,273),(642,1,NULL,NULL,466),(643,2,NULL,NULL,467),(644,2,NULL,NULL,468),(645,1,NULL,NULL,469),(646,2,NULL,NULL,284),(647,2,NULL,NULL,470),(648,1,NULL,NULL,471),(649,1,NULL,NULL,472),(650,1,NULL,NULL,473),(651,1,NULL,NULL,474),(652,2,NULL,NULL,295),(653,1,NULL,NULL,296),(654,1,NULL,NULL,475),(655,1,NULL,NULL,297),(656,1,NULL,NULL,476),(657,1,NULL,NULL,477),(658,1,NULL,NULL,32),(659,3,NULL,NULL,113),(660,1,NULL,NULL,478),(661,1,NULL,NULL,479),(662,2,NULL,NULL,480),(663,2,NULL,NULL,481),(664,2,NULL,NULL,482),(665,1,NULL,NULL,483),(666,1,NULL,NULL,484),(667,2,NULL,NULL,485),(668,1,NULL,NULL,486),(669,1,NULL,NULL,315),(670,3,NULL,NULL,316),(671,1,NULL,NULL,487),(672,1,NULL,NULL,488),(673,1,NULL,NULL,489),(674,1,NULL,NULL,490),(675,1,NULL,NULL,491),(676,1,NULL,NULL,492),(677,1,NULL,NULL,493),(678,3,NULL,NULL,494),(679,4,NULL,NULL,495),(680,1,NULL,NULL,496),(681,1,NULL,NULL,497),(682,1,NULL,NULL,498),(683,1,NULL,NULL,499),(684,1,NULL,NULL,500),(685,1,NULL,NULL,501),(686,1,NULL,NULL,146),(687,1,NULL,NULL,502),(688,1,NULL,NULL,87),(689,2,NULL,NULL,503),(690,1,NULL,NULL,151),(691,1,NULL,NULL,504),(692,1,NULL,NULL,505),(693,1,NULL,NULL,506),(694,2,NULL,NULL,507),(695,1,NULL,NULL,309),(696,1,NULL,NULL,310),(697,1,NULL,NULL,508),(698,1,NULL,NULL,509),(699,3,NULL,NULL,75),(700,1,NULL,NULL,11),(701,2,NULL,NULL,77),(702,1,NULL,NULL,317),(703,1,NULL,NULL,139),(704,1,NULL,NULL,510),(705,1,NULL,NULL,487),(706,1,NULL,NULL,511),(707,1,NULL,NULL,512),(708,1,NULL,NULL,513),(709,1,NULL,NULL,514),(710,1,NULL,NULL,515),(711,1,NULL,NULL,160),(712,1,NULL,NULL,126),(713,2,NULL,NULL,516),(714,1,NULL,NULL,517),(715,1,NULL,NULL,284),(716,2,NULL,NULL,518),(717,1,NULL,NULL,519),(718,1,NULL,NULL,520),(719,1,NULL,NULL,521),(720,1,NULL,NULL,104),(721,1,NULL,NULL,522),(722,1,NULL,NULL,85),(723,1,NULL,NULL,456),(724,1,NULL,NULL,523),(725,1,NULL,NULL,524),(726,1,NULL,NULL,525),(727,1,NULL,NULL,263),(728,1,NULL,NULL,526),(729,1,NULL,NULL,527),(730,1,NULL,NULL,528),(731,1,NULL,NULL,197),(732,1,NULL,NULL,466),(733,1,NULL,NULL,529),(734,1,NULL,NULL,530),(735,2,NULL,NULL,139),(736,1,NULL,NULL,205),(737,1,NULL,NULL,531),(738,1,NULL,NULL,532),(739,1,NULL,NULL,533),(740,1,NULL,NULL,534),(741,1,NULL,NULL,393),(742,2,NULL,NULL,22),(743,2,NULL,NULL,535),(744,2,NULL,NULL,536),(745,1,NULL,NULL,537),(746,1,NULL,NULL,538),(747,2,NULL,NULL,539),(748,1,NULL,NULL,540),(749,1,NULL,NULL,541),(750,1,NULL,NULL,542),(751,2,NULL,NULL,543),(752,1,NULL,NULL,544),(753,1,NULL,NULL,545),(754,1,NULL,NULL,546),(755,1,NULL,NULL,87),(756,1,NULL,NULL,547),(757,1,NULL,NULL,548),(758,1,NULL,NULL,549),(759,1,NULL,NULL,151),(760,1,NULL,NULL,550),(761,4,NULL,NULL,116),(762,1,NULL,NULL,551),(763,2,NULL,NULL,552),(764,1,NULL,NULL,553),(765,1,NULL,NULL,554),(766,1,NULL,NULL,555),(767,1,NULL,NULL,556),(768,1,NULL,NULL,557),(769,1,NULL,NULL,558),(770,1,NULL,NULL,559),(771,1,NULL,NULL,560),(772,1,NULL,NULL,561),(773,1,NULL,NULL,562),(774,1,NULL,NULL,563),(775,1,NULL,NULL,564),(776,1,NULL,NULL,495),(777,1,NULL,NULL,565),(778,1,NULL,NULL,566),(779,1,NULL,NULL,567),(780,1,NULL,NULL,568),(781,1,NULL,NULL,569),(782,1,NULL,NULL,63);
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
) ENGINE=InnoDB AUTO_INCREMENT=570 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `documentomusica_vocabulario`
--

LOCK TABLES `documentomusica_vocabulario` WRITE;
/*!40000 ALTER TABLE `documentomusica_vocabulario` DISABLE KEYS */;
INSERT INTO `documentomusica_vocabulario` VALUES (1,'tem'),(2,'aqu'),(3,'tenh'),(4,'derram'),(5,'no'),(6,'te'),(7,'ande'),(8,'long'),(9,'foi'),(10,'beij'),(11,'lug'),(12,'pod'),(13,'sou'),(14,'saudad'),(15,'soment'),(16,'mostr'),(17,'sol'),(18,'ansios'),(19,'olhos'),(20,'viv'),(21,'senhor'),(22,'fal'),(23,'tard'),(24,'hoj'),(25,'acolh'),(26,'temp'),(27,'refra'),(28,'rest'),(29,'got'),(30,'salt'),(31,'volt'),(32,'ond'),(33,'pergunt'),(34,'estrad'),(35,'olhand'),(36,'nad'),(37,'corr'),(38,'ver'),(39,'meu'),(40,'desist'),(41,'gast'),(42,'estou'),(43,'me'),(44,'mar'),(45,'coraca'),(46,'cont'),(47,'vou'),(48,'eu'),(49,'mim'),(50,'alegr'),(51,'pai'),(52,'intr'),(53,'amou'),(54,'quer'),(55,'sei'),(56,'amor'),(57,'rasg'),(58,'encontr'),(59,'num'),(60,'abrac'),(61,'decid'),(62,'esper'),(63,'muit'),(64,'meus'),(65,'bens'),(66,'ja'),(67,'x'),(68,'dor'),(69,'estiv'),(70,'sai'),(71,'jardim'),(72,'amad'),(73,'maos'),(74,'chag'),(75,'deix'),(76,'sair'),(77,'sempr'),(78,'final'),(79,'ª'),(80,'fer'),(81,'so'),(82,'sint'),(83,'mago'),(84,'jesus'),(85,'tao'),(86,'ououoh'),(87,'ser'),(88,'vi'),(89,'pra'),(90,'voc'),(91,'diant'),(92,'lind'),(93,'cruz'),(94,'del'),(95,'mal'),(96,'minh'),(97,'sang'),(98,'nunc'),(99,'ped'),(100,'saiss'),(101,'venc'),(102,'receb'),(103,'cur'),(104,'part'),(105,'entregu'),(106,'sorr'),(107,'maravilh'),(108,'for'),(109,'dois'),(110,'lanc'),(111,'deus'),(112,'ama'),(113,'diz'),(114,'olh'),(115,'unid'),(116,'par'),(117,'abr'),(118,'gent'),(119,'repent'),(120,'filh'),(121,'brot'),(122,'ve'),(123,'fund'),(124,'tristez'),(125,'alg'),(126,'mund'),(127,'brac'),(128,'tras'),(129,'bom'),(130,'la'),(131,'esta'),(132,'nasc'),(133,'cha'),(134,'daqu'),(135,'experiment'),(136,'estam'),(137,'vid'),(138,'mort'),(139,'faz'),(140,'dia'),(141,'agon'),(142,'tron'),(143,'escond'),(144,'ressuscit'),(145,'nov'),(146,'cabec'),(147,'perda'),(148,'compreend'),(149,'rend'),(150,'levant'),(151,'noss'),(152,'voe'),(153,'ceu'),(154,'entreg'),(155,'cert'),(156,'alt'),(157,'voo'),(158,'arrast'),(159,'depend'),(160,'ergu'),(161,'crist'),(162,'busca'),(163,'alcand'),(164,'vej'),(165,'irma'),(166,'vem'),(167,'pro'),(168,'venh'),(169,'busqu'),(170,'dfagdfgadf'),(171,'muralh'),(172,'alelu'),(173,'roch'),(174,'sentiment'),(175,'verdadeir'),(176,'salvaca'),(177,'paz'),(178,'conhec'),(179,'poss'),(180,'conden'),(181,'trop'),(182,'precios'),(183,'pis'),(184,'racional'),(185,'possivel'),(186,'deriv'),(187,'candidat'),(188,'clivag'),(189,'congreg'),(190,'primeir'),(191,'cavac'),(192,'atribu'),(193,'tip'),(194,'benefic'),(195,'esperanc'),(196,'import'),(197,'cheg'),(198,'reacca'),(199,'central'),(200,'submet'),(201,'comig'),(202,'sous'),(203,'triunvirat'),(204,'calde'),(205,'form'),(206,'tres'),(207,'essencial'),(208,'avis'),(209,'incompatibiliz'),(210,'parec'),(211,'sao'),(212,'trabalh'),(213,'ningu'),(214,'aspect'),(215,'demasi'),(216,'valor'),(217,'inescap'),(218,'naca'),(219,'professor'),(220,'abandon'),(221,'soar'),(222,'aversa'),(223,'melhor'),(224,'decent'),(225,'moral'),(226,'vez'),(227,'pais'),(228,'egoism'),(229,'inteligent'),(230,'cultural'),(231,'necess'),(232,'modern'),(233,'direit'),(234,'esquerd'),(235,'silv'),(236,'entant'),(237,'altur'),(238,'ador'),(239,'apo'),(240,'incompreensa'),(241,'gost'),(242,'demagog'),(243,'quas'),(244,'ler'),(245,'transversal'),(246,'nutr'),(247,'guterr'),(248,'represent'),(249,'metad'),(250,'doutor'),(251,'ambica'),(252,'inevitavel'),(253,'agu'),(254,'caminh'),(255,'ganh'),(256,'cust'),(257,'interior'),(258,'falt'),(259,'simpl'),(260,'utiliz'),(261,'pricipal'),(262,'natur'),(263,'anos'),(264,'ateus'),(265,'sent'),(266,'manuel'),(267,'eufem'),(268,'grand'),(269,'derrot'),(270,'concord'),(271,'soviet'),(272,'definica'),(273,'portugal'),(274,'cair'),(275,'sens'),(276,'extrem'),(277,'andam'),(278,'trac'),(279,'provavel'),(280,'prudent'),(281,'goeth'),(282,'francisc'),(283,'mari'),(284,'bem'),(285,'vizinh'),(286,'louc'),(287,'dar'),(288,'prim'),(289,'separ'),(290,'pesso'),(291,'segund'),(292,'sa'),(293,'vitim'),(294,'aclar'),(295,'diss'),(296,'diferent'),(297,'comun'),(298,'ares'),(299,'med'),(300,'benefici'),(301,'cas'),(302,'vam'),(303,'inevitabil'),(304,'sug'),(305,'esquiv'),(306,'fin'),(307,'palavr'),(308,'nasciment'),(309,'pens'),(310,'cad'),(311,'sozinh'),(312,'risc'),(313,'anibal'),(314,'jeronim'),(315,'exist'),(316,'cois'),(317,'rein'),(318,'invej'),(319,'altiv'),(320,'apen'),(321,'human'),(322,'grav'),(323,'letr'),(324,'justic'),(325,'bloc'),(326,'desloc'),(327,'gananc'),(328,'ric'),(329,'ide'),(330,'nitid'),(331,'electrodomest'),(332,'espac'),(333,'fundamental'),(334,'pior'),(335,'ps'),(336,'total'),(337,'nenhum'),(338,'histor'),(339,'alemanh'),(340,'etc'),(341,'unic'),(342,'funcion'),(343,'profund'),(344,'pov'),(345,'flor'),(346,'principal'),(347,'tent'),(348,'instant'),(349,'destru'),(350,'enfrent'),(351,'fronteir'),(352,'ignor'),(353,'amig'),(354,'ameac'),(355,'los'),(356,'perguntav'),(357,'prest'),(358,'espanh'),(359,'use'),(360,'ocean'),(361,'bast'),(362,'pert'),(363,'holand'),(364,'territori'),(365,'canide'),(366,'contrari'),(367,'consider'),(368,'ilhas'),(369,'verdad'),(370,'franc'),(371,'encost'),(372,'belgic'),(373,'mirandel'),(374,'diviso'),(375,'espanho'),(376,'retalh'),(377,'dadiv'),(378,'basc'),(379,'catalunh'),(380,'dess'),(381,'prevalec'),(382,'maritim'),(383,'pertenc'),(384,'com'),(385,'hav'),(386,'mud'),(387,'precis'),(388,'lua'),(389,'delimit'),(390,'maneir'),(391,'resist'),(392,'dentr'),(393,'cost'),(394,'orbit'),(395,'avidez'),(396,'embor'),(397,'natural'),(398,'singul'),(399,'inteir'),(400,'var'),(401,'sul'),(402,'europ'),(403,'demarqu'),(404,'patriot'),(405,'ident'),(406,'miguel'),(407,'tom'),(408,'portugues'),(409,'lat'),(410,'verm'),(411,'ital'),(412,'pretens'),(413,'sep'),(414,'algum'),(415,'estad'),(416,'pouc'),(417,'fom'),(418,'expliqu'),(419,'imperi'),(420,'olivenc'),(421,'lhe'),(422,'estav'),(423,'estrangeir'),(424,'vir'),(425,'suic'),(426,'respond'),(427,'luis'),(428,'estrutur'),(429,'lut'),(430,'lux'),(431,'primordi'),(432,'pressa'),(433,'som'),(434,'uni'),(435,'uno'),(436,'significav'),(437,'mant'),(438,'baix'),(439,'port'),(440,'achav'),(441,'talvez'),(442,'manifest'),(443,'dez'),(444,'enraiz'),(445,'antig'),(446,'preconiz'),(447,'leon'),(448,'forc'),(449,'portugu'),(450,'divid'),(451,'nort'),(452,'plen'),(453,'resquici'),(454,'indestrutiv'),(455,'posica'),(456,'conquist'),(457,'pel'),(458,'cham'),(459,'invaso'),(460,'galiz'),(461,'muculman'),(462,'obrig'),(463,'mord'),(464,'garant'),(465,'milho'),(466,'nivel'),(467,'terrestr'),(468,'idem'),(469,'futebol'),(470,'diferenc'),(471,'suced'),(472,'atac'),(473,'velh'),(474,'permanent'),(475,'compat'),(476,'regia'),(477,'inimig'),(478,'pont'),(479,'exceptu'),(480,'estranh'),(481,'cim'),(482,'excepca'),(483,'indestrut'),(484,'cicl'),(485,'regio'),(486,'frances'),(487,'constru'),(488,'domin'),(489,'lingu'),(490,'problem'),(491,'exempl'),(492,'polit'),(493,'agria'),(494,'nacional'),(495,'pass'),(496,'acontec'),(497,'pil'),(498,'continent'),(499,'alguns'),(500,'brilh'),(501,'complex'),(502,'don'),(503,'amizad'),(504,'desanim'),(505,'aliment'),(506,'fri'),(507,'estrelinh'),(508,'pequen'),(509,'feliz'),(510,'ir'),(511,'assum'),(512,'sensiv'),(513,'ternurent'),(514,'erros'),(515,'conseg'),(516,'fort'),(517,'abaix'),(518,'frac'),(519,'present'),(520,'comet'),(521,'doc'),(522,'geral'),(523,'capaz'),(524,'jovens'),(525,'preguic'),(526,'estud'),(527,'avali'),(528,'ignorant'),(529,'intelectu'),(530,'quantific'),(531,'status'),(532,'ment'),(533,'font'),(534,'constat'),(535,'besteir'),(536,'inform'),(537,'sinal'),(538,'armazen'),(539,'condicion'),(540,'mont'),(541,'trein'),(542,'car'),(543,'result'),(544,'coloc'),(545,'adiant'),(546,'ultim'),(547,'boca'),(548,'fim'),(549,'sera'),(550,'confi'),(551,'real'),(552,'simples'),(553,'ate'),(554,'question'),(555,'segu'),(556,'requent'),(557,'consult'),(558,'prov'),(559,'reten'),(560,'proxim'),(561,'engol'),(562,'inves'),(563,'boat'),(564,'abrir'),(565,'superficial'),(566,'produz'),(567,'red'),(568,'eleg'),(569,'soci');
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
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `musica`
--

LOCK TABLES `musica` WRITE;
/*!40000 ALTER TABLE `musica` DISABLE KEYS */;
INSERT INTO `musica` VALUES (2,'EADGBE','http://www.youtube.com/watch?v=0jRLzJQA0FM','G',3,2,'Foi Por Você'),(3,'EADGBE','','C',4,3,'Sorrir é bom demais'),(6,'EADGBE','','C_SUSTENIDO',3,6,'gfjhgh'),(7,'EADGBE','','G',5,7,'Busque o alto'),(8,'EADGBE','','C_SUSTENIDO',5,8,'fgadgadfgd'),(9,'EADGBE','','C',3,9,'Busque o Alto'),(10,'EADGBE','','C_SUSTENIDO',3,10,'Busque o Alto'),(11,'EADGBE','','D_SUSTENIDO',4,11,'Busque o Alto'),(12,'EADGBE','','D_SUSTENIDO',6,12,'Busque o Alto'),(13,'EADGBE','','G',6,13,'Teste'),(14,'DGCFAD','','G_SUSTENIDO',7,14,'Teste 2'),(15,'EADGBE','','G_SUSTENIDO',8,15,'Removida'),(16,'EADGBE','','F',9,17,'Musica teste'),(17,'EADGBE','','D_SUSTENIDO',5,18,'testes'),(18,'EADGBE','','C_SUSTENIDO',9,19,'testess'),(19,'EADGBE','','C_SUSTENIDO',5,21,'aasas'),(20,'EADGBE','','D',9,23,'dgsfdgfadf'),(21,'EADGBE','','F_SUSTENIDO',3,25,'testesssdf');
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
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `musica_documento`
--

LOCK TABLES `musica_documento` WRITE;
/*!40000 ALTER TABLE `musica_documento` DISABLE KEYS */;
INSERT INTO `musica_documento` VALUES (2,'     @G             @D/F#      @Em7\nEncontrei-me com Jesus no jardim\n      @G           @D/F#    @Em7\nNunca vi nada tão lindo assim\n       @Am          @Am7/G       @D/F#\nMinhas dores entreguei em suas mãos\n    @Am        @Am7/G     @D/F#  @C/D\nE Jesus foi falando pra mim:\n       @G         @D/F#   @Em7\n\"Das feridas que eu recebi,\n      @G     @D/F#       @Em7\nNão saíram sangue, nem dor\n        @Am         @Am7/G     @D/F#\nFoi por isso que o mal eu venci\n       @Am         @Am7/G   @D/F#\nPorque delas só saía     amor,\n             @G        @D9/F#\nFoi sempre o meu amor\n    @G\nFoi por você\n             @G/F                     @C/E\nQue eu me deixei ser tão chagado e ferido\n         @Cm7/Eb                @G\nPor isso sinta-se   amado e querido\n         @C#m5-/7               @Am7    @C/D\nPois é o meu amor que cura sua dor,  ououoh\n    @G\nFoi por você\n        @G7/F                       @C/E\nQue, na cruz,  meu sangue foi derramado\n         @Cm7/Eb                @G/D\nPor isso sinta-se   querido e amado\n         @Em7                   @Am7\nPois é o meu amor que cura sua dor,\n              @C   @G/B  @Bb  @F  @Ab  @Eb @D7/11 @D7\nque cura sua dor”\n         @G     @D/F#     @Em7\nEntão, Jesus pediu-me assim\n       @G              @D/F#      @Em7 (@Em @Em/F# @Em/G @Em/B)\nQue as mágoas que estivessem em mim\n    @Am          @Am7/G     @D/F#\nQue delas não saísse mais dor\n             @Am         @Am7/G  @D/F#\nDe hoje em diante, só saísse  amor\n           @G             @D9/F#\nQue seja sempre assim\n     @G\nFoi por você\n             @G/F                     @C/E\nQue eu me deixei ser tão chagado e ferido\n         @Cm7/Eb                @G\nPor isso sinta-se   amado e querido\n         @C#m5-/7               @Am7    @C/D\nPois é o meu amor que cura sua dor,  ououoh\n    @G\nFoi por você\n        @G7/F                       @C/E\nQue, na cruz,  meu sangue foi derramado\n         @Cm7/Eb                @G/D\nPor isso sinta-se   querido e amado\n         @Em7                   @Am7\nPois é o meu amor que cura sua dor,\n              @C     @G/B\nque cura sua dor”\n\n(intro)\n\n@E|-10/12-15-10----------10--10-10-------------------------------------------\n@B|-------------12-12-13-----------13-12-13~~--10-10-12-13--13-13-12-10-12~~-\n\n\n@E|------------------8-10-------------------------------\n@B|-8-8-10-12--12-12------10~~--10-8-7-8/10~~-8~~-------\n\n\n(refrão)\n\n(1ª Parte)\n\n@E|-10/12-12-10-10----------10--10-10--------------------------------------------\n@B|----------------12-12-13-----------13-12-13~~--10-10-12-13--13-13-12-10-12~~--\n\n\n\n@E|------------------8-10-------------\n@B|-8-8-10-12--12-12------10~~--------\n\n\n(2ª Parte)\n\n@E|-10/12-12-10-10----------10--10-10--------------------------------------------\n@B|----------------12-12-13-----------13-12-13~~--10-10-12-13--13-13-12-10-12~~--\n\n\n\n@E|------------------------------------------------\n@B|-8-8-10-12--12-12-10-8-10~~--10-8-7-8/10~~-8~~--\n\n\n(final)\n\n@E|------------------------------\n@B|-8-8-10-12--12-10-8-7----8~~--\n\n\n    @Am7               @C/D      @G\nÉ o meu amor      que cura sua dor”',10,129,NULL),(3,'                 @C7+			           @Dm7\nComo é bom a gente se encontrar aqui neste lugar\n	@F9	           @C		      @Dm7	@G4/7    @G7\nOnde dois ou mais estão unidos, o Senhor está também\n\n          @Em7	     @Am7		@F7+	      @Fm7\nDe repente brota lá do fundo, algo que o mundo não tem....\n      @C7+		@Am7		 @Dm7	      @F/G     	     @C7+     @F/G\nA alegria de poder dizer: Que maravilha é ser o filho de Deus!\n\n	   @Em7 @Am7       @Em7     @Am7\nAbre seus braços ao Pai de Amor\n   @Dm  @Em  @D/F#  @F/G\nE vê que ele te ama\n	        @Em7 @Am7       @Em7         @Am7\nLança fora a tristeza e não olhe para trás\n   @Dm  @Em  @D/F#  @F/G\nSorrir é bom demais\n		   Intro\nSorrir é bom demais\n',3,42,'@C7+   @F/G (2x)'),(6,'@B @C @D',0,0,'@A @B @C'),(7,'    @G                               @Bm \nUma vida nova em Cristo venha experimentar \n     @C                           @D \nE de coração rendido, dia a dia se entregar \n      @G                         @Bm \nCom os olhos para o alto daqui menos depender \n       @C              \nPara o mundo estamos mortos \n      @C#º                  @D \nNossa vida escondida está em Deus \n  \nNasci pro céu... \n @G              @Am7            @C              @D \nOlhe, olhe mais longe, além do mundo, voe pro céu \n @G               @Am7               @C \nBusque, busque o alto, pôr sobre a vida \n     @Cm                  @G      @Am7, @C, @D \nVeja o trono, onde está Deus! \n  \n(Alçando vôo se lançar) \n       @G                         @Bm \nVem do céu essa alegria que me faz compreender \n      @C                 @D \nVejo além da agonia é certo eu vou vencer \n    @G                            @Bm \nSe levante para Cristo, não se arraste mais ao chão \n      @C                       @C#º \nMeu irmão cabeça erguida, Ele trás a nova vida \n   @D \nO amor e o perdão, olhe pro céu... \n    @C                 @D        @Bm            @E7 \nSe portanto ressuscitastes, buscai tudo que está no alto \n @Am7          @D                @C         @Cm        @G   @Am7, @C, @D \nOnde Cristo é tudo em todos, vem à vida nova se lançar.\n',5,83,'@G @Am @C @D'),(8,'dfagdfgadf',1,1,'adf'),(9,NULL,NULL,NULL,NULL),(10,NULL,NULL,NULL,NULL),(11,NULL,NULL,NULL,NULL),(12,NULL,NULL,NULL,NULL),(13,NULL,NULL,NULL,NULL),(14,NULL,NULL,NULL,NULL),(15,NULL,NULL,NULL,NULL),(16,'@G           @G7                          \n@A Alegria está no Coração,\n    @C                  @G\nde quem já conhece a JESUS.\n@G            @Em\n@A Verdadeira Paz só tem aquele\n    @A              @D7\nque já conhece a JESUS.\n\n\n@G                  @G7           @C            @Cm/F\nO Sentimento mais Precioso que vêm de nosso SENHOR.\n     @G           @D                       @G    @C  @D\nÉ o Amor que só tem quem já conhece a JESUS.\n        @G                   @G7\nPosso Pisar numa Tropa e Saltar as Muralhas \n   @C       @Cm/F\nAleluia, Aleluia!\n        @G                   @G7\nPosso Pisar numa Tropa e Saltar as Muralhas \n   @C       @Cm/F\nAleluia, Aleluia!\n@G                @G7\nEle é a Rocha da minha Salvação,\n     @C             @Cm/F\ncom Ele não há 8mais Condenação.\n        @G                   @G7\nPosso Pisar numa Tropa e Saltar as Muralhas \n   @C       @Cm/F\nAleluia, Aleluia!\n   @G         @C  @G      @G  @Em     @A  @D7\nAleluia,  Aleluia,  Aleluia,  Aleluia.\n\nO Sentimento... \n',10,57,'@G   @D   @G   @C @D'),(17,'@G           @G7                          \n@A Alegria está no Coração,\n    @C                  @G\nde quem já conhece a JESUS.\n@G            @Em\n@A Verdadeira Paz só tem aquele\n    @A              @D7\nque já conhece a JESUS.\n\n\n@G                  @G7           @C            @Cm/F\nO Sentimento mais Precioso que vêm de nosso SENHOR.\n     @G           @D                       @G    @C  @D\nÉ o Amor que só tem quem já conhece a JESUS.\n        @G                   @G7\nPosso Pisar numa Tropa e Saltar as Muralhas \n   @C       @Cm/F\nAleluia, Aleluia!\n        @G                   @G7\nPosso Pisar numa Tropa e Saltar as Muralhas \n   @C       @Cm/F\nAleluia, Aleluia!\n@G                @G7\nEle é a Rocha da minha Salvação,\n     @C             @Cm/F\ncom Ele não há 8mais Condenação.\n        @G                   @G7\nPosso Pisar numa Tropa e Saltar as Muralhas \n   @C       @Cm/F\nAleluia, Aleluia!\n   @G         @C  @G      @G  @Em     @A  @D7\nAleluia,  Aleluia,  Aleluia,  Aleluia.\n\nO Sentimento... \n',10,57,'@G   @D   @G   @C @D'),(18,NULL,NULL,NULL,NULL),(19,'Uma pessoa gosta do país em que vive e não há nada a fazer. Parece-me assim essencial que o país se submeta a segunda volta contra o doutor Mário Soares e que o Professor Doutor Aníbal Cavaco Silva ganhe. Assim como na direita há demasiado medo da segunda volta, na esquerda existem demasiadas esperanças. O risco de Mário Soares ganhar é muito menos grave que a falta de clivagem e definição do país com que inevitavelmente vamos ainda chegar à primeria das voltas, onde só os extremos beneficiam. Por essa altura Jerónimo Sousa, Francisco Louçã e Manuel Alegre ainda parecerão candidatos decentes, as três pessoas que congregam sozinhos os piores traços da nação (ainda assim com aspectos adoráveis, pricipalmente se andamos a ler Goethe). Bem sei que muitos dos que apoiam o doutor Mário Soares nutrem pelo nosso Professor e pelo que ele representa a mesma aversão que aqui deixo contra aquele triunvirato da esquerda. Há que, no entanto, ser altivo quando a isso. Parece-me que falamos de reinos diferentes. A reacção de muita da esquerda contra o Professor não é da mesma natureza que a da direita e da parte sã do PS contra aquele tipo de esquerda: no primeiro caso falamos de um deslocamento cultural, quase de nascimento, de uma incompreensão fundamental de vida, das vidas, dos custos e do valor a atribuir a quem apenas quer ser mais rico que o vizinho, e, muito derivado disso, do lugar e da forma mais inteligente de utilizar em beneficio de todos a inevitabilidade humana de coisas como a inveja, o egoísmo, a ganância, a ambição, finalmente, do sentido a dar à Justiça (assim com letra grande para dar ares) neste caldeirão todo; importa aclarar tudo isto o mais possivel, para que racionalmente nos incompatibilizemos cada vez mais e melhor. No segundo caso, penso que se fala do simples, inescapável e transversal bom senso: Jerónimo é soviético e comunista – os “trabalhadores” para ele são um electrodoméstico das suas ideias do mundo e da sua demagogia - , Louçã fala de fora do poder e do eufemismo \"esquerda moderna\", e, ou abandonaria metade do que diz (que não é muito), ou cairia vítima em três tempos do que há anos vem dizendo (é, nitidamente, um guterres extremado e ainda mais moralista – como inevitavelmente são todos os ateus -, mas cada vez menos extremado, ou, pelo menos, mais escondido, mais esquivo). Manuel Alegre, bem, Manuel Alegre suga-me as palavras. Provavelmente aqui no espaço ninguém concordará comigo, mas o caminho prudente e avisado para Portugal é o da separação das águas do interior do Bloco Central. Só assim nas contas finais ninguém sairá fundamentalmente derrotado, que não há necessidade disso.',5,246,NULL),(20,'O meu Grande Amigo Luis Miguel Rocha diz tudo. Obrigado Luis.\nPerguntava-me um amigo estrangeiro onde estava o nosso nacionalismo e patriotismo? Após alguns instantes de consideração respondi-lhe que não precisamos disso. Ele estranhou, ao passo que lhe expliquei que ser português é muito mais que pertencer a uma comunidade delimitada, é uma maneira de ser. Não queria dizer com isto que não somos patriotas nem nacionalistas, só que isso está de tal forma enraizado e a um nível tão profundo que não temos necessidade de o manifestar. Ao contrário dos outros países, é uma dádiva e não uma conquista e é algo totalmente e plenamente garantido.\nSomos a única nação-estado da Europa. Ele não compreendeu o que significava isso. Quer dizer que a nação é inteiramente compatível com o Estado, não temos divisões que nos demarquem nem separem. Portugal é o país e as ilhas e mais nada. Estamos todos perto uns dos outros no mesmo território terrestre e marítimo. Basta passarmos a fronteira para vermos uma nação com várias regiões (Catalunha, País Basco, Galiza, etc.) que, embora no mesmo território, têm mais coisas a separá-los que a uni-los. Há muitas Espanhas dentro de Espanha e fora também. @A Alemanha e o Reino Unido estão divididos por estados que não se podem ver uns aos outros, uma manta de retalhos em que os canídeos de cada região se mordem com avidez e lançam latidos ameaçadores, a Itália é outro exemplo de uma nação dividida por regiões e povos muito diferentes uns dos outros. @A França idem, idem, a Suíça, Holanda, Bélgica, etc. etc.\nIsso não se passa em Portugal. Portugal é sempre o mesmo, de cima a baixo e nas ilhas. \nTemos outra coisa singular em toda a Europa, uma língua única, de Norte a Sul, à excepção de 300 amigos em Mirandela, que ainda utilizam os resquícios deixados pelo antigo reino Leonês. Mais nenhum outro país beneficia disso. @As pretensas diferenças entre o Norte e o Sul, que o futebol preconiza, não existem. Somos um país uno.\nO meu amigo estranhou. Achava que alguma diferença havia de haver. Respondi-lhe que no Sul talvez se use mais agrião na comida que no Norte.\nSomos um país com identidade, fronteiras permanentes há 900 anos, exceptuando Olivença, estruturado desde os primórdios, de cima para baixo, com pilares indestrutíveis, corremos com muçulmanos, fomos atacados por espanhóis durante 800 anos, resistimos a 4, não 3, invasões francesas, construímos um império, dominamos os mares...\nOs nossos políticos não prestam, é verdade, mas por muito que nos tentem destruir ignoram que enfrentam uma força indestrutível. Eles passarão e nós prevaleceremos. O português funciona muito bem sob pressão. Quando fomos chamados, e aconteceu muitas vezes ao longo da nossa história, a tomar uma posição, a mudar as coisas, a enfrentar os nossos inimigos, nunca viramos as costas e fomos sempre, sem excepção, bem sucedidos.\nO nacionalismo e o patriotismo só está à flor da pele de quem se sente ameaçado e tem de lutar por ele. Nós, portugueses, não temos esse problema. Portugal e ser português é tão natural como a órbita terrestre, o nascer e o pôr-do-sol, os ciclos da lua. Muito poucos beneficiam desse luxo. Apenas dez milhões, encostados ao oceano. Chamam-lhe uma das pontas da Europa, eu chamo-lhe a porta principal do Velho Continente.',7,315,NULL),(21,'O meu Grande Amigo Luis Miguel Rocha diz tudo. Obrigado Luis.\nPerguntava-me um amigo estrangeiro onde estava o nosso nacionalismo e patriotismo? Após alguns instantes de consideração respondi-lhe que não precisamos disso. Ele estranhou, ao passo que lhe expliquei que ser português é muito mais que pertencer a uma comunidade delimitada, é uma maneira de ser. Não queria dizer com isto que não somos patriotas nem nacionalistas, só que isso está de tal forma enraizado e a um nível tão profundo que não temos necessidade de o manifestar. Ao contrário dos outros países, é uma dádiva e não uma conquista e é algo totalmente e plenamente garantido.\nSomos a única nação-estado da Europa. Ele não compreendeu o que significava isso. Quer dizer que a nação é inteiramente compatível com o Estado, não temos divisões que nos demarquem nem separem. Portugal é o país e as ilhas e mais nada. Estamos todos perto uns dos outros no mesmo território terrestre e marítimo. Basta passarmos a fronteira para vermos uma nação com várias regiões (Catalunha, País Basco, Galiza, etc.) que, embora no mesmo território, têm mais coisas a separá-los que a uni-los. Há muitas Espanhas dentro de Espanha e fora também. @A Alemanha e o Reino Unido estão divididos por estados que não se podem ver uns aos outros, uma manta de retalhos em que os canídeos de cada região se mordem com avidez e lançam latidos ameaçadores, a Itália é outro exemplo de uma nação dividida por regiões e povos muito diferentes uns dos outros. @A França idem, idem, a Suíça, Holanda, Bélgica, etc. etc.\nIsso não se passa em Portugal. Portugal é sempre o mesmo, de cima a baixo e nas ilhas. \nTemos outra coisa singular em toda a Europa, uma língua única, de Norte a Sul, à excepção de 300 amigos em Mirandela, que ainda utilizam os resquícios deixados pelo antigo reino Leonês. Mais nenhum outro país beneficia disso. @As pretensas diferenças entre o Norte e o Sul, que o futebol preconiza, não existem. Somos um país uno.\nO meu amigo estranhou. Achava que alguma diferença havia de haver. Respondi-lhe que no Sul talvez se use mais agrião na comida que no Norte.\nSomos um país com identidade, fronteiras permanentes há 900 anos, exceptuando Olivença, estruturado desde os primórdios, de cima para baixo, com pilares indestrutíveis, corremos com muçulmanos, fomos atacados por espanhóis durante 800 anos, resistimos a 4, não 3, invasões francesas, construímos um império, dominamos os mares...\nOs nossos políticos não prestam, é verdade, mas por muito que nos tentem destruir ignoram que enfrentam uma força indestrutível. Eles passarão e nós prevaleceremos. O português funciona muito bem sob pressão. Quando fomos chamados, e aconteceu muitas vezes ao longo da nossa história, a tomar uma posição, a mudar as coisas, a enfrentar os nossos inimigos, nunca viramos as costas e fomos sempre, sem excepção, bem sucedidos.\nO nacionalismo e o patriotismo só está à flor da pele de quem se sente ameaçado e tem de lutar por ele. Nós, portugueses, não temos esse problema. Portugal e ser português é tão natural como a órbita terrestre, o nascer e o pôr-do-sol, os ciclos da lua. Muito poucos beneficiam desse luxo. Apenas dez milhões, encostados ao oceano. Chamam-lhe uma das pontas da Europa, eu chamo-lhe a porta principal do Velho Continente.',7,315,NULL),(22,'A amizade consegue ser tão complexa... \nDeixa uns desanimados, outros bem felizes... \nÉ a alimentação dos fracos \nÉ o reino dos fortes \n\nFaz-nos cometer erros \nOs fracos deixam se ir abaixo \nOs fortes erguem sempre a cabeça \nos assim assim assumem-os \n\nSem pensar conquistamos \nO mundo geral \ne construimos o nosso pequeno lugar \ndeixando brilhar cada estrelinha \n\nEstrelinhas... \nDoces, sensiveis, frias, ternurentas... \nMas sempre presentes em qualquer parte \nOs donos da Amizade',3,47,NULL),(23,'A amizade consegue ser tão complexa... \nDeixa uns desanimados, outros bem felizes... \nÉ a alimentação dos fracos \nÉ o reino dos fortes \n\nFaz-nos cometer erros \nOs fracos deixam se ir abaixo \nOs fortes erguem sempre a cabeça \nos assim assim assumem-os \n\nSem pensar conquistamos \nO mundo geral \ne construimos o nosso pequeno lugar \ndeixando brilhar cada estrelinha \n\nEstrelinhas... \nDoces, sensiveis, frias, ternurentas... \nMas sempre presentes em qualquer parte \nOs donos da Amizade',3,47,NULL),(24,'Boatos produzem mentes ignorantes que, ao invés de estudarem e consultarem fontes confiáveis, simplesmente, por preguiça, engolem sem questionar qualquer informação requentada. Chegam até fazerem cara de intelectuais para abrirem o bocão e falarem um monte de besteiras... Na real, ultimamente, falar besteira passou a ser um sinal de status nas redes sociais...\n\nO que constatamos é o resultado de um condicionamento colocado sobre as costas de nossos jovens por muitos anos, treinando-os simplesmente para armazenarem informações a fim de fazerem provas, quantificando e avaliando de forma superficial os resultados para elegerem os que serão capazes de seguirem adiante para o próximo nível de condicionamento e reten',4,69,NULL),(25,'Boatos produzem mentes ignorantes que, ao invés de estudarem e consultarem fontes confiáveis, simplesmente, por preguiça, engolem sem questionar qualquer informação requentada. Chegam até fazerem cara de intelectuais para abrirem o bocão e falarem um monte de besteiras... Na real, ultimamente, falar besteira passou a ser um sinal de status nas redes sociais...\n\nO que constatamos é o resultado de um condicionamento colocado sobre as costas de nossos jovens por muitos anos, treinando-os simplesmente para armazenarem informações a fim de fazerem provas, quantificando e avaliando de forma superficial os resultados para elegerem os que serão capazes de seguirem adiante para o próximo nível de condicionamento e reten',4,69,NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `musica_tipos`
--

LOCK TABLES `musica_tipos` WRITE;
/*!40000 ALTER TABLE `musica_tipos` DISABLE KEYS */;
INSERT INTO `musica_tipos` VALUES (4,'ACAO_DE_GRACAS',2),(5,'PALESTRA',2),(6,'ADORACAO',2),(7,'ENTRADA',3),(8,'PAZ',3),(9,'FINAL',3),(13,'ATO_PENITENCIAL',6),(14,'ENTRADA',7),(15,'FINAL',7),(16,'ATO_PENITENCIAL',8),(17,'ACAO_DE_GRACAS',9),(18,'ENTRADA',10),(19,'ATO_PENITENCIAL',11),(20,'ACAO_DE_GRACAS',12),(21,'PALESTRA',12),(22,'COMUNHAO',13),(23,'FINAL',13),(24,'ENTRADA',14),(25,'FINAL',14),(26,'ATO_PENITENCIAL',15),(27,'GLORIA',15),(28,'ACLAMACAO',15),(29,'GLORIA',16),(30,'ACLAMACAO',16),(31,'ENTRADA',17),(32,'COMUNHAO',18),(33,'ACAO_DE_GRACAS',18),(34,'ACAO_DE_GRACAS',19),(35,'RESPOSTA',19),(36,'ATO_PENITENCIAL',20),(37,'ATO_PENITENCIAL',21),(38,'COMUNHAO',21);
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

-- Dump completed on 2014-03-06 16:49:45
