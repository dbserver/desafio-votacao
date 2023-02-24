package db.desafiovotacao.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;


@Entity
@Table(name = Pauta.TABLE_NAME)
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Pauta {

    public static final String TABLE_NAME = "pauta";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "uuid_pauta", unique = true)
    private UUID uuidPauta;

    @Column(name = "titulo", nullable = false, length = 200)
    @Size(min = 7, max = 200)
    @NotBlank
    private String titulo;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao = LocalDateTime.now();

    public Pauta(String titulo, String descricao){
        this.titulo = titulo;
        this.descricao = descricao;
    }
}
