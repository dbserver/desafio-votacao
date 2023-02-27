package fixtures

import br.com.six2six.fixturefactory.Fixture
import br.com.six2six.fixturefactory.Rule
import br.com.six2six.fixturefactory.loader.TemplateLoader
import com.dbserver.desafio.votacao.usecase.domain.Voto
import com.dbserver.desafio.votacao.usecase.domain.Pauta
import com.dbserver.desafio.votacao.usecase.enuns.VotoEnum

import static com.dbserver.desafio.votacao.usecase.domain.Voto.Fields.cpfAssociado
import static com.dbserver.desafio.votacao.usecase.domain.Voto.Fields.pauta
import static com.dbserver.desafio.votacao.usecase.domain.Voto.Fields.voto

class VotoTemplate implements TemplateLoader {

    public static final String VOTO_PAUTA = "Voto da Pauta de obra"
    public static final String VOTO_PAUTA_COM_SESSAO = "Voto da Pauta de obra com sessao"
    public static final String VOTO_COM_ID_PAUTA = "Voto da Pauta com id da pauta"
    public static final String VOTO_PAUTA_VAZIO = "Voto da Pauta de obra Vazio"

    @Override
    void load() {
        Fixture.of(Voto).addTemplate(VOTO_PAUTA, new Rule() {
            {
                add(cpfAssociado, "999.999.999-99")
                add(pauta, one(Pauta,PautaTemplate.PAUTA_OBRA_CADASTRADA))
                add(voto,VotoEnum.SIM)
            }
        })

        Fixture.of(Voto).addTemplate(VOTO_PAUTA_COM_SESSAO, new Rule() {
            {
                add(cpfAssociado, "999.999.999-99")
                add(pauta, one(Pauta,PautaTemplate.PAUTA_OBRA_COM_SESSAO))
                add(voto,VotoEnum.SIM)
            }
        })


        Fixture.of(Voto).addTemplate(VOTO_COM_ID_PAUTA, new Rule() {
            {
                add(cpfAssociado, "999.999.999-99")
                add(pauta, one(Pauta,PautaTemplate.PAUTA_OBRA_COM_ID))
                add(voto,VotoEnum.SIM)
            }
        })

        Fixture.of(Voto).addTemplate(VOTO_PAUTA_VAZIO, new Rule() {
            {
                add(pauta, one(Pauta,PautaTemplate.PAUTA_VAZIO))
            }
        })
    }
}
