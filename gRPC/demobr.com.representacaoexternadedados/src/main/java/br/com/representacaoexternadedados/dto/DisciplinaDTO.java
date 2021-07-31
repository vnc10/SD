package br.com.representacaoexternadedados.dto;

import br.com.representacaoexternadedados.entity.Curso;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DisciplinaDTO {

    private String nome;
    private String professor;
    private Curso curso;

}
