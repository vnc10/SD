package br.com.representacaoexternadedados.service;

import br.com.representacaoexternadedados.entity.Matricula;
import br.com.representacaoexternadedados.proto.MatriculaProto;
import br.com.representacaoexternadedados.proto.MatriculaServiceGrpc;
import br.com.representacaoexternadedados.repository.AlunoRepository;
import br.com.representacaoexternadedados.repository.CursoRepository;
import br.com.representacaoexternadedados.repository.DisciplinaRepository;
import br.com.representacaoexternadedados.repository.MatriculaRepository;
import br.com.representacaoexternadedados.service.MatriculaService;
import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MatriculaServiceImpl extends MatriculaServiceGrpc.MatriculaServiceImplBase {

    @Autowired
    MatriculaRepository matriculaRepository;

    public MatriculaServiceImpl(MatriculaRepository matriculaRepository) {
        this.matriculaRepository = matriculaRepository;
    }

    @Override
    public void consultaAluno(MatriculaProto.Matricula request, StreamObserver<MatriculaProto.Mess> responseObserver) {
        System.out.println("Request received from client:\n" + request);
        String matricula = "ID: " + request.getId() +
                " RA: " + request.getRaAluno() +
                " Codigo Disciplina: " + request.getCodigoDisciplina() +
                " Ano: " + request.getAno() +
                " Semestre: " + request.getSemestre() +
                " Nota: " + request.getNota() +
                " Faltas: " + request.getFaltas();
        List<String> result = findAluno(request.getAno());
        String str = String.join(",", result);
        MatriculaProto.Mess response = MatriculaProto.Mess.newBuilder()
                .setMess(str)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
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


