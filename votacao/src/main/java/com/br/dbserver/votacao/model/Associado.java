package com.br.dbserver.votacao.model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "associado")
@Entity(name = "Associado")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Associado {
	
	@Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private Long id;

    @Column(unique = true)
    private Long cpf;

    private String nome;
    
    @OneToMany(mappedBy = "id.associado", targetEntity = Voto.class)
	private List<Voto> listaVotos;

}
