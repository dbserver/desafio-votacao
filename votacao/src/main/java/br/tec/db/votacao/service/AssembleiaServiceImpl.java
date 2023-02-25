package br.tec.db.votacao.service;

import br.tec.db.votacao.dto.AssembleiaDTO;
import br.tec.db.votacao.enums.AssembleiaStatusEnum;
import br.tec.db.votacao.model.Assembleia;
import br.tec.db.votacao.repository.AssembleiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class AssembleiaServiceImpl implements AssembleiaService {

    @Autowired
    private AssembleiaRepository assembleiaRepository;

    @Override
    public AssembleiaDTO criarAssembleia(AssembleiaDTO assembleiaDTO) throws RuntimeException {
        Assembleia assembleia = new Assembleia();
        assembleia.setStatus(AssembleiaStatusEnum.INICIADA);
        try {
            return new AssembleiaDTO(assembleiaRepository.save(assembleia));
        } catch (RuntimeException e) {
            throw new RuntimeException("Erro ao criar assembleia");
        }
    }

    @Override
    public List<AssembleiaDTO> buscarTodasAssembleias() throws DataAccessException {
        return assembleiaRepository.findAll().stream().map(AssembleiaDTO::new).collect(Collectors.toList());
    }

    @Override
    public AssembleiaDTO buscarAssembleiaPorId(Long AssembleiaId) throws RuntimeException {
        try {
            return new AssembleiaDTO(Objects.requireNonNull(assembleiaRepository.findById(AssembleiaId).orElse(null)));
        } catch (RuntimeException e) {
            throw new RuntimeException("Assembleia não encontrada");
        }
    }

    @Override
    public void finalizarAssembleia(Long assembleiaId) throws RuntimeException {
        Assembleia assembleia = assembleiaRepository.findById(assembleiaId).orElse(null);
        if (assembleia == null) {
            throw new RuntimeException("Assembleia não encontrada");
        } else if (assembleia.getStatus().equals(AssembleiaStatusEnum.ENCERRADA)) {
            throw new RuntimeException("Assembleia já finalizada");
        } else {
            assembleia.setFim(LocalDateTime.now());
            assembleia.setStatus(AssembleiaStatusEnum.ENCERRADA);
            assembleiaRepository.save(assembleia);
        }
    }
}