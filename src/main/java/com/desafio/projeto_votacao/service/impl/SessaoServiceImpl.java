package com.desafio.projeto_votacao.service.impl;

import com.desafio.projeto_votacao.dto.SessaoDto;
import com.desafio.projeto_votacao.entity.Pauta;
import com.desafio.projeto_votacao.entity.Sessao;
import com.desafio.projeto_votacao.exceptions.CustomException;
import com.desafio.projeto_votacao.repository.SessaoRepository;
import com.desafio.projeto_votacao.service.SessaoService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SessaoServiceImpl implements SessaoService {

    private final SessaoRepository sessaoRepository;
    private final ModelMapper modelMapper;
    private final Sessao sessao = new Sessao();
    @Override
    public Sessao abrirSessaoVotacao(Integer duracaoMinutos, Pauta pauta) {

        sessao.setTempoSessao(duracaoMinutos);
        sessao.setStatus(Boolean.TRUE);
        sessao.setPauta(pauta);
        return sessaoRepository.save(sessao);
    }

    @Override
    public Pauta fecharSessaoVotacao(Sessao sessao) {

        sessao.setStatus(Boolean.FALSE);
        sessaoRepository.save(sessao);
        return sessao.getPauta();
    }

    @Override
    public List<SessaoDto> listarSessoes() {

        List<Sessao> listSessao = Optional.ofNullable(sessaoRepository.findAll())
                .orElse(new ArrayList<>());

        if (listSessao.isEmpty()) throw new CustomException(HttpStatus.NOT_FOUND, "Não há sessões cadastradas.");

        return listSessao.stream()
                .map(s -> modelMapper.map(sessao, SessaoDto.class))
                .toList();
    }

    @Override
    public List<SessaoDto> listarSessoesPorPauta(Long id) {

        List<Sessao> sessoes = Optional.ofNullable(sessaoRepository.findByPautaId(id))
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Não encontado nenhuma sessão nessa pauta."));

        return sessoes.stream()
                .map(s -> modelMapper.map(sessao, SessaoDto.class))
                .toList();
    }
}
