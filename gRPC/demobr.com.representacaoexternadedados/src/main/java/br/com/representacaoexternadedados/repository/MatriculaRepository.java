package br.com.representacaoexternadedados.repository;

import br.com.representacaoexternadedados.entity.Matricula;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface MatriculaRepository extends CrudRepository<Matricula, Long> {

    Optional<Matricula> findByAluno_Ra(Long ra);

    List<Matricula> findByAno(int ano);


}
