import java.io.DataOutputStream;
import java.io.IOException;

public interface ServerMethods {

    void exit();

    void returnTimeOrDate(int option, DataOutputStream dataOutputStream) throws IOException;

    void files(DataOutputStream dataOutputStream) throws IOException;

    void down(DataOutputStream dataOutputStream, String buffer) throws IOException;
}
