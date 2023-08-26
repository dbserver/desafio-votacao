package br.com.stapassoli.desafiovotacao.dto;

import br.com.stapassoli.desafiovotacao.enums.VotoStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class VotoDTO {

    @JsonProperty(value = "id_associado")
    private Long idAssociado;

    @JsonProperty(value = "id_sessao")
    private Long idSessao;

    @JsonProperty(value = "voto")
    private VotoStatus votoStatus;

}
