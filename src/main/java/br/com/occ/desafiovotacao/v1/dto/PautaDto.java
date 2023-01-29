package br.com.occ.desafiovotacao.v1.dto;

import br.com.occ.desafiovotacao.v1.enums.PautaStatusEnum;
import br.com.occ.desafiovotacao.v1.model.Sessao;
import br.com.occ.desafiovotacao.v1.model.Voto;
import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;


@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PautaDto extends BaseDto {

    private Long id;
    @NotNull(message = "Atributo descricao é obrigatório")
    private String descricao;
    private Sessao sessao;
    private List<Voto> votos;
    private PautaStatusEnum status;
    private Long totalVotosSim;
    private Long totalVotosNao;
    private Long totalVotos;


}
