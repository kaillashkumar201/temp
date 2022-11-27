import java.io.*;
import java.net.*;
public class FTPClient {
    public static void main(String[] args) throws Exception{
        Socket s= new Socket("localhost", 5050);
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pr= new PrintWriter(s.getOutputStream(), true);
        System.out.println("Enter file path: ");
        String x= br.readLine();
        pr.println(x);
        InputStream is= s.getInputStream();
        BufferedOutputStream bos= new BufferedOutputStream(new FileOutputStream("C:\\Users\\kaill\\OneDrive\\Desktop\\Coding\\newimg.jpg"));
        int bytesRead= 0;
        byte[] contents= new byte[10000];
        while((bytesRead= is.read(contents))!= -1){
            bos.write(contents, 0, bytesRead);
        }
        bos.close();
        s.close();
        System.out.println("File saved successfully");
    }
}
