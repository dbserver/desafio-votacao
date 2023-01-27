package br.com.adonias.desafiovotacao.business;

import br.com.adonias.desafiovotacao.dto.enums.AssociateValidationStatus;
import br.com.adonias.desafiovotacao.dto.enums.AssociateValidationStatusDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Random;

@Slf4j
@Component
public class AssociateValidationBusiness {
    public ResponseEntity<AssociateValidationStatusDTO> validate(String cpf) {
        log.info("Fake validation to CPF {}",cpf);
        Random random = new Random();
        boolean isValid = random.nextBoolean();
        return isValid ? ResponseEntity.ok(new AssociateValidationStatusDTO(AssociateValidationStatus.ABLE_TO_VOTE)):
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(new AssociateValidationStatusDTO(AssociateValidationStatus.UNABLE_TO_VOTE));
    }
}
