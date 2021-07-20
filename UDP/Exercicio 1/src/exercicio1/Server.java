package exercicio1;
import java.io.ByteArrayOutputStream;
import java.net.*; // Imported because the Socket class is needed
import java.util.HashSet;

public class Server {

    private static final HashSet<Integer> portSet = new HashSet<Integer>();

    public static void main(String[] args) throws Exception {

        // The default port
        int serverport = 7777;

        if (args.length < 1) {
            System.out.println("Usage: UDPServer " + "Now using Port# = " + serverport);
        }
        // Get the port number & host to use from the command line
        else {
            serverport = Integer.parseInt(args[0]);
            System.out.println("Usage: UDPServer " + "Now using Port# = " + serverport);
        }

        // Open a new datagram socket on the specified port
        DatagramSocket udpServerSocket = new DatagramSocket(serverport);

        System.out.println("Server started...\n");

        while(true)
        {
            // Create byte buffers to hold the messages to send and receive
            byte[] receiveData = new byte[1024];

            // Create an empty DatagramPacket packet
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

            // Block until there is a packet to receive, then receive it  (into our empty packet)
            udpServerSocket.receive(receivePacket);

            // Extract the message from the packet and make it into a string, then trim off any end characters
            String clientMessage = (new String(receivePacket.getData())).trim();

            // Print some status messages
            System.out.println("Client Connected - Socket Address: " + receivePacket.getSocketAddress());
            System.out.println("Client message: \"" + clientMessage + "\"");

            // Get the IP address and the the port number which the received connection came from
            InetAddress clientIP = receivePacket.getAddress();

            // Print out status message
            System.out.println("Client IP Address & Hostname: " + clientIP + ", " + clientIP.getHostName() + "\n");

            // Get the port number which the recieved connection came from
            int clientport = receivePacket.getPort();
            System.out.println("Adding "+clientport);
            portSet.add(clientport);

            // Response message
            String returnMessage = clientMessage;
            System.out.println(returnMessage);
            // Create an empty buffer/array of bytes to send back
            byte[] sendData  = new byte[1024];

            // Assign the message to the send buffer
            sendData = returnMessage.getBytes();
            ByteArrayOutputStream sendMessage = new ByteArrayOutputStream();
            int tamNick = clientIP.getHostName().getBytes().length;
            String nick = clientIP.getHostName();
            String msg = clientMessage;
            int tamMsg = msg.getBytes().length;
            

            sendMessage.write((byte) tamNick);
            sendMessage.write(nick.getBytes());
            sendMessage.write((byte) tamMsg);
            sendMessage.write(msg.getBytes());

            byte[] m = sendMessage.toByteArray();

            for(Integer port : portSet)
            {
                System.out.println(port != clientport);
                if(port != clientport)
                {
                    // Create a DatagramPacket to send, using the buffer, the clients IP address, and the clients port
                    DatagramPacket sendPacket = new DatagramPacket(m, m.length, clientIP, port);
                    System.out.println("Sending");
                    // Send the echoed message
                    udpServerSocket.send(sendPacket);
                }
            }
        }
    }
}