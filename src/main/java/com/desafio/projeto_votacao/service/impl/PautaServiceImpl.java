package com.desafio.projeto_votacao.service.impl;

import com.desafio.projeto_votacao.dto.PautaDto;
import com.desafio.projeto_votacao.dto.ResultadoVotacaoDto;
import com.desafio.projeto_votacao.dto.VotoDto;
import com.desafio.projeto_votacao.entity.Pauta;
import com.desafio.projeto_votacao.entity.Sessao;
import com.desafio.projeto_votacao.enums.VotoEnum;
import com.desafio.projeto_votacao.exceptions.CustomException;
import com.desafio.projeto_votacao.repository.PautaRepository;
import com.desafio.projeto_votacao.repository.SessaoRepository;
import com.desafio.projeto_votacao.service.PautaService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

@Service
@RequiredArgsConstructor
public class PautaServiceImpl implements PautaService {

    private final PautaRepository pautaRepository;
    private final SessaoRepository sessaoRepository;
    private final AssociadoServiceImpl associadoService;
    private final SessaoServiceImpl sessaoService;
    private final VotoServiceImpl votoService;
    private final ModelMapper modelMapper;

    private static final Integer TEMPO_SESSAO_DEFAULT  = 1;
    private static final long MULTIPLICADOR_DE_TEMPO  = 60000;
    private final WebClient webClient;

    @Override
    public void cadastrarPauta(String titulo, String descricao, Integer tempoSessao) {

        if (!associadoService.verificarAssociadosCadastrados()) {
            throw new CustomException(HttpStatus.NOT_FOUND, "Não há associados cadastrados.");
        }

        if (sessaoRepository.existsByStatusIsTrue()){
            throw new CustomException(HttpStatus.CONFLICT, "Já existe uma pauta com a sessão aberta.");
        }

        Pauta pauta = Pauta.builder()
                .titulo(titulo)
                .descricao(descricao)
                .build();

        pautaRepository.save(pauta);

        tempoSessao = (tempoSessao == 0 || tempoSessao > 60) ? tempoSessao : TEMPO_SESSAO_DEFAULT;

        Sessao sessaoEntity = sessaoService.abrirSessaoVotacao(tempoSessao, pauta);

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Pauta pauta = sessaoService.fecharSessaoVotacao(sessaoEntity);
                enviarResultadoVotacao(pauta.getId());
            }
        }, tempoSessao * MULTIPLICADOR_DE_TEMPO);
    }

    private ResultadoVotacaoDto enviarResultadoVotacao(Long pautaId) {

        String url = "http://localhost:8080/resultado-votacao/{pautaId}";
        return webClient.get()
                .uri(url, pautaId)
                .retrieve()
                .onStatus(HttpStatusCode::isError, response -> Mono.error(
                        new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, "Falha ao obter o resultado da votação.")))
                .bodyToMono(ResultadoVotacaoDto.class)
                .switchIfEmpty(Mono.error(new CustomException(HttpStatus.NOT_FOUND, "Nenhum voto encontrado para a pauta.")))
                .block();
    }


    @Override
    public List<PautaDto> listarPautas() {

        List<Pauta> listPauta = pautaRepository.findAll();

        if (listPauta.isEmpty()){
            throw new CustomException(HttpStatus.NOT_FOUND, "Não há pautas cadastradas.");
        }

        return listPauta.stream()
                .map(pauta -> modelMapper.map(pauta, PautaDto.class))
                .toList();

    }

    @Override
    public ResultadoVotacaoDto obterResultadoVotacao(Long pautaId) {

        Pauta pauta = pautaRepository.findById(pautaId)
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Pauta não encontrada."));

        List<VotoDto> votosDto = votoService.obterResultadoVotacao(pauta);

        long totalVotos = votosDto.size();

        long votosSim = votosDto.stream()
                .filter(votoDto -> votoDto.getVotoEnum()
                        .equals(VotoEnum.SIM)).count();

        long votosNao = votosDto.stream()
                .filter(votoDto -> votoDto.getVotoEnum()
                        .equals(VotoEnum.NAO)).count();

        String aprovado = votosSim > votosNao ? "Sim" : votosSim < votosNao ? "Não" : "Empate";

        return new ResultadoVotacaoDto(totalVotos, votosSim, votosNao, aprovado);
    }
}