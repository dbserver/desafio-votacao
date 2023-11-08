package com.example.desafiovotacao.service.interfaces;

import com.example.desafiovotacao.dto.CreatedSessionDTO;
import com.example.desafiovotacao.dto.SessionReturnDTO;
import com.example.desafiovotacao.dto.StartSessionDTO;
import com.example.desafiovotacao.entity.SessionEntity;

import java.util.List;

public interface SessionService {
    CreatedSessionDTO create(StartSessionDTO startSessionDTO);

    List<SessionReturnDTO> listAll();

    SessionReturnDTO listById(Integer sessionId);

    SessionEntity getSessionByIdIfExists(Integer id);

    SessionEntity getSessionByRulingId(Integer rulingId);

    void validateStartSessionDTO(StartSessionDTO startSessionDTO);
}