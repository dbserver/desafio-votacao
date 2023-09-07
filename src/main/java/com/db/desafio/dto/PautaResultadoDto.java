package com.db.desafio.dto;

import lombok.*;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PautaResultadoDto {

    private String titulo;
    private String resultado;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PautaResultadoDto that = (PautaResultadoDto) o;
        return Objects.equals(titulo, that.titulo) && Objects.equals(resultado, that.resultado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titulo, resultado);
    }
}
