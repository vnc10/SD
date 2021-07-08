import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Server implements ServerMethods {

    private final String rootFile = "/home/vinicius/Downloads/SD_TESTE";
    private final File fileName = new File(rootFile);
    private final File[] files = fileName.listFiles();

    public Server() {
    }

    @Override
    public void exit() {
        System.out.println("Cliente saiu. \n");
    }

    @Override
    public void returnTimeOrDate(int option, DataOutputStream dataOutputStream) throws IOException {
        DateTimeFormatter dateTimeFormatter;
        if (option == 1) {
            dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        } else {
            dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        }
        LocalDateTime now = LocalDateTime.now();
        System.out.print("Servidor disse ao Cliente: " + dateTimeFormatter.format(now) + "\n");
        dataOutputStream.writeUTF(dateTimeFormatter.format(now));
    }

    @Override
    public void files(DataOutputStream dataOutputStream) throws IOException {
        assert files != null;
        dataOutputStream.writeUTF(String.valueOf(files.length));
        for (File f : files) {
            dataOutputStream.writeUTF(f.getName());
        }
    }

    @Override
    public void down(DataOutputStream dataOutputStream, String buffer) throws IOException {
        File f = new File(rootFile + "/" + buffer.substring(5));

        if (f.isFile() && f.canRead()) {
            InputStream inStream = new FileInputStream(f);
            dataOutputStream.writeLong(f.length());
            byte[] buffered = new byte[1];

            for (int i = 0; i < f.length(); i++) {
                inStream.read(buffered);
                dataOutputStream.write(buffered);
                dataOutputStream.flush();
            }
            inStream.close();
        } else dataOutputStream.writeLong(0);
    }

}
