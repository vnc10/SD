package multicast;

import java.io.IOException;
import java.net.*;
import java.util.HashMap;
import java.util.Properties;

public class ProtocolController {
    private final MulticastSocket multicastSocket;
    private final DatagramSocket udpSocket;
    private final InetAddress group;
    private final Integer mport, uport;
    private final String nick;
    private final HashMap<String, InetAddress> onlineUsers;
    private final UIControl ui;

    public ProtocolController(Properties properties) throws IOException {
        mport = (Integer) properties.get("multicastPort");
        uport = (Integer) properties.get("udpPort");
        group = (InetAddress) properties.get("multicastIP");
        nick = (String) properties.get("nickname");
        ui = (UIControl) properties.get("UI");

        multicastSocket = new MulticastSocket(mport);
        udpSocket = new DatagramSocket(uport);

        multicastSocket.joinGroup(group);

        onlineUsers = new HashMap<>();
        onlineUsers.put("Todos", group);
    }

    public void send(String targetUser, String msg) throws IOException {
        if (targetUser.equals("Todos")) {
            Message message = new Message((byte) 3, this.nick, msg);
            this.sendMessageGroup(message);
        } else {
            Message message = new Message((byte) 4, this.nick, msg);
            this.sendMessage(message, onlineUsers.get(targetUser));
        }
    }

    private void sendMessageGroup(Message msg) throws IOException {
        try {
            byte[] data = msg.getBytes();
            DatagramPacket datagramPacket = new DatagramPacket(data, data.length, this.group, 6789);

            this.multicastSocket.send(datagramPacket);
        } catch (SocketException e) {
            System.out.println("Socket: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        }
    }

    private void sendMessage(Message msg, InetAddress target) throws IOException {
        try {
            byte[] data = msg.getBytes();
            DatagramPacket datagramPacket = new DatagramPacket(data, data.length, target, 6799);
            this.udpSocket.send(datagramPacket);
        } catch (SocketException e) {
            System.out.println("Socket: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        }
    }

    public void join() throws IOException {
        Message message = new Message((byte) 1, this.nick, "");
        this.sendMessageGroup(message);
    }

    public void leave() throws IOException {
        try {
            Message message = new Message((byte) 5, this.nick, "");
            this.sendMessageGroup(message);

            this.multicastSocket.leaveGroup(this.group);
        } catch (SocketException e) {
            System.out.println("Socket: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        } finally {
            if (multicastSocket != null) multicastSocket.close();
        }
    }

    public void close() throws IOException {
        if (udpSocket != null) udpSocket.close();
        if (multicastSocket != null) multicastSocket.close();
    }

    public void receiveMulticastPacket() throws IOException {
        byte[] buffer = new byte[1000];
        DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length);

        multicastSocket.receive(datagramPacket);

        Message message = new Message(datagramPacket.getData());

        if (!message.getSource().equals(this.nick)) {
            if (message.getType() == 1) {
                this.onlineUsers.put(message.getSource(), datagramPacket.getAddress());

                Message msgJoinACK = new Message((byte) 2, this.nick, "");
                this.sendMessage(msgJoinACK, datagramPacket.getAddress());
            } else if (message.getType() == 5) {
                this.onlineUsers.remove(message.getSource());
            }
            this.ui.update(message);
        }
    }

    public void receiveUdpPacket() throws IOException {
        byte[] data = new byte[1000];
        DatagramPacket datagramPacket = new DatagramPacket(data, data.length);

        udpSocket.receive(datagramPacket);

        Message message = new Message(datagramPacket.getData());

        if (message.getType() == 2) {
            this.onlineUsers.put(message.getSource(), datagramPacket.getAddress());
        }

        this.ui.update(message);
    }
}
