package db.desafiovotacao.repository;

import db.desafiovotacao.model.Pauta;
import db.desafiovotacao.model.VotoPauta;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

import java.util.UUID;

public interface VotoPautaRepository extends CrudRepository<VotoPauta, UUID> {
    List<VotoPauta> findByPauta(Pauta pauta);
}
