package com.db.desafiovotacao.api.controller;

import com.db.desafiovotacao.api.exception.SessionNotFoundException;
import com.db.desafiovotacao.api.record.SessionRecord;
import com.db.desafiovotacao.api.service.OpenSessionServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.UUID;

@RestController
@RequestMapping("/sessions")
public class SessionController {
    @Autowired
    private OpenSessionServiceInterface openSessionService;

    @PostMapping("/{agendaId}")
    public ResponseEntity<SessionRecord> openSession(@PathVariable UUID agendaId, @RequestParam(required = false) Duration duration) throws SessionNotFoundException {
        try {
            SessionRecord sessionRecored = openSessionService.openSession(agendaId, duration);
            return ResponseEntity.ok(sessionRecored);
        } catch (SessionNotFoundException e) {
            throw e;
        }
    }

    @ExceptionHandler(SessionNotFoundException.class)
    public ResponseEntity<String> handleSessionNotFoundException(SessionNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
}
