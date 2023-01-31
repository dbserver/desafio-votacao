package br.com.occ.desafiovotacao.utils;

import br.com.occ.desafiovotacao.v1.dto.AssociadoDto;
import br.com.occ.desafiovotacao.v1.dto.AssociadoStatusDto;
import br.com.occ.desafiovotacao.v1.enums.CpfStatusEnum;
import br.com.occ.desafiovotacao.v1.enums.VotoEnum;
import br.com.occ.desafiovotacao.v1.model.Associado;
import br.com.occ.desafiovotacao.v1.model.Pauta;
import br.com.occ.desafiovotacao.v1.model.Sessao;
import br.com.occ.desafiovotacao.v1.model.Voto;

import java.time.LocalDateTime;
import java.util.Optional;

public class EntityUtils {
    public static final Long ID = 1L;
    public static final String NOME_ASSOCIADO = "Bernardo Noah Sebastião Sales";
    public static final String CPF_ASSOCIADO = "18722519009";
    public static final boolean ATIVO = true;
    public static final String DESCRICAO_PAUTA = "Pauta votação 1";

    public static final Long ID_PAUTA = 10L;
    public static final LocalDateTime DATA_INICIO = LocalDateTime.now();
    public static final LocalDateTime DATA_FIM_UM_MINUTO = LocalDateTime.now().plusMinutes(1);
    public static final LocalDateTime DATA_FIM_DEZ_MINUTOS = LocalDateTime.now().plusMinutes(10);




    public static Associado criarAssociado(boolean ativo) {
        return new Associado(ID, NOME_ASSOCIADO, CPF_ASSOCIADO, ativo);
    }

    public static AssociadoDto criarAssociadoDto(boolean ativo) {
        return new AssociadoDto(ID, NOME_ASSOCIADO, CPF_ASSOCIADO, ativo);
    }

    public static Optional<Associado> criarAssociadoOptional(boolean ativo) {
        return Optional.of(new Associado(ID, NOME_ASSOCIADO, CPF_ASSOCIADO, ativo));
    }

    public static Optional<AssociadoDto> criarAssociadoDtoOptional(boolean ativo) {
        return Optional.of(new AssociadoDto(ID, NOME_ASSOCIADO, CPF_ASSOCIADO, ativo));
    }

    public static AssociadoStatusDto criarAssociadoStatusDto() {
        return new AssociadoStatusDto(CpfStatusEnum.ABLE_TO_VOTE);
    }

    public static Pauta criarPauta() {
        return new Pauta(ID, DESCRICAO_PAUTA);
    }

    public static Pauta criarPautaComSessao() {
        return new Pauta(ID, DESCRICAO_PAUTA, criarSessao());
    }

    public static Optional<Pauta> criarPautaOptional() {
        return Optional.of(new Pauta(ID, DESCRICAO_PAUTA));
    }

    public static Sessao criarSessao() {
        return new Sessao(ID,DATA_INICIO, DATA_FIM_DEZ_MINUTOS);
    }

    public static Optional<Sessao> criarSessaoOptional() {
        return Optional.of(new Sessao(ID,DATA_INICIO, DATA_FIM_DEZ_MINUTOS));
    }

    public static Sessao criarSessaoDataFimNull() {
        return new Sessao(ID,DATA_INICIO);
    }

    public static Sessao criarSessaoUmMinuto() {
        return new Sessao(ID,DATA_INICIO, DATA_FIM_UM_MINUTO);
    }

    public static Voto criarVoto() {
        return new Voto(ID,criarPauta(),criarAssociado(true), VotoEnum.SIM);
    }

    public static Optional<Voto> criarVotoOptional() {
        return Optional.of(new Voto(ID,criarPauta(),criarAssociado(true), VotoEnum.SIM));
    }
}
