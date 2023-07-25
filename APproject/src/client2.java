import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class client2 {
    public static void main(String[] args) throws IOException {
        Socket s=new Socket("127.0.0.1",8000);
        DataOutputStream dos=new DataOutputStream(s.getOutputStream());
        DataInputStream dis=new DataInputStream(s.getInputStream());
        dos.writeUTF("infochange\noldName:sanaz&&newname:ali&&newfamilyname:aryayii&&email:sara.mosapour@yahoo.com");
        dos.flush();
        System.out.println(dis.readUTF());
        dos.close();
        dis.close();
        s.close();

    }
}