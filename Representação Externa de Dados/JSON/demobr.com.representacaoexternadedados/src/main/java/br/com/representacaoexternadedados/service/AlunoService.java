package br.com.representacaoexternadedados.service;

import br.com.representacaoexternadedados.dto.AlunoDTO;
import br.com.representacaoexternadedados.entity.Aluno;
import br.com.representacaoexternadedados.repository.AlunoRepository;
import org.springframework.stereotype.Service;

@Service
public class AlunoService {

    private final AlunoRepository alunoRepository;

    public AlunoService(AlunoRepository alunoRepository){
        this.alunoRepository = alunoRepository;
    }

    public Aluno createAluno(AlunoDTO alunoDTO){
        Aluno aluno = new Aluno(alunoDTO);
        alunoRepository.save(aluno);
        return aluno;
    }
}
