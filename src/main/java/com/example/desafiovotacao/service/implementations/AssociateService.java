package com.example.desafiovotacao.service.implementations;

import com.example.desafiovotacao.dto.CreatedAssociateDTO;
import com.example.desafiovotacao.dto.RegisterAssociateDTO;
import com.example.desafiovotacao.entity.AssociateEntity;
import com.example.desafiovotacao.exception.AssociateExceptions;
import com.example.desafiovotacao.exception.ValidationExceptions;
import com.example.desafiovotacao.repository.AssociateRepository;
import com.example.desafiovotacao.service.interfaces.AssociateInterface;
import com.example.desafiovotacao.utils.CpfUtils;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AssociateService implements AssociateInterface {

    @Autowired
    private AssociateRepository associateRepository;

    @Override
    public CreatedAssociateDTO create(RegisterAssociateDTO associate) {
        if(associate.getCpf() == null || associate.getName() == null) {
            ValidationExceptions.faultyInformation();
        }
        if(!CpfUtils.validateCPF(associate.getCpf())) {
            ValidationExceptions.invalidCpf();
        }

        Optional<AssociateEntity> existingAssociate = associateRepository.findByCpf(associate.getCpf());
        if(existingAssociate.isPresent()){
            AssociateExceptions.associateAlreadyRegistered();
        }

        AssociateEntity newAssociate = associateRepository.save(
                AssociateEntity.builder().
                        cpf(associate.getCpf())
                        .name(associate.getName())
                        .build()
        );

        return CreatedAssociateDTO.builder()
                .id(newAssociate.getId())
                .cpf(newAssociate.getCpf())
                .build();
    }

    public AssociateEntity getAssociateByCpfIfExists(String cpf){
        Optional<AssociateEntity> existingAssociate = associateRepository.findByCpf(cpf);
        if(existingAssociate.isEmpty()) {
            AssociateExceptions.associateNotFound();
        }

        return existingAssociate.get();
    }
}