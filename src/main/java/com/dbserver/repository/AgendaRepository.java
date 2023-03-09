package com.dbserver.repository;

import com.dbserver.model.entity.Agenda;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AgendaRepository extends MongoRepository<Agenda, String> {

}
