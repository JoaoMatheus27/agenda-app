package com.joaomatheus.agenda.servico;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController 
@RequestMapping("/servicos") 

public class ServicoController {
    private final ServicoService servicoService;

    public ServicoController(ServicoService servicoService){
        this.servicoService = servicoService;
    }

    @GetMapping 
    public List<ServicoResponse> listar(){
        return servicoService.listar();
    }
    
}