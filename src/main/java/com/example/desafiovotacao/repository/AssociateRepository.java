package com.example.desafiovotacao.repository;

import com.example.desafiovotacao.entity.AssociateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AssociateRepository extends JpaRepository<AssociateEntity, Integer> {

    Optional<AssociateEntity> findByCpf(String cpf);

}
