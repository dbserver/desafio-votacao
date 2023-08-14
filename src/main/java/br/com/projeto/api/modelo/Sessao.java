package br.com.projeto.api.modelo;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.LinkedHashSet;

//import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="tb_sessoes")
public class Sessao {

    //Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @OneToOne
    @JoinColumn(name = "id_pauta")
    private Pauta pauta; 

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "sessao", cascade = CascadeType.ALL)
    private Collection<Voto> votos = new LinkedHashSet<Voto>();

    @Column(name = "data_abertura")
    //@JsonFormat(pattern = "dd/MM/yyyy'T'hh:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime dataAbertura;

    @Column(name = "data_fechamento")
    //@JsonFormat(pattern = "dd/MM/yyyy'T'hh:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime dataFechamento;

    //Get e Set
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
       
    public Pauta getPauta() {
        return pauta;
    }
    public void setPauta(Pauta pauta) {
        this.pauta = pauta;
    }
    
    public Collection<Voto> getVotos() {
        return votos;
    }
    public void setVotos(Collection<Voto> votos) {
        this.votos = votos;
    }

    public LocalDateTime getDataAbertura() {
        return dataAbertura;
    }
    public void setDataAbertura(LocalDateTime dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public LocalDateTime getDataFechamento() {
        return dataFechamento;
    }
    public void setDataFechamento(LocalDateTime dataFechamento) {
        this.dataFechamento = dataFechamento;
    }
    
}
