-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jun 23, 2016 at 01:03 AM
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
-- Table structure for table `korisnik`
--

CREATE TABLE IF NOT EXISTS `korisnik` (
  `KorisnikID` int(10) NOT NULL AUTO_INCREMENT,
  `Ime` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `KorisnickoIme` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `Password` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`KorisnikID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=3 ;

--
-- Dumping data for table `korisnik`
--

INSERT INTO `korisnik` (`KorisnikID`, `Ime`, `KorisnickoIme`, `Password`) VALUES
(1, 'Daniel', 'daniel', 'bd3dae5fb91f88a4f0978222dfd58f59a124257cb081486387cbae9df11fb879'),
(2, 'Nevena', 'nevena', 'f5743d918ebd3c2504cf28744f3bb93f9364b5a1710a100e8e75a9f0df0d6fb2');

-- --------------------------------------------------------

--
-- Table structure for table `motorne_sanke`
--

CREATE TABLE IF NOT EXISTS `motorne_sanke` (
  `MotorneSankeID` int(10) NOT NULL AUTO_INCREMENT,
  `BrojSasije` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `BrojMestaZaSedenje` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `TipSankiID` int(10) NOT NULL,
  PRIMARY KEY (`MotorneSankeID`),
  KEY `TipSankiID` (`TipSankiID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=19 ;

--
-- Dumping data for table `motorne_sanke`
--

INSERT INTO `motorne_sanke` (`MotorneSankeID`, `BrojSasije`, `BrojMestaZaSedenje`, `TipSankiID`) VALUES
(1, 'VUJ1234', '3', 1),
(2, 'CAN2015', '3', 1),
(3, 'BCC2014', '3', 1),
(4, 'ECC2016', '2', 2),
(8, 'CAN2013', '3', 1),
(9, 'VUJ2016', '3', 1),
(10, 'JOS2016', '2', 2),
(11, 'PER2011', '2.5', 1),
(12, 'CAD2015', '2', 2),
(14, 'ABA2016', '2', 2),
(15, 'BCC2014', '3', 2),
(16, 'CCA2008', '2', 1),
(17, 'KESKE123', '2', 2),
(18, 'VAL2015', '3', 1);

-- --------------------------------------------------------

--
-- Table structure for table `rezervacija_voznje`
--

CREATE TABLE IF NOT EXISTS `rezervacija_voznje` (
  `RezervacijaID` int(10) NOT NULL AUTO_INCREMENT,
  `DatumRezervacije` date NOT NULL,
  `UplataUnapred` tinyint(1) NOT NULL,
  `VozacID` int(10) NOT NULL,
  PRIMARY KEY (`RezervacijaID`),
  KEY `VozacID` (`VozacID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=6 ;

--
-- Dumping data for table `rezervacija_voznje`
--

INSERT INTO `rezervacija_voznje` (`RezervacijaID`, `DatumRezervacije`, `UplataUnapred`, `VozacID`) VALUES
(1, '2016-05-20', 1, 2),
(2, '2015-05-27', 0, 2),
(3, '2014-12-12', 0, 1),
(4, '2014-12-12', 1, 1),
(5, '2015-12-12', 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `servis`
--

CREATE TABLE IF NOT EXISTS `servis` (
  `ServisID` int(10) NOT NULL AUTO_INCREMENT,
  `DatumServisa` date NOT NULL,
  `ZamenaUlja` tinyint(1) NOT NULL,
  `BrojRadnihSati` double NOT NULL,
  `MotorneSankeID` int(10) NOT NULL,
  PRIMARY KEY (`ServisID`),
  KEY `MotorneSankeID` (`MotorneSankeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `stavka_rezervacije_voznje`
--

CREATE TABLE IF NOT EXISTS `stavka_rezervacije_voznje` (
  `RezervacijaID` int(10) NOT NULL,
  `RedniBrojStavke` int(11) NOT NULL AUTO_INCREMENT,
  `MotorneSankeID` int(10) NOT NULL,
  PRIMARY KEY (`RedniBrojStavke`,`RezervacijaID`),
  KEY `MotorneSankeID` (`MotorneSankeID`),
  KEY `RezervacijaID` (`RezervacijaID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=8 ;

-- --------------------------------------------------------

--
-- Table structure for table `tip_motornih_sanki`
--

CREATE TABLE IF NOT EXISTS `tip_motornih_sanki` (
  `TipSankiID` int(10) NOT NULL AUTO_INCREMENT,
  `NazivTipa` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `Namena` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `DuzinaKrampona` double NOT NULL,
  PRIMARY KEY (`TipSankiID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=3 ;

--
-- Dumping data for table `tip_motornih_sanki`
--

INSERT INTO `tip_motornih_sanki` (`TipSankiID`, `NazivTipa`, `Namena`, `DuzinaKrampona`) VALUES
(1, 'GTX LIMITED 600', 'TOURING', 2.5),
(2, 'SUMMIT 800', 'DUBOKI SNEG', 6.5);

-- --------------------------------------------------------

--
-- Table structure for table `vozac`
--

CREATE TABLE IF NOT EXISTS `vozac` (
  `VozacID` int(10) NOT NULL AUTO_INCREMENT,
  `DatumPrveVoznje` date NOT NULL,
  `Ime` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `Mail` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`VozacID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=3 ;

--
-- Dumping data for table `vozac`
--

INSERT INTO `vozac` (`VozacID`, `DatumPrveVoznje`, `Ime`, `Mail`) VALUES
(1, '2016-05-19', 'Stefan Jovic', 'stefan.jovic.093@gmail.com'),
(2, '2016-06-15', 'Branislav Vidojevic', 'baki@posao.com');

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
  ADD CONSTRAINT `rezervacija_voznje_ibfk_1` FOREIGN KEY (`VozacID`) REFERENCES `vozac` (`VozacID`) ON DELETE NO ACTION;

--
-- Constraints for table `servis`
--
ALTER TABLE `servis`
  ADD CONSTRAINT `servis_za` FOREIGN KEY (`MotorneSankeID`) REFERENCES `motorne_sanke` (`MotorneSankeID`);

--
-- Constraints for table `stavka_rezervacije_voznje`
--
ALTER TABLE `stavka_rezervacije_voznje`
  ADD CONSTRAINT `sanke` FOREIGN KEY (`MotorneSankeID`) REFERENCES `motorne_sanke` (`MotorneSankeID`) ON DELETE NO ACTION,
  ADD CONSTRAINT `stavka_rezervacije_voznje_ibfk_1` FOREIGN KEY (`RezervacijaID`) REFERENCES `rezervacija_voznje` (`RezervacijaID`) ON DELETE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
