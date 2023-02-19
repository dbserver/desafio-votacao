package br.com.dbserver.votacao.v1.dto.response;

import br.com.dbserver.votacao.v1.entity.Pauta;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AssembleiaResponse {
	private Long id;
	private LocalDateTime inicio;
	private LocalDateTime fim;
	List<Pauta> pautas;
}
