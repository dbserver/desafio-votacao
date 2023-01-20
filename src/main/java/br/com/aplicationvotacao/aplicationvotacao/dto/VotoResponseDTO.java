package br.com.aplicationvotacao.aplicationvotacao.dto;

import br.com.aplicationvotacao.aplicationvotacao.model.enums.VotoEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class VotoResponseDTO {

    private Long idAssociado;
    private VotoEnum voto;

}

