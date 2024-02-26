package com.fernandesclaudi.desafiovotacao.exceptions;

import com.fernandesclaudi.desafiovotacao.enums.StatusCpfEnum;
import org.springframework.http.HttpStatus;

public class ICpfInvalidoParaVotacaoException extends IBaseException {
    public ICpfInvalidoParaVotacaoException() {
        super("{\"status\":\"" + StatusCpfEnum.UNABLE_TO_VOTE + "\"}", HttpStatus.NOT_FOUND);
    }
}
