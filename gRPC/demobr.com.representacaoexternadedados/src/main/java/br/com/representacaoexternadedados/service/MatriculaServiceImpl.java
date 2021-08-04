package br.com.representacaoexternadedados.service;

import br.com.representacaoexternadedados.entity.Disciplina;
import br.com.representacaoexternadedados.entity.Matricula;
import br.com.representacaoexternadedados.proto.MatriculaProto;
import br.com.representacaoexternadedados.proto.MatriculaServiceGrpc;
import br.com.representacaoexternadedados.repository.AlunoRepository;
import br.com.representacaoexternadedados.repository.DisciplinaRepository;
import br.com.representacaoexternadedados.repository.MatriculaRepository;
import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.lang.String.valueOf;

@Service
public class MatriculaServiceImpl extends MatriculaServiceGrpc.MatriculaServiceImplBase {

    @Autowired
    MatriculaRepository matriculaRepository;
    @Autowired
    DisciplinaRepository disciplinaRepository;
    @Autowired
    AlunoRepository alunoRepository;

    public MatriculaServiceImpl(MatriculaRepository matriculaRepository, DisciplinaRepository disciplinaRepository, AlunoRepository alunoRepository) {
        this.matriculaRepository = matriculaRepository;
        this.disciplinaRepository = disciplinaRepository;
        this.alunoRepository = alunoRepository;
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
    @Override
    public void cadastraNota(MatriculaProto.Matricula request, StreamObserver<MatriculaProto.Mess> responseObserver) {
        System.out.println("Request received from client:\n" + request);
        Matricula m = getMatriculaById(request.getId());
        String str = null;
        if(m != null){
            str = "Matricula ja existe";
        }
        else {
            if(salvaMatricula(request.getId(), request.getAno(), request.getSemestre(), request.getNota(), request.getFaltas(), request.getCodigoDisciplina(), request.getRaAluno())){
                str = "Matricula salva com sucesso";
            }
            else {
                str = "Matricula falhou";
            }
        }
        MatriculaProto.Mess response = MatriculaProto.Mess.newBuilder()
                .setMess(str)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void consultaNota(MatriculaProto.Matricula request, StreamObserver<MatriculaProto.Mess> responseObserver) {
        System.out.println("Request received from client:\n" + request);
        Matricula m = consultaNota(request.getId());
        String str = null;
        if (m == null){
            str = "ID de matricula invalido";
        }
        else {
            str = String.valueOf(m.getNota());
        }
        MatriculaProto.Mess response = MatriculaProto.Mess.newBuilder()
                .setMess(str)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void atualizaNota(MatriculaProto.Matricula request, StreamObserver<MatriculaProto.Mess> responseObserver) {
        System.out.println("Request received from client:\n" + request);
        Matricula m = consultaNota(request.getId());
        String str = null;
        if (m == null){
            str = "ID de matricula invalido";
        }
        else {
            if(atualizaMatricula(m, request.getNota())){
                str = "Matricula atualizada com sucesso";
            }
            else {
                str = "Matricula não foi salva, devido a problema no servidor";
            }
        }
        MatriculaProto.Mess response = MatriculaProto.Mess.newBuilder()
                .setMess(str)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void removeNota(MatriculaProto.Matricula request, StreamObserver<MatriculaProto.Mess> responseObserver) {
        System.out.println("Request received from client:\n" + request);
        Matricula m = consultaNota(request.getId());
        String str = null;
        if (m == null){
            str = "ID de matricula invalido";
        }
        else {
            if(atualizaMatricula(m, 0.0F)){
                str = "Nota removida com sucesso";
            }
            else {
                str = "Nota não foi salva, devido a problema no servidor";
            }
        }
        MatriculaProto.Mess response = MatriculaProto.Mess.newBuilder()
                .setMess(str)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void consultaFalta(MatriculaProto.Matricula request, StreamObserver<MatriculaProto.Mess> responseObserver) {
        System.out.println("Request received from client:\n" + request);
        List<Matricula> m = consultaNotaeFaltas(request.getCodigoDisciplina(), request.getAno());
        String str = null;
        if(m == null){
            str = "Matricula errada";
        }
        else {
            List<String> result = new ArrayList<>();
            for (Matricula matricula : m) {
                result.add("Disciplina: " + matricula.getDisciplina().getNome() + " Nota: " + matricula.getNota() + " Faltas: " + matricula.getFaltas() + "\n");
            }
            str = String.join(",", result);
        }
        MatriculaProto.Mess response = MatriculaProto.Mess.newBuilder()
                .setMess(str)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    private boolean atualizaMatricula(Matricula m, float nota) {
        matriculaRepository.save(new Matricula(m.getId(), m.getAno(), m.getSemestre(),  nota, m.getFaltas(), m.getDisciplina(), m.getAluno()));
        return true;
    }

    private boolean salvaMatricula(long id, int ano, int semestre, float nota, int faltas, long codigo_disciplina, long ra_aluno) {
        disciplinaRepository.findById(codigo_disciplina);
        alunoRepository.findById(ra_aluno);
        matriculaRepository.save(new Matricula(id, ano, semestre, nota, faltas, disciplinaRepository.getOne(codigo_disciplina), alunoRepository.getOne(ra_aluno)));
        return true;
    }

    private Matricula getMatriculaById(long id) {
        Optional<Matricula> matricula = matriculaRepository.findByAluno_Ra(id);
        //System.out.print("ID de matricula invalido");
        return matricula.orElse(null);
    }

    private List<Matricula> consultaNotaeFaltas(long codigo_disciplina, int ano) {
        Disciplina disciplina = disciplinaRepository.getOne(codigo_disciplina);
        List<Matricula> matricula = matriculaRepository.findByDisciplinaAndAno(disciplina, ano);
        if (matricula.isEmpty()) {
            System.out.print("ID de matricula invalido");
            return null;
        } else {
            return matricula;
        }
    }

    private Matricula consultaNota(long id) {
        Optional<Matricula> matricula = matriculaRepository.findById(id);
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


