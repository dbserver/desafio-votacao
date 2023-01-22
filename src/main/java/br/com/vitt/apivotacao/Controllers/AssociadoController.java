package br.com.vitt.apivotacao.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.vitt.apivotacao.dto.AssociadoDTO;
import br.com.vitt.apivotacao.entities.Associado;
import br.com.vitt.apivotacao.entities.enums.Status;
import br.com.vitt.apivotacao.services.AssociadoService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/associados")
public class AssociadoController {
	
	@Autowired
	private AssociadoService service;

	@GetMapping
	public ResponseEntity<List<Associado>> findAll(){
		List<Associado> pautas = service.findAll();
		return ResponseEntity.ok(pautas);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Associado> findById(@PathVariable Long id) {
		Associado dto = service.findById(id);

		return ResponseEntity.ok().body(dto);
	}
	
	@GetMapping("/aptos/{cpf}")
	public ResponseEntity<Status> consultaStatusVotar(@PathVariable String cpf){
		Status status = service.verificarSeAptoParaVotar(cpf);
		return ResponseEntity.ok(status);
	}

	@GetMapping("/aptos")
	public ResponseEntity<List<Associado>> todosAssociadosAptosAVotar(){
		List<Associado> aptos = service.acharTodosAptosAVotar();
		return ResponseEntity.ok(aptos);
	}
	
	@PostMapping("/id/{id}")
	public ResponseEntity<Associado> ativarAssociado(@PathVariable Long id) {
		Associado associado = service.ativarAssociado(id);
		return ResponseEntity.ok(associado);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ApiResponses(value = {	
		    @ApiResponse(code = 201, response = Associado.class, message = "Created"),
		   	    
		})	
	@PostMapping
	public ResponseEntity novoAssociado(@RequestBody Associado associado) {
		Associado obj = service.insert(associado);		
		return new ResponseEntity(obj, HttpStatus.CREATED);
	}
	
	@PatchMapping("/id/{id}")
	public ResponseEntity<Associado> mudarStatus(@PathVariable Long id,
													@RequestParam(name="status") String status){
		Associado response = service.mudarStatus(id, status);
		return ResponseEntity.ok(response);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<AssociadoDTO> delete(@PathVariable Long id) {
		service.delete(id);

		return ResponseEntity.noContent().build();
	}
}
