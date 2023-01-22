package br.com.vitt.apivotacao.Controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.vitt.apivotacao.dto.PautaDTO;
import br.com.vitt.apivotacao.entities.Pauta;
import br.com.vitt.apivotacao.entities.PautaAssociado;
import br.com.vitt.apivotacao.services.PautaService;

@RestController
@RequestMapping(value = "/pautas")
public class PautaController {
	
	@Autowired
	private PautaService service;

	@GetMapping
	public ResponseEntity<List<PautaDTO>> findAll(){
		List<PautaDTO> pautas = service.findAll();
		return ResponseEntity.ok(pautas);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<PautaDTO> findById(@PathVariable Long id) {
		Pauta obj = service.findById(id);

		return ResponseEntity.ok().body(new PautaDTO(obj));
	}
	
	@GetMapping("/abertas")
	public ResponseEntity<List<PautaDTO>> findAllOpen(){
		List<PautaDTO> abertas = service.findAllOpen();
		return ResponseEntity.ok(abertas);
	}
	
	@GetMapping("/aprovadas")
	public ResponseEntity<List<PautaDTO>> findAllApproved(){
		List<PautaDTO> aprovadas = service.findAllApproved();
		return ResponseEntity.ok(aprovadas);
	}

	@PostMapping
	public ResponseEntity<PautaDTO> insert(@RequestBody Pauta pauta) {
		PautaDTO dto = service.insert(pauta);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();

		return ResponseEntity.created(uri).body(dto);
	}
	
	@PostMapping("/votar/{id}")
	public ResponseEntity<PautaAssociado> votar(@PathVariable Long id,
										@RequestParam(name="cpf") String cpf,
										@RequestParam(name="voto") String voto){
		PautaAssociado votoInfo = service.votar(id, cpf, voto);
		return ResponseEntity.ok(votoInfo);
	}
	
	@PostMapping("/reabrir/{id}")
	public ResponseEntity<PautaDTO> reabrirPauta(@PathVariable Long id) {
		PautaDTO pauta = service.reabrirPauta(id);
		return ResponseEntity.ok(pauta);
	}
	
	@PostMapping("/abrir/{id}")
	public ResponseEntity<Pauta> novaPautaHorario(@PathVariable Long id, 
												@RequestParam(required = false, name="time") String time) {		
		Pauta pauta = service.abrirVotacao(id, time);
		return ResponseEntity.ok(pauta);
	}
	
	@PutMapping("/id/{id}")
	public ResponseEntity<PautaDTO> novaPauta(@PathVariable Long id,
										@RequestBody Pauta pauta) {
		PautaDTO obj = service.atualizar(id, pauta);
		return ResponseEntity.ok(obj);
	}	
	
	@DeleteMapping("/id/{id}")
	public ResponseEntity<Void> deletarPauta(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<PautaDTO> delete(@PathVariable Long id) {
		service.delete(id);

		return ResponseEntity.noContent().build();
	}
}
