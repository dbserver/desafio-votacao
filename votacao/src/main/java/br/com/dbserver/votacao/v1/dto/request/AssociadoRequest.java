package br.com.dbserver.votacao.v1.dto.request;

import br.com.dbserver.votacao.v1.enums.StatusUsuarioEnum;

import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssociadoRequest {

    @NotBlank(message = "Campo nome deve ser preenchido")
    private String nome;

    @NotBlank(message = "Campo cpf deve ser preenchido")
    private String cpf;

    @Builder.Default
    private StatusUsuarioEnum status = StatusUsuarioEnum.PODE_VOTAR;
}
