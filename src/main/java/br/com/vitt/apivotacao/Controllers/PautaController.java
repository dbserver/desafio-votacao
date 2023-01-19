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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.vitt.apivotacao.dto.PautaDTO;
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

		PautaDTO dto = service.findById(id);

		return ResponseEntity.ok().body(dto);

	}

	@PostMapping
	public ResponseEntity<PautaDTO> insert(@RequestBody PautaDTO dto) {

		dto = service.insert(dto);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();

		return ResponseEntity.created(uri).body(dto);

	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<PautaDTO> update(@PathVariable Long id, @RequestBody PautaDTO dto) {

		dto = service.update(id, dto);

		return ResponseEntity.ok().body(dto);

	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<PautaDTO> delete(@PathVariable Long id) {

		service.delete(id);

		return ResponseEntity.noContent().build();

	}
}
