package br.com.dbserver.votacao.v1.entity;

import br.com.dbserver.votacao.v1.enums.VotoEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Entity
@Setter
@Table(name = "voto")
public class Voto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "pauta_id", nullable = false)
    private Pauta pauta;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "associado_id", nullable = false)
    private Associado associado;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private VotoEnum valor;
}