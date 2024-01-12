package br.com.dbserver.voting.services.impl;

import br.com.dbserver.voting.exceptions.ExistingResourceException;
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

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class ScheduleServiceImplTest {

    @Autowired
    private ScheduleServiceImpl scheduleService;

    @MockBean
    private ScheduleRepository scheduleRepositoryMock;

    @BeforeEach
    void setup() {
        when(scheduleRepositoryMock.save(any(Schedule.class))).thenReturn(ScheduleCreator.scheduleValid());
    }

    @Test
    public void createSchedule_WhenSuccessful() {
        assertThatCode(() -> scheduleService.createSchedule(ScheduleCreator.scheduleDTO())).doesNotThrowAnyException();
    }

    @Test
    public void createScheduleExisting_WhenNotSuccessful() {
        when(scheduleRepositoryMock.existsByTitle(anyString())).thenReturn(true);
        ExistingResourceException existingResourceException = assertThrows(ExistingResourceException.class, () -> scheduleService.createSchedule(ScheduleCreator.createScheduleDtoValid()));

        assertThat(existingResourceException.getMessage()).isEqualTo("Pauta existente");
    }


}