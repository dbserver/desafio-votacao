package br.com.stapassoli.desafiovotacao.service;

import br.com.stapassoli.desafiovotacao.dto.SessaoDTO;
import br.com.stapassoli.desafiovotacao.entity.Sessao;
import br.com.stapassoli.desafiovotacao.enums.VotoStatus;
import br.com.stapassoli.desafiovotacao.repository.SessaoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SessaoService {

    private final SessaoRepository sessaoRepository;
    private final ModelMapper modelMapper;

    public ResponseEntity<Sessao> cadastrarSessao(SessaoDTO sessaoDTO) {
        Sessao sessao = modelMapper.map(sessaoDTO, Sessao.class);
        return ResponseEntity.ok(sessaoRepository.save(sessao));
    }

    public ResponseEntity<VotoStatus> resultadoVotacao (Long idPauta) {

        Sessao sessao = this.sessaoRepository.findByPauta_Id(idPauta).orElseThrow(() -> {
            throw new EntityNotFoundException("Pauta nao encontrada");
        });

        return ResponseEntity.ok(sessao.obterVencedor());
    }

}
