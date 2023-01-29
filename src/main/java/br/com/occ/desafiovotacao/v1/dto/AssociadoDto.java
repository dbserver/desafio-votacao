package br.com.occ.desafiovotacao.v1.dto;

import lombok.*;

import javax.validation.constraints.NotNull;


@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssociadoDto extends BaseDto {

    private Long id;
    @NotNull(message = "Atributo nome é obrigatório")
    private String nome;
    private String cpf;
    private boolean ativo;
}
