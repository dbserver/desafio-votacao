package br.com.vitt.apivotacao.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.vitt.apivotacao.entities.PautaAssociado;

public interface PautaAssociadoRepository extends JpaRepository<PautaAssociado, Integer>{
	
	public Optional<PautaAssociado> findById_PautaAndId_Associado(Integer id_pauta, Integer id_associado);

}
