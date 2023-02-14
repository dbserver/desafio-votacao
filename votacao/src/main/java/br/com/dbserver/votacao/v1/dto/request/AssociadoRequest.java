package br.com.dbserver.votacao.v1.dto.request;

import br.com.dbserver.votacao.v1.enums.StatusUsuarioEnum;

import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
    @Size(min=4, max = 100, message = "Campo nome Ultrapassou o limite de caracteres, max 70")
    private String nome;

    @NotBlank(message = "Campo cpf não pode ser nulo ou vazio")
    @CPF
    private String cpf;


    @Builder.Default
    private StatusUsuarioEnum status = StatusUsuarioEnum.PODE_VOTAR;
}
