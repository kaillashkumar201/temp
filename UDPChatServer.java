import java.util.*;
import java.net.*;
import java.io.*;
public class UDPChatServer {
    public static void main(String[] args) throws Exception{
        int c_port= 4050, s_port= 4060;
        DatagramSocket server= new DatagramSocket(s_port);
        byte[] buf= new byte[1024];
        DatagramPacket packet= new DatagramPacket(buf, buf.length);
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        InetAddress ia= InetAddress.getLocalHost();
        while(true){
            server.receive(packet);
            String str= new String(packet.getData(), 0, packet.getLength());
            if(str.equals("STOP")) break;
            System.out.println("Client: "+ str);

            System.out.println("Enter msg: ");
            str= br.readLine();
            buf= str.getBytes();
            server.send(new DatagramPacket(buf, str.length(), ia, c_port));
            if(str.equals("STOP")) break;
        }
        server.close();
    }
}
