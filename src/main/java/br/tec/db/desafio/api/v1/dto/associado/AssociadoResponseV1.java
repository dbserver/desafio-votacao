package br.tec.db.desafio.api.v1.dto.associado;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AssociadoResponseV1 {
    private String cpf;
    private String nome;
}
