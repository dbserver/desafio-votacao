package br.com.occ.desafiovotacao.v1.dto;

import br.com.occ.desafiovotacao.v1.model.Pauta;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SessaoDto extends BaseDto {

    private Long id;
    private Pauta pauta;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;

}
