package com.db.polling.api.controller;

import com.db.polling.api.constants.ApiConstants;
import com.db.polling.api.dto.AgendaDTO;
import com.db.polling.api.dto.response.AgendaResponse;
import com.db.polling.api.dto.response.AgendaWrapperResponse;
import com.db.polling.domain.service.AgendaService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/agendas")
@Validated
public class AgendaController {

  private final AgendaService agendaService;

  public AgendaController(AgendaService agendaService) {
    this.agendaService = agendaService;
  }

  @PostMapping
  public ResponseEntity<AgendaDTO> createAgenda(@RequestBody @Valid AgendaDTO createAgendaDto) {
    AgendaDTO agendaDTO = agendaService.createAgenda(createAgendaDto);
    return ResponseEntity.ok().body(agendaDTO);
  }

  @GetMapping("/{id}")
  public ResponseEntity<AgendaResponse> getAgenda(@PathVariable Long id) {
    AgendaResponse agendaDTO = agendaService.getAgendaById(id);
    return ResponseEntity.ok().body(agendaDTO);
  }

  @GetMapping
  public ResponseEntity<List<AgendaResponse>> getAllAgendas(
      @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
      @RequestParam(value = "size", required = false, defaultValue = "20") Integer size, final
  HttpServletResponse httpServletResponse) {
    AgendaWrapperResponse agendaWrapperResponse = agendaService.getAllAgendas(page, size);

    httpServletResponse.addIntHeader(ApiConstants.TOTAL_ELEMENTS_HEADER,
        agendaWrapperResponse.getTotalElements());
    httpServletResponse.addIntHeader(ApiConstants.TOTAL_PAGES_HEADER,
        agendaWrapperResponse.getTotalPages());
    httpServletResponse.addHeader(ApiConstants.HAS_NEXT_HEADER,
        Boolean.toString(agendaWrapperResponse.isHasNext()));

    return ResponseEntity.ok(agendaWrapperResponse.getAgendaResponses());
  }

}

