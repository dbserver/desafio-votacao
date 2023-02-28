package fixtures

import br.com.six2six.fixturefactory.Fixture
import br.com.six2six.fixturefactory.Rule
import br.com.six2six.fixturefactory.loader.TemplateLoader
import com.dbserver.desafio.valida.cpf.endpoint.dto.PautaDuracaoDTO

import static com.dbserver.desafio.valida.cpf.endpoint.dto.PautaDuracaoDTO.Fields.idPauta
import static com.dbserver.desafio.valida.cpf.endpoint.dto.PautaDuracaoDTO.Fields.duracaoSessao

class PautaDuracaoDtoTemplate implements TemplateLoader {

    public static final String PAUTA_DURACAO_DTO_OBRA = "Pauta DTO de obra"
    public static final String PAUTA_DURACAO_DTO_OBRA_SALVA = "Pauta DTO de obra Salva"
    public static final String PAUTA_DURACAO_DTO_VAZIO = "Pauta DTO vazio"

    @Override
    void load() {
        Fixture.of(PautaDuracaoDTO).addTemplate(PAUTA_DURACAO_DTO_OBRA, new Rule() {
            {
                add(idPauta, 19900)
                add(duracaoSessao, 20)
            }
        })

        Fixture.of(PautaDuracaoDTO).addTemplate(PAUTA_DURACAO_DTO_OBRA_SALVA, new Rule() {
            {
                add(idPauta, 19900)
                add(duracaoSessao, 20)
            }
        })

        Fixture.of(PautaDuracaoDTO).addTemplate(PAUTA_DURACAO_DTO_VAZIO, new Rule() {})
    }
}
