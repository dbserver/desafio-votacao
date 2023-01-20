package br.com.aplicationvotacao.aplicationvotacao.service;


import br.com.aplicationvotacao.aplicationvotacao.dto.PautaDTO;
import br.com.aplicationvotacao.aplicationvotacao.dto.ResultadoPautaDTO;
import br.com.aplicationvotacao.aplicationvotacao.mapper.PautaMapper;
import br.com.aplicationvotacao.aplicationvotacao.model.Pauta;
import br.com.aplicationvotacao.aplicationvotacao.model.SessaoVotacao;
import br.com.aplicationvotacao.aplicationvotacao.repository.PautaRepository;
import br.com.aplicationvotacao.aplicationvotacao.repository.SessaoVotacaoRepository;
import br.com.aplicationvotacao.aplicationvotacao.service.utils.ResultadoPautaDTOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PautaService {

    @Autowired
    PautaRepository pautaRepository;

    @Autowired
    SessaoVotacaoRepository sessaoVotacaoRepository;

    @Autowired
    ResultadoPautaDTOUtils resultadoPautaDTOUtils;

    public Pauta salvarPauta(PautaDTO pautaDTO){
        Pauta pauta = PautaMapper.dtoParaPauta(pautaDTO);
        return pautaRepository.save(pauta);
    }

    public List<Pauta> buscarPautas(){
        return pautaRepository.findAll();
    }

    public Optional<Pauta> getPautaById(Long idPauta){
        return pautaRepository.findById(idPauta);
    }

    public ResultadoPautaDTO resultadoPauta(Long idPauta){

        Optional<Pauta> pauta = pautaRepository.findById(idPauta);

        if(pauta.isEmpty())
            throw new IllegalArgumentException("Pauta não existe.");
        else{
            Optional<SessaoVotacao> sessaoVotacao = sessaoVotacaoRepository.findByPautaId(idPauta);
            if(sessaoVotacao.isEmpty())
                throw new IllegalArgumentException("Sessão de votação não existe.");
            else{
                return resultadoPautaDTOUtils.getResultadoPautaDTOFromSessaoVotacao(sessaoVotacao.get());
            }
        }
    }

}
