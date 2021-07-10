import java.io.*;

public class Client implements ClientMethods {

    private static byte[] longToByteArray(final long i) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        dataOutputStream.writeLong(i);
        dataOutputStream.flush();
        return byteArrayOutputStream.toByteArray();
    }

    @Override
    public void addFile(DataOutputStream dataOutputStream, DataInputStream dataInputStream, String buffer) throws IOException {
        String fileName = buffer.substring(8);
        File f = new File(fileName);

        if (f.isFile() && f.canRead()) {
            InputStream inputStream = new FileInputStream(f);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

            byteArrayOutputStream.write((byte) 1);
            byteArrayOutputStream.write((byte) 1);
            byteArrayOutputStream.write((byte) fileName.length());
            byteArrayOutputStream.write(fileName.getBytes());
            byteArrayOutputStream.write(longToByteArray(f.length()));
            byteArrayOutputStream.write(inputStream.readAllBytes());

            inputStream.close();

            dataOutputStream.write(byteArrayOutputStream.toByteArray());

            byte[] response = new byte[3];
            dataInputStream.read(response);

            if (response[2] == 1) System.out.println("SUCESSO \n");
            else System.out.println("ERRO");
        } else System.out.print("Arquivo Inválido \n");
    }

    @Override
    public void deleteFile(DataOutputStream dataOutputStream, DataInputStream dataInputStream, String buffer) throws IOException {
        String fileName = buffer.substring(7);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        byteArrayOutputStream.write((byte) 1);
        byteArrayOutputStream.write((byte) 2);
        byteArrayOutputStream.write((byte) fileName.length());
        byteArrayOutputStream.write(fileName.getBytes());

        dataOutputStream.write(byteArrayOutputStream.toByteArray());

        byte[] response = new byte[3];
        dataInputStream.read(response);

        if (response[2] == 1) System.out.println("SUCESSO");
        else System.out.println("ERRO");
    }

    @Override
    public void getFilesList(DataOutputStream dataOutputStream, DataInputStream dataInputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        byteArrayOutputStream.write((byte) 1);
        byteArrayOutputStream.write((byte) 3);

        dataOutputStream.write(byteArrayOutputStream.toByteArray());

        byte[] response = new byte[3];
        dataInputStream.read(response);

        if (response[2] == 1) {
            byte[] number = new byte[1];
            dataInputStream.read(number);

            System.out.println("Número de Arquivos: " + number[0]);

            for (int i = 0; i < number[0]; i++) {
                byte fileNameLength = dataInputStream.readByte();

                byte[] fileName = new byte[fileNameLength];
                dataInputStream.read(fileName, 0, fileNameLength);
                System.out.println("---> " + new String(fileName));
            }
        } else System.out.println("ERRO");
    }

    @Override
    public void getFile(DataOutputStream dataOutputStream, DataInputStream dataInputStream, String buffer) throws IOException {
        String fileName = buffer.substring(8);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        byteArrayOutputStream.write((byte) 1);
        byteArrayOutputStream.write((byte) 4);
        byteArrayOutputStream.write((byte) fileName.length());
        byteArrayOutputStream.write(fileName.getBytes());

        dataOutputStream.write(byteArrayOutputStream.toByteArray());

        byte[] response = new byte[3];
        dataInputStream.read(response);

        if (response[2] == 1) {
            DataOutputStream outStream = new DataOutputStream(new FileOutputStream(fileName));

            try {
                long fileLength = dataInputStream.readLong();

                byte[] bf = new byte[1];
                for (int i = 0; i < fileLength; i++) {
                    dataInputStream.read(bf);
                    outStream.write(bf);
                    outStream.flush();
                }
                outStream.close();
                System.out.println("SUCESSO");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else System.out.println("ERRO");
    }

}
