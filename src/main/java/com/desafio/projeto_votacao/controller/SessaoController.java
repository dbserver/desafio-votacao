package com.desafio.projeto_votacao.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/public/v1/sessoes")
@Tag(name = "Sessões", description = "API para gerenciar sessões de votação")
public class SessaoController {

}
