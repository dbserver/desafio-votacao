package com.db.api.services;

import com.db.api.dtos.AssociadoDto;
import com.db.api.exceptions.AssociadoJaCadastradoException;
import com.db.api.exceptions.ParametrosInvalidosException;
import com.db.api.models.Associado;
import com.db.api.repositories.AssociadoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@Service
@AllArgsConstructor
public class AssociadoService {

    private final AssociadoRepository associadoRepository;
    private final Validator validator;


    public void registrarAssociado(AssociadoDto associadoDto) {
        validarParametros(associadoDto);

        Associado associado = new Associado(associadoDto.getNome(), associadoDto.getCpf());
        associadoRepository.save(associado);
    }

    private void validarParametros(AssociadoDto associadoDto) {
        Set<ConstraintViolation<AssociadoDto>> violations = validator.validate(associadoDto);

        if (!violations.isEmpty()) {
            throw new ParametrosInvalidosException("Por favor, verifique os dados do associado!");
        }

        if (associadoCadastrado(associadoDto.getCpf())) {
            throw new AssociadoJaCadastradoException();
        }
    }

    private boolean associadoCadastrado(String cpfAssociado) {
        return associadoRepository.existsAssociadoByCpf(cpfAssociado);
    }
}

