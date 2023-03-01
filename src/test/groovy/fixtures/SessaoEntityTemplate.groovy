package fixtures

import br.com.six2six.fixturefactory.Fixture
import br.com.six2six.fixturefactory.Rule
import br.com.six2six.fixturefactory.loader.TemplateLoader
import com.dbserver.desafio.votacao.repository.entity.SessaoEntity

import java.time.LocalDateTime
import java.time.Month

import static com.dbserver.desafio.votacao.repository.entity.SessaoEntity.Fields.inicio
import static com.dbserver.desafio.votacao.repository.entity.SessaoEntity.Fields.duracao

class SessaoEntityTemplate implements TemplateLoader {

    public static final String SESSAO_ENTITY_VALIDA = "Sessao Entity valida"
    public static final String SESSAO_ENTITY_VALIDA_SEM_ID = "Sessao Entity valida antes de ser salva"

    @Override
    void load() {
        Fixture.of(SessaoEntity).addTemplate(SESSAO_ENTITY_VALIDA, new Rule() {
            {
                add(inicio, LocalDateTime.of(2023, Month.FEBRUARY,25,19,30,40))
                add(duracao, 20)
            }
        })

        Fixture.of(SessaoEntity).addTemplate(SESSAO_ENTITY_VALIDA_SEM_ID, new Rule() {
            {
                add(inicio, LocalDateTime.of(2023, Month.FEBRUARY,25,19,30))
                add(duracao, 20)
            }
        })
    }
}
