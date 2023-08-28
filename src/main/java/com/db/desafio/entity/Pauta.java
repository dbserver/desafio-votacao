package com.db.desafio.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@NoArgsConstructor
@Getter
@Setter
@Entity
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
    private String resultado;


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
}
