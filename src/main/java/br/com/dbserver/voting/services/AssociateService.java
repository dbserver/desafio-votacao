package br.com.dbserver.voting.services;

import br.com.dbserver.voting.dtos.AssociateDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AssociateService {
    void createAssociate(AssociateDTO associateDTO);

    Page<AssociateDTO> listAll(Pageable pageable);
}
