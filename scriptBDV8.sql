-- MySQL dump 10.13  Distrib 5.7.21, for Win64 (x86_64)
--
-- Host: localhost    Database: dbgrandbazar
-- ------------------------------------------------------
-- Server version	5.7.21-log

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
-- Table structure for table `articleperime`
--

DROP TABLE IF EXISTS `articleperime`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `articleperime` (
  `ID` varchar(45) NOT NULL,
  `QuantiteJete` int(11) NOT NULL,
  `Date` date NOT NULL,
  `Matricule` int(11) NOT NULL,
  `CodeBarre` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `CodeBarreAP_idx` (`CodeBarre`),
  KEY `MatriculeAP_idx` (`Matricule`),
  CONSTRAINT `CodeBarreAP` FOREIGN KEY (`CodeBarre`) REFERENCES `typearticle` (`CodeBarre`),
  CONSTRAINT `MatriculeAP` FOREIGN KEY (`Matricule`) REFERENCES `membredupersonnel` (`Matricule`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `articleperime`
--

LOCK TABLES `articleperime` WRITE;
/*!40000 ALTER TABLE `articleperime` DISABLE KEYS */;
/*!40000 ALTER TABLE `articleperime` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categoriearticle`
--

DROP TABLE IF EXISTS `categoriearticle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `categoriearticle` (
  `ID` varchar(45) NOT NULL,
  `Libelle` varchar(45) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categoriearticle`
--

LOCK TABLES `categoriearticle` WRITE;
/*!40000 ALTER TABLE `categoriearticle` DISABLE KEYS */;
INSERT INTO `categoriearticle` VALUES ('1','Plat');
INSERT INTO `categoriearticle` VALUES ('2','Dessert');
INSERT INTO `categoriearticle` VALUES ('3','Entré');
/*!40000 ALTER TABLE `categoriearticle` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `client`
--

DROP TABLE IF EXISTS `client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `client` (
  `NumClient` int(11) NOT NULL,
  `Nom` varchar(45) NOT NULL,
  `Prenom` varchar(45) NOT NULL,
  `DateNaissance` date NOT NULL,
  `CodePostal` int(11) NOT NULL,
  `Localite` varchar(45) NOT NULL,
  `Rue` varchar(45) NOT NULL,
  `DateCreationCompte` date NOT NULL,
  PRIMARY KEY (`NumClient`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `client`
--
INSERT INTO `dbgrandbazar`.`client` (`NumClient`, `Nom`, `Prenom`, `DateNaissance`, `CodePostal`, `Localite`, `Rue`, `DateCreationCompte`) VALUES (1, "POZZI", "Alessandro", str_to_date("July 14 1996", "%M %d %Y"), 5500, "Falmagne", "Place du Bati", sysdate()), (2, "DUPONT", "Charles", str_to_date("December 26 1993", "%M %d %Y"), 6900, "Baumont", "Place du noisettier", sysdate()), (3, "RENARD", "Auguste", str_to_date("April 12 1980", "%M %d %Y"), 3400, "Miamire", "Rue du cheval noir", str_to_date("Mai 10 2018", "%M %d %Y"));

LOCK TABLES `client` WRITE;
/*!40000 ALTER TABLE `client` DISABLE KEYS */;
/*!40000 ALTER TABLE `client` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cuisinier`
--

DROP TABLE IF EXISTS `cuisinier`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cuisinier` (
  `Matricule_Cui` int(11) NOT NULL,
  PRIMARY KEY (`Matricule_Cui`),
  CONSTRAINT `Matricule_CuiC` FOREIGN KEY (`Matricule_Cui`) REFERENCES `membredupersonnel` (`Matricule`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cuisinier`
--

LOCK TABLES `cuisinier` WRITE;
/*!40000 ALTER TABLE `cuisinier` DISABLE KEYS */;
INSERT INTO `cuisinier` VALUES (2),(3),(4),(6);
/*!40000 ALTER TABLE `cuisinier` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fournisseur`
--

DROP TABLE IF EXISTS `fournisseur`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fournisseur` (
  `NumeroTVA` int(11) NOT NULL,
  `Nom` varchar(45) NOT NULL,
  `Localite` varchar(45) NOT NULL,
  `CodePostal` int(11) NOT NULL,
  `Rue` varchar(45) NOT NULL,
  PRIMARY KEY (`NumeroTVA`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fournisseur`
--

LOCK TABLES `fournisseur` WRITE;
/*!40000 ALTER TABLE `fournisseur` DISABLE KEYS */;
/*!40000 ALTER TABLE `fournisseur` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ingredient`
--

DROP TABLE IF EXISTS `ingredient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ingredient` (
  `Nom` varchar(45) NOT NULL,
  `CodeBarre` int(11) NOT NULL,
  `QuantitePortion` int(11) NOT NULL,
  PRIMARY KEY (`Nom`,`CodeBarre`),
  KEY `CodeBarreI_idx` (`CodeBarre`),
  CONSTRAINT `CodeBarreI` FOREIGN KEY (`CodeBarre`) REFERENCES `typearticle` (`CodeBarre`),
  CONSTRAINT `NomI` FOREIGN KEY (`Nom`) REFERENCES `recette` (`Nom`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ingredient`
--

LOCK TABLES `ingredient` WRITE;
/*!40000 ALTER TABLE `ingredient` DISABLE KEYS */;
/*!40000 ALTER TABLE `ingredient` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ligneticket`
--

DROP TABLE IF EXISTS `ligneticket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ligneticket` (
  `CodeBarre` int(11) NOT NULL,
  `NumTicket` int(11) NOT NULL,
  `Quantite` int(11) NOT NULL,
  `PrixReel` double NOT NULL,
  PRIMARY KEY (`CodeBarre`,`NumTicket`),
  KEY `NumTicket_idx` (`NumTicket`),
  CONSTRAINT `CodeBarre` FOREIGN KEY (`CodeBarre`) REFERENCES `typearticle` (`CodeBarre`),
  CONSTRAINT `NumTicket` FOREIGN KEY (`NumTicket`) REFERENCES `ticket` (`NumTicket`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ligneticket`
--
INSERT INTO `dbgrandbazar`.`ligneticket` (`CodeBarre`, `NumTicket`, `Quantite`, `PrixReel`) VALUES (1, 1, 20, 50.50), (2, 2, 17, 5.5), (3, 3, 10, 3.4);

LOCK TABLES `ligneticket` WRITE;
/*!40000 ALTER TABLE `ligneticket` DISABLE KEYS */;
/*!40000 ALTER TABLE `ligneticket` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lot`
--

DROP TABLE IF EXISTS `lot`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lot` (
  `IdLot` int(11) NOT NULL,
  `DatePeremption` date DEFAULT NULL,
  `Quantite` int(11) NOT NULL,
  `CodeLot` int(11) DEFAULT NULL,
  `DateFourniturePrevue` date DEFAULT NULL,
  `DateCommande` date NOT NULL,
  `CodeBarre` int(11) NOT NULL,
  `Matricule` int(11) NOT NULL,
  `NumeroTVA` int(11) NOT NULL,
  PRIMARY KEY (`IdLot`),
  KEY `CodeBarreLot_idx` (`CodeBarre`),
  KEY `MatriculeLot_idx` (`Matricule`),
  KEY `NumeroTVALot_idx` (`NumeroTVA`),
  CONSTRAINT `CodeBarreLot` FOREIGN KEY (`CodeBarre`) REFERENCES `typearticle` (`CodeBarre`),
  CONSTRAINT `MatriculeLot` FOREIGN KEY (`Matricule`) REFERENCES `membredupersonnel` (`Matricule`),
  CONSTRAINT `NumeroTVALot` FOREIGN KEY (`NumeroTVA`) REFERENCES `fournisseur` (`NumeroTVA`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lot`
--

LOCK TABLES `lot` WRITE;
/*!40000 ALTER TABLE `lot` DISABLE KEYS */;
/*!40000 ALTER TABLE `lot` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `membredupersonnel`
--

DROP TABLE IF EXISTS `membredupersonnel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `membredupersonnel` (
  `Nom` varchar(45) NOT NULL,
  `Prenom` varchar(45) NOT NULL,
  `DateNaissance` date NOT NULL,
  `Localite` varchar(80) NOT NULL,
  `CodePostal` int(11) NOT NULL,
  `Rue` varchar(80) NOT NULL,
  `Matricule` int(11) NOT NULL AUTO_INCREMENT,
  `DateEntree` date NOT NULL,
  `DateSortie` date DEFAULT NULL,
  PRIMARY KEY (`Matricule`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `membredupersonnel`
--

LOCK TABLES `membredupersonnel` WRITE;
/*!40000 ALTER TABLE `membredupersonnel` DISABLE KEYS */;
INSERT INTO `membredupersonnel` VALUES ('Dumond','Jean','1980-04-30','Graide',5555,'Rue de la station',1,'2000-04-30',NULL),('Tartanpon','Patrick','1987-05-30','Namur',5000,'Rue de la farondole',2,'2003-06-30',NULL),('ROUX','Alfred','1980-04-30','Namur',5000,'Rue de la fontaine',3,'2007-03-24',NULL),('MARTIN','Martine','1976-03-20','Namur',5000,'Rue du cerisier',4,'2004-05-23',NULL),('BERNARD','Thierry','1993-02-14','Namur',5000,'Rue du cerisier',5,'2003-06-27',NULL),('GIRARD','Pauline','1978-06-26','Namur',5000,'Rue du coussin',6,'2001-04-17',NULL),('MOREL','Jackeline','1979-06-26','Namur',5000,'Rue de la foire',7,'1999-01-27',NULL), ('AL SORNA','Vaelin','1971-02-03','Spa',4893,'Rue de la fontaine',8,'2015-09-07',sysdate());
/*!40000 ALTER TABLE `membredupersonnel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ordrepreparation`
--

DROP TABLE IF EXISTS `ordrepreparation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ordrepreparation` (
  `Date` date NOT NULL,
  `NumeroSequentiel` int(11) NOT NULL AUTO_INCREMENT,
  `QuantitePrevue` int(11) NOT NULL,
  `QuantiteProduite` int(11) DEFAULT NULL,
  `DateVente` date DEFAULT NULL,
  `DatePreparation` datetime DEFAULT NULL,
  `Remarque` varchar(45) DEFAULT NULL,
  `EstUrgent` tinyint(4) NOT NULL,
  `Nom` varchar(45) NOT NULL,
  `CodeBarre` int(11) DEFAULT NULL,
  `Matricule_Cui` int(11) DEFAULT NULL,
  `Matricule_Res` int(11) NOT NULL,
  PRIMARY KEY (`NumeroSequentiel`),
  KEY `Nom_idx` (`Nom`),
  KEY `CodeBarre_idx` (`CodeBarre`),
  KEY `Matricule_CuiOP_idx` (`Matricule_Cui`),
  KEY `Matricule_Res_idx` (`Matricule_Res`),
  CONSTRAINT `CodeBarreOP` FOREIGN KEY (`CodeBarre`) REFERENCES `typearticle` (`CodeBarre`),
  CONSTRAINT `Matricule_CuiOP` FOREIGN KEY (`Matricule_Cui`) REFERENCES `cuisinier` (`Matricule_Cui`),
  CONSTRAINT `Matricule_Res` FOREIGN KEY (`Matricule_Res`) REFERENCES `responsablevente` (`MatriculeRes`),
  CONSTRAINT `Nom` FOREIGN KEY (`Nom`) REFERENCES `recette` (`Nom`),
  CONSTRAINT `NombresNonNull`		check (`quantitePrevue` > 0 and `matricule_Res` > 0), 
  CONSTRAINT `NombresNullOuSupea0`	check ((`quantiteProduite` = null or `quantiteProduite` > 0) and (`codeBarre` = null or `codeBarre` > 0) and (`matricule_Cui` = null or `matricule_Cui` > 0))
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ordrepreparation`
--

LOCK TABLES `ordrepreparation` WRITE;
/*!40000 ALTER TABLE `ordrepreparation` DISABLE KEYS */;
INSERT INTO `ordrepreparation` VALUES ('2018-04-25',null,45,45,'2018-05-10','2018-05-06 18:06:00',NULL,0,'Allumettes au foie gras',1,3,5),('2018-04-25',null,4,NULL,NULL,'2018-04-25 18:07:13',NULL,0,'Croustillant au fromage',NULL,NULL,1),('2018-04-25',null,5,NULL,NULL,'2018-04-25 18:07:21',NULL,0,'Allumettes au foie gras',NULL,NULL,1), (sysdate(), null, 25,45, '2018-05-13','2018-05-10 15:15:18','Manque de sel',1,'Boeuf bourguignon',3,6,1);
/*!40000 ALTER TABLE `ordrepreparation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recette`
--

DROP TABLE IF EXISTS `recette`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `recette` (
  `Nom` varchar(45) NOT NULL,
  `DLC` int(11) NOT NULL,
  `Descriptif` varchar(45) NOT NULL,
  PRIMARY KEY (`Nom`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recette`
--

LOCK TABLES `recette` WRITE;
/*!40000 ALTER TABLE `recette` DISABLE KEYS */;
INSERT INTO `recette` VALUES ('Allumettes au foie gras',4568,'Pâte feuilletée au sésame et foie gras'),('Croustillant au fromage',747,'Fromage roulé à la feuille de brick et poêlé'),('Plat de pirate',1345,'Épluchez et émincez l’échalote'), ('Boeuf bourguignon',412,'Du boeuf et des ognons en quentité');
/*!40000 ALTER TABLE `recette` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservation`
--

DROP TABLE IF EXISTS `reservation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reservation` (
  `numeroSequentiel` int(11) NOT NULL,
  `codeBarre` int(11) NOT NULL,
  `quantiteReserve` int(11) NOT NULL,
  PRIMARY KEY (`numeroSequentiel`,`codeBarre`),
  KEY `codeBarreR_idx` (`codeBarre`),
  CONSTRAINT `codeBarreR` FOREIGN KEY (`codeBarre`) REFERENCES `typearticle` (`CodeBarre`),
  CONSTRAINT `numeroSequentielR` FOREIGN KEY (`numeroSequentiel`) REFERENCES `ordrepreparation` (`NumeroSequentiel`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservation`
--

LOCK TABLES `reservation` WRITE;
/*!40000 ALTER TABLE `reservation` DISABLE KEYS */;
/*!40000 ALTER TABLE `reservation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `responsablevente`
--

DROP TABLE IF EXISTS `responsablevente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `responsablevente` (
  `MatriculeRes` int(11) NOT NULL,
  PRIMARY KEY (`MatriculeRes`),
  CONSTRAINT `MatriculeResC` FOREIGN KEY (`MatriculeRes`) REFERENCES `membredupersonnel` (`Matricule`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `responsablevente`
--

LOCK TABLES `responsablevente` WRITE;
/*!40000 ALTER TABLE `responsablevente` DISABLE KEYS */;
INSERT INTO `responsablevente` VALUES (1),(5);
/*!40000 ALTER TABLE `responsablevente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ticket`
--

DROP TABLE IF EXISTS `ticket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ticket` (
  `NumTicket` int(11) NOT NULL,
  `Date` datetime NOT NULL,
  `NumClient` int(11) DEFAULT NULL,
  `Matricule` int(11) NOT NULL,
  PRIMARY KEY (`NumTicket`),
  KEY `Matricule_idx` (`Matricule`),
  KEY `NumClient_idx` (`NumClient`),
  CONSTRAINT `Matricule` FOREIGN KEY (`Matricule`) REFERENCES `membredupersonnel` (`Matricule`),
  CONSTRAINT `NumClient` FOREIGN KEY (`NumClient`) REFERENCES `client` (`NumClient`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticket`
--
INSERT INTO `dbgrandbazar`.`ticket` (`NumTicket`, `Date`, `NumClient`, `Matricule`) VALUES (1, sysdate(), 1, 5), (2, '2018-05-13 14:30:00', 2, 7), (3, '2018-04-26 11:52:41', 3, 4);

LOCK TABLES `ticket` WRITE;
/*!40000 ALTER TABLE `ticket` DISABLE KEYS */;
/*!40000 ALTER TABLE `ticket` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `typearticle`
--

DROP TABLE IF EXISTS `typearticle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `typearticle` (
  `CodeBarre` int(11) NOT NULL,
  `Libelle` varchar(45) NOT NULL,
  `Prix` double NOT NULL,
  `QuantiteEnStock` int(11) NOT NULL,
  `DatePromotionDebut` date DEFAULT NULL,
  `DatePromotionFin` date DEFAULT NULL,
  `EstPerisable` tinyint(4) NOT NULL,
  `QuantiteMinimal` int(11) DEFAULT NULL,
  `ID` varchar(45) NOT NULL,
  PRIMARY KEY (`CodeBarre`),
  KEY `ID_idx` (`ID`),
  CONSTRAINT `ID` FOREIGN KEY (`ID`) REFERENCES `categoriearticle` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `typearticle`
--

LOCK TABLES `typearticle` WRITE;
/*!40000 ALTER TABLE `typearticle` DISABLE KEYS */;
INSERT INTO `typearticle` VALUES (1,'Plat de pirate',4,0,NULL,NULL,1,NULL,'1'), (2,'Scampis',6.50,50,'2018-04-25',sysdate(),1,10,'3'), (3,'Oignon',3.90,5,NULL,NULL,1,5,'2');
/*!40000 ALTER TABLE `typearticle` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-04-25 18:08:27
