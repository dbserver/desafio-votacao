package db.desafiovotacao.dto;


import jakarta.validation.constraints.NotBlank;

public record SessaoRequest(

        @NotBlank
        String inicioSessao,

        @NotBlank
        String finalSessao
){}
