-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema multicert
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema multicert
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `multicert` DEFAULT CHARACTER SET utf8 ;
USE `multicert` ;

-- -----------------------------------------------------
-- Table `multicert`.`Cliente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `multicert`.`Cliente` (
  `nif` INT NOT NULL,
  `nome` VARCHAR(120) NOT NULL,
  `morada` VARCHAR(120) NOT NULL,
  `telefone` INT(9) NOT NULL,
  PRIMARY KEY (`nif`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
