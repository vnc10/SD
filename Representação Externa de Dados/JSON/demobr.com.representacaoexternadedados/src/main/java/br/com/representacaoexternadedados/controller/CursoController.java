package br.com.representacaoexternadedados.controller;

import br.com.representacaoexternadedados.dto.CursoDTO;
import br.com.representacaoexternadedados.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("sd/")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @PostMapping("curso")
    public ResponseEntity<?> createCurso(@RequestBody CursoDTO cursoDTO) {
        return ResponseEntity.ok().body(cursoService.createCurso(cursoDTO));
    }

    @GetMapping("curso")
    public ResponseEntity<?> findAll(){
        return ResponseEntity.ok().body(cursoService.findAll());
    }

}
