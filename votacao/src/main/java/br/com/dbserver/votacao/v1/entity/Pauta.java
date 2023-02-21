package br.com.dbserver.votacao.v1.entity;

import br.com.dbserver.votacao.v1.enums.PautaStatusEnum;
import br.com.dbserver.votacao.v1.enums.VotoEnum;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

import static java.time.LocalDateTime.now;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Getter
@Setter
@Table(name = "pauta")
public class Pauta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String descricao;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "pauta_votacao", joinColumns = {
			@JoinColumn(name = "pauta_id", referencedColumnName = "id")}, inverseJoinColumns = {
			@JoinColumn(name = "votos_id", referencedColumnName = "id")})
	private List<Voto> votos;
	private LocalDateTime inicio;
	private LocalDateTime fim;

	@Transient
	@Enumerated(EnumType.STRING)
	private PautaStatusEnum status;

	public PautaStatusEnum getStatus() {
		atualizarStatus();
		return status;
	}

	private void atualizarStatus() {
		if (fim.isBefore(now())) {
			long votosSim = votos.stream().filter(voto -> voto.getValor().equals(VotoEnum.SIM)).count();
			long votosNao = votos.stream().filter(voto -> voto.getValor().equals(VotoEnum.NAO)).count();
			if (votosSim == votosNao) {
				status = PautaStatusEnum.EMPATADA;
			} else if (votosSim > votosNao) {
				status = PautaStatusEnum.APROVADA;
			} else {
				status = PautaStatusEnum.REPROVADA;
			}
		} else {
			status = PautaStatusEnum.AGUARDANDO_RESULTADO;
		}
	}
}