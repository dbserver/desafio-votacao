package com.example.desafiovotacao.service.implementations;

import com.example.desafiovotacao.dto.CreatedSessionDTO;
import com.example.desafiovotacao.dto.SessionReturnDTO;
import com.example.desafiovotacao.dto.StartSessionDTO;
import com.example.desafiovotacao.entity.RulingEntity;
import com.example.desafiovotacao.entity.SessionEntity;
import com.example.desafiovotacao.exception.RulingExceptions;
import com.example.desafiovotacao.exception.SessionExceptions;
import com.example.desafiovotacao.exception.ValidationExceptions;
import com.example.desafiovotacao.repository.RulingRepository;
import com.example.desafiovotacao.repository.SessionRepository;
import com.example.desafiovotacao.service.interfaces.RulingService;
import com.example.desafiovotacao.service.interfaces.SessionService;
import com.example.desafiovotacao.utils.DateUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SessionServiceImpl implements SessionService {

    private final SessionRepository sessionRepository;
    private final RulingRepository rulingRepository;
    @Value("${session.duration}")
    private Integer ENV_SESSION_DURATION;

    @Override
    public CreatedSessionDTO create(StartSessionDTO startSessionDTO) {
        validateStartSessionDTO(startSessionDTO);

        Optional<RulingEntity> existingRuling = rulingRepository.findById(startSessionDTO.getRulingId());
        if(existingRuling.isEmpty()){
            RulingExceptions.rulingDoesNotExist();
        }

        SessionEntity newSession = new SessionEntity();
        newSession.setRuling(existingRuling.get());
        newSession.setDuration(startSessionDTO.getDuration() == null ? ENV_SESSION_DURATION : startSessionDTO.getDuration());
        newSession = sessionRepository.save(newSession);

        return CreatedSessionDTO.builder()
                .sessionId(newSession.getId())
                .duration(newSession.getDuration())
                .creationDate(DateUtils.formatDate(newSession.getCreationDate()))
                .build();
    }

    @Override
    public List<SessionReturnDTO> listAll() {
        return sessionRepository.listAllReturns();
    }

    @Override
    public SessionReturnDTO listById(Integer sessionId) {
        return sessionRepository.findReturnById(sessionId).get();
    }

    @Override
    public SessionEntity getSessionByRulingId(Integer rulingId) {
        Optional<SessionEntity> existingSession = sessionRepository.findLatestByRulingId(rulingId);
        if(existingSession.isEmpty()){
            SessionExceptions.sessionDontExist();
        }

        return existingSession.get();
    }

    @Override
    public SessionEntity getSessionByIdIfExists(Integer id) {
        Optional<SessionEntity> existingSession = sessionRepository.findById(id);
        if(existingSession.isEmpty()){
            SessionExceptions.sessionDontExist();
        }

        return existingSession.get();
    }

    @Override
    public void validateStartSessionDTO(StartSessionDTO startSessionDTO) {
        if(startSessionDTO.getRulingId() == null) {
            ValidationExceptions.faultyInformation();
        }
    }

}
