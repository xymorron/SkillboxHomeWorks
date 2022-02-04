CREATE DATABASE IF NOT EXISTS search_engine;
USE search_engine;
DROP TABLE IF EXISTS `_page`;
CREATE TABLE `_page` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `path` TEXT NOT NULL,
  `code` INT NOT NULL ,
  `content` MEDIUMTEXT NOT NULL,
  `site_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX(`path`(512))
);

DROP TABLE IF EXISTS `_site`;
CREATE TABLE `_site` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `status` enum('INDEXING','INDEXED','FAILED') NOT NULL,
  `status_time` DATETIME NOT NULL,
  `last_error` TEXT ,
  `url` VARCHAR(255) NOT NULL,
  `name` VARCHAR(255) NOT NULL,
   PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `_field`;
CREATE TABLE `_field` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `selector` varchar(255) NOT NULL,
  `weigth` FLOAT NOT NULL,
  PRIMARY KEY (`id`)
);

LOCK TABLES `_field` WRITE;
INSERT INTO `_field` VALUES (1,'title','title',1),(2,'body','body',0.8);
UNLOCK TABLES;

DROP TABLE IF EXISTS `_lemma`;
CREATE TABLE `_lemma` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `lemma` varchar(255) NOT NULL,
  `frequency` INT NOT NULL,
  `site_id` INT NOT NULL,
  PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `_index`;
CREATE TABLE `_index` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `page_id` INT NOT NULL,
  `lemma_id` INT NOT NULL,
  `rank` FLOAT NOT NULL,
  PRIMARY KEY (`id`)
);

