package com.db.api.stubs;

import com.db.api.models.Pauta;

public interface PautaStub {
    static Pauta gerarPautaDtoValida() {
        return Pauta.builder()
                .titulo("Novas funcionalidades")
                .descricao("Discussão sobre a adição de notificações à aplicação.")
                .build();
    }

    static Pauta gerarPautaRequestDto() {
        return Pauta.builder()
                .titulo("Novas funcionalidades")
                .build();
    }

    static Pauta gerarPautaDtoTituloVazio() {
        return Pauta.builder()
                .titulo(" ")
                .descricao("Discussão sobre a adição de notificações à aplicação.")
                .build();
    }

}
