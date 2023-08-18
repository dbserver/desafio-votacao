package com.db.votacao.api.model;

import io.swagger.annotations.ApiModelProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import java.util.UUID;

@Entity
@Table(name = Associado.TABLE_NAME)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Associado {

    public static final String TABLE_NAME = "associado";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_associado", unique = true)
    @ApiModelProperty(notes = "ID único do associado")
    private UUID idAssociado;

    @Column(name = "cpf", nullable = false, unique = true, length = 14)
    @CPF(message = "CPF inválido")
    @ApiModelProperty(notes = "CPF do associado")
    private String cpf;

}
