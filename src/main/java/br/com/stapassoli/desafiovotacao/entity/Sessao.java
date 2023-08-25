package br.com.stapassoli.desafiovotacao.entity;

import br.com.stapassoli.desafiovotacao.enums.VotoStatus;
import jakarta.persistence.*;
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

    private LocalDateTime inicio = LocalDateTime.now();

    @OneToOne
    @JoinColumn(name = "pauta_id")
    private Pauta pauta;

    @OneToMany(mappedBy = "sessao")
    private List<Voto> votos = new ArrayList<>();

    private Map<VotoStatus, Long> totalizarVotos() {
        var urna = new HashMap<VotoStatus, Long>();

        this.votos.forEach(voto -> {

            if (voto.getVotoStatus().equals(VotoStatus.SIM)) {
                Long numerosVotosSim = urna.get(VotoStatus.SIM);
                urna.put(VotoStatus.SIM, numerosVotosSim + 1);
            } else {
                Long numerosVotosSim = urna.get(VotoStatus.SIM);
                urna.put(VotoStatus.NAO, numerosVotosSim + 1);
            }

        });

        return urna;
    }

    public VotoStatus obterVencedor() {
        Map<VotoStatus, Long> votosTotalizados = this.totalizarVotos();
        return votosTotalizados.get(VotoStatus.SIM).compareTo(votosTotalizados.get(VotoStatus.NAO)) >= 1 ? VotoStatus.SIM : VotoStatus.NAO;
    }

}
