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
    public Sessao save(Sessao sessao, Long idPauta) {
        Pauta pauta = pautaService.findById(idPauta);

        if (sessao.getDataFim() == null)
            sessao.setDataFim(LocalDateTime.now().plusMinutes(1));
        Sessao sessaoSalva = repository.save(sessao);

        pauta.setSessao(sessaoSalva);

        pautaService.save(pauta);
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
