package br.com.representacaoexternadedados.repository;

import br.com.representacaoexternadedados.entity.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MatriculaRepository extends JpaRepository<Matricula, Integer> {
    Optional<Matricula> findById(Long id);
    List<Matricula> findByAno(int ano);

}
