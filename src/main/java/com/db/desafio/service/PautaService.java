package com.db.desafio.service;

import com.db.desafio.dto.PautaResultadoDto;
import com.db.desafio.entity.Pauta;
import com.db.desafio.exception.PautaException;
import com.db.desafio.repository.PautaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class PautaService {

    private PautaRepository pautaRepository;

    public List<Pauta> obterPautas() {
        return pautaRepository.findAll();
    }

    public Pauta obterPautaPorId(Long id) {
        return pautaRepository.findById(id)
                .orElseThrow(() -> new PautaException("Pauta inexistente"));
    }

    public void criarPauta(Pauta pauta) {
        pautaRepository.findByTitulo(pauta.getTitulo()).ifPresent(p -> {
            throw new PautaException("Pauta já existente com Titulo: " + pauta.getTitulo());
        });
        pautaRepository.save(pauta);
    }

    public void deletarPauta(Long id) {
        obterPautaPorId(id);
        pautaRepository.deleteById(id);
    }
    public PautaResultadoDto obterResultadoPauta(Long pautaId) {
        Pauta pauta = obterPautaPorId(pautaId);
        if (pauta.getSessaoVotacao().getVotos().isEmpty()){
             throw new PautaException("Pauta ainda não foi votada");
        }
        return new PautaResultadoDto(pauta.getTitulo(), pauta.obterResultado());
    }

}



