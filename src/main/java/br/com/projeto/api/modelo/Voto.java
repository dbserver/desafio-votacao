package br.com.projeto.api.modelo;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "tb_votos")
public class Voto {
    
    //Atributos
    @Id
    private long cpfAssociado;
    
    private LocalDateTime dataHora;

    @ManyToOne
    @JoinColumn(name = "id_sessao")
    private Sessao sessao; 

    @Column(name = "valor")
    @Enumerated(EnumType.STRING)  
    @NotEmpty(message = "O valor do voto é obrigatório e deve ser 'SIM' ou 'NAO'") 
    private ValorVoto valor;
    
    //Get e Set
    public long getCpfAssociado() {
        return cpfAssociado;
    }
    public void setCpfAssociado(long cpfAssociado) {
        this.cpfAssociado = cpfAssociado;
    }
    public LocalDateTime getDataHora() {
        return dataHora;
    }
    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }
    
    public Sessao getSessao() {
        return sessao;
    }
    public void setSessao(Sessao votacao) {
        this.sessao = votacao;
    }    
    
    public ValorVoto getValor() {
        return valor;
    }
    public void setValor(ValorVoto valor) {
        this.valor = valor;
    }
}
