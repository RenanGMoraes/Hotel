-- phpMyAdmin SQL Dump
-- version 4.0.4.1
-- http://www.phpmyadmin.net
--
-- Máquina: localhost
-- Data de Criação: 05-Junho-2021 às 20:28
-- Versão do servidor: 5.5.28
-- versão do PHP: 5.3.19

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de Dados: `hotel`
--
CREATE DATABASE IF NOT EXISTS `hotel` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `hotel`;

-- --------------------------------------------------------

--
-- Estrutura da tabela `hospedes`
--

CREATE TABLE IF NOT EXISTS `hospedes` (
  `id` tinyint(4) NOT NULL AUTO_INCREMENT,
  `nome` varchar(50) NOT NULL,
  `cpf` varchar(14) NOT NULL,
  `telefone` varchar(16) NOT NULL,
  `email` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `cpf` (`cpf`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=12 ;

--
-- Extraindo dados da tabela `hospedes`
--

INSERT INTO `hospedes` (`id`, `nome`, `cpf`, `telefone`, `email`) VALUES
(1, 'Alecs', '4235346123', '11951737124', 'alecs@gmail.com'),
(2, 'Amem', '7891346123', '11996718951', 'amem@gmail.com'),
(3, 'Gambino', '8987656989', '11990962751', 'redbone@gmail.com'),
(4, 'Helena', '1232542353', '11243252313', 'wantme@gmail.com'),
(5, 'Closed', '9865678912', '11432534121', 'mind@gmail.com'),
(6, 'Jurema', '9876544524', '11872615361', 'futuro@gmail.com'),
(7, 'Mariazinha', '8562781094', '17951737124', 'mariazinha@gmail.com'),
(8, 'Barbara', '8987656251', '11990962718', 'barbara@gmail.com'),
(10, 'Testador', '1', '1', '1');

-- --------------------------------------------------------

--
-- Estrutura da tabela `quartos`
--

CREATE TABLE IF NOT EXISTS `quartos` (
  `id` tinyint(4) NOT NULL AUTO_INCREMENT,
  `tipo` enum('SOLTEIRO','DUPLO_SOLTEIRO','CASAL') NOT NULL,
  `disponivel` tinyint(1) NOT NULL,
  `valor` decimal(10,2) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=10 ;

--
-- Extraindo dados da tabela `quartos`
--

INSERT INTO `quartos` (`id`, `tipo`, `disponivel`, `valor`) VALUES
(1, 'SOLTEIRO', 0, '100.00'),
(2, 'DUPLO_SOLTEIRO', 0, '100.00'),
(3, 'SOLTEIRO', 0, '100.00'),
(4, 'SOLTEIRO', 1, '100.00'),
(5, 'CASAL', 1, '200.00'),
(6, 'CASAL', 1, '100.00'),
(7, 'CASAL', 1, '100.00'),
(8, 'DUPLO_SOLTEIRO', 1, '100.00'),
(9, 'DUPLO_SOLTEIRO', 1, '100.00');

-- --------------------------------------------------------

--
-- Estrutura da tabela `reservas`
--

CREATE TABLE IF NOT EXISTS `reservas` (
  `pk_idReserva` tinyint(4) NOT NULL DEFAULT '0',
  `fk_idHospede` tinyint(4) NOT NULL DEFAULT '0',
  `fk_idQuarto` tinyint(4) NOT NULL DEFAULT '0',
  `dataEntrada` date NOT NULL,
  `dataSaida` date NOT NULL,
  PRIMARY KEY (`pk_idReserva`,`fk_idHospede`,`fk_idQuarto`),
  KEY `fk_idQuarto` (`fk_idQuarto`),
  KEY `fk_idHospede` (`fk_idHospede`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `reservas`
--

INSERT INTO `reservas` (`pk_idReserva`, `fk_idHospede`, `fk_idQuarto`, `dataEntrada`, `dataSaida`) VALUES
(1, 10, 3, '1111-11-11', '1111-11-21');

-- --------------------------------------------------------

--
-- Stand-in structure for view `tabelareservas`
--
CREATE TABLE IF NOT EXISTS `tabelareservas` (
`pk_idReserva` tinyint(4)
,`cpfHospede` varchar(14)
,`fk_idQuarto` tinyint(4)
,`dataEntrada` date
,`dataSaida` date
);
-- --------------------------------------------------------

--
-- Structure for view `tabelareservas`
--
DROP TABLE IF EXISTS `tabelareservas`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `tabelareservas` AS select `r`.`pk_idReserva` AS `pk_idReserva`,`h`.`cpf` AS `cpfHospede`,`r`.`fk_idQuarto` AS `fk_idQuarto`,`r`.`dataEntrada` AS `dataEntrada`,`r`.`dataSaida` AS `dataSaida` from (`reservas` `r` join `hospedes` `h`) where (`r`.`fk_idHospede` = `h`.`id`);

--
-- Constraints for dumped tables
--

--
-- Limitadores para a tabela `reservas`
--
ALTER TABLE `reservas`
  ADD CONSTRAINT `reservas_ibfk_1` FOREIGN KEY (`fk_idQuarto`) REFERENCES `quartos` (`id`),
  ADD CONSTRAINT `reservas_ibfk_2` FOREIGN KEY (`fk_idHospede`) REFERENCES `hospedes` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
