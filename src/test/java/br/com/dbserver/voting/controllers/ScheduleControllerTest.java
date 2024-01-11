package br.com.dbserver.voting.controllers;

import br.com.dbserver.voting.dtos.ScheduleDTO;
import br.com.dbserver.voting.helpers.ScheduleCreator;
import br.com.dbserver.voting.services.ScheduleService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
class ScheduleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private ScheduleController scheduleController;

    @Mock
    private ScheduleService scheduleServiceMock;

    @BeforeEach
    void setup(){
        doNothing().when(scheduleServiceMock).createSchedule(any());
    }

    @Test
    public void createSchedule_WhenSuccessful(){
        ScheduleDTO scheduleDTO = ScheduleCreator.scheduleDTO();
        assertThatCode(() -> scheduleController.createSchedule(scheduleDTO))
                .doesNotThrowAnyException();
        ResponseEntity<Void> entity = scheduleController.createSchedule(scheduleDTO);
        assertThat(entity).isNotNull();
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    public void testCreateSchedule_ValidationFailure() throws Exception {
        ScheduleDTO scheduleDTO = ScheduleCreator.createScheduleDtoInvalid();

        doNothing().when(scheduleServiceMock).createSchedule(any(ScheduleDTO.class));

        mockMvc.perform(MockMvcRequestBuilders.post("/schedule")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(scheduleDTO)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

}