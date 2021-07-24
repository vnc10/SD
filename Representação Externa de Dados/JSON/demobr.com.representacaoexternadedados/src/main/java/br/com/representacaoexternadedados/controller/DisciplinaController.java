package br.com.representacaoexternadedados.controller;

import br.com.representacaoexternadedados.dto.DisciplinaDTO;
import br.com.representacaoexternadedados.entity.Disciplina;
import br.com.representacaoexternadedados.service.DisciplinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("sd/")
public class DisciplinaController {

    @Autowired
    private DisciplinaService disciplinaService;

    @PostMapping("disciplina")
    public ResponseEntity<?> createDisciplina(@RequestBody DisciplinaDTO disciplinaDTO){
        return ResponseEntity.ok().body(disciplinaService.createDisciplina(disciplinaDTO));
    }
}
