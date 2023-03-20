package db.desafiovotacao.dto;


import jakarta.validation.constraints.NotBlank;

public record SessaoRequest(

        @NotBlank(message = "deve ser informada uma data de inicio")
        String inicioSessao,

        @NotBlank(message = "deve ser informada uma data de fim")
        String finalSessao
){}
