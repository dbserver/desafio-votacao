package com.db.polling.domain.service;

import com.db.polling.api.dto.AgendaDTO;
import com.db.polling.api.dto.response.AgendaResponse;
import com.db.polling.api.dto.response.AgendaWrapperResponse;
import jakarta.persistence.criteria.CriteriaBuilder.In;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface AgendaService {

  AgendaDTO createAgenda(AgendaDTO agendaDTO);

  AgendaResponse getAgendaById(Long id);

  AgendaWrapperResponse getAllAgendas(Integer page, Integer size);
}
