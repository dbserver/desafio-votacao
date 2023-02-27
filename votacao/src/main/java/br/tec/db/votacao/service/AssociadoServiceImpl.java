package br.tec.db.votacao.service;

import br.tec.db.votacao.dto.AssociadoDTO;
import br.tec.db.votacao.enums.AssociadoStatusEnum;
import br.tec.db.votacao.model.Associado;
import br.tec.db.votacao.repository.AssociadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class AssociadoServiceImpl implements AssociadoService {

    @Autowired
    private AssociadoRepository associadoRepository;

    @Override
    public AssociadoDTO salvarAssociado(AssociadoDTO associadoDTO) throws RuntimeException {
        Associado associado = new Associado();
        associado.setCpf(associadoDTO.cpf());
        associado.setNome(associadoDTO.nome());
        associado.setStatus(AssociadoStatusEnum.PODE_VOTAR);
        try {
            return new AssociadoDTO(associadoRepository.save(associado));
        } catch (RuntimeException e) {
            throw new RuntimeException("Erro ao salvar associado");
        }
    }

    @Override
    public AssociadoDTO buscarAssociadoPorId(Long id) throws RuntimeException {
        try {
            return new AssociadoDTO(Objects.requireNonNull(associadoRepository.findById(id).orElse(null)));
        } catch (RuntimeException e) {
            throw new RuntimeException("Associado não encontrado");
        }
    }

    @Override
    public List<AssociadoDTO> buscarTodosOsAssociados() throws RuntimeException {
        return associadoRepository.findAll().stream().map(AssociadoDTO::new).collect(Collectors.toList());
    }

}