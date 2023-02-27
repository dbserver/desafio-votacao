package br.tec.db.votacao.service;

import br.tec.db.votacao.dto.VotoDTO;
import br.tec.db.votacao.enums.PautaStatusEnum;
import br.tec.db.votacao.enums.SessaoDeVotacaoStatusEnum;
import br.tec.db.votacao.enums.VotoStatusEnum;
import br.tec.db.votacao.model.Pauta;
import br.tec.db.votacao.model.SessaoDeVotacao;
import br.tec.db.votacao.model.Voto;
import br.tec.db.votacao.repository.VotoRepository;
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
class VotoServiceImplTest {

    @Mock
    private VotoRepository votoRepository;

    @InjectMocks
    private VotoServiceImpl votoService;

    @Disabled
    @Test
    void deveCriarVoto() {
        Pauta pauta = new Pauta();
        pauta.setStatus(PautaStatusEnum.AGUARDANDO_VOTACAO);
        SessaoDeVotacao sessaoDeVotacao = new SessaoDeVotacao();
        sessaoDeVotacao.setStatus(SessaoDeVotacaoStatusEnum.INICIADA);
        sessaoDeVotacao.setPauta(pauta);
        sessaoDeVotacao.setInicio(LocalDateTime.now());
        sessaoDeVotacao.setFim(LocalDateTime.now().plusMinutes(1));
        Voto voto = new Voto();
        voto.setStatus(VotoStatusEnum.SIM);
        voto.setSessaoDeVotacao(sessaoDeVotacao);
        when(votoRepository.save(voto)).thenReturn(voto);
        VotoDTO votoDTO = votoService.votar(new VotoDTO(voto));
        assertNotNull(votoDTO);
        assertEquals(voto.getStatus(), VotoStatusEnum.SIM);
    }

}