package br.com.occ.desafiovotacao.v1.service;

import br.com.occ.desafiovotacao.config.exception.ServiceException;
import br.com.occ.desafiovotacao.v1.model.Pauta;
import br.com.occ.desafiovotacao.v1.model.Sessao;
import br.com.occ.desafiovotacao.v1.repository.SessaoRepository;
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
    IPautaService pautaService;

    @Override
    public Optional<Sessao> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Sessao> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public Sessao save(Sessao sessao, Long idPauta) {
        Optional<Pauta> pautaOptional = pautaService.findById(idPauta);
        if (pautaOptional.isEmpty())
            throw new ServiceException("Pauta não encontrada para iniciar a sessão", HttpStatus.BAD_REQUEST);

        Pauta pauta = pautaOptional.get();

        sessao.setDataInicio(LocalDateTime.now());
        if (sessao.getDataFim() == null)
            sessao.setDataFim(LocalDateTime.now().plusMinutes(1));
        Sessao sessaoSalva = repository.save(sessao);

        pauta.setSessao(sessaoSalva);

        pautaService.save(pauta);
        return sessaoSalva;
    }

    @Override
    public Sessao update(Sessao sessao) {
        Optional<Sessao> sessaoOptional = repository.findById(sessao.getId());
        if (sessaoOptional.isEmpty())
            throw new ServiceException("Sessão não encontrada!", HttpStatus.NOT_FOUND);
        return repository.save(sessao);
    }

    @Override
    public void remove(Sessao sessao) {
        repository.delete(sessao);
    }

    @Override
    public List<Sessao> findAllAtivas() {
        return repository.findAllSessoesAtivas(LocalDateTime.now());
    }
}
