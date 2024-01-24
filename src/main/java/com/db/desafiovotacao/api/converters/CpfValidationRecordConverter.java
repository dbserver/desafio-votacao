package com.db.desafiovotacao.api.converters;

import com.db.desafiovotacao.api.domain.StatusCpf;
import com.db.desafiovotacao.api.record.CpfValidationRecord;
import org.springframework.stereotype.Component;

@Component
public class CpfValidationRecordConverter {
    public CpfValidationRecord toCpfValidationRecord(StatusCpf statusCpf){
        return new CpfValidationRecord(statusCpf);
    }
}
