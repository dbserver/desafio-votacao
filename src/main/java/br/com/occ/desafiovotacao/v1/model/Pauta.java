package br.com.occ.desafiovotacao.v1.model;

import br.com.occ.desafiovotacao.v1.enums.PautaStatusEnum;
import br.com.occ.desafiovotacao.v1.enums.VotoEnum;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@ToString
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

    @OneToOne
    @JoinColumn(name = "sessao_id")
    private Sessao sessao;

    @OneToMany(mappedBy = "pauta", cascade = CascadeType.ALL)
    private List<Voto> votos;
    @Transient
    @Enumerated(EnumType.STRING)
    private PautaStatusEnum status;

    @Transient
    private Long totalVotosSim;

    @Transient
    private Long totalVotosNao;

    @Transient
    private Long totalVotos;

    public Pauta(Long id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public Pauta(Long id, String descricao, Sessao sessao) {
        this.id = id;
        this.descricao = descricao;
        this.sessao = sessao;
    }

    public Long getTotalVotos() {
        if (!this.votos.isEmpty()) {
            this.totalVotos = (long) this.votos.size();
            long votosSim = votos.stream().filter(voto -> voto.getVoto().equals(VotoEnum.SIM)).count();
            long votosNao = votos.stream().filter(voto -> voto.getVoto().equals(VotoEnum.NAO)).count();
            this.totalVotosSim = votosSim;
            this.totalVotosNao = votosNao;
            this.setStatus(votosSim, votosNao);
            return this.totalVotos;
        } else
            return BigDecimal.ZERO.longValue();
    }

    public void setStatus(Long sim, Long nao) {
        if(sim > nao)
            this.status = PautaStatusEnum.APROVADA;
        else if (nao > sim)
            this.status = PautaStatusEnum.NAO_APROVADA;
        else
            this.status = PautaStatusEnum.EMPATADA;
    }
}
