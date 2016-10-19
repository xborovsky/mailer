package cz.marek_b.mailer.dao;

import cz.marek_b.mailer.entity.Email;
import java.util.List;

public interface EmailDao {
    
    void create(Email email);
    
    void delete(Email email);
    
    List<Email> getAllReceivedEmails();
    
    List<Email> getAllSentEmails();
    
    Email findById(long id);
    
    int countUnreadReceived();
    
}
