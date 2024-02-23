package com.fernandesclaudi.desafiovotacao.service;

import com.fernandesclaudi.desafiovotacao.dto.AssociadoDto;
import com.fernandesclaudi.desafiovotacao.exceptions.IRegistroJaInseridoException;
import com.fernandesclaudi.desafiovotacao.exceptions.IRegistroNaoEncontradoException;
import com.fernandesclaudi.desafiovotacao.exceptions.IValorNaoInformadoException;
import com.fernandesclaudi.desafiovotacao.model.Associado;
import com.fernandesclaudi.desafiovotacao.repository.AssociadoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AssociadoService {
    @Autowired
    private AssociadoRepository associadoRepository;
    @Autowired
    private ModelMapper modelMapper;

    public Associado findByCpf(String cpf) {

        return associadoRepository.findByCpf(cpf);
    }

    public Associado findById(Long id) {
        Optional<Associado> associado = associadoRepository.findById(id);
        return associado.orElseThrow(() -> new IRegistroNaoEncontradoException("Pauta"));
    }

    public Associado save(AssociadoDto associadoDto) {
        if (associadoDto.getCpf().isBlank()) {
            throw new IValorNaoInformadoException("cpf");
        }

        Associado associadoEncontrado = findByCpf(associadoDto.getCpf());
        if (associadoEncontrado != null) {
            throw new IRegistroJaInseridoException("Já existe um associado com o CPF informado.");
        }

        Associado associado = modelMapper.map(associadoDto, Associado.class);

        return associadoRepository.save(associado);
    }
}
