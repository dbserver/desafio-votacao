package com.db.desafiovotacao.api.service;

import com.db.desafiovotacao.api.exception.CPFInvalidException;
import com.db.desafiovotacao.api.record.CpfValidationRecord;

public interface CpfValidationFacade {
    public CpfValidationRecord validateCpf(String cpf) throws CPFInvalidException;
}
