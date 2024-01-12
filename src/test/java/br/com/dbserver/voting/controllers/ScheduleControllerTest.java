package br.com.dbserver.voting.controllers;

import br.com.dbserver.voting.dtos.ScheduleDTO;
import br.com.dbserver.voting.helpers.Constants;
import br.com.dbserver.voting.helpers.ScheduleCreator;
import br.com.dbserver.voting.models.Schedule;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

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
        PageImpl<ScheduleDTO> schedulePage = new PageImpl<>(List.of(ScheduleCreator.createScheduleDtoValid()));
        when(scheduleServiceMock.listAll(any())).thenReturn(schedulePage);
        doNothing().when(scheduleServiceMock).createSchedule(any());
    }

    @Test
    public void shouldCreateScheduleSuccessfully(){
        ScheduleDTO scheduleDTO = ScheduleCreator.scheduleDTO();
        assertThatCode(() -> scheduleController.createSchedule(scheduleDTO))
                .doesNotThrowAnyException();
        ResponseEntity<Void> entity = scheduleController.createSchedule(scheduleDTO);
        assertThat(entity).isNotNull();
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    public void shouldCreateScheduleValidationFailure() throws Exception {
        ScheduleDTO scheduleDTO = ScheduleCreator.createScheduleDtoInvalid();

        doNothing().when(scheduleServiceMock).createSchedule(any(ScheduleDTO.class));

        mockMvc.perform(MockMvcRequestBuilders.post(Constants.API_VERSION + "/schedule")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(scheduleDTO)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void shouldListAllSchedulesSuccessfully(){
        Pageable pageable = Pageable.unpaged();
        String expectedTitle = ScheduleCreator.createScheduleDtoValid().title();
        Page<ScheduleDTO> schedulePage = scheduleController.listAll(pageable).getBody();
        assertThat(schedulePage).isNotEmpty().hasSize(1);
        assertThat(schedulePage.toList()).isNotEmpty();
        assertThat(schedulePage.toList().get(0).title()).isEqualTo(expectedTitle);
    }

}