package br.com.representacaoexternadedados.entity;

import br.com.representacaoexternadedados.dto.CursoDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "curso")
public class Curso implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    private String nome;

    public Curso(CursoDTO cursoDTO){
        this.nome = cursoDTO.getNome();
    }
}
