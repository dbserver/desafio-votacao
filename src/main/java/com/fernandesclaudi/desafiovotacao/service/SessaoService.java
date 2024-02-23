package com.fernandesclaudi.desafiovotacao.service;

import com.fernandesclaudi.desafiovotacao.dto.SessaoDto;
import com.fernandesclaudi.desafiovotacao.exceptions.IValorNaoInformadoException;
import com.fernandesclaudi.desafiovotacao.exceptions.IRegistroNaoEncontradoException;
import com.fernandesclaudi.desafiovotacao.model.Pauta;
import com.fernandesclaudi.desafiovotacao.model.Sessao;
import com.fernandesclaudi.desafiovotacao.repository.PautaRepository;
import com.fernandesclaudi.desafiovotacao.repository.SessaoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class SessaoService {
    @Autowired
    private SessaoRepository sessaoRepository;
    @Autowired
    private PautaRepository pautaRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Sessao save(SessaoDto sessaoDto) throws IRegistroNaoEncontradoException, IValorNaoInformadoException {
        if ((sessaoDto.getPauta() == null) || (sessaoDto.getPauta().getId() == 0L)) {
            throw new IValorNaoInformadoException("sessao.pauta", HttpStatus.BAD_REQUEST);
        }

        Optional<Pauta> pauta = pautaRepository.findById(sessaoDto.getPauta().getId());
        if (pauta.isEmpty()) {
            throw new IRegistroNaoEncontradoException("Pauta", HttpStatus.NOT_FOUND);
        }

        if (sessaoDto.getDuracao().equals(0L)) {
            sessaoDto.setDuracao(60L);
        }

        Sessao sessao = modelMapper.map(sessaoDto, Sessao.class);
        sessao.setPauta(pauta.get());
        sessao.setDataInicio(LocalDateTime.now());
        sessao.setDataFim(LocalDateTime.now().plusSeconds(sessaoDto.getDuracao()));

        return this.sessaoRepository.save(sessao);
    }

    public List<Sessao> sessoesAbertas() {
        return sessaoRepository.findByDataFimAfter(LocalDateTime.now());
    }
}
