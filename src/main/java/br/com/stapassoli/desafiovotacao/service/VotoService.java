package br.com.stapassoli.desafiovotacao.service;

import br.com.stapassoli.desafiovotacao.dto.VotoDTO;
import br.com.stapassoli.desafiovotacao.entity.Sessao;
import br.com.stapassoli.desafiovotacao.entity.Voto;
import br.com.stapassoli.desafiovotacao.entity.VotoId;
import br.com.stapassoli.desafiovotacao.exceptions.SessaoException;
import br.com.stapassoli.desafiovotacao.exceptions.VotoException;
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
    private final ModelMapper modelMapper;

    public void isVotoCadastrado (VotoDTO votoDTO) throws VotoException {

        VotoId votoId = new VotoId();
        votoId.setIdSessao(votoDTO.getIdSessao());
        votoId.setIdAssociado(votoDTO.getIdAssociado());

        if(votoRepository.findById(votoId).isPresent()) {
            throw new VotoException("Voto já cadastrado");
        }

    }

    public ResponseEntity<VotoDTO> cadastrarVoto(VotoDTO votoDTO)  {

        isVotoCadastrado(votoDTO);

        Sessao sessao = this.sessaoRepository
            .findById(votoDTO.getIdSessao())
            .orElseThrow(() -> new SessaoException("Sessao nao encontrada"));

        sessao.isDentroLimiteTempo();

        VotoId votoId = new VotoId();
        votoId.setIdSessao(votoDTO.getIdSessao());
        votoId.setIdAssociado(votoDTO.getIdAssociado());

        Voto voto = new Voto();
        voto.setId(votoId);
        voto.setVotoStatus(votoDTO.getVotoStatus());

        voto.setSessao(sessao);

        Voto votoSalvo = votoRepository.save(voto);

        return ResponseEntity.ok(modelMapper.map(votoSalvo, VotoDTO.class));
    }

}
