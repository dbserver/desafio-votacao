package com.sicredi.votacao.service;

import com.sicredi.votacao.entity.Pauta;
import com.sicredi.votacao.entity.SessaoVotacao;
import com.sicredi.votacao.repository.PautaRepository;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Fabio Moraes
 */
@Service
public class PautaService {

    @Autowired
    private PautaRepository pautaRepository;
    
    private static final Logger logger = LoggerFactory.getLogger(PautaService.class);
    
    
    @Transactional
public ResponseEntity<Map<String, Object>> abrirSessaoVotacao(Long pautaId, Long minutes) {
    try {
        Pauta pauta = pautaRepository.findById(pautaId).orElse(null);

        SessaoVotacao sessaoVotacao = new SessaoVotacao();
        sessaoVotacao.setDataAbertura(LocalDateTime.now());
        sessaoVotacao.setDuracaoEmMinutos(minutes != null ? minutes : 1); // Valor padrão de 1 minuto
        sessaoVotacao.setPauta(pauta);

        pauta.setSessaoVotacao(sessaoVotacao);
        pautaRepository.save(pauta);

        Map<String, Object> response = new HashMap<>();
        response.put("mensagem", "Sessão de votação aberta com sucesso.");
        return ResponseEntity.ok(response);
    } catch (Exception e) {
        logger.error("Erro ao abrir a sessão de votação.", e);
        Map<String, Object> response = new HashMap<>();
        response.put("mensagem", "Erro ao abrir a sessão de votação.");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}

    
    
    public boolean isSessaoVotacaoAberta(Long pautaId) {
    Pauta pauta = pautaRepository.findById(pautaId).orElse(null);

    if (pauta != null && pauta.getSessaoVotacao() != null) {
        SessaoVotacao sessaoVotacao = pauta.getSessaoVotacao();
        LocalDateTime dataAtual = LocalDateTime.now();
        LocalDateTime dataFechamento = sessaoVotacao.getDataAbertura().plusMinutes(sessaoVotacao.getDuracaoEmMinutos());

        boolean votacaoAberta = dataAtual.isBefore(dataFechamento);

        return votacaoAberta;
    }

    return false;
}
    
    @Cacheable(value = "pautaCache")
    public List<Pauta> listarPautas() {
        return pautaRepository.findAll();
    }

   @CacheEvict(value = "pautaCache", allEntries = true)
public ResponseEntity<Map<String, Object>> salvarPauta(Pauta pauta) {
    try {
        pautaRepository.save(pauta);

        Map<String, Object> response = new HashMap<>();
        response.put("id", pauta.getId());
        response.put("titulo", pauta.getDescricao());
        response.put("mensagem", "Pauta salva com sucesso!");

        return ResponseEntity.ok(response);
    } catch (Exception e) {
        throw new RuntimeException("Erro ao salvar a pauta no banco de dados", e);
    }
}


    @CacheEvict(value = "pautaCache", allEntries = true)
    public ResponseEntity<Map<String, Object>> atualizarPauta(Pauta pauta) {
        if (pauta.getId() != null && pautaRepository.existsById(pauta.getId())) {
            pautaRepository.save(pauta);

            Map<String, Object> response = new HashMap<>();
            response.put("id", pauta.getId());
            response.put("titulo", pauta.getDescricao());
            response.put("mensagem", "Pauta atualizada com sucesso!");

            return ResponseEntity.ok(response);
        } else {
            Map<String, Object> response = new HashMap<>();
            response.put("mensagem", "A pauta não existe no banco de dados.");
            
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @CacheEvict(value = "pautaCache", allEntries = true)
    public ResponseEntity<Map<String, Object>> apagarPauta(Long pautaId) {
        if (pautaRepository.existsById(pautaId)) {
            pautaRepository.deleteById(pautaId);

            Map<String, Object> response = new HashMap<>();
            response.put("id", pautaId);
            response.put("mensagem", "Pauta apagada com sucesso!");

            return ResponseEntity.ok(response);
        } else {
            Map<String, Object> response = new HashMap<>();
            response.put("mensagem", "A pauta não existe no banco de dados.");
            
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }


}
