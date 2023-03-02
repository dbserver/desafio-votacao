package db.desafiovotacao.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import java.util.UUID;

@Entity
@Table(name = Associado.TABLE_NAME)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Associado {

    public static final String TABLE_NAME = "associados";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "uuid_associado", unique = true)
    private UUID uuidAssociado;

    @Column(name = "cpf", unique = true, nullable = false, length = 14)
    @Size(min = 11, max = 14)
    @CPF
    @NotBlank
    private String cpf;
}
