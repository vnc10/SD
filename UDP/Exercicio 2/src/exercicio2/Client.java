package exercicio2;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        DatagramSocket datagramSocket;

        try {
            String rootFile = "/home/vinicius/Downloads/SD_TESTE";
            File fileName = new File(rootFile);
            File[] listFiles = fileName.listFiles();
            assert listFiles != null;
            String[] files = new String[listFiles.length];

            int count = 0;
            for (File f : listFiles) {
                files[count] = f.getName();
                count++;
            }

            datagramSocket = new DatagramSocket();
            String host = "127.0.0.1";

            InetAddress serverAddr = InetAddress.getByName(host);
            int serverPort = 6666;

            do {
                for (String file : files) {
                    System.out.println(file);
                }
                Scanner reader = new Scanner(System.in);
                System.out.println("Digite o nome do arquivo: ");
                String chosenFile = reader.nextLine();
                int verify = 0;
                for (String file : files) {
                    if (!chosenFile.equals(file)) {
                        verify++;
                    }
                }
                if (verify == files.length) {
                    System.out.println("Arquivo não existe");
                    return;
                }

                File f = new File(rootFile + "/" + chosenFile);

                InputStream inStream = new FileInputStream(f);
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

                byteArrayOutputStream.write(String.valueOf(f.length()).getBytes());
                byteArrayOutputStream.write((";" + chosenFile).getBytes());

                long number = f.length() / 1024 + 1;

                byte[] data = byteArrayOutputStream.toByteArray();
                DatagramPacket request = new DatagramPacket(data, data.length, serverAddr, serverPort);
                datagramSocket.send(request);

                byte[] fileArray = inStream.readAllBytes();
                inStream.close();

                String md5 = MD5.makeMD5(fileArray);

                for (int i = 0; i < number; i++) {
                    int maxContentSize = i * 1024 + 1024;
                    if (maxContentSize > fileArray.length) maxContentSize = fileArray.length;

                    byte[] fileMsg = new byte[(maxContentSize) - (i * 1024)];
                    System.arraycopy(fileArray, i * 1024, fileMsg, 0, maxContentSize - i * 1024);

                    DatagramPacket fileRequest = new DatagramPacket(fileMsg, fileMsg.length, serverAddr, serverPort);
                    datagramSocket.send(fileRequest);
                }

                assert md5 != null;
                DatagramPacket checkSumRequest = new DatagramPacket(md5.getBytes(), md5.getBytes().length, serverAddr, serverPort);
                datagramSocket.send(checkSumRequest);

                byte[] buffer = new byte[1024];
                DatagramPacket reply = new DatagramPacket(buffer, buffer.length);

                datagramSocket.receive(reply);
                String response = new String(reply.getData(), 0, reply.getLength());

                if (response.equals("1"))
                    System.out.println("Arquivo enviado");
                else
                    System.out.println("Falha ao enviar aquivo");
                datagramSocket.close();
            } while (true);
        } catch (SocketException e) {
            System.out.println("Socket: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        }
    }
}