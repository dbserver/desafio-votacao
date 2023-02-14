package br.com.dbserver.votacao.v1.dto.response;

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
    private String cpf;
    private String status;
}
