package br.com.representacaoexternadedados.service;

import br.com.representacaoexternadedados.Application;
import br.com.representacaoexternadedados.dto.DisciplinaDTO;
import br.com.representacaoexternadedados.dto.MatriculaDTO;
import br.com.representacaoexternadedados.entity.Aluno;
import br.com.representacaoexternadedados.entity.Curso;
import br.com.representacaoexternadedados.entity.Disciplina;
import br.com.representacaoexternadedados.entity.Matricula;
import br.com.representacaoexternadedados.repository.CursoRepository;
import br.com.representacaoexternadedados.repository.DisciplinaRepository;
import br.com.representacaoexternadedados.repository.MatriculaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MatriculaService {

    MatriculaRepository matriculaRepository;

    DisciplinaRepository disciplinaRepository;

    CursoRepository cursoRepository;

    public MatriculaService(MatriculaRepository matriculaRepository) {
        this.matriculaRepository = matriculaRepository;
    }

    private Matricula getMatricula(Long id) {
        Optional<Matricula> matricula = matriculaRepository.findById(id);

        if(matricula.isPresent()){
            return matricula.get();
        }
        throw new ResponseStatusException(HttpStatus.NO_CONTENT);
    }

    private List<Matricula> getMatriculaAno(int ano) {
        List<Matricula> matricula = matriculaRepository.findByAno(ano);
        return matricula;
        //throw new ResponseStatusException(HttpStatus.NO_CONTENT);
    }

    public Matricula createMatricula(MatriculaDTO matriculaDTO) {
        Matricula matricula = new Matricula(matriculaDTO);
        matriculaRepository.save(matricula);
        return matricula;
    }

    public List<String> findAluno(int ano) {
        List<Matricula> matricula = getMatriculaAno(ano);
        List<String> nomes = new ArrayList();
        for (Matricula value : matricula) {
            nomes.add(value.getAluno().getNome());
        }
        return nomes;
    }

    public Matricula updateNota(Long id, MatriculaDTO matriculaDTO){
        Matricula matricula = getMatricula(id);
        matricula.setAluno(matriculaDTO.getAluno());
        matricula.setAno(matriculaDTO.getAno());
        matricula.setDisciplina(matriculaDTO.getDisciplina());
        matricula.setFaltas(matriculaDTO.getFaltas());
        matricula.setNota(matriculaDTO.getNota());
        matricula.setSemestre(matriculaDTO.getSemestre());
        matriculaRepository.save(matricula);
        return matricula;
    }

}
