CREATE TABLE `voting_session` (
    `id`            binary(16) NOT NULL,
    `end_session`   datetime(6)           DEFAULT NULL,
    `start_session` datetime(6)           DEFAULT NULL,
    `status`        enum ('OPEN','CLOSE') DEFAULT NULL,
    `schedule_id`   binary(16)            DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `fk_schedule` (`schedule_id`),
    CONSTRAINT `fk_schedule` FOREIGN KEY (`schedule_id`) REFERENCES `schedule` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;