package exercicio2;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class Server {
    public static void main(String[] args) {
        try (DatagramSocket datagramSocket = new DatagramSocket(6666)) {
            System.out.println("Servidor iniciado");
            while (true) {
                byte[] buffer = new byte[1024];

                DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length);
                datagramSocket.receive(datagramPacket);

                String data = new String(datagramPacket.getData(), 0, datagramPacket.getLength());
                String[] parts = data.split(";", 2);
                String fileName = parts[1];
                long fileLength = Long.parseLong(parts[0]);

                long number = fileLength / 1024 + 1;

                try {
                    DataOutputStream outStream = new DataOutputStream(new FileOutputStream(new String(fileName)));

                    byte[] bf = new byte[1024];
                    for (int i = 0; i < number; i++) {
                        DatagramPacket datagramFilePacket = new DatagramPacket(bf, bf.length);
                        datagramSocket.receive(datagramFilePacket);

                        byte[] received = new byte[datagramFilePacket.getLength()];
                        System.arraycopy(bf, 0, received, 0, datagramFilePacket.getLength());

                        outStream.write(received);
                        outStream.flush();
                    }
                    outStream.close();

                    byte[] checksum = new byte[1024];
                    DatagramPacket dgramCheckSumPacket = new DatagramPacket(checksum, checksum.length);
                    datagramSocket.receive(dgramCheckSumPacket);

                    InputStream inStream = new FileInputStream(fileName);
                    String oldMD5 = new String(dgramCheckSumPacket.getData(), 0, dgramCheckSumPacket.getLength());
                    String md5 = MD5.makeMD5(inStream.readAllBytes());
                    inStream.close();

                    System.out.println(oldMD5);
                    System.out.println(md5);

                    if (oldMD5.equals(md5)) {
                        DatagramPacket reply = new DatagramPacket("1".getBytes(), "1".getBytes().length, datagramPacket.getAddress(), datagramPacket.getPort());
                        datagramSocket.send(reply);
                    } else {
                        DatagramPacket reply = new DatagramPacket("2".getBytes(), "2".getBytes().length, datagramPacket.getAddress(), datagramPacket.getPort());
                        datagramSocket.send(reply);
                    }
                } catch (IOException e) {
                    e.printStackTrace();

                    DatagramPacket reply = new DatagramPacket("2".getBytes(), "2".getBytes().length, datagramPacket.getAddress(), datagramPacket.getPort());
                    datagramSocket.send(reply);
                }
            }
        } catch (SocketException e) {
            System.out.println("Socket: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        }
    }
}