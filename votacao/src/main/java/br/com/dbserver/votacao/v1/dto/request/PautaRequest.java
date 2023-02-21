package br.com.dbserver.votacao.v1.dto.request;

import br.com.dbserver.votacao.v1.entity.Voto;
import br.com.dbserver.votacao.v1.enums.PautaStatusEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PautaRequest {

	@NotNull(message = "ID da Assembleia não pode ser null")
	Long assembleiaId;

	@Builder.Default
	private String descricao = "Sem Descrição";

	@Builder.Default
	@JsonIgnore
	private List<Voto> votos = new ArrayList<>();

	@Builder.Default
	private LocalDateTime inicio = LocalDateTime.now();

	@Builder.Default
	private LocalDateTime fim = LocalDateTime.now().plusMinutes(1);

	@Builder.Default
	@JsonIgnore
	private PautaStatusEnum status = PautaStatusEnum.AGUARDANDO_RESULTADO;

}