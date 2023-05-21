package br.stapassoli.validadorcpf.dto;

import br.stapassoli.validadorcpf.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class NumeroCpfDTOResposta {

    private Status status;

}
