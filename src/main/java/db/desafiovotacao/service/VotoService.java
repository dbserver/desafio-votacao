package db.desafiovotacao.service;

import db.desafiovotacao.model.*;
import db.desafiovotacao.service.interfaces.IVotoService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class VotoService implements IVotoService {



    private final VotoPautaService votoPautaService;

    private final AssociadoService associadoService;
    private final AssociadoPautaService associadoPautaService;



    public VotoService(VotoPautaService votoPautaService, AssociadoService associadoService,  AssociadoPautaService associadoPautaService){

        this.votoPautaService = votoPautaService;
        this.associadoService = associadoService;
        this.associadoPautaService = associadoPautaService;
    }

    @Override
    public VotoPauta cadastrarVoto(VotoPauta votoPauta, String CPF) {

        if (!validarDataHoraVoto(votoPauta))
            throw new RuntimeException("voto fora do horario de votacao"); // TODO exception

        Associado associado = associadoService.buscarPorCPF(CPF);
        Pauta pauta = votoPauta.getPauta();

        AssociadoPauta associadoPauta = associadoPautaService.buscarAssociadoPauta(associado, pauta);

        if (associadoPauta == null)
            throw new RuntimeException("associado não cadastrado para votar nessa pauta"); // TODO exception

        if(associadoPauta.getVotou())
            throw new RuntimeException("associado ja votou na pauta"); // TODO exception

        associadoPauta.setVotou(true);

        return votoPautaService.cadastrarVotoPauta(votoPauta);
    }

    private Boolean validarDataHoraVoto(VotoPauta votoPauta){

        LocalDateTime inicioSessao = votoPauta.getPauta().getSessao().getInicioSessao();
        LocalDateTime finalSessao = votoPauta.getPauta().getSessao().getFinalSessao();

        LocalDateTime dataHoraVoto = votoPauta.getVoto().getDataHoraVoto();

        return dataHoraVoto.isAfter(inicioSessao) && dataHoraVoto.isBefore(finalSessao);
    }


}
