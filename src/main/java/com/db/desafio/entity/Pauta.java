package com.db.desafio.entity;

import com.db.desafio.enumerate.VotoEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

@NoArgsConstructor
@Getter
@Setter
@Entity
@ToString
@Table(name = "tb_pauta")
public class Pauta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank
    @Column(name = "titulo", columnDefinition = "TEXT")
    private String titulo;
    @NotBlank
    @Column(name = "descricao", columnDefinition = "TEXT")
    private String descricao;
    @OneToOne(mappedBy = "pauta")
    private SessaoVotacao sessaoVotacao;


    public Pauta(Long id, String titulo, String descricao) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
    }

    public Pauta(String titulo, String descricao) {
        this.titulo = titulo;
        this.descricao = descricao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pauta pauta = (Pauta) o;
        return Objects.equals(id, pauta.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public String obterResultado(){
        return this.obterVotosPorTipo(VotoEnum.SIM) >= obterVotosPorTipo(VotoEnum.NAO)? "Aprovado" : "Rejeitado";
    }
    private Long obterVotosPorTipo(VotoEnum votoEnum) {
        return this.sessaoVotacao.getVotos().stream().filter(v -> v.getVotoEnum().equals(votoEnum)).count();
    }

    public Pauta(Long id, String titulo, String descricao, SessaoVotacao sessaoVotacao) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.sessaoVotacao = sessaoVotacao;
    }
}
