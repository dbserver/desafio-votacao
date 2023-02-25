package fixtures

import br.com.six2six.fixturefactory.Fixture
import br.com.six2six.fixturefactory.Rule
import br.com.six2six.fixturefactory.loader.TemplateLoader
import com.dbserver.desafio.votacao.usecase.domain.Pauta
import com.dbserver.desafio.votacao.usecase.domain.Sessao

import static com.dbserver.desafio.votacao.usecase.domain.Pauta.Fields.nome
import static com.dbserver.desafio.votacao.usecase.domain.Pauta.Fields.descricao
import static com.dbserver.desafio.votacao.usecase.domain.Pauta.Fields.sessao
import static fixtures.SessaoTemplate.SESSAO_VALIDA

class PautaTemplate implements TemplateLoader {

    public static final String PAUTA_OBRA = "Pauta de obra"
    public static final String PAUTA_OBRA_COM_SESSAO = "Pauta de obra com sessao valida"

    @Override
    void load() {
        Fixture.of(Pauta).addTemplate(PAUTA_OBRA, new Rule() {
            {
                add(nome, "Infiltração na caixa de agua")
                add(descricao, "A caixa de agua esta com infltração e precisade uma obra emergencial")
            }
        })

        Fixture.of(Pauta).addTemplate(PAUTA_OBRA_COM_SESSAO, new Rule() {
            {
                add(nome, "Infiltração na caixa de agua com sessao")
                add(descricao, "A caixa de agua esta com infltração e precisade uma obra emergencial com sessao")
                add(sessao,one(Sessao,SESSAO_VALIDA))
            }
        })
    }
}
