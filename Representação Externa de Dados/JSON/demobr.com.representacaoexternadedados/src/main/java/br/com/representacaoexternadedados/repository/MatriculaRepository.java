package br.com.representacaoexternadedados.repository;

import br.com.representacaoexternadedados.entity.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatriculaRepository extends JpaRepository <Matricula, Integer> {
}
