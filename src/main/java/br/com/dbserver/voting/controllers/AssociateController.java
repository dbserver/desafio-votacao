package br.com.dbserver.voting.controllers;

import br.com.dbserver.voting.dtos.AssociateDTO;
import br.com.dbserver.voting.helpers.Constants;
import br.com.dbserver.voting.services.AssociateService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static br.com.dbserver.voting.helpers.Util.localDateTimeToString;
import static java.time.LocalDateTime.now;

@RestController
@RequestMapping(path = Constants.API_VERSION + "/associate")
public class AssociateController {

    private static final Logger logger = LoggerFactory.getLogger(AssociateController.class);

    private final AssociateService associateService;

    public AssociateController(AssociateService associateService) {
        this.associateService = associateService;
    }

    @PostMapping
    public ResponseEntity<Void> createAssociate(@RequestBody @Valid AssociateDTO associateDTO){
        logger.info("Criando um associado, inicio -  {}", localDateTimeToString(now()));
        associateService.createAssociate(associateDTO);
        logger.info("Criando um associado, fim -  {}", localDateTimeToString(now()));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<AssociateDTO>> listAll(Pageable pageable){
        logger.info("Listando associado, inicio -  {}", localDateTimeToString(now()));
        Page<AssociateDTO> associates = associateService.listAll(pageable);
        logger.info("Listando associados, fim -  {}", localDateTimeToString(now()));
        return new ResponseEntity<>(associates, HttpStatus.OK);
    }


}
