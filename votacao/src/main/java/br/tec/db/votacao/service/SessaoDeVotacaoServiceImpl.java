package br.tec.db.votacao.service;

import br.tec.db.votacao.dto.SessaoDeVotacaoDTO;
import br.tec.db.votacao.enums.PautaStatusEnum;
import br.tec.db.votacao.enums.SessaoDeVotacaoStatusEnum;
import br.tec.db.votacao.enums.VotoStatusEnum;
import br.tec.db.votacao.model.Pauta;
import br.tec.db.votacao.model.SessaoDeVotacao;
import br.tec.db.votacao.repository.PautaRepository;
import br.tec.db.votacao.repository.SessaoDeVotacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SessaoDeVotacaoServiceImpl implements SessaoDeVotacaoService {

    @Autowired
    private SessaoDeVotacaoRepository sessaoDeVotacaoRepository;

    @Autowired
    private PautaRepository pautaRepository;

    @Override
    public SessaoDeVotacaoDTO criarSessaoDeVotacao(SessaoDeVotacaoDTO sessaoDeVotacaoDTO) throws RuntimeException {
        Pauta pauta = pautaRepository.findById(sessaoDeVotacaoDTO.idPauta()).orElseThrow();
        if (pauta.getStatus().equals(PautaStatusEnum.AGUARDANDO_VOTACAO)) {
            SessaoDeVotacao sessaoDeVotacao = new SessaoDeVotacao();
            sessaoDeVotacao.setInicio(sessaoDeVotacaoDTO.inicio());
            sessaoDeVotacao.setPauta(pauta);
            sessaoDeVotacao.setStatus(SessaoDeVotacaoStatusEnum.INICIADA);
            pauta.setSessaoDeVotacao(sessaoDeVotacao);
            sessaoDeVotacaoRepository.save(sessaoDeVotacao);
            return new SessaoDeVotacaoDTO(sessaoDeVotacao);
        } else {
            throw new RuntimeException("Não foi possível criar a sessão de votação, pauta já encerrada.");
        }
    }

    @Override
    public SessaoDeVotacaoDTO buscarSessaoDeVotacaoPorId(Long id) throws RuntimeException {
        try {
            return new SessaoDeVotacaoDTO(sessaoDeVotacaoRepository.findById(id).orElseThrow());
        } catch (Exception e) {
            throw new RuntimeException("Não foi possível encontrar a sessão de votação.");
        }
    }

    @Override
    public List<SessaoDeVotacaoDTO> buscarTodasAsSessoesDeVotacao() throws RuntimeException {
        return sessaoDeVotacaoRepository.findAll().stream().map(SessaoDeVotacaoDTO::new).collect(Collectors.toList());
    }

    @Override
    public SessaoDeVotacaoDTO buscarSessaoDeVotacaoPorPauta(Long id) throws RuntimeException {
        try {
            Pauta pauta = pautaRepository.findById(id).orElseThrow();
            return new SessaoDeVotacaoDTO(pauta.getSessaoDeVotacao());
        } catch (Exception e) {
            throw new RuntimeException("Não há sessão de votação para a pauta informada.");
        }
    }

    @Override
    public void encerrarSessaoDeVotacao(Long id) throws RuntimeException {
        SessaoDeVotacao sessaoDeVotacao = sessaoDeVotacaoRepository.findById(id).orElse(null);
        if (sessaoDeVotacao == null) {
            throw new RuntimeException("Sessão de votação não encontrada.");
        } else if (sessaoDeVotacao.getStatus().equals(SessaoDeVotacaoStatusEnum.ENCERRADA)) {
            throw new RuntimeException("Sessão de votação já encerrada.");
        } else {
            sessaoDeVotacao.setFim(LocalDateTime.now());
            sessaoDeVotacao.setStatus(SessaoDeVotacaoStatusEnum.ENCERRADA);
            sessaoDeVotacaoRepository.save(sessaoDeVotacao);
        }
    }

    @Override
    public void calcularResultadoDaSessaoDeVotacao(Long id) throws RuntimeException {
        SessaoDeVotacao sessaoDeVotacao = sessaoDeVotacaoRepository.findById(id).orElse(null);
        if (sessaoDeVotacao == null) {
            throw new RuntimeException("Sessão de votação não encontrada.");
        } else {
            long votosSim = sessaoDeVotacao.getVotos().stream().filter(voto -> voto.getStatus().equals(VotoStatusEnum.SIM)).count();
            long votosNao = sessaoDeVotacao.getVotos().stream().filter(voto -> voto.getStatus().equals(VotoStatusEnum.NAO)).count();
            sessaoDeVotacao.getPauta().setStatus(votosSim > votosNao ? PautaStatusEnum.APROVADA : PautaStatusEnum.REPROVADA);
            pautaRepository.save(sessaoDeVotacao.getPauta());
        }
    }

}
