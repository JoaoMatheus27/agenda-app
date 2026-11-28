package com.joaomatheus.agenda.shared.exception;

import java.util.List;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.http.ProblemDetail;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler{

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail tratarValidacao(MethodArgumentNotValidException ex){
        List<ErroCampo> erros = ex
        .getBindingResult()
        .getFieldErrors()
        .stream()
        .map(erro -> new ErroCampo(erro.getField(), erro.getDefaultMessage()))
        .toList();

        ProblemDetail erroResponse = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        erroResponse.setTitle("Dados Inválidos");
        erroResponse.setProperty("erros", erros);
        return erroResponse;
    }

    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ProblemDetail tratarNaoEncontrado(RecursoNaoEncontradoException ex){
        ProblemDetail erroRecurso = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        erroRecurso.setTitle("Recurso não encontrado");
        erroRecurso.setDetail(ex.getMessage());
        return erroRecurso;
    }

}