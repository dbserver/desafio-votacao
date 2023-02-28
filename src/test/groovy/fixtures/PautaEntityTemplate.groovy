package fixtures

import br.com.six2six.fixturefactory.Fixture
import br.com.six2six.fixturefactory.Rule
import br.com.six2six.fixturefactory.loader.TemplateLoader
import com.dbserver.desafio.votacao.repository.entity.PautaEntity
import com.dbserver.desafio.votacao.repository.entity.SessaoEntity

import static com.dbserver.desafio.votacao.repository.entity.PautaEntity.Fields.sessao
import static com.dbserver.desafio.votacao.usecase.domain.Pauta.Fields.descricao
import static com.dbserver.desafio.votacao.usecase.domain.Pauta.Fields.nome
import static com.dbserver.desafio.votacao.usecase.domain.Pauta.Fields.idPauta
import static fixtures.SessaoEntityTemplate.SESSAO_ENTITY_VALIDA
import static fixtures.SessaoEntityTemplate.SESSAO_ENTITY_VALIDA_SEM_ID

class PautaEntityTemplate implements TemplateLoader {

    public static final String PAUTA_ENTITY_OBRA = "Pauta Entity de obra"
    public static final String PAUTA_ENTITY_OBRA_COM_SESSAO = "Pauta Entity de obra com sessao"
    public static final String PAUTA_ENTITY_OBRA_SEM_SESSAO = "Pauta Entity de obra sem sessao"
    public static final String PAUTA_ENTITY_OBRA_COM_SESSAO_SEM_ID = "Pauta Entity de obra com sessao sem id"

    @Override
    void load() {
        Fixture.of(PautaEntity).addTemplate(PAUTA_ENTITY_OBRA, new Rule() {
            {
                add(nome, "Infiltração na caixa de agua")
                add(descricao, "A caixa de agua esta com infltração e precisade uma obra emergencial")
            }
        })

        Fixture.of(PautaEntity).addTemplate(PAUTA_ENTITY_OBRA_COM_SESSAO, new Rule() {
            {
                add(idPauta, 19900)
                add(nome, "Infiltração na caixa de agua com sessao")
                add(descricao, "A caixa de agua esta com infltração e precisade uma obra emergencial com sessao")
                add(sessao, one(SessaoEntity,SESSAO_ENTITY_VALIDA))
            }
        })

        Fixture.of(PautaEntity).addTemplate(PAUTA_ENTITY_OBRA_SEM_SESSAO, new Rule() {
            {
                add(idPauta, 19900)
                add(nome, "Infiltração na caixa de agua")
                add(descricao, "A caixa de agua esta com infltração e precisade uma obra emergencial")
            }
        })

        Fixture.of(PautaEntity).addTemplate(PAUTA_ENTITY_OBRA_COM_SESSAO_SEM_ID, new Rule() {
            {
                add(idPauta, 19900)
                add(nome, "Infiltração na caixa de agua")
                add(descricao, "A caixa de agua esta com infltração e precisade uma obra emergencial")
                add(sessao, one(SessaoEntity,SESSAO_ENTITY_VALIDA_SEM_ID))
            }
        })
    }
}
