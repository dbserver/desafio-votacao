package com.fernandesclaudi.desafiovotacao.service;

import com.fernandesclaudi.desafiovotacao.dto.SessaoDto;
import com.fernandesclaudi.desafiovotacao.dto.VotoDto;
import com.fernandesclaudi.desafiovotacao.enums.VotoEnum;
import com.fernandesclaudi.desafiovotacao.exceptions.IBaseException;
import com.fernandesclaudi.desafiovotacao.exceptions.IValorInvalidoException;
import com.fernandesclaudi.desafiovotacao.exceptions.IValorNaoInformadoException;
import com.fernandesclaudi.desafiovotacao.exceptions.IRegistroNaoEncontradoException;
import com.fernandesclaudi.desafiovotacao.model.Pauta;
import com.fernandesclaudi.desafiovotacao.model.Sessao;
import com.fernandesclaudi.desafiovotacao.model.Voto;
import com.fernandesclaudi.desafiovotacao.repository.PautaRepository;
import com.fernandesclaudi.desafiovotacao.repository.SessaoRepository;
import com.fernandesclaudi.desafiovotacao.repository.VotoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
    private ModelMapper modelMapper;

    public Sessao abrir(SessaoDto sessaoDto) throws IRegistroNaoEncontradoException, IValorNaoInformadoException {
        if ((sessaoDto.getPauta() == null) || (sessaoDto.getPauta().getId() == 0L)) {
            throw new IValorNaoInformadoException("sessao.pauta");
        }

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

    public VotoDto registrarVoto(VotoDto votoDto) throws IValorNaoInformadoException, IValorInvalidoException {
        if ((votoDto.getAssociado() == null) || (votoDto.getAssociado().getId() == 0L)) {
            throw new IValorNaoInformadoException("associado");
        }

        if ((votoDto.getSessao() == null) || (votoDto.getSessao().getId() == 0L)) {
            throw new IValorNaoInformadoException("sessao");
        }

        if (votoDto.getVoto() == null) {
            throw new IValorInvalidoException("Valor do voto inválido. Esperado S (Sim) ou N (Não)");
        }

        if (LocalDateTime.now().isAfter(votoDto.getSessao().getDataFim())) {
            throw new IBaseException("Sessão encerrada", HttpStatus.UNPROCESSABLE_ENTITY);
        }

        Optional<Sessao> sessao = sessaoRepository.findById(votoDto.getSessao().getId());
        if (sessao.isEmpty()) {
            throw new IRegistroNaoEncontradoException("Sessão");
        }

        return null;
    }

    public SessaoDto contabilizarVotos(Long idSessao) {
        if (idSessao == 0L) {
            throw new IValorNaoInformadoException("id");
        }

        Optional<Sessao> sessao = sessaoRepository.findById(idSessao);
        if (sessao.isEmpty()) {
            throw new IRegistroNaoEncontradoException("Sessão");
        }

        List<Voto> votos = this.votoRepository.findBySessao_IdOrderByDtVoto(idSessao);
        if (votos.isEmpty()) {
            throw new IBaseException("Nenhum voto foi registrado nesta sessão", HttpStatus.NOT_FOUND);
        }

        SessaoDto sessaoDto = this.modelMapper.map(sessao, SessaoDto.class);
        sessaoDto.setCountVotosNao((int) votos.stream().filter(voto -> voto.getVoto().value.equals(VotoEnum.NAO.value)).count());
        sessaoDto.setCountVotosSim((int) votos.stream().filter(voto -> voto.getVoto().value.equals(VotoEnum.SIM.value)).count());

        return sessaoDto;
    }
}
