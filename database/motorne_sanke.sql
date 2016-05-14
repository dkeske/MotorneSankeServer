-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: May 14, 2016 at 08:07 PM
-- Server version: 5.6.17
-- PHP Version: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `motorne_sanke`
--

-- --------------------------------------------------------

--
-- Table structure for table `motorne_sanke`
--

CREATE TABLE IF NOT EXISTS `motorne_sanke` (
  `MotorneSankeID` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `BrojSasije` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `BrojMestaZaSedenje` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `TipSankiID` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`MotorneSankeID`),
  KEY `TipSankiID` (`TipSankiID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `motorne_sanke`
--

INSERT INTO `motorne_sanke` (`MotorneSankeID`, `BrojSasije`, `BrojMestaZaSedenje`, `TipSankiID`) VALUES
('1', 'BWA2015', '2', '2'),
('2', 'CAN2015', '3', '1');

-- --------------------------------------------------------

--
-- Table structure for table `rezervacija_voznje`
--

CREATE TABLE IF NOT EXISTS `rezervacija_voznje` (
  `RezervacijaID` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `DatumRezervacije` date NOT NULL,
  `UplataUnapred` tinyint(1) NOT NULL,
  `VozacID` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`RezervacijaID`),
  KEY `VozacID` (`VozacID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `servis`
--

CREATE TABLE IF NOT EXISTS `servis` (
  `ServisID` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `DatumServisa` date NOT NULL,
  `ZamenaUlja` tinyint(1) NOT NULL,
  `BrojRadnihSati` double NOT NULL,
  `MotorneSankeID` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`ServisID`),
  KEY `MotorneSankeID` (`MotorneSankeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `stavka_rezervacije_voznje`
--

CREATE TABLE IF NOT EXISTS `stavka_rezervacije_voznje` (
  `StavkaRVID` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `RezervacijaID` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `MotorneSankeID` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `RedniBrojStavke` int(11) NOT NULL,
  PRIMARY KEY (`StavkaRVID`,`RezervacijaID`),
  KEY `MotorneSankeID` (`MotorneSankeID`),
  KEY `RezervacijaID` (`RezervacijaID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `tip_motornih_sanki`
--

CREATE TABLE IF NOT EXISTS `tip_motornih_sanki` (
  `TipSankiID` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `NazivTipa` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `Namena` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `DuzinaKrampona` double NOT NULL,
  PRIMARY KEY (`TipSankiID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `tip_motornih_sanki`
--

INSERT INTO `tip_motornih_sanki` (`TipSankiID`, `NazivTipa`, `Namena`, `DuzinaKrampona`) VALUES
('1', 'GTX LIMITED 600', 'TOURING', 2.5),
('2', 'SUMMIT 800', 'DUBOKI SNEG', 6.5);

-- --------------------------------------------------------

--
-- Table structure for table `vozac`
--

CREATE TABLE IF NOT EXISTS `vozac` (
  `VozacID` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `DatumPrveVoznje` date NOT NULL,
  `Ime` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `Mail` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`VozacID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `motorne_sanke`
--
ALTER TABLE `motorne_sanke`
  ADD CONSTRAINT `motorne_sanke_ibfk_1` FOREIGN KEY (`TipSankiID`) REFERENCES `tip_motornih_sanki` (`TipSankiID`) ON DELETE NO ACTION;

--
-- Constraints for table `rezervacija_voznje`
--
ALTER TABLE `rezervacija_voznje`
  ADD CONSTRAINT `vozac_rezervacije` FOREIGN KEY (`VozacID`) REFERENCES `vozac` (`VozacID`) ON DELETE NO ACTION;

--
-- Constraints for table `servis`
--
ALTER TABLE `servis`
  ADD CONSTRAINT `servis za` FOREIGN KEY (`MotorneSankeID`) REFERENCES `motorne_sanke` (`MotorneSankeID`);

--
-- Constraints for table `stavka_rezervacije_voznje`
--
ALTER TABLE `stavka_rezervacije_voznje`
  ADD CONSTRAINT `stavka_rezervacije_voznje_ibfk_1` FOREIGN KEY (`RezervacijaID`) REFERENCES `rezervacija_voznje` (`RezervacijaID`) ON DELETE NO ACTION,
  ADD CONSTRAINT `stavka_rezervacije_voznje_ibfk_2` FOREIGN KEY (`MotorneSankeID`) REFERENCES `motorne_sanke` (`MotorneSankeID`) ON DELETE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
