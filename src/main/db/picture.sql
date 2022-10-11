-- ----------------------------------------------------------------------------
-- MySQL Workbench Migration
-- Migrated Schemata: myDB
-- Source Schemata: picture
-- Created: Sun Oct  9 16:44:48 2022
-- Workbench Version: 8.0.23
-- ----------------------------------------------------------------------------

SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------------------------------------------------------
-- Schema myDB
-- ----------------------------------------------------------------------------
DROP SCHEMA IF EXISTS `myDB` ;
CREATE SCHEMA IF NOT EXISTS `myDB` ;

-- ----------------------------------------------------------------------------
-- Table myDB.picture
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `myDB`.`picture` (
  `Id` INT NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(50) NULL DEFAULT NULL,
  `Author` VARCHAR(50) NULL DEFAULT NULL,
  `Year` INT NULL DEFAULT NULL,
  `Storage` VARCHAR(50) NULL DEFAULT NULL,
  `Price` DECIMAL(5,2) NULL DEFAULT NULL,
  `Link` VARCHAR(50) NULL DEFAULT NULL,
  PRIMARY KEY (`Id`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;
SET FOREIGN_KEY_CHECKS = 1;
