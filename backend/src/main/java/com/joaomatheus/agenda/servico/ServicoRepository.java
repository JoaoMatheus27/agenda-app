package com.joaomatheus.agenda.servico;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicoRepository extends JpaRepository<Servico, Long> {
    List<Servico> findByAtivoTrue(Sort sort);
}
