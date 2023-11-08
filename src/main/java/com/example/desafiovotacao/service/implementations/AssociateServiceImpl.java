package com.example.desafiovotacao.service.implementations;

import com.example.desafiovotacao.dto.CreatedAssociateDTO;
import com.example.desafiovotacao.dto.RegisterAssociateDTO;
import com.example.desafiovotacao.entity.AssociateEntity;
import com.example.desafiovotacao.exception.ValidationExceptions;
import com.example.desafiovotacao.exception.enums.implementations.AssociateErrorMessages;
import com.example.desafiovotacao.exception.enums.implementations.InformationErrorMessages;
import com.example.desafiovotacao.repository.AssociateRepository;
import com.example.desafiovotacao.service.interfaces.AssociateService;
import com.example.desafiovotacao.utils.CpfUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AssociateServiceImpl implements AssociateService {
    private final AssociateRepository associateRepository;

    @Override
    public CreatedAssociateDTO create(RegisterAssociateDTO associate) {
        validateRegisterAssociateDTO(associate);
        CpfUtils.validateCPFThrow(associate.getCpf());
        validateAlreadyRegisteredAssociate(associate.getCpf());

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

    @Override
    public AssociateEntity getAssociateByCpfIfExists(String cpf){
        Optional<AssociateEntity> existingAssociate = associateRepository.findByCpf(cpf);
        if(existingAssociate.isEmpty()) {
            throw new ValidationExceptions(AssociateErrorMessages.ASSOCIATE_NOT_FOUNT);
        }

        return existingAssociate.get();
    }
    @Override
    public void validateRegisterAssociateDTO(RegisterAssociateDTO associateDTO) {
        if(associateDTO.getCpf() == null || associateDTO.getName() == null) {
            throw new ValidationExceptions(InformationErrorMessages.FAULTY_INFORMATION);
        }
    }

    @Override
    public void validateAlreadyRegisteredAssociate(String cpf) {
        Optional<AssociateEntity> existingAssociate = associateRepository.findByCpf(cpf);
        if(existingAssociate.isPresent()){
            throw new ValidationExceptions(AssociateErrorMessages.ASSOCIATE_ALERADY_REGISTERED);
        }
    }
}