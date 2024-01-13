package br.com.dbserver.voting.models;


import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "associate", uniqueConstraints = {@UniqueConstraint(columnNames = "cpf")})
public class Associate {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "cpf")
    private String cpf;

    public Associate() {
    }

    public Associate(UUID id, String name, String cpf) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
