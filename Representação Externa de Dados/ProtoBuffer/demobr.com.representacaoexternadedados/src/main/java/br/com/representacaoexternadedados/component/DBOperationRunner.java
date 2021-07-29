package br.com.representacaoexternadedados.component;

import br.com.representacaoexternadedados.entity.Matricula;
import br.com.representacaoexternadedados.proto.ConsultaProto;
import br.com.representacaoexternadedados.proto.MatriculaProto;
import br.com.representacaoexternadedados.repository.AlunoRepository;
import br.com.representacaoexternadedados.repository.CursoRepository;
import br.com.representacaoexternadedados.repository.DisciplinaRepository;
import br.com.representacaoexternadedados.repository.MatriculaRepository;
import br.com.representacaoexternadedados.service.MatriculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
    public void run(String... args) throws Exception {
        try {
            int serverPort = 7000; // porta do servidor
            ServerSocket listenSocket = new ServerSocket(serverPort);

            while (true) {
                System.out.println("Servidor aguardando conexão ...");

                /* aguarda conexões */
                Socket clientSocket = listenSocket.accept();

                System.out.println("Cliente conectado ...");

                /* recebe os dados enviados pelo cliente*/
                DataInputStream inClient = new DataInputStream(clientSocket.getInputStream());

                String valueStr = inClient.readLine();
                //System.out.println("Valor lido:" + valueStr);

                int sizeBuffer = Integer.parseInt(valueStr);

                byte[] buffer = new byte[sizeBuffer];
                inClient.read(buffer);
                MatriculaProto.Matricula m = MatriculaProto.Matricula.parseFrom(buffer);
                ConsultaProto.Consulta c = ConsultaProto.Consulta.parseFrom(buffer);
                DataOutputStream dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());
                if (m.getOp() == 1) {
                    if (getMatriculaById(m.getRaAluno()) != null) {
                        salvaMatricula(Objects.requireNonNull(getMatriculaById(m.getRaAluno())), m);
                        dataOutputStream.writeUTF("Atualizado com sucesso!");
                    }
                } else {
                    List<String> message = findAluno(c.getAno());
                    System.out.println(message);
                    dataOutputStream.writeUTF(String.valueOf(message));
                }

            }


        } catch (EOFException e){
            System.out.println("EOF: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Listen socket:" + e.getMessage());
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