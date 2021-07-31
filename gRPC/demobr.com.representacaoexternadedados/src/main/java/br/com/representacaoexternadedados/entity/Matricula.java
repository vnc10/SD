package br.com.representacaoexternadedados.entity;

import br.com.representacaoexternadedados.dto.MatriculaDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "matricula")
public class Matricula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int ano;

    private int semestre;

    private float nota;

    private int faltas;

    @ManyToOne
    @JoinColumn(name = "codigo_disciplina")
    private Disciplina disciplina;

    @ManyToOne
    @JoinColumn(name = "ra_aluno")
    private Aluno aluno;

    public Matricula(MatriculaDTO matriculaDTO) {
        this.ano = matriculaDTO.getAno();
        this.semestre = matriculaDTO.getSemestre();
        this.nota = matriculaDTO.getNota();
        this.faltas = matriculaDTO.getFaltas();
        this.disciplina = matriculaDTO.getDisciplina();
        this.aluno = matriculaDTO.getAluno();
    }
}
