package com.joaomatheus.agenda.servico;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

@Service 

public class ServicoService {
    private final ServicoRepository servicoRepository;

    public ServicoService(ServicoRepository servicoRepository){
        this.servicoRepository = servicoRepository;
    }

    public List<ServicoResponse> listar(){
        return servicoRepository.findAll()
        .stream()
        // .map(servico -> new ServicoResponse( servico.getId() , servico.getNome() , servico.getDuracaoMinutos() , servico.getPreco()))
        // .map(servico -> ServicoResponse.from(servico))
        .map(ServicoResponse::from)
        .toList();
    }   

    @Transactional 
    public ServicoResponse criar(ServicoRequest servicoRequest){
        Servico servicoEntity = new Servico(servicoRequest.nome(),servicoRequest.duracaoMinutos(), servicoRequest.preco() );
        Servico savedEntity = servicoRepository.save(servicoEntity);
        // return new ServicoResponse(savedEntity.getId() , savedEntity.getNome() , savedEntity.getDuracaoMinutos() , savedEntity.getPreco());
        return ServicoResponse.from(servicoEntity);
    }                               
};     