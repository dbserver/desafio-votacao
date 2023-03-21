package com.desafio.votacao.dto;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "AssociadoBasicoDTO")
public class AssociadoBasicoDTO implements Serializable {
    
    private static final long serialVersionUID = 597022010845966821L;

	@Schema(description = "Nome do associado")
    private String nome;

	@Schema(description = "Cpf do associado")
    private String cpf;
}
