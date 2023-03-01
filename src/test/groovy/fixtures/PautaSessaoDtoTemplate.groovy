package fixtures

import br.com.six2six.fixturefactory.Fixture
import br.com.six2six.fixturefactory.Rule
import br.com.six2six.fixturefactory.loader.TemplateLoader
import com.dbserver.desafio.votacao.endpoint.dto.PautaSessaoDTO
import com.dbserver.desafio.votacao.endpoint.dto.SessaoDTO

import static com.dbserver.desafio.votacao.endpoint.dto.PautaSessaoDTO.Fields.*

class PautaSessaoDtoTemplate implements TemplateLoader {

    public static final String PAUTA_SESSAO_DTO_OBRA = "Pauta Sessao DTO de obra"
    public static final String PAUTA_SESSAO_DTO_OBRA_SALVA = "Pauta Sessao DTO de obra salva"
    public static final String PAUTA_SESSAO_DTO_VAZIO = "Pauta Sessao DTO vazio"

    @Override
    void load() {
        Fixture.of(PautaSessaoDTO).addTemplate(PAUTA_SESSAO_DTO_OBRA, new Rule() {
            {
                add(nomePauta, "Infiltração na caixa de agua")
                add(descricaoPauta, "A caixa de agua esta com infltração e precisade uma obra emergencial")
                add(sessao, one(SessaoDTO, SessaoDtoTemplate.SESSAO_DTO_VALIDA))
            }
        })

        Fixture.of(PautaSessaoDTO).addTemplate(PAUTA_SESSAO_DTO_OBRA_SALVA, new Rule() {
            {
                add(idPauta, "19900")
                add(nomePauta, "Infiltração na caixa de agua com sessao")
                add(descricaoPauta, "A caixa de agua esta com infltração e precisade uma obra emergencial com sessao")
                add(sessao, one(SessaoDTO, SessaoDtoTemplate.SESSAO_DTO_VALIDA))
            }
        })

        Fixture.of(PautaSessaoDTO).addTemplate(PAUTA_SESSAO_DTO_VAZIO, new Rule() {})
    }
}
