package br.com.representacaoexternadedados.repository;

import br.com.representacaoexternadedados.entity.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CursoRepository extends JpaRepository<Curso, Long> {
    Optional<Curso> findByCodigo(Long codigo);
}
