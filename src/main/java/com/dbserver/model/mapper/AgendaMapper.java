package com.dbserver.model.mapper;

import com.dbserver.model.dto.AgendaRequestDTO;
import com.dbserver.model.dto.AgendaDTO;
import com.dbserver.model.entity.Agenda;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AgendaMapper {

    @Autowired
    private ObjectMapper objectMapper;

    public Agenda toEntity(AgendaRequestDTO agendaRequestDTO) {
        return objectMapper.convertValue(agendaRequestDTO, Agenda.class);
    }

    public Agenda toEntity(AgendaDTO agendaDTO) {
        return objectMapper.convertValue(agendaDTO, Agenda.class);
    }

    public AgendaDTO toDTO(Agenda agenda) {
        return objectMapper.convertValue(agenda, AgendaDTO.class);
    }

}
