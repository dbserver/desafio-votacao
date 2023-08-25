package br.com.stapassoli.desafiovotacao.entity;

import br.com.stapassoli.desafiovotacao.enums.VotoStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class Voto {

    @EmbeddedId
    private VotoId id;

    @Enumerated(EnumType.STRING)
    private VotoStatus votoStatus;

    @ManyToOne
    @JoinColumn(name = "id_sessao", insertable = false, updatable = false)
    private Sessao sessao;

}
