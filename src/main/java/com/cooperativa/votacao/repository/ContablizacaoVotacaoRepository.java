package com.cooperativa.votacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cooperativa.votacao.entity.ContabilizacaoVotacaoPauta;

@Repository
public interface ContablizacaoVotacaoRepository extends JpaRepository<ContabilizacaoVotacaoPauta, Integer>{
	
	@Query(nativeQuery = true, value = "SELECT p.id as id_Pauta,p.nome as nome_pauta,"
			+ " p.TEMPO_SESSAO, statusSessao.NOME_STATUS_SESSAO as status_Sessao,"
			+ "(select count(*) from VOTACAO.voto v where v.id_pauta= :idPauta and v.voto='Sim') as total_Sim,"
			+ "(select count(*) from VOTACAO.voto v where v.id_pauta= :idPauta and v.voto='Não') as total_Nao "
			+ "FROM VOTACAO.pauta p "
			+ "join VOTACAO.STATUS_SESSAO statusSessao on statusSessao.ID = p.ID_STATUS_SESSAO "
			+ "where p.id= :idPauta")
	ContabilizacaoVotacaoPauta buscarContablizacaoVotacaoPorPauta(Integer idPauta);

}
