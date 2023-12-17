package com.challenge.votation.controller;

import com.challenge.votation.model.DocumentRequest;
import com.challenge.votation.model.DocumentResponse;
import com.challenge.votation.model.DocumentStatus;
import com.challenge.votation.service.DocumentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.when;

@WebMvcTest(DocumentController.class)
class DocumentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private DocumentService documentService;

    @Test
    void verifyCpfDocument_shouldReturnOkStatus() throws Exception {
        String cpfDocument = "053.203.390-62";
        DocumentRequest documentRequest = new DocumentRequest(cpfDocument);
        DocumentResponse documentResponse = new DocumentResponse(DocumentStatus.ABLE_TO_VOTE);

        when(documentService.verifyCpfDocument(Mockito.any(DocumentRequest.class)))
                .thenReturn(documentResponse);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/documents")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(documentRequest)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").isNotEmpty());
    }

}
