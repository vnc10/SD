package br.com.representacaoexternadedados.component;

import br.com.representacaoexternadedados.service.MatriculaInterface;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

@Component
public class Client implements CommandLineRunner {

    @Override
    public void run(String... args) throws IOException, InterruptedException {
        try {
            System.out.println("Cliente iniciado ...");
            Registry registry = LocateRegistry.getRegistry(1088);
            MatriculaInterface matricula = (MatriculaInterface) registry.lookup("MatriculaTeste");
            Scanner reader = new Scanner(System.in);
            System.out.println("Digite 1, 2, 3, 4, 5, 6: ");
            int op = reader.nextInt();
            if (op == 1) {
                System.out.println(matricula.consultaAluno(2020));
            }
            else if (op == 2){
                System.out.println(matricula.cadastraNota(4, 1, 1, 2020, 4, (float) 5.6,2));
            }
            else if (op == 3){
                System.out.println(matricula.consultaNota(1));
            }
            else if (op == 4){
                System.out.println(matricula.atualizaNota(1, 4.4F));
            }
            else if (op == 5){
                System.out.println(matricula.removeNota(1));
            }
            else {
                System.out.println(matricula.consultaFalta(1, 2020));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}