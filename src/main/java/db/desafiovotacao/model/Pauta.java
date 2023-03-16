package db.desafiovotacao.model;

import db.desafiovotacao.dto.PautaAtualizacaoRequest;
import db.desafiovotacao.dto.PautaRequest;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String descricao;

    //@Builder.Default -> data indo nula para o BD
    private LocalDateTime dataCriacao = LocalDateTime.now();

    @Embedded
    private Sessao sessao;

    @OneToMany(mappedBy = "pauta", cascade = CascadeType.ALL)
    @Builder.Default
    private Set<AssociadoPauta> associados = new HashSet<>();

    @OneToMany(mappedBy = "pauta", cascade = CascadeType.ALL)
    @Builder.Default
    private List<VotoPauta> votos = new ArrayList<>();

    private Boolean ativo;

    public Pauta(PautaRequest pautaRequest){
        this.titulo = pautaRequest.titulo();
        this.descricao = pautaRequest.descricao();
        this.sessao = new Sessao(pautaRequest.sessaoRequest());
        this.ativo = true;
    }

    public void atualizar(PautaAtualizacaoRequest pautaAtualizacaoRequest) {

        if (pautaAtualizacaoRequest.titulo() != null)
            this.titulo = pautaAtualizacaoRequest.titulo();

        if(pautaAtualizacaoRequest.descricao() != null)
            this.descricao = pautaAtualizacaoRequest.descricao();

        if(pautaAtualizacaoRequest.sessaoRequest() != null)
            this.sessao.atualizar(pautaAtualizacaoRequest.sessaoRequest());
    }

}
