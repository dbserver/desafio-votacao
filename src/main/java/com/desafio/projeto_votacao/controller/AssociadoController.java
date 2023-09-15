package com.desafio.projeto_votacao.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/public/v1/associados")
@Tag(name = "Associados", description = "API para gerenciar associados")
public class AssociadoController {



}