package db.desafiovotacao.service.interfaces;

import db.desafiovotacao.model.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface IPautaService {
    Pauta cadastrarPauta(Pauta pauta);

    Pauta buscarPautaPorID(Long id);

    Page<Pauta> listarPautas(Pageable pageable);
}
