package com.cooperativa.votacao.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "STATUS_SESSAO",schema = "VOTACAO")
public class StatusSessaoEntity {
	
	@Id
    private Integer id;
    
    private String nomeStatusSessao;
    
    

	public StatusSessaoEntity() {
		super();
	}

	public StatusSessaoEntity(StatusSessaoEnum statusSessaoEnum) {
		this.id = statusSessaoEnum.getId();
		this.nomeStatusSessao = statusSessaoEnum.name();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNomeStatusSessao() {
		return nomeStatusSessao;
	}

	public void setNomeStatusSessao(String nomeStatusSessao) {
		this.nomeStatusSessao = nomeStatusSessao;
	}
    
    

}
