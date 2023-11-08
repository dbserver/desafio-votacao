package com.example.desafiovotacao.facade;

import com.example.desafiovotacao.dto.FacadeDTO;
import com.example.desafiovotacao.utils.CpfUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@AutoConfigureTestDatabase
public class CpfFacadeTests {

    @Autowired
    private CpfFacade cpfFacade;

    @Test
    void shouldReturnAbleOrUnable() {
        FacadeDTO facadeDTO = cpfFacade.isCpfValid(CpfUtils.generateCPF()).getBody();

        assertNotNull(facadeDTO);
        assertTrue("ABLE_TO_VOTE".equals(facadeDTO.getStatus()) || "UNABLE_TO_VOTE".equals(facadeDTO.getStatus()));
    }

}
