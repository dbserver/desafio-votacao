package br.com.stapassoli.desafiovotacao.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class PautaDTO {

    @NotNull
    @NotBlank(message = "deve ter um assunto")
    private String assunto;

}
