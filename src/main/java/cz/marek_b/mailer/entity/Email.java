package cz.marek_b.mailer.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "email")
@NamedQueries({
    @NamedQuery(name = Email.QUERY_FIND_ALL_RECEIVED, query = "SELECT e FROM Email e WHERE e.isOutgoing = false ORDER BY e.created DESC"),
    @NamedQuery(name = Email.QUERY_FIND_ALL_SENT, query = "SELECT e FROM Email e WHERE e.isOutgoing = true ORDER BY e.created DESC"),
    @NamedQuery(name = Email.QUERY_COUNT_UNREAD_RECEIVED, query = "SELECT COUNT(e.id) FROM Email e WHERE e.isOutgoing = false AND e.read = false")
})
public class Email implements Serializable {
    private static final long serialVersionUID = 7827615744138606045L;
    
    public static final String QUERY_FIND_ALL_RECEIVED = "Email.findAllReceived";
    public static final String QUERY_FIND_ALL_SENT = "Email.findAllSent";
    public static final String QUERY_COUNT_UNREAD_RECEIVED = "Email.countUnreadReceived";
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "sender", nullable = false)
    private String sender;
    @Column(name = "receiver", nullable = false)
    private String receiver;
    @Column(name = "subject", nullable = true, length = 255)
    private String subject;
    @Column(name = "text", nullable = true)
    @Lob
    private String text;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created", nullable = false)
    private Date created;
    @Column(name = "is_outgoing", nullable = false)
    private boolean isOutgoing;
    @Column(name = "is_read", nullable = false)
    private boolean read = false;
    
    public Email() {}
    
    public Email(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public boolean isIsOutgoing() {
        return isOutgoing;
    }

    public void setIsOutgoing(boolean isOutgoing) {
        this.isOutgoing = isOutgoing;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + (int) (this.id ^ (this.id >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Email other = (Email) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Email{" + 
                    "id=" + id + 
                    ", sender=" + sender + 
                    ", receiver=" + receiver + 
                    ", subject=" + subject + 
                    ", text=" + text + 
                    ", created=" + created + 
                    ", isOutgoing=" + isOutgoing + 
                    ", read=" + read +
                '}';
    }
    
}
