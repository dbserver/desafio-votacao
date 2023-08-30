package com.db.desafio.service;


import com.db.desafio.dto.VotoDto;
import com.db.desafio.entity.Sessao;
import com.db.desafio.entity.Voto;
import com.db.desafio.exception.SessaoException;
import com.db.desafio.repository.SessaoRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@AllArgsConstructor
@Service
public class SessaoService {

    private SessaoRepository sessaoRepository;
    private PautaService pautaService;
    private AssociadoService associadoService;


    public void abrirSessao(Long pautaId) {
        var sessao = new Sessao(pautaService.obterPautaPorId(pautaId));
        sessaoRepository.save(sessao);
    }

    public Sessao obterSessao(Long id) {
        return sessaoRepository.findById(id)
                .orElseThrow(() -> new SessaoException("Sessao inexistente"));
    }

    public List<Sessao> obterSessoes() {
        return sessaoRepository.findAll();
    }


    public Sessao retonarSesaoAberta(Long id) {
        LocalDateTime encerrarSessao = LocalDateTime.now();
        Sessao sessao = sessaoRepository.findById(id).
                orElseThrow(() -> new SessaoException("Sessao inexistente"));

        if (encerrarSessao.isAfter(sessao.getFinalSessao())) {
            throw new SessaoException("Sessão já está encerrada");
        }
        return sessao;
    }


    public void votarSessao(Long sessaoId, VotoDto votoDto) {
        Sessao sessao = retonarSesaoAberta(sessaoId);
        verificarVotoExistente(sessao, votoDto.getCpf());
        var voto = new Voto(votoDto.getVotoEnum(),
                associadoService.obterAssociadoPorCpf(votoDto.getCpf()));
        sessao.getVotos().add(voto);
        sessaoRepository.save(sessao);
    }

    private void verificarVotoExistente(Sessao sessao, String cpf) {
        if (sessao.getVotos().stream().anyMatch(voto -> voto.getAssociado().getCpf().equals(cpf))) {
            throw new SessaoException("O associado já votou.");
        }
    }



}
