package br.com.representacaoexternadedados.entity;

import br.com.representacaoexternadedados.dto.MatriculaDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "matricula")
public class Matricula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "ano")
    private int ano;

    @Column(name = "semestre")
    private int semestre;

    @Column(name = "nota")
    private float nota;

    @Column(name = "faltas")
    private int faltas;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "codigo_disciplina")
    private Disciplina disciplina;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
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
