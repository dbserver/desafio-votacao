package br.tec.db.desafiovotacao.business;

import br.tec.db.desafiovotacao.dto.AssociateDTO;
import br.tec.db.desafiovotacao.entities.Associate;
import br.tec.db.desafiovotacao.services.AssociateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AssociateBusiness {

    @Autowired
    private AssociateService associateService;

    public ResponseEntity<AssociateDTO> getAssociateById(Long id) {
        return ResponseEntity.ok(null);
    }

    public ResponseEntity<List<AssociateDTO>> getAssociates() {
        return ResponseEntity.ok(null);
    }

    public ResponseEntity<AssociateDTO> createOrUpdate(AssociateDTO associateDTO) {
        return ResponseEntity.ok(null);
    }

    public void delete(Long id) {
    }
}
