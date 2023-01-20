package br.com.aplicationvotacao.aplicationvotacao.model;


import br.com.aplicationvotacao.aplicationvotacao.model.enums.VotoEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name="voto")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Voto implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @NotNull(message = "O campo id_associado é obrigatório.")
    private Long idAssociado;

    @NotNull(message = "O campo id_sessao_votacao é obrigatório.")
    @ManyToOne
    @JoinColumn(name = "fk_sessao_votacao")
    private SessaoVotacao sessaoVotacao;

    @NotNull(message = "O campo voto é obrigatório.")
    private VotoEnum voto;

}

