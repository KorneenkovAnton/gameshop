-- -----------------------------------------------------
-- Schema site_of_games
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `site_of_games` CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `site_of_games` ;

-- -----------------------------------------------------
-- Table `site_of_games`.`address`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `site_of_games`.`address` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `country` VARCHAR(45) NULL DEFAULT NULL,
  `city` VARCHAR(45) NULL DEFAULT NULL,
  `street` VARCHAR(45) NULL DEFAULT NULL,
  `number_of_house` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC));


-- -----------------------------------------------------
-- Table `site_of_games`.`system_requirements`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `site_of_games`.`system_requirements` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `operation_system` VARCHAR(45) NOT NULL,
  `cpu_name` VARCHAR(45) NOT NULL,
  `cpu_frequency` FLOAT NOT NULL,
  `ram` INT(11) NOT NULL,
  `video_adapter_name` VARCHAR(45) NOT NULL,
  `video_adapter_memory` INT(11) NOT NULL,
  `free_space` INT(11) NOT NULL,
  PRIMARY KEY (`id`));

-- -----------------------------------------------------
-- Table `site_of_games`.`file`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `site_of_games`.`file` (
  `id` BIGINT NOT NULL auto_increment,
  `data` LONGBLOB NOT NULL,
  `mime_type` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC));

-- -----------------------------------------------------
-- Table `site_of_games`.`game`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `site_of_games`.`game` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `cost` INT(11) NULL DEFAULT NULL,
  `release_date` DATE NULL DEFAULT NULL,
  `description` VARCHAR(1000) NULL DEFAULT NULL,
  `developer` VARCHAR(45) NOT NULL,
  `min_system_req_id` BIGINT NOT NULL,
  `rec_system_req_id` BIGINT NOT NULL,
  `file_id` BIGINT,
  PRIMARY KEY (`id`),
  INDEX `min_system_req_idx` (`min_system_req_id` ASC),
  INDEX `rec_system_req_idx` (`rec_system_req_id` ASC),
  CONSTRAINT `min_system_req_id`
    FOREIGN KEY (`min_system_req_id`)
    REFERENCES `site_of_games`.`system_requirements` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `rec_system_req_id`
    FOREIGN KEY (`rec_system_req_id`)
    REFERENCES `site_of_games`.`system_requirements` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `file_id`
    FOREIGN KEY (`file_id`)
    REFERENCES `site_of_games`.`file` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `site_of_games`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `site_of_games`.`user` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `user_login` VARCHAR(45) NOT NULL,
  `user_password` VARCHAR(45) NOT NULL,
  `date_of_birthday` DATE NULL DEFAULT NULL,
  `email` VARCHAR(45) NULL DEFAULT NULL,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  `sname` VARCHAR(45) NULL DEFAULT NULL,
  `is_admin` BINARY(1) NOT NULL DEFAULT '0',
  `address_id` BIGINT NULL DEFAULT NULL,
  `money` INT(11) NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `user_id_UNIQUE` (`id` ASC),
  UNIQUE INDEX `user_login_UNIQUE` (`user_login` ASC),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC),
  INDEX `address_id_idx` (`address_id` ASC),
  CONSTRAINT `address_id`
    FOREIGN KEY (`address_id`)
    REFERENCES `site_of_games`.`address` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `site_of_games`.`user_game`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `site_of_games`.`user_game` (
  `user_id` BIGINT NOT NULL,
  `game_id` BIGINT NOT NULL,
  PRIMARY KEY (`user_id`, `game_id`),
  INDEX `game_id` (`game_id` ASC),
  CONSTRAINT `user_game_ibfk_1`
    FOREIGN KEY (`user_id`)
    REFERENCES `site_of_games`.`user` (`id`),
  CONSTRAINT `user_game_ibfk_2`
    FOREIGN KEY (`game_id`)
    REFERENCES `site_of_games`.`game` (`id`));
    

-- -----------------------------------------------------
-- Table `site_of_games`.`comment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `site_of_games`.`comment` (
  `id` BIGINT NOT NULL,
  `user_id` BIGINT NOT NULL,
  `game_id` BIGINT NOT NULL,
  `message` VARCHAR(250) NOT NULL,
  `create_date` DATETIME NULL DEFAULT NOW(),
  PRIMARY KEY (`id`),
  INDEX `game_id` (`game_id` ASC),
  CONSTRAINT `comment_ibfk_1`
    FOREIGN KEY (`user_id`)
    REFERENCES `site_of_games`.`user` (`id`),
  CONSTRAINT `comment_ibfk_2`
    FOREIGN KEY (`game_id`)
    REFERENCES `site_of_games`.`game` (`id`));


-- -----------------------------------------------------
-- Table `site_of_games`.`user_user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `site_of_games`.`user_user` (
  `user_id` BIGINT NOT NULL,
  `user_friend_id` BIGINT NOT NULL,
  `status` BINARY(1) NULL DEFAULT '0',
  INDEX `user_id` (`user_id` ASC),
  INDEX `user_friend_id` (`user_friend_id` ASC),
  CONSTRAINT `user_user_ibfk_1`
    FOREIGN KEY (`user_id`)
    REFERENCES `site_of_games`.`user` (`id`),
  CONSTRAINT `user_user_ibfk_2`
    FOREIGN KEY (`user_friend_id`)
    REFERENCES `site_of_games`.`user` (`id`));
