package com.sicredi.votacao.controller;

import com.sicredi.votacao.entity.Pauta;
import com.sicredi.votacao.repository.PautaRepository;
import com.sicredi.votacao.service.PautaService;
import com.sicredi.votacao.service.VotoService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Fabio Moraes
 */
@RestController
@RequestMapping("/api/pautas")
public class PautaController {

    private final PautaService pautaService;

    @Autowired
    private VotoService votoService;

    @Autowired
    private PautaRepository pautaRepository;

    @GetMapping
    @RequestMapping("/")
    public ResponseEntity<List<Map<String, Object>>> listarPautas() {
        try {
            List<Pauta> pautas = pautaRepository.findAll();
            List<Map<String, Object>> responseList = new ArrayList<>();

            for (Pauta pauta : pautas) {
                Map<String, Object> response = new HashMap<>();
                response.put("id", pauta.getId());
                response.put("sessao", pauta.getSessaoVotacao());
                response.put("descricao", pauta.getDescricao());

                long votosSim = votoService.contarVotos(true, pauta.getId());
                long votosNao = votoService.contarVotos(false, pauta.getId());

                response.put("votosSim", votosSim);
                response.put("votosNao", votosNao);

                responseList.add(response);
            }

            return ResponseEntity.ok(responseList);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Autowired
    public PautaController(PautaService pautaService) {
        this.pautaService = pautaService;
    }

    @PostMapping("/criarpauta")
    public ResponseEntity<Map<String, Object>> criarPauta(@RequestBody Map<String, String> requestBody) {
        try {
            String descricao = requestBody.get("descricao");

            if (descricao != null && !descricao.isEmpty()) {
                Pauta novaPauta = new Pauta(descricao);
                return pautaService.salvarPauta(novaPauta);
            } else {
                Map<String, Object> response = new HashMap<>();
                response.put("mensagem", "A descrição da pauta é obrigatória.");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Map<String, Object> response = new HashMap<>();
            response.put("mensagem", "Erro interno ao processar a solicitação." + e.getCause() + " || " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PutMapping("/editpauta/{id}")
    public ResponseEntity<Map<String, Object>> atualizarPauta(@PathVariable Long id, @RequestBody Pauta pautaRequest) {
        Pauta pautaAtualizada = new Pauta(pautaRequest.getDescricao());
        pautaAtualizada.setId(id);
        return pautaService.atualizarPauta(pautaAtualizada);
    }

    @DeleteMapping("/apagarpauta/{id}")
    public ResponseEntity<Map<String, Object>> apagarPauta(@PathVariable Long id) {
        return pautaService.apagarPauta(id);
    }

    @PostMapping("/{pautaId}/abrir-sessao")
    public ResponseEntity<Map<String, Object>> abrirSessaoVotacao(
            @PathVariable Long pautaId,
            @RequestParam(required = false) Long minutes) {
        return pautaService.abrirSessaoVotacao(pautaId, minutes);
    }

}
