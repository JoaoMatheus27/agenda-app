package com.joaomatheus.agenda.servico;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicoRepository extends JpaRepository<Servico, Long> {
    //O Spring gera sozinho os métodos de salvar, buscar, listar e apagar.
}
