package br.com.occ.desafiovotacao.v1.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SessaoDto extends BaseDto {

    private Long id;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dataInicio;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dataFim;

    public SessaoDto(Long id, LocalDateTime dataFim) {
        this.id = id;
        this.dataFim = dataFim;
    }
}

