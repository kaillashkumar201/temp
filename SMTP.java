import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class SMTP {
    public static void main(String[] args){
        Scanner inp= new Scanner(System.in);
        String SenderUser= "kaillashkumar2003";
        String SenderMail= "kaillashkumar2003@gmail.com";
        String SenderPassword = "vwhttksyykvgzbka";
        System.out.println("Destination address: ");
        String toMail= inp.nextLine();
        String toHost= "smtp.gmail.com";

        Properties SessionProperties= new Properties();
        SessionProperties.put("mail.smtp.auth", "true");
        SessionProperties.put("mail.smtp.starttls.enable", "true");
        SessionProperties.put("mail.smtp.host", toHost);
        SessionProperties.put("mail.smtp.port", 587);

        Session CurrentSession= Session.getInstance(SessionProperties,
        new javax.mail.Authenticator(){
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(SenderMail, SenderPassword);
            }
        });

        try{
            Message thisMessage= new MimeMessage(CurrentSession);
            thisMessage.setFrom(new InternetAddress(SenderMail));
            thisMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toMail));
            System.out.println("Enter subject for the mail: ");
            String Subject= inp.nextLine();
            System.out.println("Enter body of the mail: ");
            String body= inp.nextLine();
            thisMessage.setSubject(Subject);
            thisMessage.setContent(body, "text/html");
            Transport.send(thisMessage);
            System.out.println("Mail has been sent successfully");
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
