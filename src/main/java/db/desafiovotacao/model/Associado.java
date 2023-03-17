package db.desafiovotacao.model;

import db.desafiovotacao.dto.AssociadoRequest;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import java.util.UUID;

@Entity
@Table(name = "associados")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Associado {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "cpf", unique = true, nullable = false, length = 14)
    @Size(min = 11, max = 14)
    @CPF
    @NotBlank
    private String CPF;

    public Associado(AssociadoRequest associadoRequest) {
        this.CPF = associadoRequest.cpf();
    }
}
