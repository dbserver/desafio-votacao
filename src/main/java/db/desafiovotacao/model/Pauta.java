package db.desafiovotacao.model;

import db.desafiovotacao.dto.PautaRequest;
import db.desafiovotacao.dto.PautaResponse;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.*;


@Entity
@Table(name = "pautas")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Pauta {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String titulo;
    private String descricao;

//    @Builder.Default -> data indo nula para o BD
    private LocalDateTime dataCriacao = LocalDateTime.now();

    @Embedded
    private Sessao sessao;

    @OneToMany(mappedBy = "pauta", cascade = CascadeType.ALL)
    @Builder.Default
    private Set<AssociadoPauta> associados = new HashSet<>();

    @OneToMany(mappedBy = "pauta", cascade = CascadeType.ALL)
    @Builder.Default
    private List<VotoPauta> votos = new ArrayList<>();

    public Pauta(PautaRequest pautaRequest){
        this.titulo = pautaRequest.titulo();
        this.descricao = pautaRequest.descricao();
        this.sessao = new Sessao(pautaRequest.sessaoRequest());
    }

    public PautaResponse pautaResponse(){
        return new PautaResponse(
                this.id.toString(),
                this.titulo,
                this.sessao.getInicioSessao().toString(),
                this.sessao.getFinalSessao().toString()
        );
    }
}
