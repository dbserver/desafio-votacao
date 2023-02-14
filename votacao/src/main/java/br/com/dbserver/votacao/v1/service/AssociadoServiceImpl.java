package br.com.dbserver.votacao.v1.service;

import br.com.dbserver.votacao.v1.dto.request.AssociadoRequest;
import br.com.dbserver.votacao.v1.dto.response.AssociadoResponse;
import br.com.dbserver.votacao.v1.entity.Associado;
import br.com.dbserver.votacao.v1.mapper.MapperAssociado;
import br.com.dbserver.votacao.v1.repository.AssociadoRepository;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AssociadoServiceImpl implements AssociadoService {

    private final AssociadoRepository associadoRepository;


    @Override
    public AssociadoResponse salvar(AssociadoRequest associadoDto) {
        Associado associado = MapperAssociado.INSTANCE.associadoRequesToAssociado(associadoDto);
        associadoRepository.save(associado);

        return MapperAssociado.INSTANCE.associadoToResponse(associado);
    }
}
