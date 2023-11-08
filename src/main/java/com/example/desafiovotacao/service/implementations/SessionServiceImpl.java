package com.example.desafiovotacao.service.implementations;

import com.example.desafiovotacao.dto.CreatedSessionDTO;
import com.example.desafiovotacao.dto.SessionReturnDTO;
import com.example.desafiovotacao.dto.StartSessionDTO;
import com.example.desafiovotacao.entity.RulingEntity;
import com.example.desafiovotacao.entity.SessionEntity;
import com.example.desafiovotacao.exception.ValidationExceptions;
import com.example.desafiovotacao.exception.enums.implementations.InformationErrorMessages;
import com.example.desafiovotacao.exception.enums.implementations.RulingErrorMessages;
import com.example.desafiovotacao.exception.enums.implementations.SessionErrorMessages;
import com.example.desafiovotacao.repository.RulingRepository;
import com.example.desafiovotacao.repository.SessionRepository;
import com.example.desafiovotacao.service.interfaces.SessionService;
import com.example.desafiovotacao.utils.DateUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

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

        RulingEntity existingRuling = rulingRepository.findById(startSessionDTO.getRulingId()).orElseThrow(() -> {
            throw new ValidationExceptions(RulingErrorMessages.RULING_DOES_NOT_EXIST);
        });

        SessionEntity newSession = sessionRepository.save(
                SessionEntity.builder()
                        .ruling(existingRuling)
                        .duration(startSessionDTO.getDuration() == null ? ENV_SESSION_DURATION : startSessionDTO.getDuration())
                        .build()
        );

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
        return sessionRepository.findLatestByRulingId(rulingId).orElseThrow(() -> {
            throw new ValidationExceptions(SessionErrorMessages.SESSION_DOES_NOT_EXIST);
        });
    }

    @Override
    public SessionEntity getSessionByIdIfExists(Integer id) {
        return sessionRepository.findById(id).orElseThrow(() -> {
            throw new ValidationExceptions(SessionErrorMessages.SESSION_DOES_NOT_EXIST);
        });
    }

    @Override
    public void validateStartSessionDTO(StartSessionDTO startSessionDTO) {
        if(startSessionDTO.getRulingId() == null) {
            throw new ValidationExceptions(InformationErrorMessages.FAULTY_INFORMATION);
        }
    }

}
