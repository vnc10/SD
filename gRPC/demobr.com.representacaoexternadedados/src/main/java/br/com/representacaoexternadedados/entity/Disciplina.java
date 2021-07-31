package br.com.representacaoexternadedados.entity;

import br.com.representacaoexternadedados.dto.DisciplinaDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "disciplina")
public class Disciplina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    private String nome;

    private String professor;

    @ManyToOne
    @JoinColumn(name = "codigo_curso")
    private Curso curso;

    public Disciplina(DisciplinaDTO disciplinaDTO) {
        this.nome = disciplinaDTO.getNome();
        this.professor = disciplinaDTO.getProfessor();
        this.curso = disciplinaDTO.getCurso();
    }

}
