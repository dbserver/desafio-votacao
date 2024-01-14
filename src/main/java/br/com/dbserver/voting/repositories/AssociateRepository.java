package br.com.dbserver.voting.repositories;

import br.com.dbserver.voting.models.Associate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AssociateRepository extends JpaRepository<Associate, UUID> {
    Optional<Associate> findByCpf(String cpf);
}
