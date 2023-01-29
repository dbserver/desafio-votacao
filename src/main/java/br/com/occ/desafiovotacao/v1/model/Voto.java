package br.com.occ.desafiovotacao.v1.model;

import br.com.occ.desafiovotacao.v1.enums.VotoEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tab_voto")
public class Voto extends BaseModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "pauta_id", nullable = false)
    private Pauta pauta;

    @OneToOne
    @JoinColumn(name = "associado_id", nullable = false)
    private Associado associado;

    @Column(nullable = false)
    @NotNull(message = "Atributo voto é obrigatório")
    @Enumerated(EnumType.STRING)
    private VotoEnum voto;
}
