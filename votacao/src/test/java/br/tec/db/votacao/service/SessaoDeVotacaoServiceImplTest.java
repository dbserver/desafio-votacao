package br.tec.db.votacao.service;

import br.tec.db.votacao.dto.SessaoDeVotacaoDTO;
import br.tec.db.votacao.enums.PautaStatusEnum;
import br.tec.db.votacao.enums.SessaoDeVotacaoStatusEnum;
import br.tec.db.votacao.model.Pauta;
import br.tec.db.votacao.model.SessaoDeVotacao;
import br.tec.db.votacao.repository.SessaoDeVotacaoRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SessaoDeVotacaoServiceImplTest {

    @Mock
    private SessaoDeVotacaoRepository sessaoDeVotacaoRepository;

    @InjectMocks
    private SessaoDeVotacaoServiceImpl sessaoDeVotacaoService;

    @Disabled
    @Test
    void deveCriarSessaoDeVotacao() {
        Pauta pauta = new Pauta();
        pauta.setStatus(PautaStatusEnum.AGUARDANDO_VOTACAO);
        SessaoDeVotacao sessaoDeVotacao = new SessaoDeVotacao();
        sessaoDeVotacao.setStatus(SessaoDeVotacaoStatusEnum.INICIADA);
        sessaoDeVotacao.setPauta(pauta);
        sessaoDeVotacao.setInicio(LocalDateTime.now());
        sessaoDeVotacao.setFim(LocalDateTime.now().plusMinutes(1));
        when(sessaoDeVotacaoRepository.save(sessaoDeVotacao)).thenReturn(sessaoDeVotacao);
        SessaoDeVotacaoDTO sessaoDeVotacaoDTO = sessaoDeVotacaoService.criarSessaoDeVotacao(new SessaoDeVotacaoDTO(sessaoDeVotacao));
        assertNotNull(sessaoDeVotacaoDTO);
        assertEquals(sessaoDeVotacao.getStatus(), SessaoDeVotacaoStatusEnum.INICIADA);
    }

}