import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
    public static void main(String[] args) {
        try {
            int serverPort = 6666;
            ServerSocket listenSocket = new ServerSocket(serverPort);

            while (true) {
                System.out.println("Servidor aguardando conexao ...");

                Socket clientSocket = listenSocket.accept();

                System.out.println("Cliente conectado ... Criando thread ...");

                ClientThread c = new ClientThread(clientSocket);
                c.start();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

class ClientThread extends Thread {

    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;
    Socket clientSocket;

    public ClientThread(Socket clientSocket) {
        try {
            this.clientSocket = clientSocket;
            dataInputStream = new DataInputStream(clientSocket.getInputStream());
            dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());
        } catch (IOException e) {
            System.out.println("Connection:" + e.getMessage());
        }
    }

    @Override
    public void run() {
        String buffer = "";

        try {

            while (true) {
                buffer = dataInputStream.readUTF();
                System.out.println("Cliente digitou o comando: " + buffer);
                Server server = new Server();
                if (buffer.equals("EXIT")) {
                    server.exit();
                    break;
                } else if (buffer.equals("TIME")) {
                    server.returnTimeOrDate(1, dataOutputStream);

                } else if (buffer.equals("DATE")) {
                    server.returnTimeOrDate(2, dataOutputStream);

                } else if (buffer.equals("FILES")) {
                    server.files(dataOutputStream);

                } else if (buffer.startsWith("DOWN ")) {
                    server.down(dataOutputStream, buffer);
                } else {
                    System.out.print("Comando invalido: " + buffer + "\n");
                    dataOutputStream.writeUTF(buffer);
                }
            }
        } catch (EOFException e) {
            System.out.println("EOF: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IOE: " + e.getMessage());
        } finally {
            try {
                dataInputStream.close();
                dataOutputStream.close();
                clientSocket.close();
            } catch (IOException e) {
                System.err.println("IOE: " + e);
            }
        }
        System.out.println("Comunicação de Thread Cliente foi finalizada.");
    }

}
