package com.example.desafiovotacao.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(schema = "voteschallenge", name = "votes")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VoteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Boolean vote;
    @Column(insertable = false, updatable = false)
    private Date creationDate;
    @ManyToOne
    @JoinColumn(name = "session_id")
    private SessionEntity session;
    @ManyToOne
    @JoinColumn(name = "cpf", referencedColumnName = "cpf")
    private AssociateEntity associate;
}