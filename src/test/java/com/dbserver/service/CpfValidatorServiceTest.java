package com.dbserver.service;

import com.dbserver.client.CpfValidatorClient;
import com.dbserver.client.ValidationDTO;
import com.dbserver.exception.BusinessException;
import com.dbserver.exception.ConflictException;
import com.dbserver.exception.UnableToVoteException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import static org.assertj.core.api.AssertionsForClassTypes.catchThrowableOfType;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.*;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;


@ExtendWith(MockitoExtension.class)
class CpfValidatorServiceTest {

    @Mock
    private CpfValidatorClient cpfValidatorClient;

    @InjectMocks
    private CpfValidatorService cpfValidatorService;

    @Test
    void shouldValidateCpf() {
        String cpf = "00523156065";
        ResponseEntity<ValidationDTO> ableToVote = new ResponseEntity<>(new ValidationDTO("ABLE_TO_VOTE"), OK);
        when(cpfValidatorClient.validate(cpf)).thenReturn(ableToVote);
        cpfValidatorService.validate(cpf);
        verify(cpfValidatorClient, times(1)).validate(cpf);
    }

    @Test
    void shouldThrowInvalidCPF() {
        String cpf = "00523156065";
        ResponseEntity<ValidationDTO> ableToVote = new ResponseEntity<>(new ValidationDTO("UNABLE_TO_VOTE"), NOT_FOUND);
        when(cpfValidatorClient.validate(cpf)).thenReturn(ableToVote);
        UnableToVoteException throwable =
                catchThrowableOfType(() -> cpfValidatorService.validate(cpf), UnableToVoteException.class);
        assertThat(throwable.getClass(), equalTo(UnableToVoteException.class));
        assertThat(throwable.getReason(), equalTo("UNABLE_TO_VOTE"));
    }


    @Test
    void shouldThrowBusinessException() {
        String cpf = "00523156065";
        when(cpfValidatorClient.validate(cpf)).thenThrow(new RuntimeException("erro teste"));
        BusinessException throwable =
                catchThrowableOfType(() -> cpfValidatorService.validate(cpf), BusinessException.class);
        assertThat(throwable.getClass(), equalTo(BusinessException.class));
    }

}
