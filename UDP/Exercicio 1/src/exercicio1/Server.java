package exercicio1;
import java.io.ByteArrayOutputStream;
import java.net.*; // Imported because the Socket class is needed
import java.util.HashSet;

public class Server {

    private static final HashSet<Integer> portSet = new HashSet<Integer>();

    public static void main(String[] args) throws Exception {

        int serverport = 7777;
        System.out.println("Servidor iniciado na porta: " + serverport);
        DatagramSocket udpServerSocket = new DatagramSocket(serverport);

        while(true)
        {
            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            udpServerSocket.receive(receivePacket);
            String clientMessage = (new String(receivePacket.getData())).trim();
            System.out.println(clientMessage + " no IP: " + receivePacket.getSocketAddress());
            InetAddress clientIP = receivePacket.getAddress();
            int clientport = receivePacket.getPort();
            portSet.add(clientport);

            String returnMessage = clientMessage;
            System.out.println(returnMessage);

            ByteArrayOutputStream sendMessage = new ByteArrayOutputStream();
            int nickSize = clientIP.getHostName().getBytes().length;
            String nick = clientIP.getHostName();
            String inputMessage = clientMessage;
            int sizeMessage = inputMessage.getBytes().length;

            sendMessage.write((byte) nickSize);
            sendMessage.write(nick.getBytes());
            sendMessage.write((byte) sizeMessage);
            sendMessage.write(inputMessage.getBytes());

            byte[] data = sendMessage.toByteArray();

            for(Integer port : portSet)
            {
                System.out.println(port != clientport);
                if(port != clientport)
                {
                    DatagramPacket sendPacket = new DatagramPacket(data, data.length, clientIP, port);
                    udpServerSocket.send(sendPacket);
                }
            }
        }
    }
}