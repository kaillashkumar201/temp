import java.util.*;
import java.net.*;
import java.io.*;
public class DNSServer {
    public static void main(String[] args) throws Exception{
        int s_port= 9080, c_port= 9090;
        DatagramSocket server= new DatagramSocket(s_port);
        byte[] sendBuffer= new byte[1024];
        byte[] receiveBuffer= new byte[1024];
        InetAddress ia= InetAddress.getLocalHost();
        String ip[]={"165.165.80.80","165.165.79.1"};
        String name[]={"www.aptitude_source.com","www.Sharifguys.com"};
        DatagramPacket packet= new DatagramPacket(receiveBuffer, receiveBuffer.length);
        System.out.println("Server started");
        while(true){
            server.receive(packet);
            String msg= new String(packet.getData(), 0, packet.getLength());
            msg= msg.trim();
            int flag= 0;
            for(int i= 0; i<ip.length; i++){
                if(msg.equals(ip[i])){
                    sendBuffer= name[i].getBytes();
                    server.send(new DatagramPacket(sendBuffer, name[i].length(), ia, c_port));
                    flag= 1;
                    break;
                }
            }
            if(flag== 0){
                msg= "Invalid IP";
                sendBuffer= msg.getBytes();
                server.send(new DatagramPacket(sendBuffer, msg.length(), ia, c_port));
            }
        }
        //server.close();
    }
}
