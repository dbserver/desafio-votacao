package br.com.stapassoli.desafiovotacao.dto;

import br.com.stapassoli.desafiovotacao.entity.Sessao;
import br.com.stapassoli.desafiovotacao.enums.VotoStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class VencedorDTO {

    Long votosSim;
    Long votosNao;
    String vencedor;

    public VencedorDTO(Sessao sessao) {
        this.votosSim = sessao.obterVotos(VotoStatus.SIM);
        this.votosNao =  sessao.obterVotos(VotoStatus.NAO);
        this.vencedor = votosSim.compareTo(votosNao) == 0L ? "Empate" : sessao.obterVencedor().toString();
    }
}
