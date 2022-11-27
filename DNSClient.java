import java.util.*;
import java.net.*;
import java.io.*;
public class DNSClient {
    public static void main(String[] args) throws Exception{
        int s_port= 9080, c_port= 9090;
        DatagramSocket client= new DatagramSocket(c_port);
        byte[] sendBuffer= new byte[1024];
        byte[] receiveBuffer= new byte[1024];
        InetAddress ia= InetAddress.getLocalHost();
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        DatagramPacket packet= new DatagramPacket(receiveBuffer, receiveBuffer.length);
        while(true){
            System.out.println("Enter the IP address: ");
            String ip= br.readLine();
            sendBuffer= ip.getBytes();
            client.send(new DatagramPacket(sendBuffer, ip.length(), ia, s_port));

            client.receive(packet);
            String msg= new String(packet.getData(), 0, packet.getLength());
            System.out.println("The corresponding URL is: "+ msg);
        }
        //client.close();
    }
}
