package br.com.representacaoexternadedados.service;

import br.com.representacaoexternadedados.dto.AlunoDTO;
import br.com.representacaoexternadedados.entity.Aluno;

public interface AlunoService {

    Aluno createAluno(AlunoDTO alunoDTO);

}
