package com.db.polling.api.controller;

import com.db.polling.api.constants.ApiConstants;
import com.db.polling.api.dto.AssociateDTO;
import com.db.polling.api.dto.response.AssociateResponse;
import com.db.polling.api.dto.response.AssociateWrapperResponse;
import com.db.polling.domain.service.AssociateService;
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

@Validated
@RestController
@RequestMapping("/api/v1/associates")
public class AssociateController {

  private final AssociateService associateService;

  public AssociateController(AssociateService associateService) {
    this.associateService = associateService;
  }

  @PostMapping
  public ResponseEntity<AssociateDTO> createAssociate(
      @RequestBody @Valid AssociateDTO createAssociateDTO) {
    AssociateDTO associateDTO = associateService.createAssociate(createAssociateDTO);

    return ResponseEntity.ok().body(associateDTO);
  }

  @GetMapping("/{id}")
  public ResponseEntity<AssociateResponse> getAssociate(@PathVariable Long id) {
    AssociateResponse associateDTO = associateService.getAssociateById(id);
    return ResponseEntity.ok().body(associateDTO);
  }

  @GetMapping
  public ResponseEntity<List<AssociateResponse>> getAllAssociates(
      @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
      @RequestParam(value = "size", required = false, defaultValue = "20") Integer size, final
  HttpServletResponse httpServletResponse) {

    AssociateWrapperResponse associateResponseDTO = associateService.getAllAssociates(page, size);

    httpServletResponse.addIntHeader(ApiConstants.TOTAL_ELEMENTS_HEADER,
        associateResponseDTO.getTotalElements());
    httpServletResponse.addIntHeader(ApiConstants.TOTAL_PAGES_HEADER,
        associateResponseDTO.getTotalPages());
    httpServletResponse.addHeader(ApiConstants.HAS_NEXT_HEADER,
        Boolean.toString(associateResponseDTO.isHasNext()));

    return ResponseEntity.ok().body(associateResponseDTO.getAssociateGetResponses());
  }

}
