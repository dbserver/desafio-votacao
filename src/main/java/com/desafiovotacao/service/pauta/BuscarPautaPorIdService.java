
package com.desafiovotacao.service.pauta;

import com.desafiovotacao.domain.Pauta;
import com.desafiovotacao.repository.PautaRepository;
import com.desafiovotacao.service.interfaces.IBuscarPautaPorIdService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BuscarPautaPorIdService implements IBuscarPautaPorIdService {

    private final PautaRepository pautaRepository;

    public BuscarPautaPorIdService(PautaRepository pautaRepository) {
        this.pautaRepository = pautaRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Pauta buscar(String id) {
        return this.pautaRepository.findById(id).orElse(null);
    }
}
