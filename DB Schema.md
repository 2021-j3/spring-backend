# ERD 설계

- 0. create shop db

    ```sql
    CREATE SCHEMA `shop` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
    ```

- 1. account TABLE

    ```sql
    DROP TABLE IF EXISTS `shop`.`account`;

    CREATE TABLE `shop`.`account` (
      `account_id` BIGINT NOT NULL AUTO_INCREMENT,
      `nickname` VARCHAR(50) NOT NULL,
      `password_hash` VARCHAR(50) NOT NULL,
      `first_name` VARCHAR(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
      `last_name` VARCHAR(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
      `gender` ENUM('MALE','FEMALE') COLLATE utf8mb4_unicode_ci DEFAULT NULL,
      `email` VARCHAR(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
      `birthday` VARCHAR(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
      `phone_number` VARCHAR(15) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
      `registered_at` DATETIME NOT NULL,
      `last_login` DATETIME NULL DEFAULT NULL,
      `account_type` ENUM('USER', 'SELLER', 'ADMIN'),

    	**PRIMARY KEY (`account_id`),
      UNIQUE INDEX `uq_phone` (`phone_number` ASC),
      UNIQUE INDEX `uq_email` (`email` ASC)**

    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
    ```

- 2. product TABLE

    ```sql
    DROP TABLE IF EXISTS `shop`.`product`;

    CREATE TABLE `shop`.`product` (
      `product_id` BIGINT NOT NULL AUTO_INCREMENT,
      `account_id` BIGINT NOT NULL,
      `title` VARCHAR(50) NOT NULL,
      `meta_title` VARCHAR(50) NULL,
      `slug` VARCHAR(50) NOT NULL,
      `summary` TINYTEXT NULL,
      `sku` VARCHAR(50) NOT NULL,
      `price` FLOAT NOT NULL DEFAULT 0,
      `discount` FLOAT NOT NULL DEFAULT 0,
      `quantity` SMALLINT NOT NULL DEFAULT 0,
      `created_at` DATETIME NOT NULL,
      `updated_at` DATETIME NULL DEFAULT NULL,
      `published_at` DATETIME NULL DEFAULT NULL,
      `starts_at` DATETIME NULL DEFAULT NULL,
      `ends_at` DATETIME NULL DEFAULT NULL,
      `content` TEXT NULL DEFAULT NULL,

      PRIMARY KEY (`product_id`),
      UNIQUE INDEX `uq_slug` (`slug` ASC),

      CONSTRAINT `fk_product_account`
        FOREIGN KEY (`account_id`) REFERENCES `shop`.`account` (`account_id`)

    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
    ```

- 3. cart_item TABLE

    ```sql
    DROP TABLE IF EXISTS `shop`.`cart_item`;

    CREATE TABLE `shop`.`cart_item` (
      `cart_item_id` BIGINT NOT NULL AUTO_INCREMENT,
      `account_id` BIGINT NOT NULL,
      `product_id` BIGINT NOT NULL,
      `sku` VARCHAR(50) NOT NULL,
      `price` FLOAT NOT NULL DEFAULT 0,
      `discount` FLOAT NOT NULL DEFAULT 0,
      `quantity` SMALLINT NOT NULL DEFAULT 0,
      `active` TINYINT NOT NULL DEFAULT 0,
      `created_at` DATETIME NOT NULL,
      `updated_at` DATETIME NULL DEFAULT NULL,
      `content` TEXT NULL DEFAULT NULL,

      PRIMARY KEY (`cart_item_id`),

      CONSTRAINT `fk_cart_item_account`
        FOREIGN KEY (`account_id`) REFERENCES `shop`.`account` (`account_id`),

      CONSTRAINT `fk_cart_item_product` 
        FOREIGN KEY (`product_id`) REFERENCES `shop`.`product` (`product_id`)

    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
    ```

- 4. orders TABLE

    ```sql
    DROP TABLE IF EXISTS `shop`.`orders`;

    CREATE TABLE `shop`.`orders` (
      `orders_id` BIGINT NOT NULL AUTO_INCREMENT,
      `account_id` BIGINT NULL DEFAULT NULL,
      `session_id` VARCHAR(100) NOT NULL,
      `token` VARCHAR(100) NOT NULL,
      `status` SMALLINT NOT NULL DEFAULT 0,
      `item_price_total` DECIMAL NOT NULL DEFAULT 0,
      `item_discount` FLOAT NOT NULL DEFAULT 0,
      `tax` FLOAT NOT NULL DEFAULT 0,
      `shipping` DECIMAL NOT NULL DEFAULT 0,
      `user_discount` FLOAT NOT NULL DEFAULT 0,
      `grand_total` FLOAT NOT NULL DEFAULT 0,
      `first_name` VARCHAR(45) NULL DEFAULT NULL,
      `last_name` VARCHAR(45) NULL DEFAULT NULL,
      `email` VARCHAR(50) NULL,
      `phone_number` VARCHAR(25) NULL,
      `road_address` VARCHAR(50) NULL DEFAULT NULL,
      `address` VARCHAR(50) NULL DEFAULT NULL,
      `city` VARCHAR(50) NULL DEFAULT NULL,
      `province` VARCHAR(50) NULL DEFAULT NULL,
      `country` VARCHAR(50) NULL DEFAULT NULL,
      `created_at` DATETIME NOT NULL,
      `updated_at` DATETIME NULL DEFAULT NULL,
      `content` TEXT NULL DEFAULT NULL,

      PRIMARY KEY (`orders_id`),

      CONSTRAINT `fk_orders_account`
        FOREIGN KEY (`account_id`) REFERENCES `shop`.`account` (`account_id`)

    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
    ```

- 5. order_item TABLE

    ```sql
    DROP TABLE IF EXISTS `shop`.`order_item`;

    CREATE TABLE `shop`.`order_item` (
      `order_item_id` BIGINT NOT NULL AUTO_INCREMENT,
      `product_id` BIGINT NOT NULL,
      `orders_id` BIGINT NOT NULL,
      `sku` VARCHAR(100) NOT NULL,
      `price` FLOAT NOT NULL DEFAULT 0,
      `discount` FLOAT NOT NULL DEFAULT 0,
      `quantity` SMALLINT NOT NULL DEFAULT 0,
      `created_at` DATETIME NOT NULL,
      `updated_at` DATETIME NULL DEFAULT NULL,
      `content` TEXT NULL DEFAULT NULL,

      PRIMARY KEY (`order_item_id`),

      CONSTRAINT `fk_order_item_product`
        FOREIGN KEY (`product_id`) REFERENCES `shop`.`product` (`product_id`),

      CONSTRAINT `fk_order_item_orders`
        FOREIGN KEY (`orders_id`) REFERENCES `shop`.`orders` (`orders_id`)

    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
    ```
