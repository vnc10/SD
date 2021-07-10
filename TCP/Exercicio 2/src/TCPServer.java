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
        } catch (Exception e) {
            e.printStackTrace();
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
        } catch (IOException ioe) {
            System.out.println("Connection:" + ioe.getMessage());
        }
    }

    @Override
    public void run() {
        byte[] option = new byte[2];
        Server server = new Server();
        try {
            while (true) {
                dataInputStream.read(option);

                if (option[1] == 1) {
                    server.addFile(dataInputStream, dataOutputStream);

                } else if (option[1] == 2) {
                    server.deleteFile(dataInputStream, dataOutputStream);

                } else if (option[1] == 3) {
                    server.getFilesList(dataOutputStream);

                } else if (option[1] == 4) {
                    server.getFile(dataInputStream, dataOutputStream);

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
