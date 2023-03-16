package db.desafiovotacao.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class Resultado {
    private Integer votosPositivos;
    private Integer votosNegativos;

    public Resultado(Integer votosPositivos, Integer totalVotos){
        this.votosPositivos = votosPositivos;
        this.votosNegativos = totalVotos - votosPositivos;
    }
}
