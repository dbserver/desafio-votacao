package br.tec.db.votacao.service;

import br.tec.db.votacao.dto.AssembleiaDTO;
import br.tec.db.votacao.enums.AssembleiaStatusEnum;
import br.tec.db.votacao.model.Assembleia;
import br.tec.db.votacao.repository.AssembleiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class AssembleiaServiceImpl implements AssembleiaService {

    @Autowired
    private AssembleiaRepository assembleiaRepository;

    @Override
    public AssembleiaDTO criarAssembleia(AssembleiaDTO assembleiaDTO) {
        Assembleia assembleia = new Assembleia();
        assembleia.setStatus(AssembleiaStatusEnum.INICIADA);
        return new AssembleiaDTO(assembleiaRepository.save(assembleia));
    }

    @Override
    public List<AssembleiaDTO> buscarTodasAssembleias() {
        return assembleiaRepository.findAll().stream().map(AssembleiaDTO::new).collect(Collectors.toList());
    }

    @Override
    public AssembleiaDTO buscarAssembleiaPorId(Long AssembleiaId) {
        return new AssembleiaDTO(Objects.requireNonNull(assembleiaRepository.findById(AssembleiaId).orElse(null)));
    }

}