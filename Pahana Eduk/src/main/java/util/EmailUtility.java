package util;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailUtility {
    public static void sendEmail(String to, String subject, String messageText) throws Exception {
        String host = "smtp.gmail.com";
        final String user = "kavishkasupunpathiraja@gmail.com"; // your Gmail
        final String password = "zdglcgnmqdvotlld"; // app password without spaces/line breaks

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");
        
        // Additional properties for better reliability
        props.put("mail.smtp.ssl.trust", host);
        props.put("mail.smtp.connectiontimeout", "5000");
        props.put("mail.smtp.timeout", "5000");

        Session session = Session.getInstance(props,
            new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(user, password);
                }
            });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(subject);
            
            // Use setContent instead of setText for better compatibility
            message.setContent(messageText, "text/plain");
            
            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException("Failed to send email", e);
        }
    }
}