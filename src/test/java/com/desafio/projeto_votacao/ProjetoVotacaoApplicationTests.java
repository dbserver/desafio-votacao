package com.desafio.projeto_votacao;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.context.ApplicationContext;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
public class ProjetoVotacaoApplicationTests {

    @Mock
    private ProjetoVotacaoApplication projetoVotacaoApplication;

    @Mock
    private ApplicationContext context;

    @Test
    void contextLoadsApplication() {
        assertNotNull(projetoVotacaoApplication);
    }

    @Test
    void contextLoadsContext() {
        assertNotNull(context);
    }

    @Test
    void modelMapperBeanTest() {
        ModelMapper modelMapper = context.getBean(ModelMapper.class);
        assertNull( modelMapper);
    }

}