drop schema if exists `shop`;
CREATE SCHEMA `shop` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
DROP TABLE IF EXISTS `shop`.`account`;

CREATE TABLE `shop`.`account`
(
    `account_id`    BIGINT      NOT NULL AUTO_INCREMENT,
    `nickname`      VARCHAR(50) NOT NULL,
    `password_hash` VARCHAR(120) NOT NULL,
    `first_name`    VARCHAR(45)            DEFAULT NULL,
    `last_name`     VARCHAR(45)            DEFAULT NULL,
    `gender`        ENUM ('MALE','FEMALE') DEFAULT NULL,
    `email`         VARCHAR(50)            DEFAULT NULL,
    `birthday`      VARCHAR(45)            DEFAULT NULL,
    `phone_number`  VARCHAR(15)            DEFAULT NULL,
    `registered_at` DATETIME    NOT NULL,
    `last_login`    DATETIME    NULL       DEFAULT NULL,
    `account_type`  ENUM ('USER', 'SELLER', 'ADMIN'),

    PRIMARY KEY (`account_id`),
    UNIQUE INDEX `uq_phone` (`phone_number` ASC),
    UNIQUE INDEX `uq_email` (`email` ASC)

) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;

DROP TABLE IF EXISTS `shop`.`product`;

CREATE TABLE `shop`.`product`
(
    `product_id`    BIGINT      NOT NULL AUTO_INCREMENT,
    `seller_id`     BIGINT      NOT NULL,
    `title`         VARCHAR(50) NOT NULL,
    `meta_title`    VARCHAR(50) NULL,
    `slug`          VARCHAR(50) NOT NULL,
    `sku`           VARCHAR(50) NOT NULL,
    `price`         DECIMAL     NOT NULL DEFAULT 0,
    `discount_rate` FLOAT       NOT NULL DEFAULT 0,
    `quantity`      INT         NOT NULL DEFAULT 0,
    `created_at`    DATETIME    NOT NULL,
    `updated_at`    DATETIME    NULL     DEFAULT NULL,
    `published_at`  DATETIME    NULL     DEFAULT NULL,
    `starts_at`     DATETIME    NULL     DEFAULT NULL,
    `ends_at`       DATETIME    NULL     DEFAULT NULL,
    `content`       TEXT        NULL     DEFAULT NULL,

    PRIMARY KEY (`product_id`),
    UNIQUE INDEX `uq_slug` (`slug` ASC),

    CONSTRAINT `fk_product_account`
        FOREIGN KEY (`seller_id`) REFERENCES `shop`.`account` (`account_id`)

) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;

DROP TABLE IF EXISTS `shop`.`cart_item`;

CREATE TABLE `shop`.`cart_item`
(
    `cart_item_id`  BIGINT      NOT NULL AUTO_INCREMENT,
    `account_id`    BIGINT      NOT NULL,
    `product_id`    BIGINT      NOT NULL,
    `sku`           VARCHAR(50) NOT NULL,
    `price`         DECIMAL     NOT NULL DEFAULT 0,
    `discount_rate` FLOAT       NOT NULL DEFAULT 0,
    `quantity`      INT         NOT NULL DEFAULT 0,
#  `active`        TINYINT       NOT NULL DEFAULT 0,
    `created_at`    DATETIME    NOT NULL,
    `updated_at`    DATETIME    NULL     DEFAULT NULL,
    `content`       TEXT        NULL     DEFAULT NULL,

    PRIMARY KEY (`cart_item_id`),

    CONSTRAINT `fk_cart_item_account`
        FOREIGN KEY (`account_id`) REFERENCES `shop`.`account` (`account_id`),

    CONSTRAINT `fk_cart_item_product`
        FOREIGN KEY (`product_id`) REFERENCES `shop`.`product` (`product_id`)

) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;

DROP TABLE IF EXISTS `shop`.`orders`;

