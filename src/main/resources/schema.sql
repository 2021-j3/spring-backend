CREATE DATABASE IF NOT EXISTS `shop`;

USE `shop`;

CREATE TABLE IF NOT EXISTS `shop`.`account` (
  `account_id`    BIGINT NOT NULL AUTO_INCREMENT,
  `nickname`      VARCHAR(50) NOT NULL UNIQUE,
  `password_hash` VARCHAR(50) NOT NULL,
  `first_name`    VARCHAR(45) DEFAULT NULL,
  `last_name`     VARCHAR(45) DEFAULT NULL,
  `gender`        ENUM('MALE','FEMALE') DEFAULT NULL,
  `email`         VARCHAR(50) DEFAULT NULL,
  `birthday`      VARCHAR(45) DEFAULT NULL,
  `phone_number`  VARCHAR(15) DEFAULT NULL,
  `registered_at` DATETIME NOT NULL,
  `last_login`    DATETIME DEFAULT NULL,
  `account_type`  ENUM('USER', 'SELLER', 'ADMIN'),

  PRIMARY KEY (`account_id`),

  UNIQUE INDEX `uq_phone` (`phone_number` ASC),
  UNIQUE INDEX `uq_email` (`email` ASC)

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
