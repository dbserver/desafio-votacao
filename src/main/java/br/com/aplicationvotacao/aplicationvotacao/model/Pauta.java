package br.com.aplicationvotacao.aplicationvotacao.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="pauta")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Pauta implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    private String descricao;

    public Pauta(Long idPauta) {
        this.id = idPauta;
    }

}
