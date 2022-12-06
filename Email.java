import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class Email {

    public Email()
    {}

    public static void sendEmail(String to , String msg)
    {
        final String username = "testingschoolprojects@outlook.com";
        final String password = "testemail123";
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp-mail.outlook.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
          new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
          });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("testingschoolprojects@outlook.com"));
            message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(to));
            message.setSubject("Confirmation Movie Ticket System");
            message.setText(msg);

            Transport.send(message);

            System.out.println("Done");

        } 

        catch (MessagingException e) 
        {
            // throw new RuntimeException(e);
            e.printStackTrace();
        }
    }


    // public static void main(String[] args) 
    // {
    //     String to = "testingschoolprojects@outlook.com";
    //     sendEmail(to , "hello");
    // }

}
