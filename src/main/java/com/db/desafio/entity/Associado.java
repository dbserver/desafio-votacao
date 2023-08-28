package com.db.desafio.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tb_associado")
public class Associado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @NotBlank
    @Column(name = "nome")
    private String nome;
    @NotBlank
    @Column(name = "cpf")
    private String cpf;

    public Associado(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }

    public Associado(Long id, String nome, String cpf) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Associado associado = (Associado) o;
        return Objects.equals(id, associado.id) && Objects.equals(nome, associado.nome) && Objects.equals(cpf, associado.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
