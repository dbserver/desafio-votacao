package com.example.desafiovotacao.facade;

import com.example.desafiovotacao.dto.FacadeDTO;
import com.example.desafiovotacao.utils.CpfUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CpfFacade {
    public ResponseEntity<FacadeDTO> isCpfValid(String cpf){
        FacadeDTO facadeDTO = new FacadeDTO();
        if(CpfUtils.facadeRandomCpf(cpf)){
            facadeDTO.setStatus("ABLE_TO_VOTE");
            return ResponseEntity.status(HttpStatus.OK).body(facadeDTO);
        }

        facadeDTO.setStatus("UNABLE_TO_VOTE");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(facadeDTO);
    }
}
