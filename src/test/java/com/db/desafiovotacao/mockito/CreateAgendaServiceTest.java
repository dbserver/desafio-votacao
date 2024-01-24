package com.db.desafiovotacao.mockito;

import com.db.desafiovotacao.api.converters.CreateAgendaResponseRecordConverter;
import com.db.desafiovotacao.api.entity.Agenda;
import com.db.desafiovotacao.api.record.CreateAgendaResponseRecord;
import com.db.desafiovotacao.api.repository.AgendaRepository;
import com.db.desafiovotacao.api.service.CreateAgendaService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringJUnitConfig
@ComponentScan(basePackages = "com.db.desafiovotacao.converters")
public class CreateAgendaServiceTest {

    @InjectMocks
    CreateAgendaService createAgendaService;

    @Spy
    CreateAgendaResponseRecordConverter createAgendaResponseRecordConverter;

    @Mock
    AgendaRepository agendaRepository;

    @Test
    void createAnAgenda() {
        Agenda agendaResponseMocked = new Agenda(UUID.randomUUID(), "Agenda's name",null );
        when(agendaRepository.save(any())).thenReturn(agendaResponseMocked);


        CreateAgendaResponseRecord createAgendaResponseRecord = createAgendaService.createAgenda("Agenda's name");

        assertEquals(agendaResponseMocked.getId(), createAgendaResponseRecord.id(), "ID is not correct");
        assertEquals(agendaResponseMocked.getName(), createAgendaResponseRecord.name(), "Name is not correct");

    }
}
