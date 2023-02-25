package fixtures

import br.com.six2six.fixturefactory.Fixture
import br.com.six2six.fixturefactory.Rule
import br.com.six2six.fixturefactory.loader.TemplateLoader
import com.dbserver.desafio.votacao.endpoint.dto.PautaDTO

import static com.dbserver.desafio.votacao.endpoint.dto.PautaDTO.Fields.nomePauta
import static com.dbserver.desafio.votacao.endpoint.dto.PautaDTO.Fields.descricaoPauta

class PautaDtoTemplate implements TemplateLoader {

    public static final String PAUTA_DTO_OBRA = "Pauta DTO de obra"
    public static final String PAUTA_DTO_OBRA_CADASTRADA = "Pauta DTO de obra cadastrada"
    public static final String PAUTA_DTO_VAZIO = "Pauta DTO vazio"

    @Override
    void load() {
        Fixture.of(PautaDTO).addTemplate(PAUTA_DTO_OBRA, new Rule() {
            {
                add(nomePauta, "Infiltração na caixa de agua")
                add(descricaoPauta, "A caixa de agua esta com infltração e precisade uma obra emergencial")
            }
        })

        Fixture.of(PautaDTO).addTemplate(PAUTA_DTO_OBRA_CADASTRADA, new Rule() {
            {
                add(nomePauta, "Infiltração na caixa de agua")
                add(descricaoPauta, "A caixa de agua esta com infltração e precisade uma obra emergencial")
            }
        })

        Fixture.of(PautaDTO).addTemplate(PAUTA_DTO_VAZIO, new Rule() {})
    }
}
