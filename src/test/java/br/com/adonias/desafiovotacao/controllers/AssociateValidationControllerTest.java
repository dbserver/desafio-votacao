package br.com.adonias.desafiovotacao.controllers;

import br.com.adonias.desafiovotacao.business.AssociateValidationBusiness;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class AssociateValidationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AssociateValidationBusiness business;

    @Test
    void validateCPF() throws Exception {
        this.mockMvc.perform(get("/validation/{cpf}", "071"))
                .andExpect(status().isOk());

    }
}
