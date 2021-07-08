import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class TCPClient {
    public static void main(String[] args) {
        Socket clientSocket = null;
        Scanner reader = new Scanner(System.in);

        try {
            int serverPort = 6666;
            InetAddress serverAddr = InetAddress.getByName("127.0.0.1");

            clientSocket = new Socket(serverAddr, serverPort);

            DataInputStream dataInputStream = new DataInputStream(clientSocket.getInputStream());
            DataOutputStream dataOutputStream= new DataOutputStream(clientSocket.getOutputStream());

            String buffer = "";

            while (true) {
                System.out.print("$ ");
                buffer = reader.nextLine();
                dataOutputStream.writeUTF(buffer);
                Client client = new Client();
                if (buffer.equals("EXIT")){
                    client.exit();
                    break;
                }
                else if (buffer.equals("TIME") || buffer.equals("DATE")){
                    client.printTimeOrDate(dataInputStream.readUTF());
                }
                else if (buffer.equals("FILES")) {
                    client.files(dataInputStream);

                } else if (buffer.startsWith("DOWN ")) {
                    client.down(buffer.substring(5), dataInputStream);

                } else {
                    buffer = dataInputStream.readUTF();
                    System.out.println("Comando inválido: " + buffer);
                }
            }
        } catch (UnknownHostException e) {
            System.out.println("Socket:" + e.getMessage());
        } catch (EOFException e) {
            System.out.println("EOF:" + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO:" + e.getMessage());
        } finally {
            try {
                assert clientSocket != null;
                clientSocket.close();
                reader.close();
            } catch (IOException e) {
                System.out.println("IO: " + e);
            }
        }
    }
}
