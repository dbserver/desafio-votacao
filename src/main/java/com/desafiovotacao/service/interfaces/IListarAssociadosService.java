package com.desafiovotacao.service.interfaces;

import com.desafiovotacao.dto.AssociadoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IListarAssociadosService {

    Page<AssociadoDTO> listar(Pageable page);

}
