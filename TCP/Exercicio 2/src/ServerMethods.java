import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public interface ServerMethods {

    void addFile(DataInputStream dataInputStream, DataOutputStream dataOutputStream) throws IOException;

    void deleteFile(DataInputStream dataInputStream, DataOutputStream dataOutputStream) throws IOException;

    void getFilesList(DataOutputStream dataOutputStream) throws IOException;

    void getFile(DataInputStream dataInputStream, DataOutputStream dataOutputStream) throws IOException;
}
