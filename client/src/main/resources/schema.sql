--DROP TABLE IF EXISTS users;
--DROP TABLE IF EXISTS clients;

CREATE TABLE IF NOT EXISTS `users` (
  `id` int AUTO_INCREMENT  PRIMARY KEY,
  `name` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `mobile_number` varchar(20) NOT NULL,
  `password` varchar(255) NOT NULL,
  `create_dt` date DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS `clients` (
  `id` int AUTO_INCREMENT  PRIMARY KEY,
  `identifiant` varchar(255) NOT NULL,
  `nom_client` varchar(50) NOT NULL,
  `prenom_client` varchar(255) NOT NULL,
  `profession_client` varchar(200) NOT NULL,
  `client_revenu` varchar(255) NOT NULL,
  `create_dt` date DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS `fichier` (
  `id` int AUTO_INCREMENT  PRIMARY KEY,
  `titre` varchar(255) NOT NULL,
  `url` varchar(50) NOT NULL,
  `statut` varchar(255) NOT NULL,
  `deleted` boolean DEFAULT false,
  `create_dt` date DEFAULT NULL
);

INSERT INTO `users` (`name`,`email`,`mobile_number`,`password`,`create_dt`)
 VALUES ('admin','admin@admin.com','+22666811850', 'password', CURDATE());