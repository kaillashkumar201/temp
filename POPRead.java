import java.util.*;
import javax.mail.*;
import java.io.*;
import java.net.*;
public class POPRead {
    public static void check(String host, String storeType, String user, String password){
        try{
            Properties properties= new Properties();
            properties.put("mail.pop3.host", host);
            properties.put("mail.pop3.port", "995");
            properties.put("mail.pop3.starttls.enable", "true");

            Session emailSession= Session.getDefaultInstance(properties);
            Store store= emailSession.getStore("pop3s");

            store.connect(host, user, password);

            Folder emailFolder= store.getFolder("INBOX");
            emailFolder.open(Folder.READ_ONLY);

            Message[] messages= emailFolder.getMessages();

            for(int i= 0; i<messages.length; i++){
                Message message= messages[i];
                System.out.println("-------------------");
                System.out.println("Email no: "+ (i+1));
                System.out.println("Subject: "+ message.getSubject());
                System.out.println("From: "+ message.getFrom()[0]);
                System.out.println("Body: "+ message.getContent().toString());
                if(i>5) break;
            }

            store.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args){
        String host= "pop.gmail.com";
        String mailStoreType= "pop3";
        String username = "kaillashkumar2003@gmail.com";
        String password = " vwhttksyykvgzbka ";

        check(host, mailStoreType, username, password);
    }
}
