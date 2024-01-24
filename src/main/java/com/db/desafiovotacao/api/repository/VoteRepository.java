package com.db.desafiovotacao.api.repository;

import com.db.desafiovotacao.api.entity.Agenda;
import com.db.desafiovotacao.api.entity.Member;
import com.db.desafiovotacao.api.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface VoteRepository extends JpaRepository<Vote, UUID> {
    boolean existsByMemberAndAgenda(Member member, Agenda agenda);
}
