package br.com.representacaoexternadedados.entity;

import br.com.representacaoexternadedados.dto.CursoDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "curso")
public class Curso implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo")
    private Long codigo;

    @Column(name = "nome")
    private String nome;

    public Curso(CursoDTO cursoDTO){
        this.nome = cursoDTO.getNome();
    }


}
