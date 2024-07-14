DROP DATABASE IF EXISTS libros_rincondeloslibros;
CREATE DATABASE libros_rincondeloslibros;
USE libros_rincondeloslibros;

DROP TABLE IF EXISTS `libros`;

CREATE TABLE IF NOT EXISTS `libros` (
    `id` INT(10) NOT NULL AUTO_INCREMENT,
    `titulo` VARCHAR(255) NOT NULL,
    `autor` VARCHAR(255) NOT NULL,
	`genero` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`id`)
);
INSERT INTO `libros` (`titulo`,`autor`,`genero`) VALUES
('Isabel', 'Facundo Pastor','biografia'),
('El cisne negro', 'Nassim Nicholas Taleb','negocios'),
('La armadura de luz', 'Ken Follet','ciencia ficcion');