SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema getartur
-- -----------------------------------------------------
CREATE DATABASE IF NOT EXISTS `getartur` CHARACTER SET = 'utf8mb4' COLLATE = 'utf8mb4_bin';
USE `getartur`;

-- -----------------------------------------------------
-- Table `variable_store`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `customer` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(20) NULL,
  `address_line_1` VARCHAR(255) NULL,
  `address_line_2` VARCHAR(255) NULL,
  `zip_code` VARCHAR(255) NULL,
  `address_line_2` VARCHAR(255) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `UK_VariableStoreKey` (`vs_key` ASC))
ENGINE = InnoDB;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
