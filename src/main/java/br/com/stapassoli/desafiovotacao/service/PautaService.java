package br.com.stapassoli.desafiovotacao.service;

import br.com.stapassoli.desafiovotacao.dto.PautaDTO;
import br.com.stapassoli.desafiovotacao.entity.Pauta;
import br.com.stapassoli.desafiovotacao.repository.PautaRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PautaService {

    private final PautaRepository pautaRepository;
    private final ModelMapper modelMapper;

    public ResponseEntity<Pauta> cadastrarPauta(PautaDTO pautaDto) {
        Pauta pauta = modelMapper.map(pautaDto, Pauta.class);
        return ResponseEntity.ok(pautaRepository.save(pauta));
    }

}
