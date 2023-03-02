package db.desafiovotacao.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

import java.util.UUID;

@Entity
@Table(name = Sessao.TABLE_NAME)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Sessao {

    public static final String TABLE_NAME = "sessoes";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "uuid_sessao", nullable = false)
    private UUID uuidSessao;

    @Column(name = "data_criacao", nullable = false)
    private LocalDateTime dataCriacao = LocalDateTime.now();

    @NotNull
    @Column(name = "inicio_sessao", nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime inicioSessao;

    @Column(name = "final_sessao")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime finalSessao;
}
