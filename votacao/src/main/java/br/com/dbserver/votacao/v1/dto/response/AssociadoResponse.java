package br.com.dbserver.votacao.v1.dto.response;

import br.com.dbserver.votacao.v1.enums.StatusUsuarioEnum;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AssociadoResponse {
    private Long id;
    private String nome;
    private String documento;
    private StatusUsuarioEnum status;
}
