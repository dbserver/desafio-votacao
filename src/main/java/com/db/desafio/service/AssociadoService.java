package com.db.desafio.service;


import com.db.desafio.entity.Associado;
import com.db.desafio.exception.AssociadoException;
import com.db.desafio.repository.AssociadoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class AssociadoService {

    private AssociadoRepository associadoRepository;


    public List<Associado> obterAssociados() {
        return associadoRepository.findAll();
    }
    public Associado obterAssociadoPorId(Long id) {
       return associadoRepository.findById(id)
               .orElseThrow(() -> new AssociadoException("Associado inexistente"));
    }
    public void criarAssociado(Associado associado) {
        associadoRepository.findByCpf(associado.getCpf()).ifPresent(a -> {
            throw new AssociadoException("Associado já existente com Cpf: " + associado.getCpf());
        });
        associadoRepository.save(associado);
    }
    public void deletarAssociado(Long id) {
         obterAssociadoPorId(id);
        associadoRepository.deleteById(id);
    }
    public Associado obterAssociadoPorCpf(String cpf) {
        return associadoRepository.findByCpf(cpf)
                .orElseThrow(() -> new AssociadoException("Associado inexistente"));
    }

}
