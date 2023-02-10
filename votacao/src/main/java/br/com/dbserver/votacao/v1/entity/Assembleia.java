package br.com.dbserver.votacao.v1.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Entity
@Table(name = "assembleia")
public class Assembleia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Builder.Default
    private LocalDateTime inicio = LocalDateTime.now();

    private LocalDateTime fim;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "assembleia_pauta", joinColumns = {
            @JoinColumn(name = "assembleia_id", referencedColumnName = "id") }, inverseJoinColumns = {
            @JoinColumn(name = "pauta_id", referencedColumnName = "id") })
    List<Pauta> pautas;
}