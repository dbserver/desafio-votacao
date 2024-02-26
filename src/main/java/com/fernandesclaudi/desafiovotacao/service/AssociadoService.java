package com.fernandesclaudi.desafiovotacao.service;

import com.fernandesclaudi.desafiovotacao.dto.AssociadoDto;
import com.fernandesclaudi.desafiovotacao.dto.CpfDto;
import com.fernandesclaudi.desafiovotacao.enums.StatusCpfEnum;
import com.fernandesclaudi.desafiovotacao.exceptions.ICpfInvalidoParaVotacaoException;
import com.fernandesclaudi.desafiovotacao.exceptions.IRegistroJaInseridoException;
import com.fernandesclaudi.desafiovotacao.exceptions.IRegistroNaoEncontradoException;
import com.fernandesclaudi.desafiovotacao.model.Associado;
import com.fernandesclaudi.desafiovotacao.repository.AssociadoRepository;
import com.fernandesclaudi.desafiovotacao.util.RandomValidateCpf;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AssociadoService {
    @Autowired
    private AssociadoRepository associadoRepository;
    @Autowired
    private ModelMapper modelMapper;


    public Associado findById(Long id) {
        Optional<Associado> associado = this.associadoRepository.findById(id);
        return associado.orElseThrow(() -> new IRegistroNaoEncontradoException("Pauta"));
    }

    public Associado save(AssociadoDto associadoDto) {

        Associado associadoEncontrado = associadoRepository.findByCpf(associadoDto.getCpf());
        if (associadoEncontrado != null) {
            throw new IRegistroJaInseridoException("Já existe um associado com o CPF informado.");
        }

        Associado associado = modelMapper.map(associadoDto, Associado.class);

        return associadoRepository.save(associado);
    }

    public String isValidCpf(String cpf) {
        boolean cpfValido = RandomValidateCpf.validarCpfRandom(cpf);
        if (cpfValido) {
            throw new ICpfInvalidoParaVotacaoException();
        }
        return "{\"status\":\"" + StatusCpfEnum.ABLE_TO_VOTE + "\"}";
    }
}
