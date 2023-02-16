package br.tec.db.votacao.service;

import br.tec.db.votacao.dto.AssembleiaDTO;
import br.tec.db.votacao.model.Assembleia;
import br.tec.db.votacao.repository.AssembleiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AssembleiaService {

    @Autowired
    private AssembleiaRepository assembleiaRepository;

    public AssembleiaDTO criar(AssembleiaDTO assembleiaDTO) {
        Assembleia assembleia = new Assembleia();
        assembleia.setStatus(assembleiaDTO.status());
        return new AssembleiaDTO(assembleiaRepository.save(assembleia));
    }

    public List<AssembleiaDTO> listar() {
        return assembleiaRepository.findAll().stream().map(AssembleiaDTO::new).collect(Collectors.toList());
    }
}