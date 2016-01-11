-- phpMyAdmin SQL Dump
-- version 3.4.11.1deb2+deb7u2
-- http://www.phpmyadmin.net
--
-- Client: localhost
-- Généré le: Lun 11 Janvier 2016 à 00:59
-- Version du serveur: 5.5.46
-- Version de PHP: 5.4.4-14+deb7u5

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données: `SopraBD`
--

-- --------------------------------------------------------

--
-- Structure de la table `Equipments`
--

CREATE TABLE IF NOT EXISTS `Equipments` (
  `equipmentID` int(3) unsigned NOT NULL AUTO_INCREMENT,
  `description` text COLLATE utf8_unicode_ci,
  PRIMARY KEY (`equipmentID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=5 ;

--
-- Contenu de la table `Equipments`
--

INSERT INTO `Equipments` (`equipmentID`, `description`) VALUES
(1, 'Visio'),
(2, 'Téléphone'),
(3, 'Salle Digilab'),
(4, 'Sécurisée');

-- --------------------------------------------------------

--
-- Structure de la table `InvitedUsers`
--

CREATE TABLE IF NOT EXISTS `InvitedUsers` (
  `invitedUserID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `reservationRef` int(10) unsigned NOT NULL,
  `userRef` int(10) unsigned NOT NULL,
  PRIMARY KEY (`invitedUserID`),
  KEY `userRef` (`userRef`),
  KEY `reservationRef` (`reservationRef`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `OldInvitedUsers`
--

CREATE TABLE IF NOT EXISTS `OldInvitedUsers` (
  `oldInvitedUserID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `oldReservationRef` int(12) unsigned NOT NULL,
  `userRef` int(10) unsigned NOT NULL,
  PRIMARY KEY (`oldInvitedUserID`),
  KEY `oldReservationRef` (`oldReservationRef`),
  KEY `oldInvitedUserID` (`userRef`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `OldReservations`
--

CREATE TABLE IF NOT EXISTS `OldReservations` (
  `oldReservationID` int(12) unsigned NOT NULL AUTO_INCREMENT,
  `roomRef` int(10) unsigned NOT NULL,
  `start` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `end` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `ownerRef` int(10) unsigned NOT NULL,
  PRIMARY KEY (`oldReservationID`),
  KEY `roomRef` (`roomRef`),
  KEY `ownerRef` (`ownerRef`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=5 ;

--
-- Contenu de la table `OldReservations`
--

INSERT INTO `OldReservations` (`oldReservationID`, `roomRef`, `start`, `end`, `ownerRef`) VALUES
(1, 2, '2016-01-14 14:30:00', '2016-01-14 15:00:00', 15),
(2, 1, '2015-12-07 11:18:54', '2015-12-11 10:20:54', 1),
(3, 3, '2015-12-22 09:00:00', '2015-12-22 13:00:00', 1);

-- --------------------------------------------------------

--
-- Structure de la table `Reservations`
--

CREATE TABLE IF NOT EXISTS `Reservations` (
  `reservationID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `roomRef` int(5) unsigned NOT NULL,
  `start` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `end` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `ownerRef` int(10) unsigned NOT NULL,
  PRIMARY KEY (`reservationID`),
  KEY `roomRef` (`roomRef`),
  KEY `ownerRef` (`ownerRef`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=4 ;

--
-- Contenu de la table `Reservations`
--

INSERT INTO `Reservations` (`reservationID`, `roomRef`, `start`, `end`, `ownerRef`) VALUES
(3, 1, '2016-01-10 22:13:49', '2016-01-22 17:10:00', 6);

-- --------------------------------------------------------

--
-- Structure de la table `RoomEquipments`
--

CREATE TABLE IF NOT EXISTS `RoomEquipments` (
  `roomEquipmentID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `roomRef` int(5) unsigned NOT NULL,
  `equipmentRef` int(3) unsigned NOT NULL,
  PRIMARY KEY (`roomEquipmentID`),
  KEY `roomRef` (`roomRef`),
  KEY `equipmentRef` (`equipmentRef`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=7 ;

--
-- Contenu de la table `RoomEquipments`
--

INSERT INTO `RoomEquipments` (`roomEquipmentID`, `roomRef`, `equipmentRef`) VALUES
(1, 1, 1),
(2, 1, 2),
(3, 2, 4),
(4, 3, 3),
(5, 3, 1),
(6, 3, 2);

-- --------------------------------------------------------

--
-- Structure de la table `Rooms`
--

CREATE TABLE IF NOT EXISTS `Rooms` (
  `roomID` int(5) unsigned NOT NULL AUTO_INCREMENT,
  `siteRef` int(4) unsigned NOT NULL,
  `number` text COLLATE utf8_unicode_ci NOT NULL,
  `capacity` smallint(4) NOT NULL,
  PRIMARY KEY (`roomID`),
  KEY `siteRef` (`siteRef`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=4 ;

--
-- Contenu de la table `Rooms`
--

INSERT INTO `Rooms` (`roomID`, `siteRef`, `number`, `capacity`) VALUES
(1, 1, 'Salle Fourier', 60),
(2, 1, 'Salle 102', 30),
(3, 2, 'Salle 3', 3);

-- --------------------------------------------------------

--
-- Structure de la table `Sites`
--

CREATE TABLE IF NOT EXISTS `Sites` (
  `siteID` int(4) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `address` text COLLATE utf8_unicode_ci NOT NULL,
  `description` text COLLATE utf8_unicode_ci,
  PRIMARY KEY (`siteID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=3 ;

--
-- Contenu de la table `Sites`
--

INSERT INTO `Sites` (`siteID`, `name`, `address`, `description`) VALUES
(1, 'INSA Toulouse', 'Avenue de Rangueil', 'Le meilleur endroit du monde'),
(2, 'GEI', 'Impasse de l''informatique', NULL);

-- --------------------------------------------------------

--
-- Structure de la table `test`
--

CREATE TABLE IF NOT EXISTS `test` (
  `name` varchar(8) NOT NULL,
  `age` int(8) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `test`
--

INSERT INTO `test` (`name`, `age`) VALUES
('jr', 0),
('jack', 21),
('name', 25);

-- --------------------------------------------------------

--
-- Structure de la table `Users`
--

CREATE TABLE IF NOT EXISTS `Users` (
  `userID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `surname` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `mailAddress` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `password` text COLLATE utf8_unicode_ci NOT NULL,
  `admin` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`userID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=21 ;

--
-- Contenu de la table `Users`
--

INSERT INTO `Users` (`userID`, `name`, `surname`, `mailAddress`, `password`, `admin`) VALUES
(1, 'Le Botlan', 'Didier', 'didier@lebotlan.fr', 'pizza', 1),
(3, 'testUser', 'test', 'a@a.a', 'a', 1),
(5, 'Donald', 'Dodo', 'donald@disney.fr', 'coincoin', 0),
(6, 'Marre', 'Daniel', 'marre@marre.marre', 'marre', 0),
(7, 'lala', 'lili', 'lala@lili.fr', 'lala', 0),
(14, 'rgautier', 'rgautier', 'rgautier@rgautier.rgautier', 'rgautier', 1),
(15, 'Machin', 'Truc', 'a@b.c', 'mc', 0),
(17, 'root', 'admin', 'admin@admin.admin', 'adminadmin', 1),
(20, 'Bertin', 'Alexis', 'alexis@bertin.fr', 'alexis', 1);

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `InvitedUsers`
--
ALTER TABLE `InvitedUsers`
  ADD CONSTRAINT `InvitedUsers_ibfk_1` FOREIGN KEY (`reservationRef`) REFERENCES `Reservations` (`reservationID`),
  ADD CONSTRAINT `InvitedUsers_ibfk_2` FOREIGN KEY (`userRef`) REFERENCES `Users` (`userID`);

--
-- Contraintes pour la table `OldInvitedUsers`
--
ALTER TABLE `OldInvitedUsers`
  ADD CONSTRAINT `OldInvitedUsers_ibfk_1` FOREIGN KEY (`oldReservationRef`) REFERENCES `OldReservations` (`oldReservationID`),
  ADD CONSTRAINT `OldInvitedUsers_ibfk_2` FOREIGN KEY (`userRef`) REFERENCES `Users` (`userID`);

--
-- Contraintes pour la table `OldReservations`
--
ALTER TABLE `OldReservations`
  ADD CONSTRAINT `OldReservations_ibfk_1` FOREIGN KEY (`roomRef`) REFERENCES `Rooms` (`roomID`),
  ADD CONSTRAINT `OldReservations_ibfk_2` FOREIGN KEY (`ownerRef`) REFERENCES `Users` (`userID`);

--
-- Contraintes pour la table `Reservations`
--
ALTER TABLE `Reservations`
  ADD CONSTRAINT `Reservations_ibfk_1` FOREIGN KEY (`roomRef`) REFERENCES `Rooms` (`roomID`),
  ADD CONSTRAINT `Reservations_ibfk_2` FOREIGN KEY (`ownerRef`) REFERENCES `Users` (`userID`);

--
-- Contraintes pour la table `RoomEquipments`
--
ALTER TABLE `RoomEquipments`
  ADD CONSTRAINT `RoomEquipments_ibfk_1` FOREIGN KEY (`roomRef`) REFERENCES `Rooms` (`roomID`),
  ADD CONSTRAINT `RoomEquipments_ibfk_2` FOREIGN KEY (`equipmentRef`) REFERENCES `Equipments` (`equipmentID`);

--
-- Contraintes pour la table `Rooms`
--
ALTER TABLE `Rooms`
  ADD CONSTRAINT `Rooms_ibfk_1` FOREIGN KEY (`siteRef`) REFERENCES `Sites` (`siteID`);

DELIMITER $$
--
-- Événements
--
CREATE DEFINER=`root`@`localhost` EVENT `deleteOldReservation` ON SCHEDULE EVERY 1 DAY STARTS '2016-01-10 22:37:23' ON COMPLETION NOT PRESERVE ENABLE DO INSERT INTO `OldReservations`(`oldReservationID`, `roomRef`, `start`, `end`, `ownerRef`)
SELECT null, `roomRef`, `start`, `end`, `ownerRef`
FROM `Reservations`
WHERE end < NOW()$$

DELIMITER ;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
