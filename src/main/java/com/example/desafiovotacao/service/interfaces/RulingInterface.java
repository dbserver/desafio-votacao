package com.example.desafiovotacao.service.interfaces;

import com.example.desafiovotacao.dto.CountingResultsDTO;
import com.example.desafiovotacao.dto.CreatedRulingDTO;
import com.example.desafiovotacao.dto.RegisterRulingDTO;
import com.example.desafiovotacao.dto.RulingReturnDTO;
import com.example.desafiovotacao.entity.RulingEntity;

import java.util.List;

public interface RulingInterface {
    CreatedRulingDTO create(RegisterRulingDTO ruling);

    CountingResultsDTO countVotes(Integer rulingId);

    List<RulingReturnDTO> listAll();

    RulingEntity getRulingEntityIfExists(Integer rulingId);

    RulingReturnDTO getRulingReturnIfExists(Integer rulingId);
}