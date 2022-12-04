import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class Email {
    private String recipient = "";
    private String sender = "";
    private String host = "localhost";
    private Properties properties = System.getProperties();
    private Session session;


    public Email(String recipient, String sender){
        this.recipient = recipient;
        this.sender = sender;
        properties.setProperty("mail.smtp.host", host);
        session = Session.getDefaultInstance(properties);
    }

    
    public void sendEmail(){
        try{
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(sender));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));

            // Set Subject: header field
            message.setSubject("This is the Subject Line!");

            // Send the actual HTML message, as big as you like
            message.setContent("<h1>This is actual message</h1>", "text/html");

            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch(MessagingException e){
            e.printStackTrace();
        }

    }
    
}
