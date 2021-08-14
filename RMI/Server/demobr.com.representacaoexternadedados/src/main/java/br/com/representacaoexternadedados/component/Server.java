package br.com.representacaoexternadedados.component;

import br.com.representacaoexternadedados.repository.AlunoRepository;
import br.com.representacaoexternadedados.repository.CursoRepository;
import br.com.representacaoexternadedados.repository.DisciplinaRepository;
import br.com.representacaoexternadedados.repository.MatriculaRepository;
import br.com.representacaoexternadedados.service.MatriculaInterface;
import br.com.representacaoexternadedados.service.MatriculaService;
import br.com.representacaoexternadedados.service.MatriculaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

@Component
public class Server implements CommandLineRunner {

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
            /* inicializa um objeto remoto */
            MatriculaInterface matricula = new MatriculaServiceImpl(matriculaRepository, disciplinaRepository, alunoRepository);

            /* registra o objeto remoto no Binder */
            Registry registry = LocateRegistry.createRegistry(1088);
            registry.rebind("MatriculaTeste", matricula);

            /* aguardando invocacoes remotas */
            System.out.println("Servidor pronto ...");
        } catch (Exception e) {
            System.out.println(e);
        } //catch
    }
}
