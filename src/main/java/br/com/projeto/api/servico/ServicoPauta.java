package br.com.projeto.api.servico;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.projeto.api.modelo.Mensagem;
import br.com.projeto.api.modelo.Pauta;
import br.com.projeto.api.modelo.Sessao;
import br.com.projeto.api.modelo.Voto;
import br.com.projeto.api.repositorio.RepositorioPauta;
import br.com.projeto.api.repositorio.RepositorioSessao;

@Service
public class ServicoPauta {
    
    @Autowired
    private Mensagem mensagem;

    @Autowired
    private RepositorioPauta repositorioPauta;

    @Autowired
    private RepositorioSessao repositorioSessao;     
    
    public ResponseEntity<?> cadastrar(Pauta obj){

        if(obj.getNome().equals("")){
            mensagem.setMensagem("Nome da pauta não pode estar vazio!");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<>(repositorioPauta.save(obj), HttpStatus.CREATED);
        } 
    }

    public ResponseEntity<?> listar(){
        if(repositorioPauta.count() == 0){
            mensagem.setMensagem("Não existem pautas cadastradas");
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(repositorioPauta.findAll(), HttpStatus.OK);
        }
    }

    public Map<String, String> resultado(int idPauta) {

        Pauta pauta = repositorioPauta.findById(idPauta);

        List<Sessao> sessoes = repositorioSessao.findByPauta(pauta);
        Collection<Voto> votos = new ArrayList<>(); 

        if(sessoes.size() > 0){
            for (Sessao sessao : sessoes) {
                votos.addAll(sessao.getVotos());
            }
        }

        Map<String, String> result = new HashMap<>();
        result.put("Pauta", pauta.getNome());
        result.put("SIM", String.valueOf(votos.stream().filter(v -> v.getValor().toString().equalsIgnoreCase("SIM")).count()));
        result.put("NAO", String.valueOf(votos.stream().filter(v -> v.getValor().toString().equalsIgnoreCase("NAO")).count()));

        return result;
    }

    public Pauta consultarId(int id){
        return repositorioPauta.findById(id);
    }
}
