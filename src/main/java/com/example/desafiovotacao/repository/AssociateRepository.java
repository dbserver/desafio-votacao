package com.example.desafiovotacao.repository;

import com.example.desafiovotacao.entity.AssociateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AssociateRepository extends JpaRepository<AssociateEntity, Integer> {

    Optional<AssociateEntity> findByCpf(String cpf);

}
