package com.db.desafiovotacao.mockito;

import com.db.desafiovotacao.api.entity.Agenda;
import com.db.desafiovotacao.api.record.CreateAgendaResponseRecord;
import com.db.desafiovotacao.api.service.CreateAgendaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@ComponentScan(basePackages = "com.db.desafiovotacao.converters")
@AutoConfigureMockMvc
public class AgendaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @LocalServerPort
    private Integer port;

    @MockBean
    private CreateAgendaService createAgendaService;

    @Test
    void createAnAgenda() throws Exception {
        UUID uuid = UUID.randomUUID();
        CreateAgendaResponseRecord agenda = new CreateAgendaResponseRecord(uuid,"Agenda's name");
        when(createAgendaService.createAgenda(any())).thenReturn(agenda);

        mockMvc.perform(MockMvcRequestBuilders.post("/agendas")
                        .param("name","Agenda's name" )
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Agenda's name"));
    }
}
