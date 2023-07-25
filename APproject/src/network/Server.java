package network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public void start() throws Exception {

        ServerSocket serverSocket=new ServerSocket(8000);
        while (true){
            ///System.out.println("b");
            Socket socket=serverSocket.accept();
            System.out.println("socketaccept");
            new clientHandler(socket).start();
        }
    }
}
