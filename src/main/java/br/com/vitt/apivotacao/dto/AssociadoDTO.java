package br.com.vitt.apivotacao.dto;

import java.io.Serializable;

import br.com.vitt.apivotacao.entities.Associado;

public class AssociadoDTO implements Serializable{
	
	private static final long serialVersionUID = 1L; 

	private Long id;
	private String nome;
	private String cpf;
	private Integer status;
	private boolean ativo;
	
	public AssociadoDTO() {}
	
	public AssociadoDTO(Long id, String nome, String cpf, Integer status, boolean ativo) {
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.status = status;
		this.ativo = ativo;
	}

	public AssociadoDTO(Associado entity) {
		this.id =  entity.getId();
		this.nome =  entity.getNome();
		this.cpf = entity.getCpf();
		this.status =  entity.getStatus();
		this.ativo =  entity.getAtivo();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
}
