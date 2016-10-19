package cz.marek_b.mailer.service;

import cz.marek_b.mailer.dao.EmailDao;
import cz.marek_b.mailer.entity.Email;
import cz.marek_b.mailer.qualifiers.Mock;
import cz.marek_b.mailer.util.TimeManager;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
@Mock
public class MailSenderMockImpl implements MailSender {

    @Inject EmailDao emailDao;
    
    @Override
    public boolean sendMail(String recipient, String subject, String message) {
        Email email = new Email();
        email.setSender("23boro23@gmail.com");
        email.setReceiver(recipient);
        email.setSubject(subject);
        email.setText(message);
        email.setIsOutgoing(false);
        email.setCreated(TimeManager.getCurrentDate());
        
        emailDao.create(email);
        
        return true;
    }
    
}
