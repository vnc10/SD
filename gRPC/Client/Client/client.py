from __future__ import print_function

import grpc

#import helloworld_pb2
#import helloworld_pb2_grpc
#import HelloService_pb2
#import HelloService_pb2_grpc
import matricula_pb2
import matricula_pb2_grpc


def client():
  #configura o canal de comunicacao
    channel = grpc.insecure_channel('localhost:7777')
  
    stub = matricula_pb2_grpc.MatriculaServiceStub(channel)
  #inicializa e configura o stub
  #stub = helloworld_pb2_grpc.GreeterStub(channel)
  
  #chamada remota
  #response = stub.SayHello(helloworld_pb2.HelloRequest(name='Zoro'))
    op = input("Digite 1, 2, 3, 4, 5, 6: ")
    if op == '1':
        response = stub.consultaAluno(matricula_pb2.Matricula(id=1, ra_aluno=1, codigo_disciplina=1, ano=2020, semestre=4, nota=5.5, faltas=1))
        print("Recebido: " + response.mess)
    elif op == '2':
        response = stub.cadastraNota(matricula_pb2.Matricula(id=3, ra_aluno=3, codigo_disciplina=1, ano=2020, semestre=4, nota=5.5, faltas=1))
        print("Recebido: " + response.mess)
    elif op == '3':
        response = stub.consultaNota(matricula_pb2.Matricula(id=1, ra_aluno=3, codigo_disciplina=1, ano=2020, semestre=4, nota=0.0, faltas=1))
        print("Recebido: " + response.mess)
    elif op == '4':
        response = stub.atualizaNota(matricula_pb2.Matricula(id=1, ra_aluno=1, codigo_disciplina=1, ano=2020, semestre=4, nota=9.5, faltas=1))
        print("Recebido: " + response.mess)
    elif op == '5':
        response = stub.removeNota(matricula_pb2.Matricula(id=1, ra_aluno=1, codigo_disciplina=1, ano=2020, semestre=4, nota=9.5, faltas=1))
        print("Recebido: " + response.mess)
    else:
        response = stub.consultaFalta(matricula_pb2.Matricula(id=1, ra_aluno=1, codigo_disciplina=1, ano=2020, semestre=4, nota=9.5, faltas=1))
        print("Recebido: " + response.mess)

if __name__ == '__main__':
    client()
