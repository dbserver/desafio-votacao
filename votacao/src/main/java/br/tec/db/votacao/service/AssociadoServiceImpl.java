package br.tec.db.votacao.service;

import br.tec.db.votacao.dto.AssociadoDTO;
import br.tec.db.votacao.enums.AssociadoStatusEnum;
import br.tec.db.votacao.model.Associado;
import br.tec.db.votacao.repository.AssociadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AssociadoServiceImpl implements AssociadoService {

    @Autowired
    private AssociadoRepository associadoRepository;

    @Override
    public AssociadoDTO salvarAssociado(AssociadoDTO associadoDTO) {
        Associado associado = new Associado();
        associado.setCpf(associadoDTO.cpf());
        associado.setNome(associadoDTO.nome());
        associado.setStatus(AssociadoStatusEnum.PODE_VOTAR);
        associadoRepository.save(associado);
        return new AssociadoDTO(associado);
    }

    @Override
    public AssociadoDTO buscarAssociadoPorId(Long id) {
        Associado associado = associadoRepository.findById(id).orElseThrow();
        return new AssociadoDTO(associado);
    }

    @Override
    public List<AssociadoDTO> buscarTodosOsAssociados() {
        return associadoRepository.findAll().stream().map(AssociadoDTO::new).collect(Collectors.toList());
    }

}