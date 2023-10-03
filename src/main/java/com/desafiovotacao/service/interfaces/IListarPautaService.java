package com.desafiovotacao.service.interfaces;

import com.desafiovotacao.dto.PautaDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IListarPautaService {

    Page<PautaDTO> listar(Pageable page);

}
