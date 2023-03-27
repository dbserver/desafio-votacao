
package com.dbserver.desafiovotacao.model;

import com.dbserver.desafiovotacao.enums.AssembleiaEnum;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "assembleia")
public class Assembleia implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "nome", length = 255, nullable = false)
    private String nomeAssembleia;

    @Column(name = "inicio_assembleia", nullable = false)
    private LocalDateTime aberturaAssembleia = LocalDateTime.now();
    @Column(name = "fim_assembleia")
    private LocalDateTime encerramentoAssembleia;
    @OneToMany
    @JoinTable(name = "assembleia_pauta", joinColumns = @JoinColumn(name = "assembleia_id"),
            inverseJoinColumns = @JoinColumn(name = "pauta_id"))
    private List<Pauta> listaPauta = new ArrayList<>();
    @JoinColumn(name = "status")
    @Enumerated(EnumType.STRING)
    private AssembleiaEnum status;
}
