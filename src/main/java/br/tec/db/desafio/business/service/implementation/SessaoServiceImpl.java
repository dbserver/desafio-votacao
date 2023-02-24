package br.tec.db.desafio.business.service.implementation;

import br.tec.db.desafio.api.v1.dto.sessao.SessaoMapperV1;
import br.tec.db.desafio.api.v1.dto.sessao.request.SessaoParaCriarRequestV1;
import br.tec.db.desafio.api.v1.dto.sessao.request.SessaoParaSaberTotalVotosRequestV1;
import br.tec.db.desafio.api.v1.dto.sessao.request.SessaoParaVotarRequestV1;
import br.tec.db.desafio.api.v1.dto.sessao.response.SessaoCriadaResponseV1;
import br.tec.db.desafio.api.v1.dto.sessao.response.SessaoTotalVotosResponseV1;
import br.tec.db.desafio.api.v1.dto.sessao.response.SessaoVotadaResponseV1;
import br.tec.db.desafio.business.domain.Associado;
import br.tec.db.desafio.business.domain.AssociadoSessao;
import br.tec.db.desafio.business.domain.Pauta;
import br.tec.db.desafio.business.domain.Sessao;
import br.tec.db.desafio.business.service.SessaoService;
import br.tec.db.desafio.business.service.implementation.validacao.sessao.ValidacaoSessao;
import br.tec.db.desafio.exception.BusinessException;
import br.tec.db.desafio.repository.AssociadoRepository;
import br.tec.db.desafio.repository.AssociadoSessaoRepository;
import br.tec.db.desafio.repository.PautaRepository;
import br.tec.db.desafio.repository.SessaoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SessaoServiceImpl implements SessaoService {
    private final SessaoRepository sessaoRepository;
    private final PautaRepository pautaRepository;
    private final AssociadoRepository associadoRepository;
    private final AssociadoSessaoRepository associadoSessaoRepository;
    private final List<ValidacaoSessao> validacoesSessao;

    public SessaoServiceImpl(SessaoRepository sessaoRepository, PautaRepository pautaRepository, AssociadoRepository associadoRepository, AssociadoSessaoRepository associadoSessaoRepository, List<ValidacaoSessao> validacoesSessao) {
        this.sessaoRepository = sessaoRepository;
        this.pautaRepository = pautaRepository;
        this.associadoRepository = associadoRepository;
        this.associadoSessaoRepository = associadoSessaoRepository;
        this.validacoesSessao = validacoesSessao;
    }

    @Override
    public SessaoCriadaResponseV1 criarUmaNovaSessao(SessaoParaCriarRequestV1 sessaoRequestV1) {
        this.validacoesSessao.forEach(v -> v.validarSessao(sessaoRequestV1));

        Sessao sessaoToCreate = SessaoMapperV1.sessaoParaCriarRequestV1ToSessao(
                sessaoRequestV1
        );
        Pauta pautaEncontrada = pautaRepository.findPautaByAssunto
                (sessaoToCreate.getPauta().getAssunto());

        sessaoToCreate.setPauta(pautaEncontrada);

        return SessaoMapperV1.sessaoToSessaoCriadaResponseV1(
                sessaoRepository.save(sessaoToCreate)
        );
    }

    @Override
    public SessaoVotadaResponseV1 votarEmUmaSessao(SessaoParaVotarRequestV1 sessaoRequestV1) {
        Sessao sessaoToCreate = SessaoMapperV1.sessaoParaVotarRequestV1ToSessao(
                sessaoRequestV1
        );

        Pauta pautaEncontrada = pautaRepository.findPautaByAssunto
                (sessaoToCreate.getPauta().getAssunto());
        Sessao sessaoEncontrada = sessaoRepository.findByPautaId
                (pautaEncontrada.getId());
        Associado associadoEncontrado = associadoRepository.findAssociadoByCpf(
                sessaoRequestV1.getCpf());
        Long associadoNaSessao = associadoSessaoRepository.findByAssociadoIdAndSessaoId(
                associadoEncontrado.getId(),
                sessaoEncontrada.getId());

        if(associadoNaSessao != null){
            throw new BusinessException("Associado já votou");
        }


        if(sessaoEncontrada.getDuracao().isBefore(LocalDateTime.now())){
            throw new BusinessException("Sessão expirada");
        }

        sessaoEncontrada.addAssociado(associadoEncontrado);
        sessaoEncontrada.setVoto(sessaoToCreate.getVoto());
        return SessaoMapperV1.sessaoToSessaoVotadaResponseV1(
                sessaoRepository.save(sessaoEncontrada)
        );
    }

    @Override
    public SessaoTotalVotosResponseV1 totalDeVotosDaSessao(SessaoParaSaberTotalVotosRequestV1 sessaoRequestV1) {
        Sessao sessaoToCreate = SessaoMapperV1.sessaoParaSaberTotalVotosRequestV1ToSessao(
                sessaoRequestV1
        );
        Pauta pautaEncontrada = pautaRepository.findPautaByAssunto
                (sessaoToCreate.getPauta().getAssunto());
        Sessao sessaoEncontrada = sessaoRepository.findByPautaId
                (pautaEncontrada.getId());


        return SessaoMapperV1.sessaoToSessaoTotalVotosResponseV1(
                sessaoEncontrada
        );
    }


}
