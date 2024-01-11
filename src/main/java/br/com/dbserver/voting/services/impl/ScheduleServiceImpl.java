package br.com.dbserver.voting.services.impl;

import br.com.dbserver.voting.converters.schedule.ScheduleDtoToScheduleMapper;
import br.com.dbserver.voting.dtos.ScheduleDTO;
import br.com.dbserver.voting.exceptions.ExistingResourceException;
import br.com.dbserver.voting.models.Schedule;
import br.com.dbserver.voting.repositories.ScheduleRepository;
import br.com.dbserver.voting.services.ScheduleService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    final ScheduleRepository scheduleRepository;
    final ScheduleDtoToScheduleMapper scheduleDtoToScheduleMapper;

    public ScheduleServiceImpl(ScheduleRepository scheduleRepository, ScheduleDtoToScheduleMapper scheduleDtoToScheduleMapper) {
        this.scheduleRepository = scheduleRepository;
        this.scheduleDtoToScheduleMapper = scheduleDtoToScheduleMapper;
    }

    @Transactional
    @Override
    public void createSchedule(ScheduleDTO scheduleDTO) {
        if(scheduleRepository.existsByTitle(scheduleDTO.title())){
            throw new ExistingResourceException("Pauta existente");
        }
        Schedule schedule = scheduleDtoToScheduleMapper.map(scheduleDTO, new Schedule());
        scheduleRepository.save(schedule);
    }
}
