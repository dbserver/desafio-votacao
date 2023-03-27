
package com.dbserver.desafiovotacao.controller;

import com.dbserver.desafiovotacao.dto.AssembleiaRequest;
import com.dbserver.desafiovotacao.dto.AssembleiaResponse;
import com.dbserver.desafiovotacao.dto.ClienteRequest;
import com.dbserver.desafiovotacao.model.Assembleia;
import com.dbserver.desafiovotacao.model.Pauta;
import com.dbserver.desafiovotacao.service.AssembleiaServiceImplementacao;
import jakarta.validation.Valid;

import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class AssembleiaController {
    private AssembleiaServiceImplementacao assembleiaService;
    
    @Autowired
    public AssembleiaController(AssembleiaServiceImplementacao assembleiaService) {
        this.assembleiaService = assembleiaService;
    }
    
    @GetMapping("/{id}")
	public ResponseEntity<AssembleiaResponse> procuraAssembleia(@PathVariable UUID id) {

		Optional<Assembleia> assembleiaAtual;
        AssembleiaResponse resposta;
		assembleiaAtual = assembleiaService.encontrarAssembleiaPorID(id);

		if (assembleiaAtual.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else{
            resposta = new AssembleiaResponse(assembleiaAtual.get());
        }

		return new ResponseEntity<>(resposta, HttpStatus.OK);
	}
        
        @GetMapping("/numeropautas/{id}")
    public ResponseEntity<Integer> totalPautas(@PathVariable UUID id) {
        return new ResponseEntity<>(this.assembleiaService.totalPautas(id), HttpStatus.OK);
    }

    @GetMapping("/tudo")
    public ResponseEntity<Iterable<Assembleia>> mostrarTudo() {
        return new ResponseEntity<>(this.assembleiaService.mostraTudo(), HttpStatus.OK);
    }

    @GetMapping("/todas")
    public ResponseEntity<Iterable<AssembleiaResponse>> mostrarAssembleias() {
        return new ResponseEntity<>(this.assembleiaService.mostrarAssembleias(), HttpStatus.OK);
    }

    @GetMapping("/mostrarpautas/{id}")
    public ResponseEntity<Iterable<Pauta>> mostrarPautas(@PathVariable UUID id) {
        return new ResponseEntity<>(this.assembleiaService.mostraPautas(id), HttpStatus.OK);
    }
    @GetMapping("/finalizarassembleia/{id}")
    public ResponseEntity<Assembleia> finalizaAssembleia(@PathVariable UUID id) {
        return new ResponseEntity<>(this.assembleiaService.finalizarAssembleia(id), HttpStatus.OK);
    }

    @PostMapping("/adicionapauta/{id}")
    public ResponseEntity<Assembleia> adicionaPautaNaAssembleia(@PathVariable UUID id, @RequestBody ClienteRequest clienteRequest) {
        return new ResponseEntity<>(this.assembleiaService.adicionarPauta(id, clienteRequest), HttpStatus.OK);
    }
    
    @PostMapping
    public ResponseEntity<Assembleia> criarAssembleia(@Valid @RequestBody AssembleiaRequest assembleiaRequest, BindingResult bindingResult) {
        if(assembleiaRequest.nomeAssembleia().isBlank())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(assembleiaService.salvarAssembleia(assembleiaRequest), HttpStatus.CREATED);
    }
    
}
