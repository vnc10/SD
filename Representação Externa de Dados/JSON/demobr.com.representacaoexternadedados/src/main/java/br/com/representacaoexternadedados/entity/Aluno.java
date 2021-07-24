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
@Builder
@Table(name = "aluno")
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ra")
    private Long ra;

    @Column(name = "nome")
    private String nome;

    @Column(name = "periodo")
    private int periodo;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "codigo_curso")
    private Curso curso;

    public Aluno(AlunoDTO alunoDTO) {
        this.nome = alunoDTO.getNome();
        this.periodo = alunoDTO.getPeriodo();
        this.curso = alunoDTO.getCurso();
    }
}
