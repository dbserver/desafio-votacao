package br.tec.db.desafio.business.service.implementation;

import br.tec.db.desafio.api.v1.dto.pauta.PautaMapperV1;
import br.tec.db.desafio.api.v1.dto.sessao.SessaoMapperV1;
import br.tec.db.desafio.api.v1.dto.sessao.SessaoRequestV1;
import br.tec.db.desafio.api.v1.dto.sessao.SessaoResponseV1;
import br.tec.db.desafio.business.entity.Sessao;
import br.tec.db.desafio.business.service.PautaService;
import br.tec.db.desafio.business.service.SessaoService;
import br.tec.db.desafio.business.service.implementation.validacao.sessao.ValidacaoSessao;
import br.tec.db.desafio.repository.SessaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SessaoServiceImpl implements SessaoService {
    private final SessaoRepository sessaoRepository;
    private final List<ValidacaoSessao> validacoesSessao;

    public SessaoServiceImpl(SessaoRepository sessaoRepository, List<ValidacaoSessao> validacoesSessao) {
        this.sessaoRepository = sessaoRepository;
        this.validacoesSessao = validacoesSessao;
    }

    @Override
    public SessaoResponseV1 criarUmaNovaSessao(SessaoRequestV1 sessaoRequestV1) {
        this.validacoesSessao.forEach(v -> v.validarSessao(sessaoRequestV1));

        Sessao sessaoToCreate = SessaoMapperV1.sessaoRequestV1ToSessao(
                sessaoRequestV1
        );

        return SessaoMapperV1.sessaoToSessaoResponseV1(
                sessaoRepository.save(sessaoToCreate)
        );
    }
}
