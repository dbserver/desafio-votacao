package br.tec.db.votacao.service;

import br.tec.db.votacao.dto.VotoDTO;
import br.tec.db.votacao.enums.VotoStatusEnum;
import br.tec.db.votacao.model.Associado;
import br.tec.db.votacao.model.SessaoDeVotacao;
import br.tec.db.votacao.model.Voto;
import br.tec.db.votacao.repository.AssociadoRepository;
import br.tec.db.votacao.repository.SessaoDeVotacaoRepository;
import br.tec.db.votacao.repository.VotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VotoServiceImpl implements VotoService {

    @Autowired
    private VotoRepository votoRepository;

    @Autowired
    private SessaoDeVotacaoRepository sessaoDeVotacaoRepository;

    @Autowired
    private AssociadoRepository associadoRepository;

    @Override
    public VotoDTO criarVoto(VotoDTO votoDTO) {
        SessaoDeVotacao sessaoDeVotacao = sessaoDeVotacaoRepository.findById(votoDTO.idSessaoDeVotacao()).orElseThrow();
        Associado associado = associadoRepository.findById(votoDTO.idAssociado()).orElseThrow();
        Voto voto = new Voto();
        voto.setAssociado(associado);
        voto.setSessaoDeVotacao(sessaoDeVotacao);
        voto.setStatus(votoDTO.voto());
        System.out.println(voto.getStatus().equals(VotoStatusEnum.SIM));
        sessaoDeVotacao.getVotos().add(voto);
        votoRepository.save(voto);
        return new VotoDTO(voto);
    }

    @Override
    public Long calcularVotosSimPorSessaoDeVotacao(Long id) {
        SessaoDeVotacao sessaoDeVotacao = sessaoDeVotacaoRepository.findById(id).orElseThrow();
        return sessaoDeVotacao.getVotos().stream().filter(voto -> voto.getStatus().equals(VotoStatusEnum.SIM)).count();
    }

    @Override
    public Long calcularVotosNaoPorSessaoDeVotacao(Long id) {
        SessaoDeVotacao sessaoDeVotacao = sessaoDeVotacaoRepository.findById(id).orElseThrow();
        return sessaoDeVotacao.getVotos().stream().filter(voto -> voto.getStatus().equals(VotoStatusEnum.NAO)).count();
    }

    //TODO: Ajustar método para calcular o resultado da votação (está retornando 0)
}
