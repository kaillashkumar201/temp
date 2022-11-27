import java.io.*;
import java.util.*;
import java.net.*;
public class TCPChatClient {
    public static void main(String[] args) throws Exception{
        Socket s= new Socket("localhost", 8888);
        BufferedReader br1= new BufferedReader(new InputStreamReader(s.getInputStream()));
        BufferedReader br2= new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pr= new PrintWriter(s.getOutputStream(), true);
        while(true){
            System.out.println("Enter msg: ");
            String msg= br2.readLine();
            if(msg.equals("STOP")){
                pr.println("STOP");
                break;
            }
            pr.println(msg);

            msg= br1.readLine();
            if(msg.equals("STOP")) break;
            System.out.println("SERVER: "+ msg);
        }
    }
}
