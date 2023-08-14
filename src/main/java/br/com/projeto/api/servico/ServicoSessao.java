package br.com.projeto.api.servico;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.projeto.api.modelo.Mensagem;
import br.com.projeto.api.modelo.Pauta;
import br.com.projeto.api.modelo.Sessao;
import br.com.projeto.api.repositorio.RepositorioPauta;
import br.com.projeto.api.repositorio.RepositorioSessao;

@Service
public class ServicoSessao {
    @Autowired
    private Mensagem mensagem;

    @Autowired
    private RepositorioSessao repositorioSessao;
    
    @Autowired
    private RepositorioPauta repositorioPauta;
    
    
    public ResponseEntity<?> cadastrar(int id_pauta, LocalDateTime dataFechamento){

        Pauta pauta = repositorioPauta.findById(id_pauta);

        if(pauta == null){
            mensagem.setMensagem("A pauta não existe!");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        }else{
            Sessao sessao = repositorioSessao.buscarSessaoAbertaPorPauta(id_pauta);

            if(sessao != null){
                mensagem.setMensagem("A sessão ja existe!");
                return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
            }else{
                sessao = new Sessao();
                sessao.setPauta(pauta);
                sessao.setDataAbertura(LocalDateTime.now());
                sessao.setDataFechamento(dataFechamento);
                if(dataFechamento == null){
                    sessao.setDataFechamento(LocalDateTime.now().plusMinutes(1));
                }
                return new ResponseEntity<>(repositorioSessao.save(sessao), HttpStatus.CREATED);
            }
        } 
    }  
    
}
