package br.com.occ.desafiovotacao.v1.model;

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
@Table(name = "tab_associado")
public class Associado extends BaseModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Atributo nome é obrigatório")
    @Column(nullable = false)
    private String nome;

    @NotNull(message = "Atributo cpf é obrigatório")
    @Column(nullable = false)
    private String cpf;

    private Boolean ativo;
}
