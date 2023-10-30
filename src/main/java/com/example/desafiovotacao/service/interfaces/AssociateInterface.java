package com.example.desafiovotacao.service.interfaces;

import com.example.desafiovotacao.dto.CreatedAssociateDTO;
import com.example.desafiovotacao.dto.RegisterAssociateDTO;

public interface AssociateInterface {
    CreatedAssociateDTO create(RegisterAssociateDTO associate);
}
