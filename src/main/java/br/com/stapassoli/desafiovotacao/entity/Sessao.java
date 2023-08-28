package br.com.stapassoli.desafiovotacao.entity;

import br.com.stapassoli.desafiovotacao.enums.VotoStatus;
import br.com.stapassoli.desafiovotacao.exceptions.PautaException;
import br.com.stapassoli.desafiovotacao.exceptions.SessaoException;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class Sessao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(pattern = "dd/MM/yyyy hh:mm:ss")
    private LocalDateTime limite;

    @OneToOne
    @JoinColumn(name = "pauta_id", unique = true)
    private Pauta pauta;

    @OneToMany(mappedBy = "sessao", fetch = FetchType.LAZY)
    private List<Voto> votos = new ArrayList<>();

    private Map<VotoStatus, Long> totalizarVotos() {
        var urna = new HashMap<VotoStatus, Long>();
        urna.put(VotoStatus.SIM, 0L);
        urna.put(VotoStatus.NAO, 0L);

        this.votos.forEach(voto -> {

            if (voto.getVotoStatus().equals(VotoStatus.SIM)) {
                urna.put(VotoStatus.SIM, urna.get(VotoStatus.SIM) + 1);
            } else {
                urna.put(VotoStatus.NAO, urna.get(VotoStatus.NAO) + 1);
            }
        });

        return urna;
    }

    public Long obterVotos(VotoStatus votoStatus) {
        return this.totalizarVotos().get(votoStatus);
    }

    public VotoStatus obterVencedor() {
        Map<VotoStatus, Long> votosTotalizados = this.totalizarVotos();
        return votosTotalizados.get(VotoStatus.SIM).compareTo(votosTotalizados.get(VotoStatus.NAO)) >= 1 ? VotoStatus.SIM : VotoStatus.NAO;
    }

    public Map<VotoStatus, Long> obterQuantidadeVotos() {
        return this.totalizarVotos();
    }

    public void isDentroLimiteTempo() throws PautaException {

        boolean excedeu = LocalDateTime.now().compareTo(this.getLimite()) >= 1;

        if (excedeu) {
            throw new SessaoException("Tempo excedido");
        }
    }


}
