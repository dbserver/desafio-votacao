package com.example.desafiovotacao.service.interfaces;

import com.example.desafiovotacao.dto.CreatedSessionDTO;
import com.example.desafiovotacao.dto.SessionReturnDTO;
import com.example.desafiovotacao.dto.StartSessionDTO;

import java.util.List;

public interface SessionInterface {
    CreatedSessionDTO create(StartSessionDTO startSessionDTO);

    List<SessionReturnDTO> listAll();

    SessionReturnDTO listById(Integer sessionId);
}