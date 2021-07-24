package br.com.representacaoexternadedados.repository;

import br.com.representacaoexternadedados.entity.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DisciplinaRepository extends JpaRepository<Disciplina, Long> {
}
