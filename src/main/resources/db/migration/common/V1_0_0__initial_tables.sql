SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema getartur
-- -----------------------------------------------------
CREATE DATABASE IF NOT EXISTS `getartur` CHARACTER SET = 'utf8mb4' COLLATE = 'utf8mb4_bin';
USE `getartur`;

-- -----------------------------------------------------
-- Table `customer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `customer` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `number` VARCHAR(255) NOT NULL,
  `phone` VARCHAR(255),
  `website` VARCHAR(255),
  `vatin` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `address`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `address` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `customer_id` BIGINT(20) NOT NULL,
  `description` VARCHAR(255) NULL,
  `line_1` VARCHAR(255) NULL,
  `line_2` VARCHAR(255) NULL,
  `zip` VARCHAR(255) NULL,
  `city` VARCHAR(255) NULL,
  `country` VARCHAR(255) NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_customer_adress`
    FOREIGN KEY (`customer_id`)
    REFERENCES `customer` (`id`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `project`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `project` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `customer_id` BIGINT(20) NOT NULL,
  `name` VARCHAR(255) NULL,
  `started` DATE NOT NULL,
  `finished` DATE,
  `rate` DECIMAL(6,2) NOT NULL,
  `rate_type` VARCHAR(32) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_contract_project`
    FOREIGN KEY (`customer_id`)
    REFERENCES `customer` (`id`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `time_tracking`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `time_tracking` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `project_id` BIGINT(20) NOT NULL,
  `invoice_id` BIGINT(20),
  `description` MEDIUMTEXT NULL,
  `start` DATETIME(6) NOT NULL,
  `end` DATETIME(6) NOT NULL,
  `duration_in_minutes` INTEGER NOT NULL,
  `created` DATETIME(6) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_project_time_tracking`
    FOREIGN KEY (`project_id`)
    REFERENCES `project` (`id`),
  CONSTRAINT `fk_invoice_time_tracking`
    FOREIGN KEY (`invoice_id`)
    REFERENCES `invoice` (`id`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `invoice`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `invoice` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `customer_id` BIGINT(20) NOT NULL,
  `number` VARCHAR(255) NOT NULL,
  `issued` DATE NOT NULL,
  `delivery` VARCHAR(255) NOT NULL,
  `intro` MEDIUMTEXT NULL,
  `outro` MEDIUMTEXT NULL,
  `created` DATETIME(6) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_customer_invoice`
    FOREIGN KEY (`customer_id`)
    REFERENCES `customer` (`id`))
ENGINE = InnoDB;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
