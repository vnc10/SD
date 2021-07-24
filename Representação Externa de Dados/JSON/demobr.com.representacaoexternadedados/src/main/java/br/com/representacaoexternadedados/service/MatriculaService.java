package br.com.representacaoexternadedados.service;

import br.com.representacaoexternadedados.dto.MatriculaDTO;
import br.com.representacaoexternadedados.entity.Matricula;
import br.com.representacaoexternadedados.repository.MatriculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MatriculaService {

    MatriculaRepository matriculaRepository;

    public MatriculaService(MatriculaRepository matriculaRepository) {
        this.matriculaRepository = matriculaRepository;
    }

    public Matricula createMatricula(MatriculaDTO matriculaDTO){
        Matricula matricula = new Matricula(matriculaDTO);
        matriculaRepository.save(matricula);
        return matricula;
    }

}
