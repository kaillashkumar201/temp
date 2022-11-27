import java.io.*;
import java.net.*;
public class FTPServer {
    public static void main(String[] args) throws Exception{
        ServerSocket ss= new ServerSocket(5050);
        Socket s= ss.accept();
        BufferedReader br= new BufferedReader(new InputStreamReader(s.getInputStream()));
        OutputStream os= s.getOutputStream();
        String path= br.readLine();
        File fp= new File(path);
        BufferedInputStream fin= new BufferedInputStream(new FileInputStream(fp));
        byte[] contents;
        long current= 0, fileLength= fp.length();
        int size= 10000;
        while(true){
            if(size < 10000) break;
            current+= size;
            if(current > fileLength) size= (int)(current- fileLength);
            contents= new byte[size];
            fin.read(contents, 0, size);
            os.write(contents);
        }
        os.flush();
        ss.close();
        s.close();
    }
}
