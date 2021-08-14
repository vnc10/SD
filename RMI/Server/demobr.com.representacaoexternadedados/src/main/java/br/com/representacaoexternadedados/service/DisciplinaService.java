package br.com.representacaoexternadedados.service;

import br.com.representacaoexternadedados.dto.DisciplinaDTO;
import br.com.representacaoexternadedados.entity.Disciplina;
import br.com.representacaoexternadedados.repository.DisciplinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DisciplinaService {

    private final DisciplinaRepository disciplinaRepository;

    public DisciplinaService(DisciplinaRepository disciplinaRepository) {
        this.disciplinaRepository = disciplinaRepository;
    }

    public Disciplina createDisciplina(DisciplinaDTO disciplinaDTO) {
        Disciplina disciplina = new Disciplina(disciplinaDTO);
        disciplinaRepository.save(disciplina);
        return disciplina;
    }


}
