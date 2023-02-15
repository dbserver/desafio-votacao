package br.tec.db.votacao.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "voto")
@EqualsAndHashCode(of = "id")
public class Voto {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String voto;

        @OneToOne
        private Associado associado;

        @ManyToOne
        private SessaoDeVotacao sessaoDeVotacao;
}
