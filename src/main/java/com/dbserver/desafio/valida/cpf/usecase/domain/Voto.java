package com.dbserver.desafio.valida.cpf.usecase.domain;

import com.dbserver.desafio.valida.cpf.usecase.enuns.VotoEnum;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

@Getter
@Setter
@EqualsAndHashCode
@FieldNameConstants
public class Voto {

    private String cpfAssociado;

    private Pauta pauta;

    private VotoEnum voto;
}
