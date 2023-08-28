package br.com.stapassoli.desafiovotacao.entity;

import br.com.stapassoli.desafiovotacao.enums.VotoStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class Voto {

    @EmbeddedId
    @Column(unique = true)
    private VotoId id;

    @Enumerated(EnumType.STRING)
    private VotoStatus votoStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    private Sessao sessao;

}
