package com.db.api.dtos;

import com.db.api.dtos.request.PautaRequestSessao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Future;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SessaoDto {
    private PautaRequestSessao pauta;
    @Future
    private LocalDateTime dataEncerramento;
}
