-- phpMyAdmin SQL Dump
-- version 3.4.11.1deb2+deb7u2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Jan 24, 2016 at 01:26 PM
-- Server version: 5.5.46
-- PHP Version: 5.4.4-14+deb7u5

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `SopraBD`
--

-- --------------------------------------------------------

--
-- Table structure for table `Equipments`
--

CREATE TABLE IF NOT EXISTS `Equipments` (
  `equipmentID` int(3) unsigned NOT NULL AUTO_INCREMENT,
  `description` text COLLATE utf8_unicode_ci,
  PRIMARY KEY (`equipmentID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=6 ;

--
-- Dumping data for table `Equipments`
--

INSERT INTO `Equipments` (`equipmentID`, `description`) VALUES
(1, 'Visio'),
(2, 'Téléphone'),
(3, 'Salle Digilab'),
(4, 'Sécurisée'),
(5, 'Tableau');

-- --------------------------------------------------------

--
-- Table structure for table `InvitedUsers`
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
-- Table structure for table `OldInvitedUsers`
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
-- Table structure for table `OldReservations`
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
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=8 ;

--
-- Dumping data for table `OldReservations`
--

INSERT INTO `OldReservations` (`oldReservationID`, `roomRef`, `start`, `end`, `ownerRef`) VALUES
(4, 4, '2016-01-21 00:00:00', '2016-01-28 00:00:00', 20),
(5, 4, '2016-01-21 00:00:00', '2016-01-28 00:00:00', 20),
(6, 4, '2016-01-21 00:00:00', '2016-01-28 00:00:00', 20),
(7, 15, '2016-01-28 16:00:00', '2016-01-28 16:43:00', 20);

-- --------------------------------------------------------

--
-- Table structure for table `Reservations`
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
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=19 ;

--
-- Dumping data for table `Reservations`
--

INSERT INTO `Reservations` (`reservationID`, `roomRef`, `start`, `end`, `ownerRef`) VALUES
(6, 4, '2016-01-13 16:00:00', '2016-01-13 17:00:00', 6),
(7, 5, '2016-01-19 00:00:00', '2016-01-26 00:00:00', 3),
(8, 4, '2016-01-18 00:00:00', '2016-01-18 20:00:00', 14),
(9, 5, '2016-01-17 00:00:00', '2016-01-18 00:00:00', 20),
(11, 4, '2016-01-21 14:34:00', '2016-01-28 09:46:00', 20),
(15, 12, '2016-01-26 15:00:00', '2016-01-28 15:34:00', 20),
(16, 15, '2016-01-28 16:00:00', '2016-01-28 16:43:00', 20),
(18, 14, '2016-02-18 09:00:00', '2016-02-18 19:00:00', 20);

-- --------------------------------------------------------

--
-- Table structure for table `RoomEquipments`
--

CREATE TABLE IF NOT EXISTS `RoomEquipments` (
  `roomEquipmentID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `roomRef` int(5) unsigned NOT NULL,
  `equipmentRef` int(3) unsigned NOT NULL,
  PRIMARY KEY (`roomEquipmentID`),
  KEY `roomRef` (`roomRef`),
  KEY `equipmentRef` (`equipmentRef`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=7 ;

-- --------------------------------------------------------

--
-- Table structure for table `Rooms`
--

CREATE TABLE IF NOT EXISTS `Rooms` (
  `roomID` int(5) unsigned NOT NULL AUTO_INCREMENT,
  `siteRef` int(4) unsigned NOT NULL,
  `number` text COLLATE utf8_unicode_ci NOT NULL,
  `capacity` smallint(4) NOT NULL,
  PRIMARY KEY (`roomID`),
  KEY `siteRef` (`siteRef`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=19 ;

--
-- Dumping data for table `Rooms`
--

INSERT INTO `Rooms` (`roomID`, `siteRef`, `number`, `capacity`) VALUES
(4, 4, 'Fourier', 100),
(5, 5, 'Salle 404', 10),
(10, 5, 'STPI102', 32),
(11, 2, 'Salle 3', 10),
(12, 2, 'Salle 101', 9),
(14, 4, 'Salle B2', 44),
(15, 5, 'STPI 4', 45),
(17, 5, 'Salle 20', 100),
(18, 2, 'frefe', 56);

-- --------------------------------------------------------

--
-- Table structure for table `Sites`
--

CREATE TABLE IF NOT EXISTS `Sites` (
  `siteID` int(4) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `address` text COLLATE utf8_unicode_ci NOT NULL,
  `description` text COLLATE utf8_unicode_ci,
  PRIMARY KEY (`siteID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=10 ;

--
-- Dumping data for table `Sites`
--

INSERT INTO `Sites` (`siteID`, `name`, `address`, `description`) VALUES
(2, 'GEI', 'Impasse de l''informatique', 'wololohh'),
(4, 'Castle', 'Far far away', 'But, where is fiona?'),
(5, 'Insa Toulouse', 'Avenue des sciences appliquées', 'Deleted by error...Sorry ^^'' gg'),
(8, 'Hell', '6 pieds sous terre', 'Personne n''a envie d''y aller'),
(9, 'VFVGDFa', 'fefe', 'fefe');

-- --------------------------------------------------------

--
-- Table structure for table `test`
--

CREATE TABLE IF NOT EXISTS `test` (
  `name` varchar(8) NOT NULL,
  `age` int(8) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `test`
--

INSERT INTO `test` (`name`, `age`) VALUES
('jr', 0),
('jack', 21),
('name', 25);

-- --------------------------------------------------------

--
-- Table structure for table `Users`
--

CREATE TABLE IF NOT EXISTS `Users` (
  `userID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `surname` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `mailAddress` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `password` text COLLATE utf8_unicode_ci NOT NULL,
  `admin` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`userID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=23 ;

--
-- Dumping data for table `Users`
--

INSERT INTO `Users` (`userID`, `name`, `surname`, `mailAddress`, `password`, `admin`) VALUES
(3, 'testUser', 'test', 'a@a.a', 'a', 1),
(5, 'Donald', 'Dodo', 'donald@disney.fr', 'coincoin', 0),
(6, 'Marai', 'Daniel', 'marre@marre.marre', 'marre', 0),
(7, 'lala', 'lili', 'lala@lili.fr', 'lala', 0),
(14, 'rgautier', 'rgautier', 'rgautier@rgautier.rgautier', 'rgautier', 1),
(15, 'Machin', 'Truc', 'a@b.T', 'mc', 0),
(17, 'root', 'admin', 'admin@admin.admin', 'adminadmin', 1),
(20, 'Bertin', 'Alexis', 'alexis@bertin.fr', 'alexis', 1),
(22, 'Le Botlan', 'Didier', 'didier@lebotlan.fr', 'pizza', 1);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `InvitedUsers`
--
ALTER TABLE `InvitedUsers`
  ADD CONSTRAINT `InvitedUsers_ibfk_1` FOREIGN KEY (`reservationRef`) REFERENCES `Reservations` (`reservationID`),
  ADD CONSTRAINT `InvitedUsers_ibfk_2` FOREIGN KEY (`userRef`) REFERENCES `Users` (`userID`);

--
-- Constraints for table `OldInvitedUsers`
--
ALTER TABLE `OldInvitedUsers`
  ADD CONSTRAINT `OldInvitedUsers_ibfk_1` FOREIGN KEY (`oldReservationRef`) REFERENCES `OldReservations` (`oldReservationID`),
  ADD CONSTRAINT `OldInvitedUsers_ibfk_2` FOREIGN KEY (`userRef`) REFERENCES `Users` (`userID`);

--
-- Constraints for table `OldReservations`
--
ALTER TABLE `OldReservations`
  ADD CONSTRAINT `OldReservations_ibfk_1` FOREIGN KEY (`roomRef`) REFERENCES `Rooms` (`roomID`),
  ADD CONSTRAINT `OldReservations_ibfk_2` FOREIGN KEY (`ownerRef`) REFERENCES `Users` (`userID`);

--
-- Constraints for table `Reservations`
--
ALTER TABLE `Reservations`
  ADD CONSTRAINT `Reservations_ibfk_1` FOREIGN KEY (`roomRef`) REFERENCES `Rooms` (`roomID`),
  ADD CONSTRAINT `Reservations_ibfk_2` FOREIGN KEY (`ownerRef`) REFERENCES `Users` (`userID`);

--
-- Constraints for table `RoomEquipments`
--
ALTER TABLE `RoomEquipments`
  ADD CONSTRAINT `RoomEquipments_ibfk_1` FOREIGN KEY (`roomRef`) REFERENCES `Rooms` (`roomID`),
  ADD CONSTRAINT `RoomEquipments_ibfk_2` FOREIGN KEY (`equipmentRef`) REFERENCES `Equipments` (`equipmentID`);

--
-- Constraints for table `Rooms`
--
ALTER TABLE `Rooms`
  ADD CONSTRAINT `Rooms_ibfk_1` FOREIGN KEY (`siteRef`) REFERENCES `Sites` (`siteID`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
