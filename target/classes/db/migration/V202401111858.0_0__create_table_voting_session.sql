CREATE TABLE `voting_session` (
    `id`            bigint NOT NULL AUTO_INCREMENT,
    `end_session`   datetime(6)           DEFAULT NULL,
    `start_session` datetime(6)           DEFAULT NULL,
    `status`        enum ('OPEN','CLOSE') DEFAULT NULL,
    `schedule_id`   bigint                DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `fk_schedule` (`schedule_id`),
    CONSTRAINT `fk_schedule` FOREIGN KEY (`schedule_id`) REFERENCES `schedule` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;