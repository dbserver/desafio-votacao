package db.desafiovotacao.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

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
    private String CPF;
}
