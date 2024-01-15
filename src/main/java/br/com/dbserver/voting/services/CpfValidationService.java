package br.com.dbserver.voting.services;

import br.com.dbserver.voting.dtos.CpfValidationResponseDTO;
import org.springframework.http.ResponseEntity;

public interface CpfValidationService {
    String validateCpf(String cpf);
}
