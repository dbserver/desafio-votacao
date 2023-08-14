package br.com.projeto.api.repositorio;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.projeto.api.modelo.Voto;

public interface RepositorioVoto extends CrudRepository<Voto,Integer>{
        
    @Query(value = "select count(*) from tb_votos where cpf_associado = :cpfAssociado and id_sessao = :id_sessao", nativeQuery = true)
    public int countSessaoCpfAssociado(long cpfAssociado, int id_sessao);
}
