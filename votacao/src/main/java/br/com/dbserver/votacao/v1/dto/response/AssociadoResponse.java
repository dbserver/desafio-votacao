package br.com.dbserver.votacao.v1.dto.response;

import br.com.dbserver.votacao.v1.enums.StatusUsuarioEnum;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssociadoResponse {
    private Long id;
    private String nome;
    private String documento;
    private StatusUsuarioEnum status;
}
