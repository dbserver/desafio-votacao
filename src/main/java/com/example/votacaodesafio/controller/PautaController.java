package com.example.votacaodesafio.controller;


import com.example.votacaodesafio.domain.entity.Pauta;
import com.example.votacaodesafio.service.GenericService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("api/pauta")
public class PautaController extends GenericRestController<Pauta, Long>{

    public PautaController(GenericService<Pauta, Long> service) {
        super(service);
    }

}
