package br.com.representacaoexternadedados.service;

import br.com.representacaoexternadedados.dto.AlunoDTO;
import br.com.representacaoexternadedados.entity.Aluno;
import br.com.representacaoexternadedados.repository.AlunoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlunoServiceImplem implements AlunoService {

    private final AlunoRepository alunoRepository;

    public AlunoServiceImplem(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    @Override
    public Aluno createAluno(AlunoDTO alunoDTO) {
        Aluno aluno = new Aluno(alunoDTO);
        alunoRepository.save(aluno);
        return aluno;
    }

}
