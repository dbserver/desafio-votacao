package br.com.stapassoli.desafiovotacao.repository;

import br.com.stapassoli.desafiovotacao.entity.Sessao;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessaoRepository extends JpaRepository<Sessao, Long> {

    Optional<Sessao> findByPauta_Id(Long id);




}
