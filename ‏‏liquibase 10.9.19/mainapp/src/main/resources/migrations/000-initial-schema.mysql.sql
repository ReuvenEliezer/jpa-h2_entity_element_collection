--liquibase formatted sql
-- changeset reuven:2082019-2
ALTER
DATABASE netapp CHARACTER
SET = 'utf8' COLLATE = 'utf8_general_ci';

--changeset dudim:1511272183088-2
GRANT ALL ON `netapp`.* TO 'dbsync' IDENTIFIED BY 'dbsync';
FLUSH PRIVILEGES;


--changeset reuvene:09092019-1
CREATE TABLE `netapp`.`note`
(
    `Id`        INT(11)     NOT NULL AUTO_INCREMENT,
    `Title`     VARCHAR(45) NULL,
    `Content`   VARCHAR(45) NULL,
    `CreatedAt` DATETIME    NOT NULL,
    `UpdatedAt` DATETIME    NOT NULL,
    PRIMARY KEY (`Id`)
);
--     ENGINE = InnoDB
-- DEFAULT CHARACTER SET = utf8
--     COLLATE = utf8_bin;

--changeset reuvene:09092019-2
CREATE TABLE `netapp`.`author`
(
    `id`   INT(11)     NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(45) NULL UNIQUE,
    PRIMARY KEY (`id`)
);

--changeset reuvene:09092029-3
CREATE TABLE IF NOT EXISTS `netapp`.`author_phone_number`
(
    `author_id`    INT(11) NOT NULL,
    `phone_number` INT(11) DEFAULT NULL,
    FOREIGN KEY (`author_id`) REFERENCES `netapp`.`author` (`id`)
);

--changeset reuvene:09092019-4

CREATE TABLE IF NOT EXISTS author_address
(
    author_id  INT(11)  NOT NULL,
    address_type VARCHAR(50) NOT NULL,
    street       VARCHAR(50) NOT NULL,
    country      VARCHAR(50) NOT NULL,
    FOREIGN KEY (author_id) REFERENCES `netapp`.`author` (`id`)
);

--changeset reuvene:09092019-5
CREATE TABLE `netapp`.`book`
(
    `id`     INT(11)     NOT NULL AUTO_INCREMENT,
    `name`   VARCHAR(45) NULL,
    `author` INT(11)     NULL,
    PRIMARY KEY (`id`)
);

