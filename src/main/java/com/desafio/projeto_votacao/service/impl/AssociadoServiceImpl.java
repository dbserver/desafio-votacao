package com.desafio.projeto_votacao.service.impl;

import com.desafio.projeto_votacao.dto.AssociadoDto;
import com.desafio.projeto_votacao.service.AssociadoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AssociadoServiceImpl implements AssociadoService {

    @Override
    public AssociadoDto cadastrarAssociado(String nome, String cpf) {

        return null;
    }

    @Override
    public List<AssociadoDto> listarAssociados() {

        return null;

    }
    @Override
    public boolean verificarAssociadosCadastrados () {
        return true;
    }
}
