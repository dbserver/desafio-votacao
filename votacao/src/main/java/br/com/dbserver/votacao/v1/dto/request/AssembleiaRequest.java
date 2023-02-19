package br.com.dbserver.votacao.v1.dto.request;




import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AssembleiaRequest {

	@Builder.Default
	private LocalDateTime inicio = LocalDateTime.now();

	@Builder.Default
	private LocalDateTime fim = LocalDateTime.now().plusHours(4);
}
