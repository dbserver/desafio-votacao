package br.com.dbserver.votacao.v1.entity;

import br.com.dbserver.votacao.v1.enums.StatusUsuarioEnum;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Entity
@Setter
@Table(name = "associado")
public class Associado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String cpf;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    private StatusUsuarioEnum status = StatusUsuarioEnum.PODE_VOTAR;
}
