package com.fernandesclaudi.desafiovotacao.exceptions;

import com.fernandesclaudi.desafiovotacao.config.VersionApi;
import lombok.Getter;
import org.springframework.http.HttpStatus;

public class IBaseException extends RuntimeException {
    private final String apiVersion = VersionApi.VERSION;

    @Getter
    private HttpStatus httpStatus;
    public IBaseException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

}
