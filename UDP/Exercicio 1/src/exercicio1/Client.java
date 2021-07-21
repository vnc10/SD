package exercicio1;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) throws Exception {

        int clientport = 7777;
        String host = "localhost";
        System.out.println("Voce está conectado na porta: " + clientport);
        InetAddress ia = InetAddress.getByName(host);
        SenderThread sender = new SenderThread(ia, clientport);
        sender.start();
        ReceiverThread receiver = new ReceiverThread(sender.getSocket());
        receiver.start();
    }
}

class SenderThread extends Thread {

    private final InetAddress serverIPAddress;
    private final DatagramSocket udpClientSocket;
    private final int serverport;

    public SenderThread(InetAddress address, int serverport) throws SocketException {
        this.serverIPAddress = address;
        this.serverport = serverport;
        this.udpClientSocket = new DatagramSocket();
        this.udpClientSocket.connect(serverIPAddress, serverport);
    }

    public DatagramSocket getSocket() {
        return this.udpClientSocket;
    }

    public void run() {
        try {
            Scanner reader = new Scanner(System.in);
            System.out.println("Digite seu nick: ");
            String inputNick = reader.nextLine();
            String nick = inputNick + ": ";
            byte[] data = new byte[1024];
            int nickSize = nick.getBytes().length;
            String connectedMessage = nick + " Conectado";
            data = connectedMessage.getBytes();

            DatagramPacket blankPacket = new DatagramPacket(data, data.length, serverIPAddress, serverport);
            udpClientSocket.send(blankPacket);

            BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                String clientMessage = inFromUser.readLine();

                if (clientMessage.equals("."))
                    break;

                byte[] message = new byte[1024];
                ByteArrayOutputStream sendMessage = new ByteArrayOutputStream();
                String inputMessage = clientMessage;
                int sizeInputMessage = inputMessage.getBytes().length;

                sendMessage.write((byte) nickSize);
                sendMessage.write(nick.getBytes());
                sendMessage.write((byte) sizeInputMessage);
                sendMessage.write(inputMessage.getBytes());

                message = sendMessage.toByteArray();
                DatagramPacket sendPacket = new DatagramPacket(message, message.length, serverIPAddress, serverport);

                udpClientSocket.send(sendPacket);

                Thread.yield();
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}

class ReceiverThread extends Thread {

    private final DatagramSocket udpClientSocket;

    public ReceiverThread(DatagramSocket ds) throws SocketException {
        this.udpClientSocket = ds;
    }


    public void run() {

        byte[] receiveData = new byte[1024];

        while (true) {
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            try {
                udpClientSocket.receive(receivePacket);
                byte[] b = receivePacket.getData();
                byte sizeNick = b[0];
                byte sizeMessage = b[1 + (int) sizeNick];
                System.out.println(new String(receivePacket.getData(), 2 + (int) sizeNick, sizeMessage));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}