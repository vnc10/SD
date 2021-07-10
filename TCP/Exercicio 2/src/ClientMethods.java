import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public interface ClientMethods {

    void addFile(DataOutputStream dataOutputStream, DataInputStream dataInputStream, String buffer) throws IOException;

    void deleteFile(DataOutputStream dataOutputStream, DataInputStream dataInputStream, String buffer) throws IOException;

    void getFilesList(DataOutputStream dataOutputStream, DataInputStream dataInputStream) throws IOException;

    void getFile(DataOutputStream dataOutputStream, DataInputStream dataInputStream, String buffer) throws IOException;
}
