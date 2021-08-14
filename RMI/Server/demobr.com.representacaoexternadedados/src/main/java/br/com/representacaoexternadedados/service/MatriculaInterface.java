package br.com.representacaoexternadedados.service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface MatriculaInterface extends Remote {
    public List<String> consultaAluno(int ano) throws RemoteException;
    public String cadastraNota(long id, long ra_aluno, long codigo_disciplina, int ano, int semestre, float nota, int faltas) throws RemoteException;
    public String consultaNota(long id) throws RemoteException;
    public String atualizaNota(long id, float nota) throws RemoteException;
    public String removeNota(long id) throws RemoteException;
    public String consultaFalta(long codigo_disciplina, int ano) throws RemoteException;
}
