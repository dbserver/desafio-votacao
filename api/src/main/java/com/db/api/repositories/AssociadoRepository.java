package com.db.api.repositories;

import com.db.api.models.Associado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssociadoRepository extends JpaRepository<Associado, Long> {
    boolean existsAssociadoByCpf(String cpfAssociado);
}
