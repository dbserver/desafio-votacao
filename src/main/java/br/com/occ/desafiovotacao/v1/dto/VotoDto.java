package br.com.occ.desafiovotacao.v1.dto;

import br.com.occ.desafiovotacao.v1.enums.VotoEnum;
import br.com.occ.desafiovotacao.v1.model.Associado;
import br.com.occ.desafiovotacao.v1.model.Sessao;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VotoDto extends BaseDto {

    private Long id;

    @NotNull(message = "Atributo sessao é obrigatório")
    private Sessao sessao;

    @NotNull(message = "Atributo associado é obrigatório")
    private Associado associado;

    @NotNull(message = "Atributo voto é obrigatório")
    private VotoEnum voto;
}