CREATE TABLE `shop`.`orders`
(
    `order_id`         BIGINT       NOT NULL AUTO_INCREMENT,
    `account_id`       BIGINT       NULL     DEFAULT NULL,
    `session_id`       VARCHAR(100) NOT NULL,
    `token`            VARCHAR(100) NOT NULL,
    `status`           SMALLINT     NOT NULL DEFAULT 0,
    `item_price_total` DECIMAL      NOT NULL DEFAULT 0,
    `item_discount`    FLOAT        NOT NULL DEFAULT 0,
    `tax`              FLOAT        NOT NULL DEFAULT 0,
    `shipping`         DECIMAL      NOT NULL DEFAULT 0,
    `user_discount`    FLOAT        NOT NULL DEFAULT 0,
    `grand_total`      FLOAT        NOT NULL DEFAULT 0,
    `first_name`       VARCHAR(45)  NULL     DEFAULT NULL,
    `last_name`        VARCHAR(45)  NULL     DEFAULT NULL,
    `email`            VARCHAR(50)  NULL,
    `phone_number`     VARCHAR(25)  NULL,
    `road_address`     VARCHAR(50)  NULL     DEFAULT NULL,
    `address`          VARCHAR(50)  NULL     DEFAULT NULL,
    `city`             VARCHAR(50)  NULL     DEFAULT NULL,
    `province`         VARCHAR(50)  NULL     DEFAULT NULL,
    `country`          VARCHAR(50)  NULL     DEFAULT NULL,
    `zip_code`         int          not null,
    `created_at`       DATETIME     NOT NULL,
    `updated_at`       DATETIME     NULL     DEFAULT NULL,
    `content`          TEXT         NULL     DEFAULT NULL,

    PRIMARY KEY (`order_id`),

    CONSTRAINT `fk_orders_account`
        FOREIGN KEY (`account_id`) REFERENCES `shop`.`account` (`account_id`)

) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;

DROP TABLE IF EXISTS `shop`.`order_item`;

CREATE TABLE `shop`.`order_item`
(
    `order_item_id` BIGINT      NOT NULL AUTO_INCREMENT,
    `product_id`    BIGINT      NOT NULL,
    `order_id`      BIGINT      NOT NULL,
    `sku`           VARCHAR(50) NOT NULL,
    `price`         DECIMAL     NOT NULL DEFAULT 0,
    `discount_rate` FLOAT       NOT NULL DEFAULT 0,
    `quantity`      INT         NOT NULL DEFAULT 0,
    `created_at`    DATETIME    NOT NULL,
    `updated_at`    DATETIME             DEFAULT NULL,
    `content`       TEXT                 DEFAULT NULL,

    PRIMARY KEY (`order_item_id`),

    CONSTRAINT `fk_order_item_product`
        FOREIGN KEY (`product_id`) REFERENCES `shop`.`product` (`product_id`),

    CONSTRAINT `fk_order_item_orders`
        FOREIGN KEY (`order_id`) REFERENCES `shop`.`orders` (`order_id`)

) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;

DROP TABLE IF EXISTS `shop`.`payment`;

CREATE TABLE `shop`.`payment`
(
    `payment_id` BIGINT       NOT NULL AUTO_INCREMENT,
    `account_id` BIGINT       NOT NULL,
    `order_id`   BIGINT       NOT NULL,
    `code`       VARCHAR(100) NOT NULL,
    `type`       SMALLINT     NOT NULL DEFAULT 0,
    `status`     SMALLINT     NOT NULL DEFAULT 0,
    `created_at` DATETIME     NOT NULL,
    `updated_at` DATETIME              DEFAULT NULL,
    `content`    TEXT                  DEFAULT NULL,

    PRIMARY KEY (`payment_id`),

    CONSTRAINT `fk_payment_account`
        FOREIGN KEY (`account_id`) REFERENCES `shop`.`account` (`account_id`),

    CONSTRAINT `fk_payment_orders`
        FOREIGN KEY (`order_id`) REFERENCES `shop`.`orders` (`order_id`)

) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;

DROP TABLE IF EXISTS `shop`.`review`;

