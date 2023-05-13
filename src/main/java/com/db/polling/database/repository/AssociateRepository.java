package com.db.polling.database.repository;

import com.db.polling.database.entity.AssociateEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AssociateRepository extends JpaRepository<AssociateEntity, Long> {

  Optional<AssociateEntity> findByCpf(String cpf);


}