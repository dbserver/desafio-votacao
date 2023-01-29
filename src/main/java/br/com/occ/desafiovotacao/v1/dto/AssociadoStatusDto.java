package br.com.occ.desafiovotacao.v1.dto;

import br.com.occ.desafiovotacao.v1.enums.CpfStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalTime;
import java.util.Random;

@Data
@AllArgsConstructor
public class AssociadoStatusDto {

    @Enumerated(EnumType.STRING)
    private CpfStatusEnum status;

    public AssociadoStatusDto() {
        Random gerador = new Random();
        if (gerador.nextInt(1000) % 2 == 0)
            this.status = CpfStatusEnum.ABLE_TO_VOTE;
        else
            this.status = CpfStatusEnum.UNABLE_TO_VOTE;
    }
}
