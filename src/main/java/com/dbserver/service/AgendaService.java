package com.dbserver.service;

import com.dbserver.exception.BusinessException;
import com.dbserver.exception.EntityNotFoundException;
import com.dbserver.model.dto.AgendaRequestDTO;
import com.dbserver.model.dto.AgendaDTO;
import com.dbserver.model.entity.Agenda;
import com.dbserver.model.mapper.AgendaMapper;
import com.dbserver.repository.AgendaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AgendaService {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AgendaRepository agendaRepository;
    @Autowired
    private AgendaMapper agendaMapper;

    public AgendaDTO create(AgendaRequestDTO agendaRequestDTO) {
        Agenda agenda = agendaMapper.toEntity(agendaRequestDTO);
        return agendaMapper.toDTO(this.save(agenda));
    }

    public Agenda save(Agenda agenda) {
        try {
            return agendaRepository.save(agenda);
        } catch (RuntimeException e) {
            throw new BusinessException();
        }
    }

    public AgendaDTO getById(String id) {
        return agendaMapper.toDTO(this.findById(id));
    }

    private Agenda findById(String id) {
        return agendaRepository.findById(id)
                .orElseThrow(() -> {
                    LOGGER.error("Agenda not found: {}", id);
                    throw new EntityNotFoundException("Agenda not found");
                });
    }

    public List<AgendaDTO> getAll() {
        return agendaRepository.findAll().stream()
                .map(agenda -> agendaMapper.toDTO(agenda))
                .collect(Collectors.toList());
    }

    public AgendaDTO update(String id, AgendaRequestDTO agendaRequestDTO) {
        Agenda agenda = this.findById(id);
        agenda.setTitle(agendaRequestDTO.getTitle());
        agenda.setDescription(agenda.getDescription());
        return agendaMapper.toDTO(this.save(agenda));
    }

    public void verifyIfexistsById(String id) {
        if (!agendaRepository.existsById(id)) {
            LOGGER.error("Agenda not found: {}", id);
            throw new EntityNotFoundException("Agenda not found");
        }
    }

}
