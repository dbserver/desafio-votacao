package com.desafiovotacao.entrypoint;

import com.desafiovotacao.dto.ApiResponse;
import com.desafiovotacao.dto.AssociadoDTO;
import com.desafiovotacao.service.interfaces.IListarAssociadosService;
import com.desafiovotacao.service.interfaces.ISalvarAssociadoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/associados")
public class AssociadoController {

    private final ISalvarAssociadoService salvarAssociadoService;
    private final IListarAssociadosService listarAssociadosService;

    public AssociadoController(
            ISalvarAssociadoService iSalvarAssociadoService,
            IListarAssociadosService listarAssociadosService
    ) {
        this.salvarAssociadoService = iSalvarAssociadoService;
        this.listarAssociadosService = listarAssociadosService;
    }

    @GetMapping
    public ResponseEntity<Page<AssociadoDTO>> list(Pageable page) {
        Page<AssociadoDTO> associados = this.listarAssociadosService.listar(page);
        return ResponseEntity.ok(associados);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<AssociadoDTO>> salvar(@Validated @RequestBody AssociadoDTO associadoDTO) {
        ApiResponse<AssociadoDTO> response = new ApiResponse<>();
        try{
            response.setData(this.salvarAssociadoService.salvar(associadoDTO));
        } catch (Exception e) {
            response.setError(e.getMessage() != null  ? e.getMessage() : "Ocorreu um erro. Tente novamente mais tarde.");
        }
        return ResponseEntity.ok(response);
    }
}
