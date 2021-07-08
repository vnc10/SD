import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Client implements ClientMethods {
    public Client() {
    };

    @Override
    public void exit() {
        System.out.println("Desconectado com sucesso");
    }

    @Override
    public void printTimeOrDate(String buffer) {
        System.out.println(buffer);
    }


    @Override
    public void files(DataInputStream dataInputStream) throws IOException {
        int number = Integer.parseInt(dataInputStream.readUTF());
        System.out.println("Servidor disse: são " + number + " arquivos:");
        for(int i = 0; i < number; i++) {
            String file = dataInputStream.readUTF();
            System.out.println("---> " + file);
        }
    }

    @Override
    public void down(String fileName, DataInputStream dataInputStream) throws IOException {
        long number = dataInputStream.readLong();

        if (number == 0) {
            System.out.print("Arquivo Inválido \n");
        } else {
            try {
                DataOutputStream outStream = new DataOutputStream(new FileOutputStream(fileName));

                byte[] bf = new byte[1];
                for (int i = 0; i < number; i++) {
                    dataInputStream.read(bf);
                    outStream.write(bf);
                    outStream.flush();
                }
                outStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Arquivo " + fileName + " baixado com sucesso \n");
        }
    }
}
