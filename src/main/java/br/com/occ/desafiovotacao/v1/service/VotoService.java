package br.com.occ.desafiovotacao.v1.service;

import br.com.occ.desafiovotacao.config.exception.ServiceException;
import br.com.occ.desafiovotacao.v1.model.Associado;
import br.com.occ.desafiovotacao.v1.model.Pauta;
import br.com.occ.desafiovotacao.v1.model.Voto;
import br.com.occ.desafiovotacao.v1.repository.VotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class VotoService implements IVotoService{

    @Autowired
    VotoRepository repository;

    @Autowired
    IAssociadoService associadoService;

    @Autowired
    IPautaService pautaService;

    @Override
    public Optional<Voto> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Voto> findAllByAssociado(Long associadoId) {
        return repository.findAllByAssociado_Id(associadoId);
    }

    @Override
    public Voto votar(Voto voto) {
        Optional<Associado> associadoOptional = associadoService.findById(voto.getAssociado().getId());
        if (associadoOptional.isEmpty())
            throw new ServiceException("Associado não encontrado", HttpStatus.BAD_REQUEST);

        Optional<Pauta> pautaOptional = pautaService.findById(voto.getPauta().getId());
        if (pautaOptional.isEmpty())
            throw new ServiceException("Pauta não encontrada", HttpStatus.BAD_REQUEST);

        if (pautaOptional.get().getSessao() == null)
            throw new ServiceException("Sessão ainda não foi aberta para esta pauta", HttpStatus.BAD_REQUEST);

        if (pautaOptional.get().getSessao().getDataFim().isAfter(LocalDateTime.now()))
            throw new ServiceException("Sessão encerrada! Não pode mais receber votos", HttpStatus.BAD_REQUEST);

        return repository.save(voto);
    }

}
