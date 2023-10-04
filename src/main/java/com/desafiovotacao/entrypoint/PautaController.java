package com.desafiovotacao.entrypoint;

import com.desafiovotacao.dto.ApiResponse;
import com.desafiovotacao.dto.PautaDTO;
import com.desafiovotacao.service.interfaces.IListarPautaService;
import com.desafiovotacao.service.interfaces.IObterPautaPorIdAndResultado;
import com.desafiovotacao.service.interfaces.ISalvarPautaService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/pautas")
public class PautaController {

    private final ISalvarPautaService salvarPautaService;
    private final IListarPautaService listarPautaService;
    private final IObterPautaPorIdAndResultado obterResultadoVotacaoService;

    public PautaController(
            ISalvarPautaService salvarPautaService,
            IObterPautaPorIdAndResultado obterResultadoVotacaoService,
            IListarPautaService listarPautaService
    ) {
        this.salvarPautaService = salvarPautaService;
        this.listarPautaService = listarPautaService;
        this.obterResultadoVotacaoService = obterResultadoVotacaoService;
    }

    @GetMapping
    public ResponseEntity<Page<PautaDTO>> list(Pageable page) {
        Page<PautaDTO> pautas = this.listarPautaService.listar(page);
        return ResponseEntity.ok(pautas);
    }

    @GetMapping("{pautaId}")
    public ResponseEntity<ApiResponse<PautaDTO>> obterPautaPorId(@PathVariable String pautaId) {
        ApiResponse<PautaDTO> response = new ApiResponse<>();
        try{
            response.setData(this.obterResultadoVotacaoService.buscar(pautaId));
        } catch (Exception e) {
            response.setError(e.getMessage());
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<PautaDTO> salvar(@Validated @RequestBody PautaDTO pauta) {
        PautaDTO pautaDTO = this.salvarPautaService.salvar(pauta);
        return ResponseEntity.ok(pautaDTO);
    }
}
