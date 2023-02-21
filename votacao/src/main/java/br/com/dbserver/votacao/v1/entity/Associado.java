package br.com.dbserver.votacao.v1.entity;

import br.com.dbserver.votacao.v1.enums.StatusUsuarioEnum;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "associado")
public class Associado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String documento;

    @Enumerated(EnumType.STRING)
    private StatusUsuarioEnum status;
}
