CREATE TABLE `associate` (
    `id`   bigint NOT NULL AUTO_INCREMENT,
    `cpf`  varchar(255) DEFAULT NULL,
    `name` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `cpf_UNIQUE` (`cpf`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;