ALTER TABLE `voting`.`vote`
    ADD COLUMN `voting_session_id` BIGINT NULL AFTER `schedule_id`,
    ADD INDEX `voting_session_idx` (`voting_session_id` ASC) VISIBLE;
;
ALTER TABLE `voting`.`vote`
    ADD CONSTRAINT `voting_session`
        FOREIGN KEY (`voting_session_id`)
            REFERENCES `voting`.`voting_session` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION;
