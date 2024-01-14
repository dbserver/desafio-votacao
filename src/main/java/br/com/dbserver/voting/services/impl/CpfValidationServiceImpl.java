package br.com.dbserver.voting.services.impl;

import br.com.dbserver.voting.controllers.AssociateController;
import br.com.dbserver.voting.dtos.CpfValidationResponseDTO;
import br.com.dbserver.voting.helpers.Util;
import br.com.dbserver.voting.services.CpfValidationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

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
    public ResponseEntity<CpfValidationResponseDTO> validateCpf(String cpf) {
        try {
            String apiUrl = UriComponentsBuilder.fromHttpUrl(cpfValidationApiUrl)
                    .path("/{cpf}")
                    .buildAndExpand(Util.removeNonNumericCharacterFromCpf(cpf))
                    .toUriString();

            return restTemplate.getForEntity(apiUrl, CpfValidationResponseDTO.class);
        } catch (ResourceAccessException e) {
            // Registra a exceção no log
            Logger logger = LoggerFactory.getLogger(getClass());
            logger.error("Erro de conexão: o serviço de validação de CPF não está disponível.", e);

            // Retorna uma resposta padrão ou null, dependendo do seu caso
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
        }
    }
}
