package br.com.occ.desafiovotacao.utils;

import br.com.occ.desafiovotacao.v1.dto.AssociadoDto;
import br.com.occ.desafiovotacao.v1.model.Associado;

import java.util.Optional;

public class EntityUtils {
    public static final Long ID = 1L;
    public static final String NOME = "Bernardo Noah Sebastião Sales";
    public static final String CPF = "18722519009";
    public static final boolean ATIVO = true;


    public static Associado criarAssociado(boolean ativo) {
        return new Associado(ID,NOME, CPF, ativo);
    }

    public static AssociadoDto criarAssociadoDto(boolean ativo) {
        return new AssociadoDto(ID,NOME, CPF, ativo);
    }

    public static Optional<Associado> criarAssociadoOptional(boolean ativo) {
        return Optional.of(new Associado(ID,NOME, CPF, ativo));
    }

    public static Optional<AssociadoDto> criarAssociadoDtoOptional(boolean ativo) {
        return Optional.of(new AssociadoDto(ID,NOME, CPF, ativo));
    }
}
