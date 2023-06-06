package com.example.votacaodesafio.controller;

import com.example.votacaodesafio.domain.entity.Assosciado;
import com.example.votacaodesafio.service.GenericService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("api/assosciado")
public class AssosciadoController extends GenericRestController<Assosciado, Long>{

    public AssosciadoController(GenericService<Assosciado, Long> service) {
        super(service);
    }

}
