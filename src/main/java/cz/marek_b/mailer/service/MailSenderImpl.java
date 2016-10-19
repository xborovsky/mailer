package cz.marek_b.mailer.service;

import java.util.Properties;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Stateless
public class MailSenderImpl implements MailSender {

    private static final String USERNAME = "*****";
    private static final String PASSWORD = "*****";

    private Properties mailProps;

    @PostConstruct
    public void init() {
        mailProps = new Properties();
        mailProps.put("mail.smtp.auth", "true");
        mailProps.put("mail.smtp.starttls.enable", "true");
        mailProps.put("mail.smtp.host", "smtp.gmail.com");
        mailProps.put("mail.smtp.port", "587");
    }

    @Override
    public boolean sendMail(String recipient, String subject, String message) {
        Session session = Session.getInstance(mailProps,
                new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(USERNAME, PASSWORD);
                    }
                });

        try {
            Message mail = new MimeMessage(session);
            mail.setFrom(new InternetAddress(USERNAME));
            mail.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
            mail.setSubject(subject);
            mail.setText(message);

            Transport.send(mail);
            
            return true;
        } catch (MessagingException e) {
            System.out.println(e.getMessage());
            System.out.println(e);
            return false;
        }
    }

}
