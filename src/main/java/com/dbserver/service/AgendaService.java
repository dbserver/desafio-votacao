package com.dbserver.service;

import com.dbserver.exception.BusinessException;
import com.dbserver.exception.EntityNotFoundException;
import com.dbserver.model.dto.AgendaDTO;
import com.dbserver.model.dto.AgendaCreateDTO;
import com.dbserver.model.entity.Agenda;
import com.dbserver.mapper.AgendaMapper;
import com.dbserver.repository.AgendaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendaService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AgendaRepository agendaRepository;
    @Autowired
    private AgendaMapper agendaMapper;

    public AgendaDTO create(AgendaCreateDTO agendaCreateDTO) {
        logger.info("Starting agenda creation: {}", agendaCreateDTO);
        Agenda agenda = agendaMapper.toEntity(agendaCreateDTO);
        AgendaDTO agendaDTO = agendaMapper.toDTO(this.save(agenda));
        logger.info("Agenda created: {}", agendaDTO);
        return agendaDTO;
    }

    private Agenda save(Agenda agenda) {
        try {
            return agendaRepository.save(agenda);
        } catch (RuntimeException e) {
            logger.error("Error saving agenda: ", e);
            throw new BusinessException();
        }
    }

    public AgendaDTO getAgendaById(String id) {
        logger.info("Starting agenda search: {}", id);
        AgendaDTO agendaDTO = agendaMapper.toDTO(this.findById(id));
        logger.info("Agenda found: {}", agendaDTO);
        return agendaDTO;
    }

    public Agenda findById(String id) {
        return agendaRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Agenda not found: {}", id);
                    throw new EntityNotFoundException("Agenda not found for id: " + id);
                });
    }

    public List<AgendaDTO> getAll() {
        return agendaRepository.findAll().stream()
                .map(agenda -> agendaMapper.toDTO(agenda))
                .toList();
    }

}
