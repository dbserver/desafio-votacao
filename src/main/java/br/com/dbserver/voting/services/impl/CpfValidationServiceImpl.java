package br.com.dbserver.voting.services.impl;

import br.com.dbserver.voting.dtos.CpfValidationResponseDTO;
import br.com.dbserver.voting.enums.StatusCpfEnum;
import br.com.dbserver.voting.exceptions.UnableVoteException;
import br.com.dbserver.voting.exceptions.UnavailableServiceException;
import br.com.dbserver.voting.helpers.Util;
import br.com.dbserver.voting.services.CpfValidationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;
import java.util.Objects;

@Service
public class CpfValidationServiceImpl implements CpfValidationService {

    private static final Logger logger = LoggerFactory.getLogger(CpfValidationServiceImpl.class);

    private final String cpfValidationApiUrl;

    private final RestTemplate restTemplate;

    public CpfValidationServiceImpl(@Value("${cpf.validation.api.url}") String cpfValidationApiUrl, RestTemplateBuilder restTemplateBuilder) {
        this.cpfValidationApiUrl = cpfValidationApiUrl;
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public String validateCpf(String cpf) {
        try {
            String apiUrl = UriComponentsBuilder.fromHttpUrl(cpfValidationApiUrl)
                    .path("/{cpf}")
                    .buildAndExpand(Util.removeNonNumericCharacterFromCpf(cpf))
                    .toUriString();

            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

            HttpEntity<?> requestEntity = new HttpEntity<>(headers);

            ResponseEntity<CpfValidationResponseDTO> responseEntity = restTemplate.exchange(
                    apiUrl,
                    HttpMethod.GET,
                    requestEntity,
                    CpfValidationResponseDTO.class
            );
            return Objects.requireNonNull(responseEntity.getBody()).status();

        } catch (HttpClientErrorException e) {
            logger.info(e.getMessage());
            throw new UnableVoteException(StatusCpfEnum.UNABLE_TO_VOTE.name());
        } catch (Exception e) {
            throw new UnavailableServiceException("Erro ao chamar a API de validação de CPF: ".concat(e.getMessage()));
        }
    }

}
