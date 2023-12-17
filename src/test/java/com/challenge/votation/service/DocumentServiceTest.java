package com.challenge.votation.service;

import com.challenge.votation.model.DocumentRequest;
import com.challenge.votation.model.DocumentResponse;
import com.challenge.votation.model.DocumentStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertTrue;

class DocumentServiceTest {

    @InjectMocks
    private DocumentService documentService;

    @Mock
    private Random random;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testVerifyCpfDocumentAbleToVote() {
        DocumentRequest documentRequest = new DocumentRequest("053.203.390-62");

        DocumentResponse response = documentService.verifyCpfDocument(documentRequest);

        assertTrue(response.getStatus().equals(DocumentStatus.ABLE_TO_VOTE) || response.getStatus().equals(DocumentStatus.UNABLE_TO_VOTE));
    }
}
