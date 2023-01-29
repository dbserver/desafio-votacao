package br.com.occ.desafiovotacao.v1.dto;

import br.com.occ.desafiovotacao.v1.enums.PautaStatusEnum;
import br.com.occ.desafiovotacao.v1.model.Sessao;
import br.com.occ.desafiovotacao.v1.model.Voto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Set;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PautaDto extends BaseDto {

    private Long id;
    @NotNull(message = "Atributo descricao é obrigatório")
    private String descricao;
    private PautaStatusEnum status;
    private Sessao sessao;
    private Set<Voto> votos;
}
