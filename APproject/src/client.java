import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class client {
    public static void main(String[] args) throws IOException {
        Socket s=new Socket("127.0.0.1",8000);
        DataOutputStream dos=new DataOutputStream(s.getOutputStream());
        DataInputStream dis=new DataInputStream(s.getInputStream());
        dos.writeUTF("signUp\nName:sanaz&&FamilyName:moosapour&&EmailAddress:sa.82mo@yahoo.com&&Password:3045Sanaz&&Cash:200");
        dos.flush();
        System.out.println(dis.readUTF());
        dos.close();
        dis.close();
        s.close();

    }
}
