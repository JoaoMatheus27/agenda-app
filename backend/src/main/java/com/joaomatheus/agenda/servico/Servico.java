package com.joaomatheus.agenda.servico;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "servico")
public class Servico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Integer duracaoMinutos;
    private BigDecimal preco;
    private boolean ativo = true; 



    protected Servico() {
        // body of the constructor
    }

    public Servico(String nome, Integer duracaoMinutos, BigDecimal preco ){
        // body of the constructor
        this.nome = nome;
        this.duracaoMinutos = duracaoMinutos;
        this.preco = preco;

    }      

    // getter methods
    public Long getId(){
        return id;
    }    

   // getter methods
    public String getNome(){
        return nome;
    }


    public Integer getDuracaoMinutos() {
        return duracaoMinutos;
    }

  
    public BigDecimal getPreco() {
        return preco;
    }

    // getter methods
    public boolean isAtivo(){
        return ativo;
    }

    public void atualizar(String nome, Integer duracaoMinutos, BigDecimal preco){
        this.nome = nome;
        this.duracaoMinutos = duracaoMinutos;
        this.preco = preco;
    }

    public void desativar(){
        this.ativo = false;
    }

}

