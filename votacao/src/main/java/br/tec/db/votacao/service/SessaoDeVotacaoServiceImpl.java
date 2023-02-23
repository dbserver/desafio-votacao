package br.tec.db.votacao.service;

import br.tec.db.votacao.dto.SessaoDeVotacaoDTO;
import br.tec.db.votacao.enums.SessaoDeVotacaoStatusEnum;
import br.tec.db.votacao.model.Pauta;
import br.tec.db.votacao.model.SessaoDeVotacao;
import br.tec.db.votacao.repository.PautaRepository;
import br.tec.db.votacao.repository.SessaoDeVotacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SessaoDeVotacaoServiceImpl implements SessaoDeVotacaoService {

    @Autowired
    private SessaoDeVotacaoRepository sessaoDeVotacaoRepository;

    @Autowired
    private PautaRepository pautaRepository;

    @Override
    public SessaoDeVotacaoDTO criarSessaoDeVotacao(SessaoDeVotacaoDTO sessaoDeVotacaoDTO) {
        Pauta pauta = pautaRepository.findById(sessaoDeVotacaoDTO.idPauta()).orElseThrow();
        SessaoDeVotacao sessaoDeVotacao = new SessaoDeVotacao();
        sessaoDeVotacao.setInicio(sessaoDeVotacaoDTO.inicio());
        sessaoDeVotacao.setPauta(pauta);
        sessaoDeVotacao.setStatus(SessaoDeVotacaoStatusEnum.INICIADA);
        pauta.setSessaoDeVotacao(sessaoDeVotacao);
        sessaoDeVotacaoRepository.save(sessaoDeVotacao);
        return new SessaoDeVotacaoDTO(sessaoDeVotacao);
    }

    @Override
    public SessaoDeVotacaoDTO buscarSessaoDeVotacaoPorId(Long id) {
        SessaoDeVotacao sessaoDeVotacao = sessaoDeVotacaoRepository.findById(id).orElseThrow();
        return new SessaoDeVotacaoDTO(sessaoDeVotacao);
    }

    @Override
    public List<SessaoDeVotacaoDTO> buscarTodasAsSessoesDeVotacao() {
        return sessaoDeVotacaoRepository.findAll().stream().map(SessaoDeVotacaoDTO::new).collect(Collectors.toList());
    }

    @Override
    public SessaoDeVotacaoDTO buscarSessaoDeVotacaoPorPauta(Long id) {
        Pauta pauta = pautaRepository.findById(id).orElseThrow();
        return new SessaoDeVotacaoDTO(pauta.getSessaoDeVotacao());
    }
}
