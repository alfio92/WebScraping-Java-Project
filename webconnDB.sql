-- MySQL Script generated by MySQL Workbench
-- 09/13/17 03:47:56
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema webconn
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema webconn
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `webconn` DEFAULT CHARACTER SET utf8 ;
USE `webconn` ;

-- -----------------------------------------------------
-- Table `webconn`.`webresource`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `webconn`.`webresource` (
  `hostname` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`hostname`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `webconn`.`page`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `webconn`.`page` (
  `url` VARCHAR(255) NOT NULL,
  `webresource_hostname` VARCHAR(255) NOT NULL,
  `title` VARCHAR(255) NOT NULL,
  `content_type` VARCHAR(128) NULL,
  `content_encoding` VARCHAR(128) NULL,
  `content_language` VARCHAR(64) NULL,
  `cache_control` VARCHAR(64) NULL,
  `connection` VARCHAR(45) NULL,
  `server` VARCHAR(45) NULL,
  INDEX `fk_page_webresource1_idx` (`webresource_hostname` ASC),
  UNIQUE INDEX `url_UNIQUE` (`url` ASC),
  PRIMARY KEY (`url`),
  CONSTRAINT `fk_page_webresource1`
    FOREIGN KEY (`webresource_hostname`)
    REFERENCES `webconn`.`webresource` (`hostname`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `webconn`.`image`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `webconn`.`image` (
  `idimage` INT NOT NULL AUTO_INCREMENT,
  `page_url` VARCHAR(255) NOT NULL,
  `src` VARCHAR(1024) NULL,
  `alt` VARCHAR(256) NULL,
  `width` VARCHAR(4) NULL,
  `height` VARCHAR(4) NULL,
  PRIMARY KEY (`idimage`),
  INDEX `fk_image_page1_idx` (`page_url` ASC),
  CONSTRAINT `fk_image_page1`
    FOREIGN KEY (`page_url`)
    REFERENCES `webconn`.`page` (`url`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `webconn`.`text`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `webconn`.`text` (
  `idtext` INT NOT NULL AUTO_INCREMENT,
  `type` VARCHAR(45) NULL,
  `text` VARCHAR(1024) NULL,
  `page_url` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`idtext`),
  INDEX `fk_text_page1_idx` (`page_url` ASC),
  CONSTRAINT `fk_text_page1`
    FOREIGN KEY (`page_url`)
    REFERENCES `webconn`.`page` (`url`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `webconn`.`link`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `webconn`.`link` (
  `idlink` INT NOT NULL AUTO_INCREMENT,
  `page_url` VARCHAR(255) NOT NULL,
  `href` VARCHAR(1024) NULL,
  `title` VARCHAR(512) NULL,
  `text` VARCHAR(512) NULL,
  PRIMARY KEY (`idlink`),
  INDEX `fk_link_page1_idx` (`page_url` ASC),
  CONSTRAINT `fk_link_page1`
    FOREIGN KEY (`page_url`)
    REFERENCES `webconn`.`page` (`url`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `webconn`.`media`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `webconn`.`media` (
  `idmedia` INT NOT NULL AUTO_INCREMENT,
  `page_url` VARCHAR(255) NOT NULL,
  `src` VARCHAR(1024) NULL,
  `type` VARCHAR(64) NULL,
  `width` VARCHAR(4) NULL,
  `height` VARCHAR(4) NULL,
  PRIMARY KEY (`idmedia`),
  INDEX `fk_media_page1_idx` (`page_url` ASC),
  CONSTRAINT `fk_media_page1`
    FOREIGN KEY (`page_url`)
    REFERENCES `webconn`.`page` (`url`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
