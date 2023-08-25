package br.com.stapassoli.desafiovotacao.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SessaoDTO {


    @JsonFormat(pattern = "dd/MM/yyyy hh:mm:ss")
    private LocalDateTime inicio;

    @JsonProperty(value = "id_pauta")
    private Long idPauta;

}
