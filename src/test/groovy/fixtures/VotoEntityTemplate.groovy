package fixtures

import br.com.six2six.fixturefactory.Fixture
import br.com.six2six.fixturefactory.Rule
import br.com.six2six.fixturefactory.loader.TemplateLoader
import com.dbserver.desafio.votacao.repository.entity.PautaEntity
import com.dbserver.desafio.votacao.repository.entity.VotoEntity
import com.dbserver.desafio.votacao.usecase.domain.Pauta
import com.dbserver.desafio.votacao.usecase.enuns.VotoEnum

import static com.dbserver.desafio.votacao.repository.entity.VotoEntity.Fields.cpfAssociado
import static com.dbserver.desafio.votacao.repository.entity.VotoEntity.Fields.pauta
import static com.dbserver.desafio.votacao.repository.entity.VotoEntity.Fields.voto
import static com.dbserver.desafio.votacao.repository.entity.VotoEntity.Fields.idVoto

class VotoEntityTemplate implements TemplateLoader {

    public static final String VOTO_ENTITY_PAUTA = "Voto Entity da Pauta de obra"
    public static final String VOTO_ENTITY_PAUTA_SALVA = "Voto Entity da Pauta de obra Salva"

    @Override
    void load() {
        Fixture.of(VotoEntity).addTemplate(VOTO_ENTITY_PAUTA, new Rule() {
            {
                add(cpfAssociado, "999.999.999-99")
                add(pauta, one(PautaEntity,PautaEntityTemplate.PAUTA_ENTITY_OBRA_COM_SESSAO))
                add(voto,VotoEnum.SIM)
            }
        })


        Fixture.of(VotoEntity).addTemplate(VOTO_ENTITY_PAUTA_SALVA, new Rule() {
            {
                add(cpfAssociado, "999.999.999-99")
                add(pauta, one(PautaEntity,PautaEntityTemplate.PAUTA_ENTITY_OBRA_COM_SESSAO))
                add(voto,VotoEnum.SIM)
                add(idVoto,29900)
            }
        })
    }
}
