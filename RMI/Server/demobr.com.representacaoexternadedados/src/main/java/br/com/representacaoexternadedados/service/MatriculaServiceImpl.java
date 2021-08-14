package br.com.representacaoexternadedados.service;

import br.com.representacaoexternadedados.entity.Disciplina;
import br.com.representacaoexternadedados.entity.Matricula;
import br.com.representacaoexternadedados.repository.AlunoRepository;
import br.com.representacaoexternadedados.repository.DisciplinaRepository;
import br.com.representacaoexternadedados.repository.MatriculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MatriculaServiceImpl extends UnicastRemoteObject implements MatriculaInterface {

    @Autowired
    MatriculaRepository matriculaRepository;
    @Autowired
    DisciplinaRepository disciplinaRepository;
    @Autowired
    AlunoRepository alunoRepository;

    public MatriculaServiceImpl(MatriculaRepository matriculaRepository, DisciplinaRepository disciplinaRepository, AlunoRepository alunoRepository) throws RemoteException {
        super();
        this.matriculaRepository = matriculaRepository;
        this.disciplinaRepository = disciplinaRepository;
        this.alunoRepository = alunoRepository;
        System.out.println("Objeto remoto instanciado");
    }

    @Override
    public List<String> consultaAluno(int ano) throws RemoteException {
        List<String> result = findAluno(ano);
        System.out.println(result);
        return result;
    }

    @Override
    public String cadastraNota(long id, long ra_aluno, long codigo_disciplina, int ano, int semestre, float nota, int faltas) throws RemoteException {
        Matricula m = getMatriculaById(id);
        String str = null;
        if(m != null){
            str = "Matricula ja existe";
        }
        else {
            if(salvaMatricula(id, ano, semestre, nota, faltas, codigo_disciplina, ra_aluno)){
                str = "Matricula salva com sucesso";
            }
            else {
                str = "Matricula falhou";
            }
        }
        return str;
    }

    @Override
    public String consultaNota(long id) throws RemoteException {
        Matricula m = consultaNotaById(id);
        String str = null;
        if (m == null){
            str = "ID de matricula invalido";
        }
        else {
            str = String.valueOf(m.getNota());
        }
        return str;
    }

    @Override
    public String atualizaNota(long id, float nota) throws RemoteException {
        Matricula m = consultaNotaById(id);
        String str = null;
        if (m == null){
            str = "ID de matricula invalido";
        }
        else {
            if(atualizaMatricula(m, nota)){
                str = "Matricula atualizada com sucesso";
            }
            else {
                str = "Matricula não foi salva, devido a problema no servidor";
            }
        }
        return str;
    }

    @Override
    public String removeNota(long id) throws RemoteException {
        Matricula m = consultaNotaById(id);
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
        return str;
    }

    @Override
    public String consultaFalta(long codigo_disciplina, int ano) throws RemoteException {
        List<Matricula> m = consultaNotaeFaltas(codigo_disciplina, ano);
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
        return str;
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

    private Matricula getMatriculaById(long id) {
        Optional<Matricula> matricula = matriculaRepository.findByAluno_Ra(id);
        //System.out.print("ID de matricula invalido");
        return matricula.orElse(null);
    }

    private boolean salvaMatricula(long id, int ano, int semestre, float nota, int faltas, long codigo_disciplina, long ra_aluno) {
        disciplinaRepository.findById(codigo_disciplina);
        alunoRepository.findById(ra_aluno);
        matriculaRepository.save(new Matricula(id, ano, semestre, nota, faltas, disciplinaRepository.getOne(codigo_disciplina), alunoRepository.getOne(ra_aluno)));
        return true;
    }

    private Matricula consultaNotaById(long id) {
        Optional<Matricula> matricula = matriculaRepository.findById(id);
        if (matricula.isPresent()) {
            return matricula.get();
        } else {
            System.out.print("ID de matricula invalido");
            return null;
        }
    }

    private boolean atualizaMatricula(Matricula m, float nota) {
        matriculaRepository.save(new Matricula(m.getId(), m.getAno(), m.getSemestre(), nota, m.getFaltas(), m.getDisciplina(), m.getAluno()));
        return true;
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
}


