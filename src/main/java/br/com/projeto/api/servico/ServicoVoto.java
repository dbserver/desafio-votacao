package br.com.projeto.api.servico;

import java.time.LocalDateTime;
//import java.util.InputMismatchException;

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
import br.com.projeto.api.repositorio.RepositorioVoto;

@Service
public class ServicoVoto {
    
    @Autowired
    private Mensagem mensagem;

    @Autowired
    private RepositorioVoto repositorioVoto;
    
    @Autowired
    private RepositorioSessao repositorioSessao;
    
    @Autowired
    private RepositorioPauta repositorioPauta;

    public ResponseEntity<?> votar(int idPauta, Voto voto) {

       /*  if(isCPF(String.valueOf(voto.getCpfAssociado())) == false){
            mensagem.setMensagem("CPF Inválido!");
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
        }else{ */

            Pauta pauta = repositorioPauta.findById(idPauta);

            if(pauta == null){
                mensagem.setMensagem("A pauta não existe!");
                return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
            }

            Sessao sessao = repositorioSessao.buscarSessaoAbertaPorPauta(idPauta);

            if(sessao == null){
                mensagem.setMensagem("A sessão não existe!");
                return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
            }
            
            /*if (LocalDateTime.now().isAfter(VOTO.getDataFechamento())) {            
                mensagem.setMensagem("A sessão está fechada!");
                return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
            }*/

            voto.setSessao(sessao);
            voto.setDataHora(LocalDateTime.now());

            if(repositorioVoto.countSessaoCpfAssociado(voto.getCpfAssociado(), sessao.getId()) > 0) {
                mensagem.setMensagem("O associado ja votou nesta pauta!");
                return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
            }

            mensagem.setMensagem("Voto computado!");
            return new ResponseEntity<>(mensagem, HttpStatus.OK);
        //}
    }

   
}
