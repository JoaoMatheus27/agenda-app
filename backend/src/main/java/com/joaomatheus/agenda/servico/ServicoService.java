package com.joaomatheus.agenda.servico;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joaomatheus.agenda.shared.exception.RecursoNaoEncontradoException;

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
    
    public ServicoResponse buscarPorId(Long id){
        
        Servico resultadoBuscar = servicoRepository.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException("Serviço não encontrado: " + id));
        return ServicoResponse.from(resultadoBuscar);

    }
    
};     