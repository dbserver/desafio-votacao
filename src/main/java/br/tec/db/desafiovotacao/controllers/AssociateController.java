package br.tec.db.desafiovotacao.controllers;


import br.tec.db.desafiovotacao.business.AssociateBusiness;
import br.tec.db.desafiovotacao.dto.AssociateDTO;
import br.tec.db.desafiovotacao.entities.Associate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("associates")
public class AssociateController {

    @Autowired
    private AssociateBusiness business;

    @GetMapping("{id}")
    public ResponseEntity<AssociateDTO> getAssociate(@PathVariable Long id){
        return business.getAssociateById(id);
    }

    @GetMapping
    public ResponseEntity<List<AssociateDTO>> getAllAssociates(){
        return business.getAssociates();
    }

    @PostMapping
    public ResponseEntity<AssociateDTO> create(@RequestBody AssociateDTO associateDTO){
        return business.createOrUpdate(associateDTO);
    }

    @PutMapping
    public ResponseEntity<AssociateDTO> update(@RequestBody AssociateDTO associateDTO){
        return business.createOrUpdate(associateDTO);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id){
        business.delete(id);
    }
}
