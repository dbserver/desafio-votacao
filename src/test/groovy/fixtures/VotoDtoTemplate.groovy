package fixtures

import br.com.six2six.fixturefactory.Fixture
import br.com.six2six.fixturefactory.Rule
import br.com.six2six.fixturefactory.loader.TemplateLoader
import com.dbserver.desafio.votacao.endpoint.dto.VotoDTO
import com.dbserver.desafio.votacao.usecase.enuns.VotoEnum

import static com.dbserver.desafio.votacao.endpoint.dto.VotoDTO.Fields.cpfAssociado
import static com.dbserver.desafio.votacao.endpoint.dto.VotoDTO.Fields.idPauta
import static com.dbserver.desafio.votacao.endpoint.dto.VotoDTO.Fields.voto

class VotoDtoTemplate implements TemplateLoader {

    public static final String VOTO_DTO_PAUTA = "Voto DTO de obra"
    public static final String VOTO_DTO_PAUTA_VAZIO = "Voto DTO de obra vazio"

    @Override
    void load() {
        Fixture.of(VotoDTO).addTemplate(VOTO_DTO_PAUTA, new Rule() {
            {
                add(cpfAssociado, "999.999.999-99")
                add(idPauta, 19900)
                add(voto,VotoEnum.SIM)
            }
        })

        Fixture.of(VotoDTO).addTemplate(VOTO_DTO_PAUTA_VAZIO, new Rule() {})
    }
}
