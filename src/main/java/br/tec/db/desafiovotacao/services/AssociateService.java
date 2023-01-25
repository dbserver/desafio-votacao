package br.tec.db.desafiovotacao.services;

import br.tec.db.desafiovotacao.repositories.AssociateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssociateService {

    @Autowired
    private AssociateRepository repository;


}
