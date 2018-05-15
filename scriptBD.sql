DROP TABLE IF EXISTS `reservation`;
DROP TABLE IF EXISTS `lot`;
DROP TABLE IF EXISTS `ordrepreparation`;
DROP TABLE IF EXISTS `ligneticket`;
DROP TABLE IF EXISTS `ticket`;
DROP TABLE IF EXISTS `ingredient`;
DROP TABLE IF EXISTS `recette`;
DROP TABLE IF EXISTS `fournisseur`;
DROP TABLE IF EXISTS `client`;
DROP TABLE IF EXISTS `articleperime`;
DROP TABLE IF EXISTS `cuisinier`;
DROP TABLE IF EXISTS `responsablevente`;
DROP TABLE IF EXISTS `membredupersonnel`;
DROP TABLE IF EXISTS `typearticle`;
DROP TABLE IF EXISTS `categoriearticle`;

CREATE TABLE `categoriearticle` (
  `ID` int(10) NOT NULL auto_increment,
  `Libelle` varchar(45) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE `typearticle` (
  `CodeBarre` int(11) NOT NULL auto_increment,
  `Libelle` varchar(45) NOT NULL,
  `Prix` double NOT NULL,
  `QuantiteEnStock` int(11) NOT NULL,
  `DatePromotionDebut` date DEFAULT NULL,
  `DatePromotionFin` date DEFAULT NULL,
  `EstPerisable` tinyint(4) NOT NULL,
  `QuantiteMinimal` int(11) DEFAULT NULL,
  `ID` int(10) NOT NULL,
  `EstUnePreparation` tinyint(4) NOT NULL,
  PRIMARY KEY (`CodeBarre`),
  KEY `ID_idx` (`ID`),
  CONSTRAINT `ID` FOREIGN KEY (`ID`) REFERENCES `categoriearticle` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

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

CREATE TABLE `responsablevente` (
  `MatriculeRes` int(11) NOT NULL,
  PRIMARY KEY (`MatriculeRes`),
  CONSTRAINT `MatriculeResC` FOREIGN KEY (`MatriculeRes`) REFERENCES `membredupersonnel` (`Matricule`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `cuisinier` (
  `Matricule_Cui` int(11) NOT NULL,
  PRIMARY KEY (`Matricule_Cui`),
  CONSTRAINT `Matricule_CuiC` FOREIGN KEY (`Matricule_Cui`) REFERENCES `membredupersonnel` (`Matricule`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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

CREATE TABLE `client` (
  `NumClient` int(11) NOT NULL auto_increment,
  `Nom` varchar(45) NOT NULL,
  `Prenom` varchar(45) NOT NULL,
  `DateNaissance` date NOT NULL,
  `CodePostal` int(11) NOT NULL,
  `Localite` varchar(45) NOT NULL,
  `Rue` varchar(45) NOT NULL,
  `DateCreationCompte` date NOT NULL,
  PRIMARY KEY (`NumClient`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE `fournisseur` (
  `NumeroTVA` int(11) NOT NULL,
  `Nom` varchar(45) NOT NULL,
  `Localite` varchar(45) NOT NULL,
  `CodePostal` int(11) NOT NULL,
  `Rue` varchar(45) NOT NULL,
  PRIMARY KEY (`NumeroTVA`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `recette` (
  `Nom` varchar(45) NOT NULL,
  `DLC` int(11) NOT NULL,
  `Descriptif` varchar(45) NOT NULL,
  PRIMARY KEY (`Nom`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `ingredient` (
  `Nom` varchar(45) NOT NULL,
  `CodeBarre` int(11) NOT NULL,
  `QuantitePortion` int(11) NOT NULL,
  PRIMARY KEY (`Nom`,`CodeBarre`),
  KEY `CodeBarreI_idx` (`CodeBarre`),
  CONSTRAINT `CodeBarreI` FOREIGN KEY (`CodeBarre`) REFERENCES `typearticle` (`CodeBarre`),
  CONSTRAINT `NomI` FOREIGN KEY (`Nom`) REFERENCES `recette` (`Nom`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `ticket` (
  `NumTicket` int(11) NOT NULL auto_increment,
  `Date` datetime NOT NULL,
  `NumClient` int(11) DEFAULT NULL,
  `Matricule` int(11) NOT NULL,
  PRIMARY KEY (`NumTicket`),
  KEY `Matricule_idx` (`Matricule`),
  KEY `NumClient_idx` (`NumClient`),
  CONSTRAINT `Matricule` FOREIGN KEY (`Matricule`) REFERENCES `membredupersonnel` (`Matricule`),
  CONSTRAINT `NumClient` FOREIGN KEY (`NumClient`) REFERENCES `client` (`NumClient`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

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

CREATE TABLE `reservation` (
  `numeroSequentiel` int(11) NOT NULL,
  `codeBarre` int(11) NOT NULL,
  `quantiteReserve` int(11) NOT NULL,
  PRIMARY KEY (`numeroSequentiel`,`codeBarre`),
  KEY `codeBarreR_idx` (`codeBarre`),
  CONSTRAINT `codeBarreR` FOREIGN KEY (`codeBarre`) REFERENCES `typearticle` (`CodeBarre`),
  CONSTRAINT `numeroSequentielR` FOREIGN KEY (`numeroSequentiel`) REFERENCES `ordrepreparation` (`NumeroSequentiel`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `categoriearticle` VALUES (null,'Plat');
INSERT INTO `categoriearticle` VALUES (null,'Dessert');
INSERT INTO `categoriearticle` VALUES (null,'Entré');
INSERT INTO `categoriearticle` VALUES (null,'Bio');
INSERT INTO `categoriearticle` VALUES (null,'Surgele');





INSERT INTO `typearticle`
VALUES (null,'Plat de pirate',4,0,NULL,NULL,1,NULL,1,1),
(null,'Scampis',6.50,50,'2018-04-25',sysdate(),1,10,1,0),
(null,'Oignon',3.90,5,NULL,NULL,1,5,2,0),
(null,'Allumettes au foie gras',32.34,7,NULL,NULL,1,5,1,1),
(null,'Boeuf bourguignon',14.34,3,NULL,NULL,1,5,1,1),
(null,'Croustillant au fromage',3.90,100,NULL,NULL,1,5,3,1),
(null,'Pot-au-feu',11.90,11,NULL,NULL,1,5,1,1),
(null,'Poireau',1.99,50,NULL,NULL,1,25,2,0),
(null,'Pâté en croute',8.99,2,NULL,NULL,1,5,5,1);


INSERT INTO `membredupersonnel`
VALUES ('Dumond','Jean','1980-04-30','Graide',5555,'Rue de la station',1,'2000-04-30',NULL),
('Tartanpon','Patrick','1987-05-30','Namur',5000,'Rue de la farondole',2,'2003-06-30',NULL),
('ROUX','Alfred','1980-04-30','Namur',5000,'Rue de la fontaine',3,'2007-03-24',NULL),
('MARTIN','Martine','1976-03-20','Namur',5000,'Rue du cerisier',4,'2004-05-23',NULL),
('BERNARD','Thierry','1993-02-14','Namur',5000,'Rue du cerisier',5,'2003-06-27',NULL),
('GIRARD','Pauline','1978-06-26','Namur',5000,'Rue du coussin',6,'2001-04-17',NULL),
('MOREL','Jackeline','1979-06-26','Namur',5000,'Rue de la foire',7,'1999-01-27',NULL),
('AL SORNA','Vaelin','1971-02-03','Spa',4893,'Rue de la fontaine',8,'2015-09-07',sysdate());

INSERT INTO `responsablevente` VALUES (1),(5), (7);

INSERT INTO `cuisinier` VALUES (2),(3),(4),(6);

INSERT INTO `dbgrandbazar`.`client` (`NumClient`, `Nom`, `Prenom`, `DateNaissance`, `CodePostal`, `Localite`, `Rue`, `DateCreationCompte`) 
VALUES (null, "HUBERT", "Julius", '1996-07-14', 5500, "Falmagne", "Place du Bati", sysdate()),
(null, "DUPONT", "Charles", '1996-11-26', 6900, "Baumont", "Place du noisettier", sysdate()),
(null, "RENARD", "Auguste", '1980-04-12', 3400, "Miamire", "Rue du cheval noir", '2018-05-10'),
(null, "LOINVOYANT", "Gustave", '1965-01-10', 3100, "Malmedy", "Rue du cheval blanc", '2008-07-03');


INSERT INTO `recette` 
VALUES ('Allumettes au foie gras',4568,'Pâte feuilletée au sésame et foie gras'),
('Croustillant au fromage',747,'Fromage roulé à la feuille de brick et poêlé'),
('Plat de pirate',1345,'Épluchez et émincez l’échalote'),
('Boeuf bourguignon',412,'Du boeuf et des ognons en quantité'),
('Pot-au-feu',33,'Viande, légumes et beaucoup d\'épices'),
('Pâté en croute',159,'Viande hachée, beurre et crème');

INSERT INTO `dbgrandbazar`.`ticket` (`NumTicket`, `Date`, `NumClient`, `Matricule`)
VALUES (null, sysdate(), 1, 5),
(null, '2018-05-13 14:30:00', 1, 7),
(null, '2018-04-26 11:52:41', 4, 1), 
(null, '2018-04-26 11:52:41', 3, 4), 
(null, '2018-04-26 11:52:41', 3, 5), 
(null, '2018-04-26 11:52:41', 1, 1), 
(null, sysdate(), 3, 4), 
(null, '2018-04-26 11:52:41', 2, 4), 
(null, '2018-04-26 11:52:41', 4, 5);

INSERT INTO `dbgrandbazar`.`ligneticket` (`CodeBarre`, `NumTicket`, `Quantite`, `PrixReel`) 
VALUES (1, 7, 20, 50.50),
(2, 1, 17, 5.5),
(3, 1, 4, 3.4),
(2, 2, 23, 5.5),
(2, 3, 1, 5.5),
(6, 4, 7, 35.0),
(3, 5, 5, 3.4),
(5, 6, 6, 1.9),
(1, 6, 11, 3.4),
(7, 7, 3, 12.3),
(2, 8, 9, 5.5),
(8, 9, 8, 2.9),
(8, 4, 10, 2.9);


INSERT INTO `ordrepreparation` 
VALUES ('2018-04-25',null,45,45,'2018-05-10','2018-05-06 18:06:00',NULL,0,'Allumettes au foie gras',1,3,5),
('2018-04-25',null,4,NULL,NULL,'2018-04-25 18:07:13',NULL,0,'Croustillant au fromage',NULL,NULL,1),
('2018-04-25',null,5,NULL,NULL,'2018-04-25 18:07:21',NULL,0,'Allumettes au foie gras',NULL,NULL,5),
(sysdate(), null, 25,45, '2018-05-13','2018-05-10 15:15:18','Manque de sel',1,'Boeuf bourguignon',5,6,1),
('2018-05-14',null,108,250,'2018-05-15','2018-04-30 15:30:21',NULL,1,'Pot-au-feu',7,6,1),
('2018-05-03',null,23,24,NULL,'2018-05-01 20:33:21','Un délice !',0,'Pâté en croute',9,4,5),
('2018-05-03',null,74,NULL,NULL,'2018-05-11 11:50:21','Peut faire mieux',0,'Pâté en croute',9,null,1),
(sysdate(),null,3,NULL,NULL,null,NULL,1,'Pot-au-feu',NULL,4,5), 
('2018-05-13',null,9,18,'2018-05-14',null,NULL,1,'Boeuf bourguignon',5,null,7),

('2018-05-07',null,56,56,'2018-05-01','2018-04-30 11:24:20','Peut faire mieux',0,'Allumettes au foie gras',null,2,1),
('2018-05-07',null,32,32,'2018-05-01','2018-04-30 10:49:20',null,0,'Allumettes au foie gras',null,2,1),
('2018-05-07',null,32,32,'2018-05-15','2018-05-14 10:28:20',null,2,'Allumettes au foie gras',null,2,1),


('2018-05-07',null,3,null,null,'2018-05-07 13:24:20',null,2,'Pot-au-feu',null,null,1),
('2018-05-07',null,3,null,null,'2018-05-07 13:24:20',null,2,'Pot-au-feu',null,null,1),
('2018-05-07',null,3,null,null,'2018-05-07 13:24:20',null,2,'Pot-au-feu',null,null,1),
('2018-05-07',null,3,null,null,'2018-05-07 13:24:20',null,2,'Pot-au-feu',null,null,1),
('2018-05-07',null,3,null,null,'2018-05-07 14:24:20',null,2,'Pot-au-feu',null,null,1),
('2018-05-07',null,3,null,null,'2018-05-07 14:24:20',null,2,'Pot-au-feu',null,null,1),
('2018-05-07',null,3,null,null,'2018-05-07 14:24:20',null,2,'Pot-au-feu',null,null,1),
('2018-05-07',null,3,null,null,'2018-05-07 14:24:20',null,2,'Pot-au-feu',null,null,1),
('2018-05-07',null,3,null,null,'2018-05-07 14:24:20',null,2,'Pot-au-feu',null,null,1),
('2018-05-07',null,3,null,null,'2018-05-07 14:24:20',null,2,'Pot-au-feu',null,null,1),
('2018-05-07',null,3,null,null,'2018-05-07 15:24:20',null,2,'Pot-au-feu',null,null,1),
('2018-05-07',null,3,null,null,'2018-05-07 15:24:20',null,2,'Pot-au-feu',null,null,1),
('2018-05-07',null,3,null,null,'2018-05-07 15:24:20',null,2,'Pot-au-feu',null,null,1),
('2018-05-07',null,8,null,null,'2018-05-07 15:24:20',null,2,'Pot-au-feu',null,null,1),
('2018-05-07',null,7,null,null,'2018-05-07 15:24:20',null,2,'Pot-au-feu',null,null,1),
('2018-05-07',null,6,null,null,'2018-05-07 16:24:20',null,2,'Pot-au-feu',null,null,1),
('2018-05-07',null,5,null,null,'2018-05-07 16:24:20',null,2,'Pot-au-feu',null,null,1),
('2018-05-07',null,4,null,null,'2018-05-07 16:24:20',null,2,'Pot-au-feu',null,null,1),
('2018-05-07',null,3,null,null,'2018-05-07 16:24:20',null,2,'Pot-au-feu',null,null,1),
('2018-05-07',null,2,null,null,'2018-05-07 16:24:20',null,2,'Pot-au-feu',null,null,1),
('2018-05-07',null,1,null,null,'2018-05-07 16:24:20',null,2,'Pot-au-feu',null,null,1),
('2018-05-07',null,2,null,null,'2018-05-07 16:24:20',null,2,'Pot-au-feu',null,null,1),
('2018-05-07',null,3,null,null,'2018-05-07 12:24:20',null,2,'Pot-au-feu',null,null,1),
('2018-05-07',null,5,null,null,'2018-05-07 13:24:20',null,2,'Pot-au-feu',null,null,1),
('2018-05-07',null,35,null,null,'2018-05-07 12:24:20',null,2,'Pot-au-feu',null,null,1),
('2018-05-07',null,4,null,null,'2018-05-07 13:24:20',null,2,'Pot-au-feu',null,null,1),
('2018-05-07',null,3,null,null,'2018-05-07 14:24:20',null,2,'Pot-au-feu',null,null,1),
('2018-05-07',null,9,null,null,'2018-05-07 15:24:20',null,2,'Pot-au-feu',null,null,1),
('2018-05-07',null,8,null,null,'2018-05-07 12:24:20',null,2,'Pot-au-feu',null,null,1),
('2018-05-07',null,7,null,null,'2018-05-07 13:24:20',null,2,'Pot-au-feu',null,null,1),
('2018-05-07',null,6,null,null,'2018-05-07 14:24:20',null,2,'Pot-au-feu',null,null,1),
('2018-05-07',null,5,null,null,'2018-05-07 15:24:20',null,2,'Pot-au-feu',null,null,1),
('2018-05-07',null,4,null,null,'2018-05-07 16:24:20',null,2,'Pot-au-feu',null,null,1);


INSERT INTO `reservation`
VALUES(2,3,20),
(3,1,10),
(3,2,30),
(1,3,12),
(4,2,11);

