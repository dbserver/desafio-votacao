package fixtures

import br.com.six2six.fixturefactory.Fixture
import br.com.six2six.fixturefactory.Rule
import br.com.six2six.fixturefactory.loader.TemplateLoader
import com.dbserver.desafio.votacao.endpoint.dto.PautaIdDTO

import static com.dbserver.desafio.votacao.endpoint.dto.PautaIdDTO.Fields.idPauta

class PautaIdDtoTemplate implements TemplateLoader {

    public static final String PAUTA_ID_DTO_OBRA = "Pauta Id DTO de obra"
    public static final String PAUTA_ID_DTO_OBRA_VAZIA = "Pauta Id DTO de obra vazia"

    @Override
    void load() {
        Fixture.of(PautaIdDTO).addTemplate(PAUTA_ID_DTO_OBRA, new Rule() {
            {
                add(idPauta, 19900)
            }
        })
        Fixture.of(PautaIdDTO).addTemplate(PAUTA_ID_DTO_OBRA_VAZIA, new Rule() {})
    }
}
