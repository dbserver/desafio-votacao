package br.tec.db.desafio.api.v1.dto.associado.client;

import br.tec.db.desafio.business.domain.enums.StatusCpf;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AssociadoClientResponseV1 {
    private StatusCpf statusCpf;
}
