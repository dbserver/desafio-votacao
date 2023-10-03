package com.desafiovotacao.dto;

import com.desafiovotacao.domain.Associado;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AssociadoDTO {

    private String id;

    private String nome;

    private String cpf;

    public Associado toEntity() {
        Associado associado = new Associado();
        associado.setCpf(this.cpf);
        associado.setNome(this.nome);
        associado.setId(this.id);
        return associado;
    }

    public static AssociadoDTO fromEntity(Associado associado) {
        AssociadoDTO associadoDTO = new AssociadoDTO();
        associadoDTO.setCpf(associado.getCpf());
        associadoDTO.setNome(associado.getNome());
        associadoDTO.setId(associado.getId());
        return associadoDTO;
    }
}
