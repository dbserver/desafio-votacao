package br.com.dbserver.voting.services.impl;

import br.com.dbserver.voting.dtos.ScheduleDTO;
import br.com.dbserver.voting.helpers.ScheduleCreator;
import br.com.dbserver.voting.models.Schedule;
import br.com.dbserver.voting.repositories.ScheduleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;


import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;

@SpringBootTest
class ScheduleServiceImplTest {

    @Autowired
    private ScheduleServiceImpl scheduleService;

    @MockBean
    private ScheduleRepository scheduleRepositoryMock;

    @BeforeEach
    void setup() {
        when(scheduleRepositoryMock.save(any(Schedule.class))).thenReturn(ScheduleCreator.scheduleValid());
        when(scheduleRepositoryMock.findById(any())).thenReturn(Optional.of(ScheduleCreator.scheduleValid()));
        PageImpl<Schedule> schedulePage = new PageImpl<>(List.of(ScheduleCreator.creatScheduleValid()));
        when(scheduleRepositoryMock.findAll(any(Pageable.class))).thenReturn(schedulePage);
    }

    @Test
    public void shouldCreateScheduleWhenSuccessful() {
        assertThatCode(() -> scheduleService.createSchedule(ScheduleCreator.scheduleDTO())).doesNotThrowAnyException();
    }

    @Test
    public void shouldListAllSchedulesSuccessfully(){
        Pageable pageable = Pageable.unpaged();
        String expectedTitle = ScheduleCreator.createScheduleDtoValid().title();
        Page<ScheduleDTO> schedulePage = scheduleService.listAll(pageable);

        assertThat(schedulePage).isNotEmpty().hasSize(1);
        assertThat(schedulePage.toList()).isNotEmpty();
        assertThat(schedulePage.toList().get(0).title()).isEqualTo(expectedTitle);
    }

}