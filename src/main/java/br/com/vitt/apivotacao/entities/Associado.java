package br.com.vitt.apivotacao.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.vitt.apivotacao.entities.enums.Status;

@Entity
@Table(name = "tb_associado")
public class Associado implements Serializable{
	
	private static final long serialVersionUID = 1L; 
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false)
	private String nome;
	
	@Column(nullable=false, unique=true)
	private String cpf;
	private Integer status;
	private boolean ativo = true;
	
	@JsonIgnore
	@OneToMany(mappedBy = "id.associado")
	private Set<PautaAssociado> pautaAssociado = new HashSet<>();
	
	public Associado() {}

	public Associado(String nome,String cpf) {
		this.nome = nome;
		this.cpf = cpf;
	}
	
	public Associado(String nome,String cpf,Status status) {
		this.nome = nome;
		this.cpf = cpf;
		setStatus(status);
	}

	public Associado(Long id, String nome, String cpf, Integer status, boolean ativo) {
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.status = status;
		this.ativo = ativo;
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

	public Status getStatus() {
		return Status.toEnum(status);
	}

	public void setStatus(Status status) {
		this.status = status.getCod();
	}

	public boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	
	public Set<PautaAssociado> getPautaAssociado() {
		return pautaAssociado;
	}

	public void setPautaAssociado(Set<PautaAssociado> pautaAssociado) {
		this.pautaAssociado = pautaAssociado;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Associado other = (Associado) obj;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Associado [id=" + id + ", nome=" + nome + ", cpf=" + cpf + ", status=" + getStatus() + "]";
	}
}
