package br.com.dbserver.votacao.v1.entity;

import br.com.dbserver.votacao.v1.enums.PautaStatusEnum;
import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Entity
@Table(name = "pauta")
public class Pauta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String descricao;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "pauta_votacao", joinColumns = {
            @JoinColumn(name = "pauta_id", referencedColumnName = "id") }, inverseJoinColumns = {
            @JoinColumn(name = "votos_id", referencedColumnName = "id") })
    private List<Voto> votos;

    private LocalDateTime inicio;
    private LocalDateTime fim;

    @Builder.Default
    private PautaStatusEnum status = PautaStatusEnum.AGUARDANDO_RESULTADO;
}