package ddsutn.Business.Notificacion;

import ddsutn.Business.Persona.Contacto;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MAIL implements Notificar {

    @Override
    public void notificar(Contacto contacto, String mensaje) throws IOException {

        Properties prop = new Properties();
        InputStream input = new FileInputStream("src/main/java/ddsutn/Business/Notificacion/Config/MAIL.prop");
        prop.load(input);

        String to = contacto.getEmail();
        String from = prop.getProperty("mail_from");
        String host = prop.getProperty("host");

        Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", prop.getProperty("port"));
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        // Get the Session object and pass username and password
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, prop.getProperty("password"));
            }
        });

        // Used to debug SMTP issues
        session.setDebug(true);

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To, subject & message
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(prop.getProperty("subject"));
            message.setText(mensaje);

            // Send message
            Transport.send(message);

        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}

