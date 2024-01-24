package com.db.desafiovotacao.mockito;

import com.db.desafiovotacao.api.entity.Agenda;
import com.db.desafiovotacao.api.repository.AgendaRepository;
import com.db.desafiovotacao.api.service.CreateAgendaService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringJUnitConfig
public class CreateAgendaServiceTest {

    @InjectMocks
    CreateAgendaService createAgendaService;

    @Mock
    AgendaRepository agendaRepository;

    @Test
    void createAnAgenda() {
        Agenda agendaResponseMocked = new Agenda(UUID.randomUUID(), "Agenda's name",null );
        when(agendaRepository.save(any())).thenReturn(agendaResponseMocked);


        Agenda agendaResponse = createAgendaService.createAgenda("Agenda's name");

        assertEquals(agendaResponseMocked.getId(), agendaResponse.getId(), "ID is not correct");
        assertEquals(agendaResponseMocked.getName(), agendaResponse.getName(), "Name is not correct");
        assertNull(agendaResponse.getVotes(), "Votes is not correct");
    }
}
