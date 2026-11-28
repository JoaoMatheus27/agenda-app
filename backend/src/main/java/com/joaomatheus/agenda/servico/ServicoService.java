package com.joaomatheus.agenda.servico;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joaomatheus.agenda.shared.exception.RecursoNaoEncontradoException;

@Service 

public class ServicoService {

    private final ServicoRepository servicoRepository;

    public ServicoService(ServicoRepository servicoRepository){
        this.servicoRepository = servicoRepository;
    }

    public List<ServicoResponse> listar(){
        return servicoRepository.findByAtivoTrue(Sort.by("nome"))
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
        return ServicoResponse.from(savedEntity);

    }      
    
    public ServicoResponse buscarPorId(Long id){

        Servico entidadeSalva = buscarEntidade(id) ;
        return ServicoResponse.from(entidadeSalva);

    }

    @Transactional
    public ServicoResponse atualizar(Long id, ServicoRequest servicoRequest){

        Servico entidadeSalva = buscarEntidade(id) ;
        entidadeSalva.atualizar(servicoRequest.nome(), servicoRequest.duracaoMinutos(), servicoRequest.preco());
        return ServicoResponse.from(entidadeSalva);

    }

    @Transactional
    public void desativar(Long id){

        Servico entidadeSalva = buscarEntidade(id);
        entidadeSalva.desativar();

    }

    private Servico buscarEntidade(Long id){

        return servicoRepository.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException("Serviço não encontrado: " + id));

    }
    
}   