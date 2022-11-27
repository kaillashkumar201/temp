import java.net.*;
import java.util.*;
import java.io.*;
public class PingServer {
    public static void main(String[] args) throws Exception{
        ServerSocket ss= new ServerSocket(2156);
        Socket s= ss.accept();
        if(s.isConnected())
        System.out.println("Connected to client at: "+ s.getInetAddress());
        DataInputStream din= new DataInputStream(s.getInputStream());
        DataOutputStream dout= new DataOutputStream(s.getOutputStream());
        String Packets= "";
        String Address= "";
        if(din.readUTF().equals("P")){
            Packets= new StringBuilder().append(din.readInt()).toString();
            System.out.println("Requested no. of packets to ping: "+ Packets);
        }

        if(din.readUTF().equals("A")){
            Address= din.readUTF();
            System.out.println("Requested address to ping: "+ Address);
        }

        System.out.println("[INFO]: Executing ping -n "+Packets+" "+ Address);
        Process PingProc= new ProcessBuilder("ping", "-n", Packets, Address).start();
        BufferedReader br= new BufferedReader(new InputStreamReader(PingProc.getInputStream()));
        String PingOut= br.readLine();
        while(PingOut!= null){
            dout.writeUTF(PingOut);
            PingOut= br.readLine();
        }
        br.close();
        din.close();
        dout.close();
        s.close();
        ss.close();
    }
}
