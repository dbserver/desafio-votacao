package br.com.stapassoli.desafiovotacao.service;

import br.com.stapassoli.desafiovotacao.dto.VotoDTO;
import br.com.stapassoli.desafiovotacao.entity.Sessao;
import br.com.stapassoli.desafiovotacao.entity.Voto;
import br.com.stapassoli.desafiovotacao.entity.VotoId;
import br.com.stapassoli.desafiovotacao.repository.SessaoRepository;
import br.com.stapassoli.desafiovotacao.repository.VotoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VotoService {

    private final VotoRepository votoRepository;
    private final SessaoRepository sessaoRepository;

    public ResponseEntity<Voto> cadastrarSessao(VotoDTO votoDTO) {

        VotoId votoId = new VotoId();
        votoId.setIdSessao(votoDTO.getIdSessao());
        votoId.setIdAssociado(votoDTO.getIdAssociado());

        Voto voto = new Voto();
        voto.setId(votoId);
        voto.setVotoStatus(votoDTO.getVotoStatus());

        Sessao sessao = this.sessaoRepository
                .findById(votoDTO.getSessao())
                .orElseThrow(() -> new EntityNotFoundException("Sessao nao encontrada"));

        voto.setSessao(sessao);

        return ResponseEntity.ok(votoRepository.save(voto));
    }

}
