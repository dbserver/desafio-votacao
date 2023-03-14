package com.dbserver.service;

import com.dbserver.client.CpfValidatorClient;
import com.dbserver.client.ValidationDTO;
import com.dbserver.exception.BusinessException;
import com.dbserver.exception.UnableToVoteException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class CpfValidatorService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CpfValidatorClient cpfValidatorClient;

    public void validate(String cpf) {
        logger.info("Starting cpf validation: {}", cpf);

        ResponseEntity<ValidationDTO> validate = validateCpf(cpf);
        if (validate.getStatusCode().equals(NOT_FOUND)) {
            logger.error("Invalid cpf: {}", cpf);
            throw new UnableToVoteException();
        }

        logger.info("Valid cpf: {}", cpf);
    }

    private ResponseEntity<ValidationDTO> validateCpf(String cpf) {
        try {
            return this.cpfValidatorClient.validate(cpf);
        } catch (RuntimeException e) {
            logger.error("Erro validating cpf: ", e);
            throw new BusinessException();
        }
    }

}
