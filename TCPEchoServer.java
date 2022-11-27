import java.io.*;
import java.util.*;
import java.net.*;
public class TCPEchoServer {
    public static void main(String[] args) throws Exception{
        ServerSocket ss= new ServerSocket(5050);
        Socket s= ss.accept();
        BufferedReader br= new BufferedReader(new InputStreamReader(s.getInputStream()));
        while(true){
            String msg= br.readLine();
            if(msg.equals("STOP")) break;
            System.out.println("Client: "+ msg);
        }
    }
}
