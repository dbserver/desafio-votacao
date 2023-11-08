package com.example.desafiovotacao.service.interfaces;

import com.example.desafiovotacao.dto.CreatedAssociateDTO;
import com.example.desafiovotacao.dto.RegisterAssociateDTO;
import com.example.desafiovotacao.entity.AssociateEntity;

public interface AssociateService {
    CreatedAssociateDTO create(RegisterAssociateDTO associate);

    AssociateEntity getAssociateByCpfIfExists(String cpf);

    void validateRegisterAssociateDTO(RegisterAssociateDTO associateDTO);

    void validateAlreadyRegisteredAssociate(String cpf);
}
