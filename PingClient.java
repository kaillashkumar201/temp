import java.util.*;
import java.net.*;
import java.io.*;
public class PingClient {
    public static void main(String[] args) throws Exception{
        Socket s= new Socket("localhost", 2156);
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        if(s.isConnected())
        System.out.println("Connected to server at: "+ s.getInetAddress());
        Scanner inp= new Scanner(System.in);
        DataInputStream din= new DataInputStream(s.getInputStream());
        DataOutputStream dout= new DataOutputStream(s.getOutputStream());
        System.out.println("Enter no. of packets to send: ");
        int Packets= inp.nextInt();
        System.out.println("Enter address to ping: ");
        String Address= br.readLine();
        dout.writeUTF("P");
        dout.writeInt(Packets);
        dout.writeUTF("A");
        dout.writeUTF(Address);
        String PingProcOut= din.readUTF();
        try{
            do{
                System.out.println("[OUTPUT]: "+ PingProcOut);
            }while((PingProcOut= din.readUTF())!= null);
        }catch(EOFException e){}
        System.out.println("Completed Ping Process");
        dout.close();
        din.close();
        s.close();
    }
}
