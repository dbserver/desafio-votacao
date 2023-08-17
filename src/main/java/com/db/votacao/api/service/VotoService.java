package com.db.votacao.api.service;

import com.db.votacao.api.enums.EnumOpcoesVoto;
import com.db.votacao.api.interfaces.IVotoService;
import com.db.votacao.api.model.Voto;
import com.db.votacao.api.repository.VotoRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class VotoService implements IVotoService {

    private final VotoRepository votoRepository;

    public VotoService(VotoRepository votoRepository) {
        this.votoRepository = votoRepository;
    }

    @Override
    public Voto criarVoto(Voto voto) {
        return votoRepository.save(voto);
    }

    public Map<EnumOpcoesVoto, Long> calcularTotalizadorVotos(UUID pautaId) {
        Map<EnumOpcoesVoto, Long> totalizador = new HashMap<>();

        totalizador.put(EnumOpcoesVoto.SIM, votoRepository.countByPauta_IdPautaAndVoto(pautaId, EnumOpcoesVoto.SIM));
        totalizador.put(EnumOpcoesVoto.NAO, votoRepository.countByPauta_IdPautaAndVoto(pautaId, EnumOpcoesVoto.NAO));

        return totalizador;
    }
}
