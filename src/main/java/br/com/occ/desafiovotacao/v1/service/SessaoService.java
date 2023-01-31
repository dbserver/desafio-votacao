package br.com.occ.desafiovotacao.v1.service;

import br.com.occ.desafiovotacao.config.exception.ServiceException;
import br.com.occ.desafiovotacao.v1.dto.PautaDto;
import br.com.occ.desafiovotacao.v1.dto.SessaoDto;
import br.com.occ.desafiovotacao.v1.model.Pauta;
import br.com.occ.desafiovotacao.v1.model.Sessao;
import br.com.occ.desafiovotacao.v1.repository.PautaRepository;
import br.com.occ.desafiovotacao.v1.repository.SessaoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class SessaoService implements ISessaoService{

    @Autowired
    SessaoRepository repository;

    @Autowired
    PautaRepository pautaRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public Sessao findById(Long id) {
        Optional<Sessao> sessao = repository.findById(id);
        return sessao.orElseThrow(() -> new ServiceException("Sessão não encontrada", HttpStatus.NOT_FOUND));
    }

    @Override
    public List<Sessao> findAll() {
        List<Sessao> sessaos = repository.findAll();
        if (sessaos.isEmpty())
            throw new ServiceException("Não existe sessões cadastradas", HttpStatus.BAD_REQUEST);
        return sessaos;
    }

    @Override
    @Transactional
    public Sessao save(SessaoDto sessao, Long idPauta) {
        Pauta pauta = pautaRepository
                .findById(idPauta)
                .orElseThrow(() -> new ServiceException("Pauta não encontrada",  HttpStatus.NOT_FOUND));

        if (sessao.getDataFim() == null)
            sessao.setDataFim(LocalDateTime.now().plusMinutes(1));
        Sessao sessaoSalva = repository.save(sessao.toEntity(modelMapper, Sessao.class));

        pauta.setSessao(sessaoSalva);

        pautaRepository.save(pauta);

        return sessaoSalva;
    }

    @Override
    public List<Sessao> findAllAtivas() {
        List<Sessao> sessaos = repository.findAllSessoesAtivas(LocalDateTime.now());
        if (sessaos.isEmpty())
            throw new ServiceException("Não existe sessões cadastradas", HttpStatus.BAD_REQUEST);
        return sessaos;
    }
}
