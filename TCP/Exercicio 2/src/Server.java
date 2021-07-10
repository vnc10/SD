import java.io.*;

public class Server implements ServerMethods {

    private final String rootFile = "/home/vinicius/Downloads/SD_TESTE";
    private final File fileName = new File(rootFile);
    private final File[] files = fileName.listFiles();

    private static byte[] longToByteArray(final long i) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        dataOutputStream.writeLong(i);
        dataOutputStream.flush();
        return byteArrayOutputStream.toByteArray();
    }

    @Override
    public void addFile(DataInputStream dataInputStream, DataOutputStream dataOutputStream) throws IOException {
        System.out.println("Cliente disse: ADDFILE");
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        byteArrayOutputStream.write((byte) 2);
        byteArrayOutputStream.write((byte) 1);

        byte[] fileNameLength = new byte[1];
        dataInputStream.read(fileNameLength);

        byte[] fileName = new byte[fileNameLength[0]];
        dataInputStream.read(fileName);

        long fileLength = dataInputStream.readLong();

        try {
            DataOutputStream outStream = new DataOutputStream(new FileOutputStream(rootFile + "/" + new String(fileName)));

            byte[] bf = new byte[1];
            for (int i = 0; i < fileLength; i++) {
                dataInputStream.read(bf);
                outStream.write(bf);
                outStream.flush();
            }
            outStream.close();

            byteArrayOutputStream.write((byte) 1);
            dataOutputStream.write(byteArrayOutputStream.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();

            byteArrayOutputStream.write((byte) 2);
            dataOutputStream.write(byteArrayOutputStream.toByteArray());
        }
    }

    @Override
    public void deleteFile(DataInputStream dataInputStream, DataOutputStream dataOutputStream) throws IOException {
        System.out.println("Cliente disse: DELETE");
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        byteArrayOutputStream.write((byte) 2);
        byteArrayOutputStream.write((byte) 2);

        byte[] fileNameLength = new byte[1];
        dataInputStream.read(fileNameLength);

        byte[] fileName = new byte[fileNameLength[0]];
        dataInputStream.read(fileName);

        File file = new File(rootFile + "/" + new String(fileName));

        if (file.delete()) {
            byteArrayOutputStream.write((byte) 1);
            dataOutputStream.write(byteArrayOutputStream.toByteArray());
        } else {
            byteArrayOutputStream.write((byte) 2);
            dataOutputStream.write(byteArrayOutputStream.toByteArray());
        }
    }

    @Override
    public void getFilesList(DataOutputStream dataOutputStream) throws IOException {
        System.out.println("Cliente disse: GETFILESLIST");
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        byteArrayOutputStream.write((byte) 2);
        byteArrayOutputStream.write((byte) 3);
        byteArrayOutputStream.write((byte) 1);
        assert files != null;
        byteArrayOutputStream.write((byte) files.length);

        dataOutputStream.write(byteArrayOutputStream.toByteArray());

        for (File f : files) {
            ByteArrayOutputStream sendFile = new ByteArrayOutputStream();

            sendFile.write(f.getName().length());
            sendFile.write(f.getName().getBytes());

            dataOutputStream.write(sendFile.toByteArray());
            dataOutputStream.flush();
        }
    }

    @Override
    public void getFile(DataInputStream dataInputStream, DataOutputStream dataOutputStream) throws IOException {
        System.out.println("Cliente disse: GETFILE");
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        byteArrayOutputStream.write((byte) 2);
        byteArrayOutputStream.write((byte) 4);

        byte[] fileNameLength = new byte[1];
        dataInputStream.read(fileNameLength);

        byte[] fileName = new byte[fileNameLength[0]];
        dataInputStream.read(fileName);

        File f = new File(rootFile + "/" + new String(fileName));

        if (f.isFile() && f.canRead()) {
            InputStream inStream = new FileInputStream(f);

            byteArrayOutputStream.write((byte) 1);
            byteArrayOutputStream.write(longToByteArray(f.length()));
            byteArrayOutputStream.write(inStream.readAllBytes());

            inStream.close();

            dataOutputStream.write(byteArrayOutputStream.toByteArray());
        } else {
            byteArrayOutputStream.write((byte) 2);
            dataOutputStream.write(byteArrayOutputStream.toByteArray());
        }
    }
}
