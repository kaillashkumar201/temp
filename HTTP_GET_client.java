import java.io.*;
import java.net.*;
public class HTTP_GET_client{
    public static void main(String[] args) throws Exception{
        BufferedReader ifu= new BufferedReader(new InputStreamReader(System.in));
        Socket clientSocket= new Socket("localhost", 8080);
        //DataOutputStream ots= new DataOutputStream(clientSocket.getOutputStream());
        PrintWriter pr= new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader ifs= new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        System.out.println("GET URL: ");
        String sentence= ifu.readLine();
        //ots.writeBytes(sentence+'\n');
        pr.println(sentence+'\n');
        String ms= ifs.readLine();
        System.out.println("Response:\n"+ ms);
        clientSocket.close();
    }
}