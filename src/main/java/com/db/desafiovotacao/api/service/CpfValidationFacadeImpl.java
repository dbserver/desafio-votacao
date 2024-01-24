package com.db.desafiovotacao.api.service;

import com.db.desafiovotacao.api.converters.CpfValidationRecordConverter;
import com.db.desafiovotacao.api.domain.StatusCpf;
import com.db.desafiovotacao.api.exception.CPFInvalidException;
import com.db.desafiovotacao.api.record.CpfValidationRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CpfValidationFacadeImpl implements CpfValidationFacade{

    @Autowired
    private CpfValidationRecordConverter cpfValidationRecordConverter;

    public CpfValidationRecord validateCpf(String cpf) throws CPFInvalidException {
            if(isCpf(cpf)){
                return cpfValidationRecordConverter.toCpfValidationRecord(StatusCpf.ABLE_TO_VOTE);
            }else{
                throw new CPFInvalidException(StatusCpf.UNABLE_TO_VOTE.name());
            }
    }

    private boolean isCpf(String cpf) {
        cpf = cpf.replace(".", "");
        cpf = cpf.replace("-", "");

        try{
            Long.parseLong(cpf);
        } catch(NumberFormatException e){
            return false;
        }

        int d1, d2;
        int digito1, digito2, resto;
        int digitoCPF;
        String nDigResult;

        d1 = d2 = 0;
        digito1 = digito2 = resto = 0;

        for (int nCount = 1; nCount < cpf.length() - 1; nCount++) {
            digitoCPF = Integer.valueOf(cpf.substring(nCount - 1, nCount)).intValue();

            d1 = d1 + (11 - nCount) * digitoCPF;

            d2 = d2 + (12 - nCount) * digitoCPF;
        };

        resto = (d1 % 11);

        if (resto < 2)
            digito1 = 0;
        else
            digito1 = 11 - resto;

        d2 += 2 * digito1;

        resto = (d2 % 11);

        if (resto < 2)
            digito2 = 0;
        else
            digito2 = 11 - resto;

        String nDigVerific = cpf.substring(cpf.length() - 2, cpf.length());

        nDigResult = String.valueOf(digito1) + String.valueOf(digito2);

        return nDigVerific.equals(nDigResult);
    }
}
