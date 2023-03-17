package db.desafiovotacao.model;

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

    private Boolean ativo = true;
}
