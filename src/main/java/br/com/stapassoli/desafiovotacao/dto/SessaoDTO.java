package br.com.stapassoli.desafiovotacao.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SessaoDTO {


    @JsonFormat(pattern = "dd/MM/yyyy hh:mm:ss")
    private LocalDateTime inicio = LocalDateTime.now();

    @JsonFormat(pattern = "dd/MM/yyyy hh:mm:ss")
    private LocalDateTime fim = LocalDateTime.now().plusMinutes(10L);

    @JsonProperty(value = "id_pauta")
    private Long idPauta;

}
