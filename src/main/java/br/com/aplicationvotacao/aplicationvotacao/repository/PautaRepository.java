package br.com.aplicationvotacao.aplicationvotacao.repository;


import br.com.aplicationvotacao.aplicationvotacao.model.Pauta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PautaRepository extends JpaRepository<Pauta, Long> {

    @Override
    Optional<Pauta> findById(Long id);

}

