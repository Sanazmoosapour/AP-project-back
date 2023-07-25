package network;

import java.net.Socket;
import java.io.*;
import java.util.Scanner;
import controller.Controller;

public class clientHandler extends Thread{
    private Socket socket;
    public clientHandler(Socket socket){
        this.socket=socket;
    }
    @Override
    public void run(){
        try {
            DataInputStream dis=new DataInputStream(socket.getInputStream());
            DataOutputStream dos=new DataOutputStream(socket.getOutputStream());

            StringBuilder request=new StringBuilder();
            int c=dis.read();
            while(c!=0){
                request.append((char) c);
                c=dis.read();
            }

            //String request=dis.readUTF();

            Scanner sc=new Scanner(request.toString());

            String command=sc.nextLine();
            System.out.println("command: "+command);
            String data=sc.nextLine();
            System.out.println("data: "+data);
            //System.out.println("readsuccess");
            //String response=new Controller().run(command,data);
            String response=new Controller().run(command,data);
            System.out.println("socket");
            System.out.println(response);
            dos.writeBytes(response);
            dos.flush();

            dos.close();
            dis.close();
            socket.close();
            System.out.println("close");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
