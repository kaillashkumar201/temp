import java.util.*;
import java.io.*;
import java.net.*;
public class TCPEchoClient {
    public static void main(String[] args) throws Exception{
        Socket s= new Socket("localhost", 5050);
        PrintWriter pr= new PrintWriter(s.getOutputStream(), true);
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        while(true){
            System.out.println("Enter msg: ");
            String msg= br.readLine();
            if(msg.equals("STOP")){
                pr.println("STOP");
                break;
            }
            pr.println(msg);
        }
    }
}
