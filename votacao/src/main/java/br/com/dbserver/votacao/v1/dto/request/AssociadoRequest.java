package br.com.dbserver.votacao.v1.dto.request;

import br.com.dbserver.votacao.validator.CpfOuCnpj;
import br.com.dbserver.votacao.v1.enums.StatusUsuarioEnum;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssociadoRequest {

    @NotBlank(message = "Campo nome não pode ser nulo ou vazio")
    @Size(min=4, max = 150, message = "Campo nome Ultrapassou o limite de caracteres, max 150")
    private String nome;

    @NotBlank(message = "Campo documento não pode ser nulo ou vazio")
    @CpfOuCnpj
    private String documento;


    @Builder.Default
    private StatusUsuarioEnum status = StatusUsuarioEnum.PODE_VOTAR;
}
