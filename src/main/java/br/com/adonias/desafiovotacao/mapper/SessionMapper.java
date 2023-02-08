package br.com.adonias.desafiovotacao.mapper;

import br.com.adonias.desafiovotacao.dto.SessionDTO;
import br.com.adonias.desafiovotacao.entities.Session;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SessionMapper {

    @Autowired
    private ModelMapper modelMapper;

    public SessionDTO convertToDto(Session session) {
        return modelMapper.map(session, SessionDTO.class);
    }

    public Session convertToEntity(SessionDTO sessionDTO) {
        return modelMapper.map(sessionDTO, Session.class);
    }
}
