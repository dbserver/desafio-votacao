package com.example.desafiovotacao.repository;

import com.example.desafiovotacao.entity.RulingEntity;
import com.example.desafiovotacao.dto.RulingReturnDTO;
import com.example.desafiovotacao.dto.VotesDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RulingRepository extends JpaRepository<RulingEntity, Integer> {
    @Query("SELECT NEW com.example.desafiovotacao.dto.VotesDTO(" +
            "   SUM(CASE WHEN ve.vote = TRUE THEN 1 ELSE 0 END), " +
            "   SUM(CASE WHEN ve.vote = FALSE THEN 1 ELSE 0 END) " +
            ") " +
            "FROM RulingEntity re " +
            "   JOIN SessionEntity se ON se.ruling.id = re.id " +
            "   JOIN VoteEntity ve ON ve.session.id = se.id " +
            "WHERE re.id = :rulingId " +
            "   AND se.creationDate = (SELECT MAX(se2.creationDate) FROM SessionEntity se2 WHERE se2.ruling.id = re.id)")
    VotesDTO countVotes(Integer rulingId);

    @Query("SELECT NEW com.example.desafiovotacao.dto.RulingReturnDTO(" +
            "   re.id, " +
            "   re.title, " +
            "   re.description, " +
            "   CASE re.results WHEN TRUE THEN 'Sim' WHEN FALSE THEN 'Não' ELSE 'NÃO CONTABILIZADO' END, " +
            "   FORMAT(re.creationDate, 'dd/MM/yyyy')," +
            "   FORMAT(re.voteCountDate, 'dd/MM/yyyy') " +
            ") " +
            "FROM RulingEntity re")
    List<RulingReturnDTO> listAllReturn();

    @Query("SELECT NEW com.example.desafiovotacao.dto.RulingReturnDTO(" +
            "   re.id, " +
            "   re.title, " +
            "   re.description, " +
            "   CASE re.results WHEN TRUE THEN 'Sim' WHEN FALSE THEN 'Não' ELSE 'NÃO CONTABILIZADO' END, " +
            "   FORMAT(re.creationDate, 'dd/MM/yyyy')," +
            "   FORMAT(re.voteCountDate, 'dd/MM/yyyy') " +
            ") " +
            "FROM RulingEntity re " +
            "WHERE re.id = :rulingId")
    Optional<RulingReturnDTO> listReturnById(Integer rulingId);
}