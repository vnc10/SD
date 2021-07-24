package br.com.representacaoexternadedados.service;

import br.com.representacaoexternadedados.dto.CursoDTO;
import br.com.representacaoexternadedados.entity.Curso;

import java.util.List;

public interface CursoService {
    Curso createCurso(CursoDTO cursoDTO);

    List<Curso> findAll();
}
