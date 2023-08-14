package br.com.projeto.api.controle;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.projeto.api.modelo.Pauta;
import br.com.projeto.api.modelo.Voto;
import br.com.projeto.api.servico.ServicoPauta;
import br.com.projeto.api.servico.ServicoSessao;
import br.com.projeto.api.servico.ServicoVoto;
import jakarta.validation.Valid;

@RestController
public class Controle {

    @Autowired
    private ServicoPauta servicoPauta;
        
    @Autowired
    private ServicoSessao servicoSessao;   
    
    @Autowired
    private ServicoVoto servicoVoto;  

    @PostMapping("/pauta/cadastrar")
    public ResponseEntity<?> cadastrar(@Valid @RequestBody Pauta obj){
        return servicoPauta.cadastrar(obj);
    }

    @GetMapping("/pauta/listar")
    public ResponseEntity<?> selecionar(){
        return servicoPauta.listar();
    }

    @PostMapping("/{idPauta}/cadastrarSessao")
    public ResponseEntity<?> cadastrar(@PathVariable int idPauta){
        return servicoSessao.cadastrar(idPauta, null);
    }

    @PostMapping("/{idPauta}/votar")
    public ResponseEntity<?> votar(@Valid @PathVariable int idPauta, @RequestBody Voto obj){
        return servicoVoto.votar(idPauta, obj);
    }

    @GetMapping("/{idPauta}/resultado")
    public Map<String, String> resultado(@PathVariable int idPauta){
        return servicoPauta.resultado(idPauta);
    }

}