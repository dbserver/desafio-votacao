package br.com.dbserver.voting.services;

import br.com.dbserver.voting.dtos.ScheduleDTO;

public interface ScheduleService {
    void createSchedule(ScheduleDTO scheduleDTO);
}
