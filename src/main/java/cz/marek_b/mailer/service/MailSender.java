package cz.marek_b.mailer.service;

public interface MailSender {
    
    boolean sendMail(String recipient, String subject, String message);
    
}
