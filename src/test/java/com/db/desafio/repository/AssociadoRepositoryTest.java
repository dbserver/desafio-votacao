package com.db.desafio.repository;

import com.db.desafio.entity.Associado;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;


@DataJpaTest
@ActiveProfiles("test")
@Sql(scripts = "classpath:massa-de-dados-associado.sql")
@ExtendWith(SpringExtension.class)
class AssociadoRepositoryTest {

    @Autowired
    private AssociadoRepository associadoRepository;

    @Test
    void validaQueryBuscaAssociadoaPorCpF(){
        Associado resultadoEsperado = new Associado("Joao","123.456.789-01");

        Optional<Associado> resultadoAtual = associadoRepository.findByCpf("123.456.789-01");

       Assertions.assertEquals(resultadoEsperado.getCpf(),resultadoAtual.get().getCpf());
       Assertions.assertEquals(resultadoEsperado.getNome(),resultadoAtual.get().getNome());


    }
}
