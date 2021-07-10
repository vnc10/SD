import java.net.*;
import java.io.*;
import java.util.Scanner;

public class TCPClient {
    public static void main (String[] args) {
        Socket clientSocket = null;
        Scanner reader = new Scanner(System.in);

        try {
            int serverPort = 6666;
            InetAddress serverAddr = InetAddress.getByName("127.0.0.1");

            clientSocket = new Socket(serverAddr, serverPort);

            DataInputStream dataInputStream = new DataInputStream(clientSocket.getInputStream());
            DataOutputStream dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());

            String buffer = "";

            while(true) {
                System.out.print("$ ");
                buffer = reader.nextLine();
                Client client = new Client();

                if(buffer.startsWith("ADDFILE ")) {
                    client.addFile(dataOutputStream, dataInputStream, buffer);

                } else if(buffer.startsWith("DELETE ")) {
                    client.deleteFile(dataOutputStream, dataInputStream, buffer);

                } else if(buffer.equals("GETFILESLIST")) {
                    client.getFilesList(dataOutputStream, dataInputStream);

                } else if(buffer.startsWith("GETFILE ")) {
                    client.getFile(dataOutputStream, dataInputStream, buffer);

                } else {
                    System.out.println("Comando não Reconhecido");
                }
            }
        } catch(UnknownHostException e) {
            System.out.println("Socket:" + e.getMessage());
        } catch(EOFException e) {
            System.out.println("EOF:" + e.getMessage());
        } catch(IOException e) {
            System.out.println("IO:" + e.getMessage());
        } finally {
            try {
                assert clientSocket != null;
                clientSocket.close();
                reader.close();
            } catch(IOException e) {
                System.out.println("IO: " + e);;
            }
        }
    }
}
