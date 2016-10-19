package cz.marek_b.mailer.dao;

import cz.marek_b.mailer.entity.Email;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class EmailDaoImpl implements EmailDao {

    @PersistenceContext(unitName = "mailer_pu")
    private EntityManager em;
    
    @Override
    public void create(Email email) {
        em.persist(email);
        em.flush();
    }

    @Override
    public void delete(Email email) {
        em.remove(email);
        em.flush();
    }

    @Override
    public List<Email> getAllReceivedEmails() {
        return em.createNamedQuery(Email.QUERY_FIND_ALL_RECEIVED, Email.class)
            .getResultList();
    }
    
    @Override
    public List<Email> getAllSentEmails() {
        return em.createNamedQuery(Email.QUERY_FIND_ALL_SENT, Email.class)
            .getResultList();
    }

    @Override
    public Email findById(long id) {
        return em.find(Email.class, id);
    }

    @Override
    public int countUnreadReceived() {
        return ((Number) em.createNamedQuery(Email.QUERY_COUNT_UNREAD_RECEIVED)
            .getSingleResult()).intValue();
    }
    
}
