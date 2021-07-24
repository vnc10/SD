package br.com.representacaoexternadedados.controller;

import br.com.representacaoexternadedados.dto.AlunoDTO;
import br.com.representacaoexternadedados.service.AlunoServiceImplem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("sd/")
public class AlunoController {

    @Autowired
    private AlunoServiceImplem alunoServiceImplem;

    @PostMapping("aluno")
    public ResponseEntity<?> createAluno(@RequestBody AlunoDTO alunoDTO) {
        return ResponseEntity.ok().body(alunoServiceImplem.createAluno(alunoDTO));
    }
}
