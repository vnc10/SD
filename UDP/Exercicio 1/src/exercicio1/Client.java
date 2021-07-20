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

    public static void main(String args[]) throws Exception {

        // The default port     
        int clientport = 7777;
        String host = "localhost";

        if (args.length < 1) {
            System.out.println("Usage: UDPClient " + "Now using host = " + host + ", Port# = " + clientport);
        }
        // Get the port number to use from the command line
        else {
            //host = args[0];
            clientport = Integer.parseInt(args[0]);
            System.out.println("Usage: UDPClient " + "Now using host = " + host + ", Port# = " + clientport);
        }

        // Get the IP address of the local machine - we will use this as the address to send the data to
        InetAddress ia = InetAddress.getByName(host);

        SenderThread sender = new SenderThread(ia, clientport);
        sender.start();
        ReceiverThread receiver = new ReceiverThread(sender.getSocket());
        receiver.start();
    }
}

class SenderThread extends Thread {

    private InetAddress serverIPAddress;
    private DatagramSocket udpClientSocket;
    private boolean stopped = false;
    private int serverport;

    public SenderThread(InetAddress address, int serverport) throws SocketException {
        this.serverIPAddress = address;
        this.serverport = serverport;
        // Create client DatagramSocket
        this.udpClientSocket = new DatagramSocket();
        this.udpClientSocket.connect(serverIPAddress, serverport);
    }

    public void halt() {
        this.stopped = true;
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
            //send blank message
            byte[] data = new byte[1024];
            int tamNick = nick.getBytes().length;
//            int tamNick = serverIPAddress.getHostName().getBytes().length;
//            String nick = serverIPAddress.getHostName();
            String conectado = nick + " Conectado";
            data = conectado.getBytes();

            DatagramPacket blankPacket = new DatagramPacket(data, data.length, serverIPAddress, serverport);
            udpClientSocket.send(blankPacket);

            // Create input stream
            BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                if (stopped)
                    return;

                // Message to send
                String clientMessage = inFromUser.readLine();

                if (clientMessage.equals("."))
                    break;

                // Create byte buffer to hold the message to send
                byte[] message = new byte[1024];

                ByteArrayOutputStream sendMessage = new ByteArrayOutputStream();
                String msg = clientMessage;
                int tamMsg = msg.getBytes().length;


                sendMessage.write((byte) tamNick);
                sendMessage.write(nick.getBytes());
                sendMessage.write((byte) tamMsg);
                sendMessage.write(msg.getBytes());

                // -------------end of header----------------------

                message = sendMessage.toByteArray();

                // Put this message into our empty buffer/array of bytes

                // Create a DatagramPacket with the data, IP address and port number
                DatagramPacket sendPacket = new DatagramPacket(message, message.length, serverIPAddress, serverport);

                // Send the UDP packet to server
                //System.out.println("I just sent: " + clientMessage);
                udpClientSocket.send(sendPacket);

                Thread.yield();
            }
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }
}

class ReceiverThread extends Thread {

    private DatagramSocket udpClientSocket;
    private boolean stopped = false;

    public ReceiverThread(DatagramSocket ds) throws SocketException {
        this.udpClientSocket = ds;
    }

    public void halt() {
        this.stopped = true;
    }

    public void run() {

        // Create a byte buffer/array for the receive Datagram packet
        byte[] receiveData = new byte[1024];

        while (true) {
            if (stopped)
                return;

            // Set up a DatagramPacket to receive the data into
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            try {
                udpClientSocket.receive(receivePacket);
                byte[] b = receivePacket.getData();
                Byte tamNick = b[0];

                Byte tamMsg = b[1 + tamNick.intValue()];
                System.out.println(new String(receivePacket.getData(), 2 + tamNick.intValue(), tamMsg.intValue()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}