package com.challenge.votation.service;

import com.challenge.votation.model.DocumentRequest;
import com.challenge.votation.model.DocumentResponse;
import com.challenge.votation.model.DocumentStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
@Log4j2
public class DocumentService {

    public DocumentResponse verifyCpfDocument(DocumentRequest documentRequest) {
        Random random = new Random();
        boolean randomBoolean = random.nextBoolean();

        if (randomBoolean) {
            log.info("CPF document {} is able to vote", documentRequest.getCpfNumber());
            return new DocumentResponse(DocumentStatus.ABLE_TO_VOTE);
        }
        log.info("CPF document {} is unable to vote", documentRequest.getCpfNumber());
        return new DocumentResponse(DocumentStatus.UNABLE_TO_VOTE);
    }
}
