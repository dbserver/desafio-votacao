package fixtures

import br.com.six2six.fixturefactory.Fixture
import br.com.six2six.fixturefactory.Rule
import br.com.six2six.fixturefactory.loader.TemplateLoader
import com.dbserver.desafio.valida.cpf.endpoint.dto.PautaDTO
import com.dbserver.desafio.valida.cpf.endpoint.dto.VotosPautaDTO

import static com.dbserver.desafio.valida.cpf.endpoint.dto.VotosPautaDTO.Fields.*

class VotosPautaDtoTemplate implements TemplateLoader {

    public static final String VOTOS_PAUTA_DTO_PAUTA = "Votos da pauta DTO de obra"
    public static final String VOTOS_PAUTA_DTO_PAUTA_COM_SESSAO = "Votos da pauta DTO de obra com sessao"
    public static final String VOTOS_PAUTA_DTO_PAUTA_VAZIO = "Votos da pauta DTO de obra vazio"

    @Override
    void load() {
        Fixture.of(VotosPautaDTO).addTemplate(VOTOS_PAUTA_DTO_PAUTA, new Rule() {
            {
                add(pauta, one(PautaDTO,PautaDtoTemplate.PAUTA_DTO_OBRA_SALVA))
                add(totalVotosNao, 11)
                add(totalVotosSim, 10)
            }
        })

        Fixture.of(VotosPautaDTO).addTemplate(VOTOS_PAUTA_DTO_PAUTA_COM_SESSAO, new Rule() {
            {
                add(pauta, one(PautaDTO,PautaDtoTemplate.PAUTA_DTO_OBRA_SOM_SESSAO))
                add(totalVotosNao, 1)
                add(totalVotosSim, 2)
            }
        })


        Fixture.of(VotosPautaDTO).addTemplate(VOTOS_PAUTA_DTO_PAUTA_COM_SESSAO, new Rule() {
            {
                add(pauta, one(PautaDTO,PautaDtoTemplate.PAUTA_DTO_OBRA_SOM_SESSAO))
                add(totalVotosNao, 1)
                add(totalVotosSim, 2)
            }
        })

        Fixture.of(VotosPautaDTO).addTemplate(VOTOS_PAUTA_DTO_PAUTA_VAZIO, new Rule() {})
    }
}
