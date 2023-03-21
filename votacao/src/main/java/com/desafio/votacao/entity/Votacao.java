package com.desafio.votacao.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TB_VOTACAO")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Votacao {

	@EmbeddedId
	private VotoId votoId;
    
}
