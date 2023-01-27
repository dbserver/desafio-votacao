package br.com.occ.desafiovotacao.v1.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssociadoDto extends BaseDto {

    private Long id;
    @NotNull(message = "Atributo nome é obrigatório")
    private String nome;
    private boolean ativo;
}
