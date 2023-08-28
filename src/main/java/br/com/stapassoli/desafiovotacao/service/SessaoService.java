package br.com.stapassoli.desafiovotacao.service;

import br.com.stapassoli.desafiovotacao.dto.SessaoDTO;
import br.com.stapassoli.desafiovotacao.entity.Sessao;
import br.com.stapassoli.desafiovotacao.enums.VotoStatus;
import br.com.stapassoli.desafiovotacao.exceptions.PautaException;
import br.com.stapassoli.desafiovotacao.exceptions.SessaoException;
import br.com.stapassoli.desafiovotacao.repository.SessaoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SessaoService {

    private final SessaoRepository sessaoRepository;
    private final ModelMapper modelMapper;

    public ResponseEntity<Sessao> cadastrarSessao(SessaoDTO sessaoDTO) {
        Sessao sessao = modelMapper.map(sessaoDTO, Sessao.class);
        try {
            return ResponseEntity.ok(sessaoRepository.save(sessao));
        }catch (JpaObjectRetrievalFailureException exception) {
            throw new SessaoException("Pauta Inexistente");
        }
    }

    public ResponseEntity<VotoStatus> resultadoVotacao(Long idPauta) throws Exception {

        Sessao sessao = this.sessaoRepository.findByPauta_Id(idPauta).orElseThrow(() -> {
            throw new PautaException("Pauta nao encontrada");
        });

        return ResponseEntity.ok(sessao.obterVencedor());
    }

}
