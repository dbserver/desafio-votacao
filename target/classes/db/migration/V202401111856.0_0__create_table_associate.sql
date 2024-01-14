CREATE TABLE `associate` (
    `id`   binary(16) NOT NULL,
    `cpf`  varchar(255) DEFAULT NULL,
    `name` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `cpf_UNIQUE` (`cpf`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;