package com.joaomatheus.agenda.servico;

import java.util.List;
import org.springframework.stereotype.Service;

@Service 

public class ServicoService {
    private final ServicoRepository servicoRepository;

    public ServicoService(ServicoRepository servicoRepository){
        this.servicoRepository = servicoRepository;
    }

    public List<ServicoResponse> listar(){
        return servicoRepository.findAll()
        .stream()
        .map(servico -> new ServicoResponse( servico.getId() , servico.getNome() , servico.getDuracaoMinutos() , servico.getPreco()))
        .toList();
    }   
}     



