-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Hostiteľ: 127.0.0.1:3308
-- Čas generovania: So 07.Mar 2020, 19:27
-- Verzia serveru: 8.0.18
-- Verzia PHP: 7.3.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Databáza: `macrohard`
--

-- --------------------------------------------------------

--
-- Štruktúra tabuľky pre tabuľku `artist_info`
--

DROP TABLE IF EXISTS `artist_info`;
CREATE TABLE IF NOT EXISTS `artist_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `active` int(11) NOT NULL,
  `origin` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Sťahujem dáta pre tabuľku `artist_info`
--

INSERT INTO `artist_info` (`id`, `name`, `active`, `origin`) VALUES
(3, 'empty', 0, 'empty'),
(4, 'empty', 0, 'empty');

-- --------------------------------------------------------

--
-- Štruktúra tabuľky pre tabuľku `genre_info`
--

DROP TABLE IF EXISTS `genre_info`;
CREATE TABLE IF NOT EXISTS `genre_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Sťahujem dáta pre tabuľku `genre_info`
--

INSERT INTO `genre_info` (`id`, `description`) VALUES
(3, 'nothing to show'),
(4, 'nothing to show');

-- --------------------------------------------------------

--
-- Štruktúra tabuľky pre tabuľku `song_info`
--

DROP TABLE IF EXISTS `song_info`;
CREATE TABLE IF NOT EXISTS `song_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `duration` varchar(5) NOT NULL,
  `released` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Sťahujem dáta pre tabuľku `song_info`
--

INSERT INTO `song_info` (`id`, `name`, `duration`, `released`) VALUES
(3, 'empty', '00:00', 0),
(4, 'empty', '00:00', 0);

-- --------------------------------------------------------

--
-- Štruktúra tabuľky pre tabuľku `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nick` varchar(20) NOT NULL,
  `pass` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Sťahujem dáta pre tabuľku `user`
--

INSERT INTO `user` (`id`, `nick`, `pass`) VALUES
(3, 'matej', 'heslo123'),
(4, 'frederik18', 'heslo123');

-- --------------------------------------------------------

--
-- Štruktúra tabuľky pre tabuľku `user_info`
--

DROP TABLE IF EXISTS `user_info`;
CREATE TABLE IF NOT EXISTS `user_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fgenre` varchar(30) NOT NULL,
  `fartist` varchar(30) NOT NULL,
  `fsong` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Sťahujem dáta pre tabuľku `user_info`
--

INSERT INTO `user_info` (`id`, `fgenre`, `fartist`, `fsong`) VALUES
(3, 'empty', 'empty', 'empty'),
(4, 'empty', 'empty', 'empty');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
