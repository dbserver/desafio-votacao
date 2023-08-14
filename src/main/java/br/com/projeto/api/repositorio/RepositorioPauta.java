package br.com.projeto.api.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.projeto.api.modelo.Pauta;

public interface RepositorioPauta extends CrudRepository<Pauta,Integer> {
    
    List<Pauta> findAll();

    List<Pauta> findByNome(String nome);

    Pauta findById(int id);

    List<Pauta> findByOrderByNome();

    List<Pauta> findByNomeContaining(String termo);
    
    List<Pauta> findByNomeStartsWith(String termo);
    
    List<Pauta> findByNomeEndsWith(String termo);

    @Query(value = "select sum(id) + 2 from pautas", nativeQuery = true)
    int somaTudo();

    @Query(value = "select * from pautas where id >= :codigo", nativeQuery = true)
    List<Pauta> codigoMaiorIgual(int codigo);
}
