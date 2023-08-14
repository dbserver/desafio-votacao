package br.com.projeto.api.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.projeto.api.modelo.Pauta;
import br.com.projeto.api.modelo.Sessao;

public interface RepositorioSessao extends CrudRepository<Sessao,Integer> { 

    List<Sessao> findAll();

    List<Sessao> findByPauta(Pauta pauta);
    
    @Query(value = "select * from tb_sessoes where id_pauta = :id_pauta", nativeQuery = true)
    Sessao buscarSessaoAbertaPorPauta(int id_pauta);
}
