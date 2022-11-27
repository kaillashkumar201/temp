import java.io.*;
import java.net.*;
public class HTTP_GET_server {
    private static final String USER_AGENT= "Google Chrome";
    static String sendGET(String GET_URL) throws Exception{
        URL URLobj= new URL(GET_URL);
        HttpURLConnection con= (HttpURLConnection) URLobj.openConnection();
        con.setRequestMethod("GET"); // or "POST"
        con.setRequestProperty("User-Agent", USER_AGENT);
        int responseCode= con.getResponseCode();
        System.out.println("GET Response Code:: "+ responseCode);
        if(responseCode == HttpURLConnection.HTTP_OK){
            BufferedReader in= new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while((inputLine= in.readLine())!= null){
                response.append(inputLine);
            }
            in.close();
            return (response.toString());
        }else{
            System.out.println("GET Request not worked");
            return (null);
        }
    }
    public static void main(String[] args) throws Exception{
        ServerSocket ss= new ServerSocket(8080);
        while(true){
            Socket conSoc= ss.accept();
            BufferedReader ifc= new BufferedReader(new InputStreamReader(conSoc.getInputStream()));
            //DataOutputStream otc= new DataOutputStream(conSoc.getOutputStream()); 
            PrintWriter pr= new PrintWriter(conSoc.getOutputStream(), true);
            String cs= ifc.readLine() + '\n';
            System.out.println("RECEIVED: "+ cs);
            String GET_URL= cs;
            //otc.writeBytes(sendGET(GET_URL) + '\n');
            pr.println(sendGET(GET_URL) + '\n');
            System.out.println("GET DONE");
            break;
        }
        ss.close();
    }
}
