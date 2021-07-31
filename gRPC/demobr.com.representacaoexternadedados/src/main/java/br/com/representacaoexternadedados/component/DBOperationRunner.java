package br.com.representacaoexternadedados.component;

import br.com.representacaoexternadedados.component.MatriculaServiceImpl;
import br.com.representacaoexternadedados.entity.Matricula;
import br.com.representacaoexternadedados.proto.MatriculaProto;
import br.com.representacaoexternadedados.repository.AlunoRepository;
import br.com.representacaoexternadedados.repository.CursoRepository;
import br.com.representacaoexternadedados.repository.DisciplinaRepository;
import br.com.representacaoexternadedados.repository.MatriculaRepository;
import br.com.representacaoexternadedados.service.MatriculaService;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class DBOperationRunner implements CommandLineRunner {

    @Autowired
    CursoRepository cursoRepository;
    @Autowired
    AlunoRepository alunoRepository;
    @Autowired
    DisciplinaRepository disciplinaRepository;
    @Autowired
    MatriculaRepository matriculaRepository;
    @Autowired
    MatriculaService matriculaService;

    @Override
    public void run(String... args) throws IOException, InterruptedException {
        Server server = ServerBuilder
                .forPort(7777)
                .addService(new MatriculaServiceImpl())
                .build();

        try {
            server.start();
            System.out.println("Servidor iniciado.");
            server.awaitTermination();
        } catch (IOException | InterruptedException e) {
            System.err.println("Erro: " + e);
        }
    }

    private void salvaMatricula(Matricula matriculaById, MatriculaProto.Matricula m) {
        matriculaRepository.save(new Matricula(matriculaById.getId(), m.getAno(), m.getSemestre(), (float) m.getNota(), m.getFaltas(), matriculaById.getDisciplina(), matriculaById.getAluno()));
    }

    private Matricula getMatriculaById(long id) {
        Optional<Matricula> matricula = matriculaRepository.findByAluno_Ra(id);
        if (matricula.isPresent()) {
            return matricula.get();
        } else {
            System.out.print("ID de matricula invalido");
            return null;
        }
    }

    private List<Matricula> getMatriculaAno(int ano) {
        return matriculaRepository.findByAno(ano);
    }

    public List<String> findAluno(int ano) {
        List<Matricula> matricula = getMatriculaAno(ano);
        List<String> nomes = new ArrayList();
        for (Matricula value : matricula) {
            nomes.add(value.getAluno().getNome());
        }
        return nomes;
    }

}