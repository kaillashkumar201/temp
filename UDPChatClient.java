import java.util.*;
import java.net.*;
import java.io.*;
public class UDPChatClient {
    public static void main(String[] args) throws Exception{
        int c_port= 4050, s_port= 4060;
        byte[] buf= new byte[1024];
        DatagramSocket client= new DatagramSocket(c_port);
        DatagramPacket packet= new DatagramPacket(buf, buf.length);
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        InetAddress ia= InetAddress.getLocalHost();
        while(true){
            System.out.println("Enter msg: ");
            String str= br.readLine();
            buf= str.getBytes();
            client.send(new DatagramPacket(buf, str.length(), ia, s_port));
            if(str.equals("STOP")) break;

            client.receive(packet);
            String s= new String(packet.getData(), 0, packet.getLength());
            if(s.equals("STOP")) break;
            System.out.println("Server: "+ s);
        }
    }
}
