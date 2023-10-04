package com.desafiovotacao.service.associado;

import com.desafiovotacao.domain.Associado;
import com.desafiovotacao.dto.AssociadoDTO;
import com.desafiovotacao.repository.AssociadoRepository;
import com.desafiovotacao.service.interfaces.ISalvarAssociadoService;
import com.desafiovotacao.service.interfaces.IValidadorCPF;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

@Service
public class SalvarAssociadoService implements ISalvarAssociadoService {

    private final AssociadoRepository associadoRepository;
    private final IValidadorCPF validadorCPF;

    public SalvarAssociadoService(IValidadorCPF validadorCPF, AssociadoRepository associadoRepository) {
        this.associadoRepository = associadoRepository;
        this.validadorCPF = validadorCPF;
    }

    @Override
    @Transactional
    public AssociadoDTO salvar(AssociadoDTO associadoDTO) throws Exception {
        boolean cpfValido = this.validadorCPF.validar(associadoDTO.getCpf());

        if(!cpfValido) {
            throw new Exception("CPF não é válido");
        }

        Associado associadoSalvo = this.associadoRepository.save(associadoDTO.toEntity());
        return AssociadoDTO.fromEntity(associadoSalvo);
    }
}
