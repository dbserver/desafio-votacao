package br.com.occ.desafiovotacao.v1.service;

import br.com.occ.desafiovotacao.config.exception.ApiException;
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
    public Voto findById(Long id) {
        Optional<Voto> votoOptional = repository.findById(id);
        return votoOptional.orElseThrow(() -> new ServiceException("Voto não encontrado para o id informado", HttpStatus.NOT_FOUND));
    }

    @Override
    public List<Voto> findAllByAssociado(Long associadoId) {
        List<Voto> votos = repository.findAllByAssociado_Id(associadoId);
        if (votos.isEmpty())
            throw new ServiceException("Associado não possui voto em nenhuma pauta", HttpStatus.NOT_FOUND);
        return votos;
    }

    @Override
    public Voto votar(Voto voto) {
        Associado associado = associadoService.findById(voto.getAssociado().getId());

        if(repository.existsVotoByAssociado_IdAndPauta_Id(voto.getAssociado().getId(), voto.getPauta().getId()))
            throw new ServiceException("Associado já votou nesta pauta", HttpStatus.BAD_REQUEST);

        if (Boolean.FALSE.equals(associado.getAtivo()))
            throw new ServiceException("Associado não está ativo", HttpStatus.BAD_REQUEST);

        Pauta pauta = pautaService.findById(voto.getPauta().getId());

        if (pauta.getSessao() == null)
            throw new ServiceException("Sessão ainda não foi aberta para pauta selecionada", HttpStatus.BAD_REQUEST);

        if (pauta.getSessao().getDataFim().isBefore(LocalDateTime.now()))
            throw new ServiceException("Sessão encerrada! Não pode mais receber votos", HttpStatus.BAD_REQUEST);

        return repository.save(voto);
    }

}
