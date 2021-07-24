package br.com.representacaoexternadedados.controller;

import br.com.representacaoexternadedados.dto.MatriculaDTO;
import br.com.representacaoexternadedados.entity.Matricula;
import br.com.representacaoexternadedados.service.MatriculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("sd/")
public class MatriculaController {

    @Autowired
    MatriculaService matriculaService;

    @PostMapping("matricula")
    public ResponseEntity<?> createMatricula(@RequestBody MatriculaDTO matriculaDTO){
        return ResponseEntity.ok().body(matriculaService.createMatricula(matriculaDTO));
    }

}
