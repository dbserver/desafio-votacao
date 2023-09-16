package com.desafio.projeto_votacao.service.impl;

import com.desafio.projeto_votacao.dto.VotoDto;
import com.desafio.projeto_votacao.entity.Associado;
import com.desafio.projeto_votacao.entity.Pauta;
import com.desafio.projeto_votacao.entity.Voto;
import com.desafio.projeto_votacao.enums.VotoEnum;
import com.desafio.projeto_votacao.exceptions.CustomException;
import com.desafio.projeto_votacao.repository.AssociadoRepository;
import com.desafio.projeto_votacao.repository.VotoRepository;
import com.desafio.projeto_votacao.service.VotoService;
import com.desafio.projeto_votacao.utils.AssociadoValidator;
import com.desafio.projeto_votacao.utils.CpfValidator;
import com.desafio.projeto_votacao.utils.VotoValidator;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VotoServiceImpl implements VotoService {

    private final AssociadoRepository associadoRepository;
    private final VotoRepository votoRepository;
    private final ModelMapper modelMapper;
    private final VotoValidator votoValidator;
    private final AssociadoValidator associadoValidator;
    @Override
    public void registrarVoto(VotoEnum votoEnum, String cpfAssociado) {

        if (associadoValidator.cpfVazioOuNulo(cpfAssociado)) throw new CustomException(HttpStatus.BAD_REQUEST, "CPF não pode ser vazio ou nulo.");

        if (!CpfValidator.isValid(cpfAssociado)) throw new CustomException(HttpStatus.BAD_REQUEST, "CPF inválido.");

        cpfAssociado = associadoValidator.removerMascaraCPF(cpfAssociado);

        if (votoValidator.associadoJaVotou(cpfAssociado)){
            throw new CustomException(HttpStatus.CONFLICT, "Associado já votou nessa pauta.");
        }

        Associado associado = Optional.ofNullable(associadoRepository.findByCpf(cpfAssociado))
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Associado não cadastrado para efetuar a votação."));

        Voto votoBuilder = Voto.builder()
                .votoEnum(votoEnum)
                .associado(associado)
                .pauta(new Pauta())
                .build();

        votoRepository.save(votoBuilder);

    }

    @Override
    public List<VotoDto> obterResultadoVotacao(Pauta pauta) {

        return null;
    }
}
