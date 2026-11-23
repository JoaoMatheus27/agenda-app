package com.joaomatheus.agenda.servico;

import java.math.BigDecimal;

  
public record ServicoResponse(Long id, String nome, Integer duracaoMinutos, BigDecimal preco) {
    public static ServicoResponse from(ServicoResponse servicoResponse){
        return new ServicoResponse(servicoResponse.getId() , servicoResponse.getNome() , servicoResponse.getDuracaoMinutos() , servicoResponse.getPreco()); 
    }
}
