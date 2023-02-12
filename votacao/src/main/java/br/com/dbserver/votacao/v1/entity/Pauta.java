package br.com.dbserver.votacao.v1.entity;

import br.com.dbserver.votacao.v1.enums.PautaStatusEnum;
import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
    @Builder.Default
    private List<Voto> votos = new ArrayList<>();

    @Builder.Default
    private LocalDateTime inicio = LocalDateTime.now();

    @Builder.Default
    private LocalDateTime fim = LocalDateTime.now().plusMinutes(1);

    @Builder.Default
    @Enumerated(EnumType.STRING)
    private PautaStatusEnum status = PautaStatusEnum.AGUARDANDO_RESULTADO;
}