CREATE TABLE `shop`.`review`
(
    `review_id`  BIGINT       NOT NULL AUTO_INCREMENT,
    `parent_id`  BIGINT       NULL references review (review_id),
    `product_id` BIGINT       NOT NULL,
    `account_id` BIGINT       NOT NULL,
    `rate`       FLOAT        NOT NULL DEFAULT 0,
    `title`      VARCHAR(100) NOT NULL,
    `created_at` DATETIME     NOT NULL,
    `content`    TEXT         NULL     DEFAULT NULL,

    PRIMARY KEY (`review_id`),

    CONSTRAINT `fk_review_account`
        FOREIGN KEY (`account_id`) REFERENCES `shop`.`account` (`account_id`),

    CONSTRAINT `fk_review_product`
        FOREIGN KEY (`product_id`) REFERENCES `shop`.`product` (`product_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;

DROP TABLE IF EXISTS `shop`.`category`;

CREATE TABLE `shop`.`category`
(
    `category_id` BIGINT       NOT NULL AUTO_INCREMENT,
    `parent_id`   BIGINT       NULL references category (category_id),
    `title`       VARCHAR(75)  NOT NULL,
    `meta_title`  VARCHAR(100) NULL DEFAULT NULL,
    `slug`        VARCHAR(100) NOT NULL,
    `content`     TEXT         NULL DEFAULT NULL,

    PRIMARY KEY (`category_id`)

) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;

DROP TABLE IF EXISTS `shop`.`product_category`;

CREATE TABLE `shop`.`product_category`
(
    `product_id`  BIGINT NOT NULL,
    `category_id` BIGINT NOT NULL,

    PRIMARY KEY (`product_id`, `category_id`),

    CONSTRAINT `fk_pc_product`
        FOREIGN KEY (`product_id`) REFERENCES `shop`.`product` (`product_id`),

    CONSTRAINT `fk_pc_category`
        FOREIGN KEY (`category_id`) REFERENCES `shop`.`category` (`category_id`)

) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;

DROP TABLE IF EXISTS `shop`.`tag`;

CREATE TABLE `shop`.`tag`
(
    `tag_id`     BIGINT       NOT NULL AUTO_INCREMENT,
    `title`      VARCHAR(75)  NOT NULL,
    `meta_title` VARCHAR(100) NULL DEFAULT NULL,
    `slug`       VARCHAR(100) NOT NULL,
    `content`    TEXT         NULL DEFAULT NULL,

    PRIMARY KEY (`tag_id`)

) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;

DROP TABLE IF EXISTS `shop`.`product_tag`;

CREATE TABLE `shop`.`product_tag`
(
    `product_id` BIGINT NOT NULL,
    `tag_id`     BIGINT NOT NULL,

    CONSTRAINT `fk_pt_product`
        FOREIGN KEY (`product_id`) REFERENCES `shop`.`product` (`product_id`),

    CONSTRAINT `fk_pt_tag`
        FOREIGN KEY (`product_id`) REFERENCES `shop`.`tag` (`tag_id`)

) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;

DROP TABLE IF EXISTS `shop`.`watch`;

CREATE TABLE `shop`.`watch`
(
    `watch_id`     BIGINT   NOT NULL AUTO_INCREMENT,
    `account_id`   BIGINT   NOT NULL,
    `product_id`   BIGINT   NOT NULL,
    `recent_watch` DATETIME NOT NULL,
    `watch_count`  INT DEFAULT 1,

    PRIMARY KEY (`watch_id`),

    CONSTRAINT `fk_watch_account`
        FOREIGN KEY (`account_id`) REFERENCES `shop`.`account` (`account_id`),

    CONSTRAINT `fk_watch_product`
        FOREIGN KEY (`product_id`) REFERENCES `shop`.`product` (`product_id`)

) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;

DROP TABLE IF EXISTS `shop`.`address`;

CREATE TABLE `shop`.`address`
(
    `address_id`   BIGINT       NOT NULL AUTO_INCREMENT,
    `account_id`   BIGINT       NOT NULL,
    `address`      VARCHAR(100) NULL DEFAULT NULL,
    `road_address` VARCHAR(100) NOT NULL,
    `city`         VARCHAR(50)  NULL DEFAULT NULL,
    `province`     VARCHAR(50)  NULL DEFAULT NULL,
    `country`      VARCHAR(50)  NULL DEFAULT NULL,
    `zip_code`     int          not null,

    PRIMARY KEY (`address_id`),

    CONSTRAINT `fk_address_account`
        FOREIGN KEY (`account_id`) REFERENCES `shop`.`account` (`account_id`)

) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;

DROP TABLE IF EXISTS `shop`.`suggestion`;

CREATE TABLE `shop`.`suggestion`
(
    `suggestion_id` BIGINT NOT NULL AUTO_INCREMENT,
    `account_id`    BIGINT NOT NULL,
    `product_id`    BIGINT DEFAULT NULL,
    `content`       TEXT   DEFAULT NULL,

    PRIMARY KEY (`suggestion_id`),

    CONSTRAINT `fk_sg_account`
        FOREIGN KEY (`account_id`) REFERENCES `shop`.`account` (`account_id`),

    CONSTRAINT `fk_sg_product`
        FOREIGN KEY (`product_id`) REFERENCES `shop`.`product` (`product_id`)

) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;