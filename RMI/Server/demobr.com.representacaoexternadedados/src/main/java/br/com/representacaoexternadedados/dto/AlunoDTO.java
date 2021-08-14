package br.com.representacaoexternadedados.dto;

import br.com.representacaoexternadedados.entity.Curso;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AlunoDTO {

    private String nome;
    private int periodo;
    private Curso curso;

}
