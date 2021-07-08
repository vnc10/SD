import java.io.DataInputStream;
import java.io.IOException;

public interface ClientMethods {

    void exit();

    void printTimeOrDate(String buffer);

    void files(DataInputStream dataInputStream) throws IOException;

    void down(String fileName, DataInputStream dataInputStream) throws IOException;

}
