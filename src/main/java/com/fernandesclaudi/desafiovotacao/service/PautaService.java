package com.fernandesclaudi.desafiovotacao.service;

import com.fernandesclaudi.desafiovotacao.dto.PautaDto;
import com.fernandesclaudi.desafiovotacao.exceptions.IRegistroNaoEncontradoException;
import com.fernandesclaudi.desafiovotacao.exceptions.IValorNaoInformadoException;
import com.fernandesclaudi.desafiovotacao.model.Associado;
import com.fernandesclaudi.desafiovotacao.model.Pauta;
import com.fernandesclaudi.desafiovotacao.repository.AssociadoRepository;
import com.fernandesclaudi.desafiovotacao.repository.PautaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PautaService {
    @Autowired
    private PautaRepository PautaRepository;
    @Autowired
    private AssociadoRepository associadoRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Pauta findById(Long id) {
        Optional<Pauta> pauta = PautaRepository.findById(id);
        return pauta.orElseThrow(() -> new IRegistroNaoEncontradoException("Pauta"));
    }

    public List<Pauta> findAllByRedator(Long idRedator) {
        return PautaRepository.findAllByRedator_IdOrderByDataDesc(idRedator);
    }

    public Pauta save(PautaDto pautaDto) {

        if (pautaDto.getRedator() == null) throw new IValorNaoInformadoException("pauta.redator");

        Associado associado = this.associadoRepository.findById(pautaDto.getRedator().getId())
                .orElseThrow(() -> new IRegistroNaoEncontradoException("Associado"));

        Pauta pauta = this.modelMapper.map(pautaDto, Pauta.class);
        pauta.setRedator(associado);
        pauta.setData(LocalDate.now());
        return PautaRepository.save(pauta);
    }
}
