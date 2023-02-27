package br.tec.db.votacao.service;

import br.tec.db.votacao.dto.PautaDTO;
import br.tec.db.votacao.enums.AssembleiaStatusEnum;
import br.tec.db.votacao.enums.PautaStatusEnum;
import br.tec.db.votacao.model.Assembleia;
import br.tec.db.votacao.model.Pauta;
import br.tec.db.votacao.repository.PautaRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PautaServiceImplTest {

    @Mock
    private PautaRepository pautaRepository;

    @InjectMocks
    private PautaServiceImpl pautaService;

    @Disabled
    @Test
    void deveCriarPauta() {
        Assembleia assembleia = new Assembleia();
        assembleia.setStatus(AssembleiaStatusEnum.INICIADA);
        Pauta pauta = new Pauta();
        pauta.setStatus(PautaStatusEnum.AGUARDANDO_VOTACAO);
        when(pautaRepository.save(pauta)).thenReturn(pauta);
        PautaDTO pautaDTO = pautaService.criarPauta(new PautaDTO(pauta));
        assertNotNull(pautaDTO);
        assertEquals(pauta.getStatus(), PautaStatusEnum.AGUARDANDO_VOTACAO);
    }

}