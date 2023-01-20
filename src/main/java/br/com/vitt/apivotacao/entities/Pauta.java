package br.com.vitt.apivotacao.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;

import br.com.vitt.apivotacao.entities.enums.StatusPauta;
import br.com.vitt.apivotacao.entities.enums.Voto;

@Entity
@Table(name = "tb_pauta")
public class Pauta implements Serializable{
	
	private static final long serialVersionUID = 1L; 
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Column(nullable=false)
	private String titulo;
	
	@Column(nullable=false)
	private Integer statusPauta = 1;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime data = LocalDateTime.now();
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime inicio;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime fim;
	
	private boolean ativo = true;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "id.pauta")
	private Set<PautaAssociado> pautaAssociado = new HashSet<>();
	
	public Pauta() {}

	public Pauta(Long id, String titulo, Integer statusPauta, LocalDateTime data, LocalDateTime inicio, LocalDateTime fim,
			boolean ativo) {
		this.id = id;
		this.titulo = titulo;
		this.statusPauta = statusPauta;
		this.data = data;
		this.inicio = inicio;
		this.fim = fim;
		this.ativo = ativo;
	}
	
	public void resultado() {
		if(fim != null && fim.isBefore(LocalDateTime.now())) {
			int aprovam = pautaAssociado.stream().filter(x-> x.getVoto() == Voto.SIM).collect(Collectors.toList()).size();
			int desaprovam = pautaAssociado.stream().filter(x-> x.getVoto() == Voto.NAO).collect(Collectors.toList()).size();
			if(aprovam>desaprovam) {
				setStatusPauta(StatusPauta.APPROVED);
			}else if(aprovam<desaprovam) {
				setStatusPauta(StatusPauta.REFUSED);
			}else {
				setStatusPauta(StatusPauta.DRAW);
			}
		}
		
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public StatusPauta getStatusPauta() {
		resultado();
		return StatusPauta.toEnum(statusPauta);
	}

	public void setStatusPauta(StatusPauta statusPauta) {
		this.statusPauta = statusPauta.getCod();
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

	public LocalDateTime getInicio() {
		return inicio;
	}

	public void setInicio(LocalDateTime inicio) {
		this.inicio = inicio;
	}

	public LocalDateTime getFim() {
		return fim;
	}

	public void setFim(LocalDateTime fim) {
		this.fim = fim;
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
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pauta other = (Pauta) obj;
		return Objects.equals(id, other.id);
	}

	public String abrirVotacao() {
		inicio = LocalDateTime.now();
		fim = LocalDateTime.of(inicio.getYear(), inicio.getMonth(), 
											inicio.getDayOfMonth(), inicio.getHour(),
											inicio.getMinute()+1, inicio.getSecond());
		setStatusPauta(StatusPauta.IN_VOTING);
		return "Pauta [id=" + id + ", titulo=" + titulo + ", statusPautaPauta=" + this.getStatusPauta().getStatus() + ", data=" + data
				+ ", Votação Aberta em " + inicio + ", será encerrada em " + fim + "]";
	}
	
	public String abrirVotacao(LocalDateTime time) {
		inicio = LocalDateTime.now();
		fim = time;
		setStatusPauta(StatusPauta.IN_VOTING);
		return "Pauta [id=" + id + ", titulo=" + titulo + ", statusPautaPauta=" + this.getStatusPauta().getStatus() + ", data=" + data
				+ ", Votação Aberta em " + inicio + ", será encerrada em " + fim + "]";
	}

	@Override
	public String toString() {
		return "Pauta [id=" + id + ", titulo=" + titulo + ", statusPautaPauta=" + this.getStatusPauta().getStatus() + ", data=" + data
				+ ", Votação Aberta em" + inicio + ", encerrada em " + fim + "]";
	}	
	
}
