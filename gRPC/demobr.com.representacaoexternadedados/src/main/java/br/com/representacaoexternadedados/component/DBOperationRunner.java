package br.com.representacaoexternadedados.component;

import br.com.representacaoexternadedados.entity.Aluno;
import br.com.representacaoexternadedados.entity.Matricula;
import br.com.representacaoexternadedados.repository.AlunoRepository;
import br.com.representacaoexternadedados.repository.CursoRepository;
import br.com.representacaoexternadedados.repository.DisciplinaRepository;
import br.com.representacaoexternadedados.repository.MatriculaRepository;
import br.com.representacaoexternadedados.service.MatriculaService;
import br.com.representacaoexternadedados.service.MatriculaServiceImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;

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
                .addService(new MatriculaServiceImpl(matriculaRepository))
                .build();

        try {
            server.start();
            System.out.println("Servidor iniciado.");
            server.awaitTermination();
        } catch (IOException | InterruptedException e) {
            System.err.println("Erro: " + e);
        }
    }

}