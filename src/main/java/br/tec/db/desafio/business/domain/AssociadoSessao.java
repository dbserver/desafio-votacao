package br.tec.db.desafio.business.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Entity
@Table(name="associado_sessoes")
public class AssociadoSessao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    @Column(name = "associados_id")
    private Long associadoId;
    @Column(name = "sessoes_id")
    private Long sessaoId;



}
