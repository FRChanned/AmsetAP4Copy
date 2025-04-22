-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : ven. 21 mars 2025 à 14:26
-- Version du serveur : 8.3.0
-- Version de PHP : 8.2.18

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `ap4usertable`
--

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

DROP TABLE IF EXISTS `utilisateur`;
CREATE TABLE IF NOT EXISTS `utilisateur` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `NOM` varchar(40) NOT NULL,
  `PRENOM` varchar(40) NOT NULL,
  `ADRESSE_MAIL` varchar(40) NOT NULL,
  `IDENTIFIANT` varchar(40) NOT NULL,
  `MOT_DE_PASSE` varchar(40) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `utilisateur`
--

INSERT INTO `utilisateur` (`ID`, `NOM`, `PRENOM`, `ADRESSE_MAIL`, `IDENTIFIANT`, `MOT_DE_PASSE`) VALUES
(1, 'Roosevelt', 'Franklin', 'franklinroosevelt@gmail.com', 'franklin849', 'roosevelt8748è(_'),
(2, 'Musk', 'Elon', 'elonmusk@gmail.com', 'elon34', 'musk8467_ç'),
(3, 'Teddy', 'Rinner', 'teddyrinner@gmail.com', 'teddy34', 'rinner715-(_'),
(4, 'Matéo', 'Sage', 'mateosage@gmail.com', 'matéo34', 'sage512àé_ç'),
(5, 'Kendrick', 'Lamar', 'kendricklamar@gmail.com', 'kendrick34', 'lamarèç)à');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
