package fixtures

import br.com.six2six.fixturefactory.Fixture
import br.com.six2six.fixturefactory.Rule
import br.com.six2six.fixturefactory.loader.TemplateLoader
import com.dbserver.desafio.valida.cpf.usecase.domain.Sessao

import java.time.LocalDateTime
import java.time.Month

import static com.dbserver.desafio.valida.cpf.usecase.domain.Sessao.Fields.duracao
import static com.dbserver.desafio.valida.cpf.usecase.domain.Sessao.Fields.inicio

class SessaoTemplate implements TemplateLoader {

    public static final String SESSAO_VALIDA = "Sessao valida"
    public static final String SESSAO_DEFAULT_VALIDA = "Sessao default com valor de 1 minuto"

    @Override
    void load() {
        Fixture.of(Sessao).addTemplate(SESSAO_VALIDA, new Rule() {
            {
                add(inicio, LocalDateTime.of(2023, Month.FEBRUARY,25,19,30,40))
                add(duracao, 20)
            }
        })

        Fixture.of(Sessao).addTemplate(SESSAO_DEFAULT_VALIDA, new Rule() {
            {
                add(inicio, LocalDateTime.of(2023, Month.FEBRUARY,25,19,30,40))
                add(duracao, 1)
            }
        })
    }
}
