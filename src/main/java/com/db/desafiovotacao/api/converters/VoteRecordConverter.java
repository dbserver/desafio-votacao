package com.db.desafiovotacao.api.converters;

import com.db.desafiovotacao.api.entity.Vote;
import com.db.desafiovotacao.api.record.VoteAgendaRecord;
import com.db.desafiovotacao.api.record.VoteMemberRecord;
import com.db.desafiovotacao.api.record.VoteRecord;
import org.springframework.stereotype.Component;

@Component
public class VoteRecordConverter {
     public VoteRecord toVoteRecord(Vote vote){
         return new VoteRecord(vote.getId(), new VoteMemberRecord(vote.getMember().getId(), vote.getMember().getName()),new VoteAgendaRecord(vote.getAgenda().getId(),vote.getAgenda().getName(), vote.getAgenda().getVotes()), vote.getVoted());
     }
}
