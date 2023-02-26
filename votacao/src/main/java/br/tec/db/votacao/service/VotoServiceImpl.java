package br.tec.db.votacao.service;

import br.tec.db.votacao.dto.VotoDTO;
import br.tec.db.votacao.enums.SessaoDeVotacaoStatusEnum;
import br.tec.db.votacao.model.Associado;
import br.tec.db.votacao.model.SessaoDeVotacao;
import br.tec.db.votacao.model.Voto;
import br.tec.db.votacao.repository.AssociadoRepository;
import br.tec.db.votacao.repository.SessaoDeVotacaoRepository;
import br.tec.db.votacao.repository.VotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VotoServiceImpl implements VotoService {

    @Autowired
    private VotoRepository votoRepository;

    @Autowired
    private SessaoDeVotacaoRepository sessaoDeVotacaoRepository;

    @Autowired
    private AssociadoRepository associadoRepository;

    @Override
    public VotoDTO votar(VotoDTO votoDTO) {
        SessaoDeVotacao sessaoDeVotacao = sessaoDeVotacaoRepository.findById(votoDTO.idSessaoDeVotacao()).orElseThrow();
        Associado associado = associadoRepository.findById(votoDTO.idAssociado()).orElseThrow();
        if (sessaoDeVotacao.getStatus().equals(SessaoDeVotacaoStatusEnum.ENCERRADA)) {
            throw new RuntimeException("Sessão de votação encerrada");
        } else if (sessaoDeVotacao.getVotos().stream().anyMatch(voto -> voto.getAssociado().getId().equals(associado.getId()))) {
            throw new RuntimeException("Associado já votou nesta sessão");
        } else {
            Voto voto = new Voto();
            voto.setAssociado(associado);
            voto.setSessaoDeVotacao(sessaoDeVotacao);
            voto.setStatus(votoDTO.voto());
            sessaoDeVotacao.getVotos().add(voto);
            votoRepository.save(voto);
            return new VotoDTO(voto);
        }
    }

    @Override
    public VotoDTO buscarVotoPorId(Long id) {
        Voto voto = votoRepository.findById(id).orElseThrow();
        return new VotoDTO(voto);
    }

    @Override
    public List<VotoDTO> buscarTodosOsVotos() {
        return votoRepository.findAll().stream().map(VotoDTO::new).collect(Collectors.toList());
    }

    @Override
    public List<VotoDTO> buscarVotosPorSessaoDeVotacao(Long id) {
        SessaoDeVotacao sessaoDeVotacao = sessaoDeVotacaoRepository.findById(id).orElseThrow();
        return sessaoDeVotacao.getVotos().stream().map(VotoDTO::new).collect(Collectors.toList());
    }
}
