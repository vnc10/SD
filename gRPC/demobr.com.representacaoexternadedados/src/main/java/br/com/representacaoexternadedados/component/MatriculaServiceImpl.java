package br.com.representacaoexternadedados.component;

import br.com.representacaoexternadedados.proto.MatriculaProto;
import br.com.representacaoexternadedados.proto.MatriculaServiceGrpc;
import io.grpc.stub.StreamObserver;

public class MatriculaServiceImpl extends MatriculaServiceGrpc.MatriculaServiceImplBase {
    public void consultaAluno(MatriculaProto.Matricula request, StreamObserver<MatriculaProto.Mess> responseObserver) {
        System.out.println("Request received from client:\n" + request);
        String greeting = "Hello, " +
                request.getRA() +
                " " +
                request.getCodDisciplina();

        System.out.println(greeting);
        MatriculaProto.Mess response = MatriculaProto.Mess.newBuilder()
                .setMess(greeting)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
