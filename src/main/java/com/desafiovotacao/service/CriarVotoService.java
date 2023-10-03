package com.desafiovotacao.service;

import com.desafiovotacao.domain.Associado;
import com.desafiovotacao.domain.SessaoPauta;
import com.desafiovotacao.domain.VotoAssociado;
import com.desafiovotacao.dto.VotoAssociadoDTO;
import com.desafiovotacao.repository.VotoRepository;
import com.desafiovotacao.service.interfaces.IBuscarAssociadoPorIdService;
import com.desafiovotacao.service.interfaces.IBuscarSessaoPorIdService;
import com.desafiovotacao.service.interfaces.IBuscarVotacaoService;
import com.desafiovotacao.service.interfaces.ICriarVotoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CriarVotoService implements ICriarVotoService {

    private final VotoRepository votoRepository;
    private final IBuscarVotacaoService buscarVotacaoService;
    private final IBuscarAssociadoPorIdService buscarAssociadoPorIdService;
    private final IBuscarSessaoPorIdService buscarSessaoPorIdService;

    public CriarVotoService(
            VotoRepository votoRepository,
            IBuscarAssociadoPorIdService buscarAssociadoPorIdService,
            IBuscarSessaoPorIdService buscarSessaoPorIdService,
            IBuscarVotacaoService buscarVotacaoService
    ) {
        this.votoRepository = votoRepository;
        this.buscarAssociadoPorIdService = buscarAssociadoPorIdService;
        this.buscarVotacaoService = buscarVotacaoService;
        this.buscarSessaoPorIdService = buscarSessaoPorIdService;
    }

    @Override
    @Transactional
    public VotoAssociadoDTO criar(VotoAssociadoDTO votoAssociadoDTO) throws Exception {
        VotoAssociado votoAssociadoEncontrado = this.buscarVotacaoService.buscar(votoAssociadoDTO.getAssociadoId(), votoAssociadoDTO.getSessaoId());

        if(votoAssociadoEncontrado != null) {
            throw new Exception("Associado já votou nessa sessão.");
        }
        Associado associado = this.buscarAssociadoPorIdService.buscar(votoAssociadoDTO.getAssociadoId());
        SessaoPauta sessaoPauta = this.buscarSessaoPorIdService.buscar(votoAssociadoDTO.getSessaoId());

        VotoAssociado votoAssociado = this.votoRepository.save(votoAssociadoDTO.toEntity());
        votoAssociado.setSessaoPauta(sessaoPauta);
        votoAssociado.setAssociado(associado);

        return VotoAssociadoDTO.fromEntity(votoAssociado);
    }
}
