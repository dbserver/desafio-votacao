package com.db.api.stubs;

import com.db.api.models.Associado;

public interface AssociadoStub {
    static Associado gerarAssociadoDtoValida() {
        return Associado.builder()
                .nome("Carla Silva")
                .cpf("44271476072")
                .build();
    }

    static Associado gerarAssociadoDtoCpfInvalida() {
        return Associado.builder()
                .nome("Mario Souza")
                .cpf("101010")
                .build();
    }
}
