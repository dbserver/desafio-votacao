
package com.dbserver.desafiovotacao.service;

import com.dbserver.desafiovotacao.dto.VotanteRequest;
import com.dbserver.desafiovotacao.model.Votante;
import com.dbserver.desafiovotacao.repository.VotanteRepositorio;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
@Service
public class VotanteServiceImplementacao implements VotanteService{
    private final VotanteRepositorio votanteRepositorio;
    
    @Autowired
	public VotanteServiceImplementacao(VotanteRepositorio votanteRepositorio) {
		this.votanteRepositorio = votanteRepositorio;

	}
        
    @Override
	public Optional<Votante> encontrarVotantePorID(UUID id) throws DataAccessException {
		return votanteRepositorio.findById(id);
	}
        
    @Override
	public long totalVotantes() {
		return votanteRepositorio.count();
	}
    @Override
	public Iterable<Votante> findAll() {
		return this.votanteRepositorio.findAll();
	}
        
    @Override
    public Votante salvarVotante(VotanteRequest votanteRequest) throws DataAccessException {
        return votanteRepositorio.save(Votante.builder().idVotante(votanteRequest.codAssociado())
				.voto(votanteRequest.votoEnum()).build());
    }
}
