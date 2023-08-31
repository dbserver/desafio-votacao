package com.db.desafio.repository;

import com.db.desafio.entity.Pauta;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;

import static com.db.desafio.util.factory.PautaFactory.pautaSemIdFactory;

@DataJpaTest
@ActiveProfiles("test")
@Sql(scripts = "classpath:massa-de-dados-pauta.sql")
class PautaRepositoryTest {

    @Autowired
    private PautaRepository pautaRepository;

    @Test
    void validaQueryBuscaPautaPorTitulo(){
        Pauta resultadoEsperado = new Pauta("Pauta1","Descricao1");

        Optional<Pauta> resultadoAtual = pautaRepository.findByTitulo("Pauta1");

        Assertions.assertEquals(resultadoEsperado.getDescricao(),resultadoAtual.get().getDescricao());

    }
}
