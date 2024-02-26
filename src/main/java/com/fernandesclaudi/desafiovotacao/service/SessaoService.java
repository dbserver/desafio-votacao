package com.fernandesclaudi.desafiovotacao.service;

import com.fernandesclaudi.desafiovotacao.dto.ContabilizacaoDto;
import com.fernandesclaudi.desafiovotacao.dto.SessaoDto;
import com.fernandesclaudi.desafiovotacao.dto.VotoDto;
import com.fernandesclaudi.desafiovotacao.enums.VotoEnum;
import com.fernandesclaudi.desafiovotacao.exceptions.IBaseException;
import com.fernandesclaudi.desafiovotacao.exceptions.IValorInvalidoException;
import com.fernandesclaudi.desafiovotacao.exceptions.IValorNaoInformadoException;
import com.fernandesclaudi.desafiovotacao.exceptions.IRegistroNaoEncontradoException;
import com.fernandesclaudi.desafiovotacao.model.Associado;
import com.fernandesclaudi.desafiovotacao.model.Pauta;
import com.fernandesclaudi.desafiovotacao.model.Sessao;
import com.fernandesclaudi.desafiovotacao.model.Voto;
import com.fernandesclaudi.desafiovotacao.repository.AssociadoRepository;
import com.fernandesclaudi.desafiovotacao.repository.PautaRepository;
import com.fernandesclaudi.desafiovotacao.repository.SessaoRepository;
import com.fernandesclaudi.desafiovotacao.repository.VotoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class SessaoService {
    @Autowired
    private SessaoRepository sessaoRepository;
    @Autowired
    private PautaRepository pautaRepository;
    @Autowired
    private VotoRepository votoRepository;
    @Autowired
    private AssociadoRepository associadoRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Sessao abrir(SessaoDto sessaoDto) throws IRegistroNaoEncontradoException, IValorNaoInformadoException {

        Optional<Pauta> pauta = pautaRepository.findById(sessaoDto.getPauta().getId());
        if (pauta.isEmpty()) {
            throw new IRegistroNaoEncontradoException("Pauta");
        }

        List<Sessao> sessaoPauta = sessaoRepository.findByDataFimAfterAndPauta_Id(LocalDateTime.now(), pauta.get().getId());
        if (!sessaoPauta.isEmpty()) {
            throw new IBaseException("Ja existe uma sessão aberta para esta pauta", HttpStatus.UNPROCESSABLE_ENTITY);
        }

        if (sessaoDto.getDuracao().equals(0L)) {
            sessaoDto.setDuracao(60L);
        }

        Sessao sessao = modelMapper.map(sessaoDto, Sessao.class);
        sessao.setPauta(pauta.get());
        sessao.setDataInicio(LocalDateTime.now());
        sessao.setDataFim(LocalDateTime.now().plusSeconds(sessaoDto.getDuracao()));

        return this.sessaoRepository.save(sessao);
    }

    public List<Sessao> sessoesAbertas() {
        return sessaoRepository.findByDataFimAfter(LocalDateTime.now());
    }

    public Voto registrarVoto(VotoDto votoDto) {

        Optional<Associado> associado = this.associadoRepository.findById(votoDto.getAssociado().getId());
        if (associado.isEmpty()) {
            throw new IRegistroNaoEncontradoException("Associado");
        }

        Optional<Sessao> sessao = sessaoRepository.findById(votoDto.getSessao().getId());
        if (sessao.isEmpty()) {
            throw new IRegistroNaoEncontradoException("Sessão");
        }

        if (LocalDateTime.now().isAfter(sessao.get().getDataFim())) {
            throw new IBaseException("Sessão já encerrada.", HttpStatus.UNPROCESSABLE_ENTITY);
        }

        if (this.votoRepository.existsVotoBySessao_IdAndAssociado_Id(sessao.get().getId(), associado.get().getId())) {
            throw new IBaseException("Associado ja votou nesta sessão", HttpStatus.UNPROCESSABLE_ENTITY);
        }

        Voto voto = new Voto();
        voto.setVoto(VotoEnum.findByValue(votoDto.getVoto()));
        voto.setSessao(sessao.get());
        voto.setAssociado(associado.get());
        voto.setDtVoto(LocalDateTime.now());
        return this.votoRepository.save(voto);
    }

    public ContabilizacaoDto contabilizarVotos(Long idSessao) {

        Optional<Sessao> sessao = sessaoRepository.findById(idSessao);
        if (sessao.isEmpty()) {
            throw new IRegistroNaoEncontradoException("Sessão");
        }

        List<Voto> votos = this.votoRepository.findBySessao_IdOrderByDtVoto(idSessao);
        if (votos.isEmpty()) {
            throw new IBaseException("Nenhum voto foi registrado nesta sessão", HttpStatus.NOT_FOUND);
        }

        return new ContabilizacaoDto(
                (int) votos.stream().filter(voto -> voto.getVoto().getValue().equals(VotoEnum.SIM.getValue())).count(),
                (int) votos.stream().filter(voto -> voto.getVoto().getValue().equals(VotoEnum.NAO.getValue())).count(),
                votos.size()
        );
    }
}
