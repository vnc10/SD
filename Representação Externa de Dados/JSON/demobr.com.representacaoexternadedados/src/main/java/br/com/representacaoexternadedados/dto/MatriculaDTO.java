package br.com.representacaoexternadedados.dto;

import br.com.representacaoexternadedados.entity.Aluno;
import br.com.representacaoexternadedados.entity.Disciplina;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MatriculaDTO {

    private int semestre;
    private float nota;
    private int faltas;
    private Disciplina disciplina;
    private Aluno aluno;

}
