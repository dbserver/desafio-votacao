package br.com.occ.desafiovotacao.v1.dto;

import br.com.occ.desafiovotacao.v1.enums.VotoEnum;
import br.com.occ.desafiovotacao.v1.model.Associado;
import br.com.occ.desafiovotacao.v1.model.Pauta;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VotoDto extends BaseDto {

    private Long id;

    @NotNull(message = "Atributo pauta é obrigatório")
    private Pauta pauta;

    @NotNull(message = "Atributo associado é obrigatório")
    private Associado associado;

    @NotNull(message = "Atributo voto é obrigatório")
    private VotoEnum voto;
}
