package br.com.representacaoexternadedados.repository;

import br.com.representacaoexternadedados.entity.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {
}
