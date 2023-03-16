package db.desafiovotacao.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;




public record SessaoRequest(

        @NotBlank
        String inicioSessao,

        @NotBlank
        String finalSessao
){}
