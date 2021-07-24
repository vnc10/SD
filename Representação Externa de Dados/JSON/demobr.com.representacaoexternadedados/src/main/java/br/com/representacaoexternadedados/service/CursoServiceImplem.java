package br.com.representacaoexternadedados.service;

import br.com.representacaoexternadedados.dto.CursoDTO;
import br.com.representacaoexternadedados.entity.Curso;
import br.com.representacaoexternadedados.repository.CursoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoServiceImplem implements CursoService {

    private CursoRepository cursoRepository;

    public CursoServiceImplem(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    @Override
    public Curso createCurso(CursoDTO cursoDTO) {
        Curso curso = new Curso(cursoDTO);
        cursoRepository.save(curso);
        return curso;
    }

    @Override
    public List<Curso> findAll() {
        return cursoRepository.findAll();
    }
}
