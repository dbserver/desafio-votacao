package com.challenge.votation.mapper;

import com.challenge.votation.model.Agenda;
import com.challenge.votation.repository.entity.AgendaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AgendaMapper {

    @Mapping(target = "agendaId", source = "id")
    @Mapping(target = "agendaName", source = "name")
    @Mapping(target = "agendaStart", source = "startDate")
    @Mapping(target = "agendaEnd", source = "endDate")
    Agenda map(AgendaEntity source);
}
