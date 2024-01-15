CREATE TABLE `vote` (
    `id`           bigint NOT NULL AUTO_INCREMENT,
    `type_vote`    enum ('SIM','NAO') DEFAULT NULL,
    `associate_id` bigint             DEFAULT NULL,
    `schedule_id`  bigint             DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `associate_fk` (`associate_id`),
    KEY `schedule_fk` (`schedule_id`),
    CONSTRAINT `associate_fk` FOREIGN KEY (`associate_id`) REFERENCES `associate` (`id`),
    CONSTRAINT `schedule_fk` FOREIGN KEY (`schedule_id`) REFERENCES `schedule` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;