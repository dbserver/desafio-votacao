package db.desafiovotacao.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "associados")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Associado {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "cpf", unique = true, nullable = false, length = 14)
    private String CPF;
}
