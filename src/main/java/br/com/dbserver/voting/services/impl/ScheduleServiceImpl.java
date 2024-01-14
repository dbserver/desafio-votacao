package br.com.dbserver.voting.services.impl;

import br.com.dbserver.voting.converters.schedule.ScheduleDtoToScheduleMapper;
import br.com.dbserver.voting.dtos.ScheduleDTO;
import br.com.dbserver.voting.models.Schedule;
import br.com.dbserver.voting.repositories.ScheduleRepository;
import br.com.dbserver.voting.services.ScheduleService;
import jakarta.transaction.Transactional;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    final ScheduleRepository scheduleRepository;
    final ScheduleDtoToScheduleMapper scheduleMapper;

    public ScheduleServiceImpl(ScheduleRepository scheduleRepository, ScheduleDtoToScheduleMapper scheduleMapper) {
        this.scheduleRepository = scheduleRepository;
        this.scheduleMapper = scheduleMapper;
    }


    @Override
    @Transactional
    @Caching(evict = {
            @CacheEvict(value = "votingSession", allEntries = true),
            @CacheEvict(value = "voteProgress", allEntries = true),
            @CacheEvict(value = {"schedules"}, allEntries = true)
    })
    public void createSchedule(ScheduleDTO scheduleDTO) {
        Schedule schedule = scheduleMapper.map(scheduleDTO, new Schedule());
        scheduleRepository.save(schedule);
    }

    @Override
    @Cacheable(value = "schedules")
    public Page<ScheduleDTO> listAll(Pageable pageable) {
        Page<Schedule> schedulePage = scheduleRepository.findAll(pageable);

        List<ScheduleDTO> schedules = schedulePage
                .stream()
                .map(schedule -> new ScheduleDTO(schedule.getId(), schedule.getTitle()))
                .collect(Collectors.toList());

        return new PageImpl<>(schedules, pageable, schedulePage.getTotalPages());
    }
}
