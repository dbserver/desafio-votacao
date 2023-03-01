package fixtures

import br.com.six2six.fixturefactory.Fixture
import br.com.six2six.fixturefactory.Rule
import br.com.six2six.fixturefactory.loader.TemplateLoader
import com.dbserver.desafio.votacao.endpoint.dto.SessaoDTO

import static com.dbserver.desafio.votacao.endpoint.dto.SessaoDTO.Fields.duracaoEmMunitos

class SessaoDtoTemplate implements TemplateLoader {

    public static final String SESSAO_DTO_VALIDA = "Sessao valida"

    @Override
    void load() {
        Fixture.of(SessaoDTO).addTemplate(SESSAO_DTO_VALIDA, new Rule() {
            {
                add(duracaoEmMunitos, 20)
            }
        })
    }
}
