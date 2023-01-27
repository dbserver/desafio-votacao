package br.com.occ.desafiovotacao.v1.dto;

import br.com.occ.desafiovotacao.v1.enums.CpfStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalTime;

@Data
@AllArgsConstructor
public class AssociadoStatusDto {

    @Enumerated(EnumType.STRING)
    private CpfStatusEnum status;

    public AssociadoStatusDto() {
        if (LocalTime.now().getSecond() % 2 == 0)
            this.status = CpfStatusEnum.ABLE_TO_VOTE;
        else
            this.status = CpfStatusEnum.UNABLE_TO_VOTE;
    }
}
