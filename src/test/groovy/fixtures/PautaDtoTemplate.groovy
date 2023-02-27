package fixtures

import br.com.six2six.fixturefactory.Fixture
import br.com.six2six.fixturefactory.Rule
import br.com.six2six.fixturefactory.loader.TemplateLoader
import com.dbserver.desafio.votacao.endpoint.dto.PautaDTO

import static com.dbserver.desafio.votacao.endpoint.dto.PautaDTO.Fields.idPauta
import static com.dbserver.desafio.votacao.usecase.domain.Pauta.Fields.descricao
import static com.dbserver.desafio.votacao.usecase.domain.Pauta.Fields.nome

class PautaDtoTemplate implements TemplateLoader {

    public static final String PAUTA_DTO_OBRA = "Pauta DTO de obra"
    public static final String PAUTA_DTO_OBRA_CADASTRADA = "Pauta DTO de obra cadastrada"
    public static final String PAUTA_DTO_OBRA_SALVA = "Pauta DTO de obra com idPauta"
    public static final String PAUTA_DTO_OBRA_SOM_SESSAO = "Pauta DTO de obra com sessao"
    public static final String PAUTA_DTO_VAZIO = "Pauta DTO vazio"

    @Override
    void load() {
        Fixture.of(PautaDTO).addTemplate(PAUTA_DTO_OBRA, new Rule() {
            {
                add(nome, "Infiltração na caixa de agua")
                add(descricao, "A caixa de agua esta com infltração e precisade uma obra emergencial")
            }
        })

        Fixture.of(PautaDTO).addTemplate(PAUTA_DTO_OBRA_CADASTRADA, new Rule() {
            {
                add(nome, "Infiltração na caixa de agua")
                add(descricao, "A caixa de agua esta com infltração e precisade uma obra emergencial")
            }
        })

        Fixture.of(PautaDTO).addTemplate(PAUTA_DTO_OBRA_SALVA, new Rule() {
            {
                add(idPauta, 19900)
                add(nome, "Infiltração na caixa de agua")
                add(descricao, "A caixa de agua esta com infltração e precisade uma obra emergencial")
            }
        })

        Fixture.of(PautaDTO).addTemplate(PAUTA_DTO_OBRA_SOM_SESSAO, new Rule() {
            {
                add(idPauta, 19900)
                add(nome, "Infiltração na caixa de agua com sessao")
                add(descricao, "A caixa de agua esta com infltração e precisade uma obra emergencial com sessao")
            }
        })

        Fixture.of(PautaDTO).addTemplate(PAUTA_DTO_VAZIO, new Rule() {})
    }
}
