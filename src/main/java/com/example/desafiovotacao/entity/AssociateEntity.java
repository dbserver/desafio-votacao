package com.example.desafiovotacao.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(schema = "voteschallenge", name = "associate")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssociateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String cpf;
    private String name;
    @Column(insertable = false, updatable = false)
    private Date creationDate;
}