package br.com.dbserver.votacao.v1.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Entity
@Setter
@Table(name = "assembleia")
public class Assembleia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Builder.Default
    private LocalDateTime inicio = LocalDateTime.now();

    @Builder.Default
    private LocalDateTime fim = LocalDateTime.now().plusHours(4);

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "assembleia_pauta", joinColumns = {
            @JoinColumn(name = "assembleia_id", referencedColumnName = "id") }, inverseJoinColumns = {
            @JoinColumn(name = "pauta_id", referencedColumnName = "id") })
    @Builder.Default
    List<Pauta> pautas = new ArrayList<>();
}