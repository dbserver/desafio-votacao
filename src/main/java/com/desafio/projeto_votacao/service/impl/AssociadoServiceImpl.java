package com.desafio.projeto_votacao.service.impl;

import com.desafio.projeto_votacao.dto.AssociadoDto;
import com.desafio.projeto_votacao.entity.Associado;
import com.desafio.projeto_votacao.exceptions.CustomException;
import com.desafio.projeto_votacao.repository.AssociadoRepository;
import com.desafio.projeto_votacao.service.AssociadoService;
import com.desafio.projeto_votacao.utils.AssociadoValidator;
import com.desafio.projeto_votacao.utils.CpfValidator;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AssociadoServiceImpl implements AssociadoService {

    private final AssociadoRepository associadoRepository;
    private final AssociadoValidator validationAssociado;
    private final ModelMapper modelMapper;

    @Override
    public AssociadoDto cadastrarAssociado(String nome, String cpf) {

        if (validationAssociado.nomeVazioOuNulo(nome) || validationAssociado.cpfVazioOuNulo(cpf))
            throw new CustomException(HttpStatus.BAD_REQUEST, "Nome ou Cpf não podem ser vazios.");

        if (!cpf.isEmpty())
            validationAssociado.removerMascaraCPF(cpf);

        if (validationAssociado.existeAssociadoComCPF(cpf)) {
            throw new CustomException(HttpStatus.CONFLICT, "Já existe um associado com esse cpf.");
        }

        if (!CpfValidator.isValid(cpf)) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "Cpf não é válido.");
        }

        Associado associado = Associado.builder()
                .nome(nome)
                .cpf(cpf)
                .build();


        Associado associadoSalvo = associadoRepository.save(associado);

        return modelMapper.map(associadoSalvo, AssociadoDto.class);
    }

    @Override
    public List<AssociadoDto> listarAssociados() {

        List<Associado> listAssociado = Optional.ofNullable(associadoRepository.findAll())
                .orElse(new ArrayList<>());

        if (listAssociado.isEmpty()) throw new CustomException(HttpStatus.NOT_FOUND, "Não há associados cadastrados.");

        return listAssociado.stream()
                .map(associado -> modelMapper
                        .map(associado, AssociadoDto.class))
                .toList();

    }

    @Override
    public boolean verificarAssociadosCadastrados() {

        return associadoRepository.existsAllAssociados();
    }
}
