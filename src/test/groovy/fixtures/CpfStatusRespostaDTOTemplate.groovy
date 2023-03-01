package fixtures

import br.com.six2six.fixturefactory.Fixture
import br.com.six2six.fixturefactory.Rule
import br.com.six2six.fixturefactory.loader.TemplateLoader
import com.dbserver.desafio.votacao.gateway.dto.CpfStatusRespostaDTO
import com.dbserver.desafio.votacao.usecase.domain.Pauta
import com.dbserver.desafio.votacao.usecase.enuns.VotoEnum

import static com.dbserver.desafio.votacao.gateway.dto.CpfStatusRespostaDTO.Fields.statusCpf

class CpfStatusRespostaDTOTemplate implements TemplateLoader {

    public static final String CPF_STATUS_DTO = "Status do CPF do Associado"

    @Override
    void load() {
        Fixture.of(CpfStatusRespostaDTO).addTemplate(CPF_STATUS_DTO, new Rule() {
            {
                add(statusCpf, "999.999.999-99")
            }
        })
    }
}
