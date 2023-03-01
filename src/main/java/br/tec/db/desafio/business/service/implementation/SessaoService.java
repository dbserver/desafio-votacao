package br.tec.db.desafio.business.service.implementation;

import br.tec.db.desafio.api.v1.dto.sessao.SessaoMapperV1;
import br.tec.db.desafio.api.v1.dto.sessao.request.SessaoParaCriarRequestV1;
import br.tec.db.desafio.api.v1.dto.sessao.request.SessaoParaSaberTotalVotosRequestV1;
import br.tec.db.desafio.api.v1.dto.sessao.request.SessaoParaVotarRequestV1;
import br.tec.db.desafio.api.v1.dto.sessao.response.SessaoCriadaResponseV1;
import br.tec.db.desafio.api.v1.dto.sessao.response.SessaoTotalVotosResponseV1;
import br.tec.db.desafio.api.v1.dto.sessao.response.SessaoVotadaResponseV1;
import br.tec.db.desafio.business.domain.Associado;
import br.tec.db.desafio.business.domain.Pauta;
import br.tec.db.desafio.business.domain.Sessao;
import br.tec.db.desafio.business.service.ISessaoService;
import br.tec.db.desafio.business.service.implementation.base.BaseSessao;
import br.tec.db.desafio.repository.AssociadoRepository;
import br.tec.db.desafio.repository.AssociadoSessaoRepository;
import br.tec.db.desafio.repository.PautaRepository;
import br.tec.db.desafio.repository.SessaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SessaoService extends BaseSessao implements ISessaoService {


    public SessaoService(SessaoRepository sessaoRepository, PautaRepository pautaRepository, AssociadoRepository associadoRepository, AssociadoSessaoRepository associadoSessaoRepository) {
        super(sessaoRepository, pautaRepository, associadoRepository, associadoSessaoRepository);
    }

    @Override
    public SessaoCriadaResponseV1 criarUmaNovaSessao(SessaoParaCriarRequestV1 sessaoRequestV1) {

        Sessao sessaoToCreate = SessaoMapperV1.sessaoParaCriarRequestV1ToSessao(
                sessaoRequestV1
        );

        Long idPauta = pautaRepository.findIdByAssunto
                (sessaoToCreate.getPauta().getAssunto());
        valida.validarSessaoInexistente(idPauta);
        Pauta pautaEncontrada = pautaRepository.findPautaByAssunto
                (sessaoToCreate.getPauta().getAssunto());

        valida.validarSessaoRepetida(pautaEncontrada.getSessao());
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
        Long idPauta = pautaRepository.findIdByAssunto
                (sessaoToCreate.getPauta().getAssunto());
        valida.validarSessaoInexistente(idPauta);
        Pauta pautaEncontrada = pautaRepository.findPautaByAssunto
                (sessaoToCreate.getPauta().getAssunto());


        Sessao sessaoEncontrada = sessaoRepository.findByPautaId
                (pautaEncontrada.getId());
        Associado associadoEncontrado = associadoRepository.findAssociadoByCpf(
                sessaoRequestV1.getCpf());
        Long associadoNaSessao = associadoSessaoRepository.findByAssociadoIdAndSessaoId(
                associadoEncontrado.getId(),
                sessaoEncontrada.getId());


        valida.validarSessaoJaVotada(associadoNaSessao);
        valida.validarSessaoExpirada(sessaoEncontrada.getDuracao());


        sessaoEncontrada.addAssociado(associadoEncontrado);
        sessaoEncontrada.novoVoto(sessaoEncontrada,sessaoRequestV1);



        return SessaoMapperV1.sessaoToSessaoVotadaResponseV1(
                sessaoRepository.save(sessaoEncontrada)
        );
    }

    @Override
    public SessaoTotalVotosResponseV1 totalDeVotosDaSessao(SessaoParaSaberTotalVotosRequestV1 sessaoRequestV1) {


        Sessao sessaoToCreate = SessaoMapperV1.sessaoParaSaberTotalVotosRequestV1ToSessao(
                sessaoRequestV1
        );

        Long idPauta = pautaRepository.findIdByAssunto
                (sessaoToCreate.getPauta().getAssunto());
        valida.validarSessaoInexistente(idPauta);

        Pauta pautaEncontrada = pautaRepository.findPautaByAssunto
                (sessaoRequestV1.getAssuntoPauta());

        Sessao sessaoEncontrada = sessaoRepository.findByPautaId
                (pautaEncontrada.getId());



        return SessaoMapperV1.sessaoToSessaoTotalVotosResponseV1(
                sessaoEncontrada
        );
    }


}
