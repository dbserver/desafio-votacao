package br.com.dbserver.voting.converters.associate;

import br.com.dbserver.voting.converters.Mapper;
import br.com.dbserver.voting.dtos.AssociateDTO;
import br.com.dbserver.voting.helpers.Util;
import br.com.dbserver.voting.models.Associate;
import org.springframework.stereotype.Component;

@Component
public class AssociateDtoToAssociateMapper implements Mapper<AssociateDTO, Associate> {
    @Override
    public Associate map(AssociateDTO associateDTO, Associate associate) {
        String cpfWithoutCharacter = Util.removeNonNumericCharacterFromCpf(associateDTO.cpf());

        return new Associate(null, associateDTO.name(), cpfWithoutCharacter);
    }
}
