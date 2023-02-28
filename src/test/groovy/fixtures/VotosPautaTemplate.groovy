package fixtures

import br.com.six2six.fixturefactory.Fixture
import br.com.six2six.fixturefactory.Rule
import br.com.six2six.fixturefactory.loader.TemplateLoader
import com.dbserver.desafio.valida.cpf.usecase.domain.Pauta
import com.dbserver.desafio.valida.cpf.usecase.domain.VotosPauta

import static com.dbserver.desafio.valida.cpf.endpoint.dto.VotosPautaDTO.Fields.totalVotosNao
import static com.dbserver.desafio.valida.cpf.endpoint.dto.VotosPautaDTO.Fields.totalVotosSim
import static com.dbserver.desafio.valida.cpf.usecase.domain.Voto.Fields.pauta

class VotosPautaTemplate implements TemplateLoader {

    public static final String VOTOS_PAUTA_PAUTA = "Votos da pauta de obra"
    public static final String VOTOS_PAUTA_PAUTA_COM_SESSAO = "Votos da pauta de obra com sessao"
    public static final String VOTOS_PAUTA_PAUTA_VAZIO = "Votos da pauta de obra vazio"

    @Override
    void load() {
        Fixture.of(VotosPauta).addTemplate(VOTOS_PAUTA_PAUTA, new Rule() {
            {
                add(pauta, one(Pauta, PautaTemplate.PAUTA_OBRA_CADASTRADA))
                add(totalVotosNao, 11)
                add(totalVotosSim, 10)
            }
        })

        Fixture.of(VotosPauta).addTemplate(VOTOS_PAUTA_PAUTA_COM_SESSAO, new Rule() {
            {
                add(pauta, one(Pauta, PautaTemplate.PAUTA_OBRA_COM_SESSAO))
                add(totalVotosNao, 1)
                add(totalVotosSim, 2)
            }
        })

        Fixture.of(VotosPauta).addTemplate(VOTOS_PAUTA_PAUTA_VAZIO, new Rule() {})
    }
}
