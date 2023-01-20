package br.com.aplicationvotacao.aplicationvotacao.service;


import br.com.aplicationvotacao.aplicationvotacao.dto.VotoDTO;
import br.com.aplicationvotacao.aplicationvotacao.mapper.VotoMapper;
import br.com.aplicationvotacao.aplicationvotacao.model.SessaoVotacao;
import br.com.aplicationvotacao.aplicationvotacao.model.Voto;
import br.com.aplicationvotacao.aplicationvotacao.repository.SessaoVotacaoRepository;
import br.com.aplicationvotacao.aplicationvotacao.repository.VotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class VotoService {

    @Autowired
    VotoRepository votoRepository;

    @Autowired
    SessaoVotacaoRepository sessaoVotacaoRepository;

    public Voto salvarVoto(VotoDTO votoDTO){

        Optional<SessaoVotacao> sessaoVotacao = sessaoVotacaoRepository.findById(votoDTO.getId_sessao_votacao());
        if(sessaoVotacao.isEmpty())
            throw new IllegalArgumentException("Sessão não existe.");
        else{
            LocalDateTime dataAtual = LocalDateTime.now();
            if(sessaoVotacao.get().getDataFim().isBefore(dataAtual) || sessaoVotacao.get().getDataInicio().isAfter(dataAtual))
                throw new IllegalArgumentException("Sessão fechada para votos.");
            else{
                Optional<Voto> votoProcurado = votoRepository.findByidAssociadoEqualsAndSessaoVotacaoIdEquals(votoDTO.getIdAssociado(),votoDTO.getId_sessao_votacao());
                if(votoProcurado.isPresent())
                    throw new IllegalArgumentException("Voto já registrado na Sessão.");
                else{
                    return votoRepository.save(VotoMapper.dtoParaVoto(votoDTO, sessaoVotacao.get()));
                }
            }
        }
    }

}
