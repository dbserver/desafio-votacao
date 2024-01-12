package br.com.dbserver.voting.services;

import br.com.dbserver.voting.dtos.ScheduleDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ScheduleService {
    void createSchedule(ScheduleDTO scheduleDTO);

    Page<ScheduleDTO> listAll(Pageable pageable);
}
