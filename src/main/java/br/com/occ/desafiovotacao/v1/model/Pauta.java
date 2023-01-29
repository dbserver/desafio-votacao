package br.com.occ.desafiovotacao.v1.model;

import br.com.occ.desafiovotacao.v1.enums.PautaStatusEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tab_pauta")
public class Pauta extends BaseModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Atributo descricao é obrigatório")
    @Column(nullable = false)
    private String descricao;

    @Enumerated(EnumType.STRING)
    private PautaStatusEnum status;

    @OneToOne
    @JoinColumn(name = "sessao_id")
    private Sessao sessao;

    @OneToMany(mappedBy = "pauta")
    private Set<Voto> votos;

    public Pauta(Long id) {
        this.id = id;
    }
}
