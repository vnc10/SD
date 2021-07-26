package br.com.representacaoexternadedados.entity;

import br.com.representacaoexternadedados.dto.AlunoDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "aluno")
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ra;

    private String nome;

    private int periodo;

    @ManyToOne
    @JoinColumn(name = "codigo_curso")
    private Curso curso;

    public Aluno(AlunoDTO alunoDTO) {
        this.nome = alunoDTO.getNome();
        this.periodo = alunoDTO.getPeriodo();
        this.curso = alunoDTO.getCurso();
    }

}
