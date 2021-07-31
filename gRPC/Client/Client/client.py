import socket
import matricula_pb2
import consulta_pb2

while True:
    client_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    client_socket.connect(("localhost", 7000))
    op = input("Digite 1 para alterar a nota ou 2 para ver os alunos de uma disciplina no ano: ")
    if (op == '1'):
        matricula = matricula_pb2.Matricula()
        matricula.ra_aluno = 1  
        matricula.ano = 2020
        matricula.semestre = 2
        matricula.nota = 5.2
        matricula.faltas = 6
        matricula.op = 1
        msg = matricula.SerializeToString()

    else:
        consulta = consulta_pb2.Consulta()
        consulta.ano = 2020
        consulta.codigo_disciplina = 1
        consulta.op = 2
        msg = consulta.SerializeToString()
    
    size = len(msg)
    client_socket.send((str(size) + "\n").encode())
    client_socket.send(msg)
    data = client_socket.recv(1024)
    print(data.replace(b'\x00', b'').decode('utf-8'))

client_socket.close()
