package cz.marek_b.mailer.resource;

import cz.marek_b.mailer.bean.NewEmailBean;
import cz.marek_b.mailer.dao.EmailDao;
import cz.marek_b.mailer.entity.Email;
import cz.marek_b.mailer.qualifiers.Mock;
import cz.marek_b.mailer.service.MailSender;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

@Path("/email")
@RequestScoped
public class EmailResource {
    
    @Inject
    private EmailDao emailDao;
    @Inject
    @Mock
    private MailSender mailSender;
    
    @GET
    @Path("/received/list")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Email> getAllReceivedEmails() {
        return emailDao.getAllReceivedEmails();
    }
    
    @GET
    @Path("/sent/list")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Email> getAllSentEmails() {
        return emailDao.getAllSentEmails();
    }
    
    @GET
    @Path("/received/count_unread")
    @Produces(MediaType.TEXT_PLAIN)
    public int countUnreadEmails() {
        return emailDao.countUnreadReceived();
    }
    
    @POST
    @Path("/send")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response sendEmail(NewEmailBean newEmailBean) {
        return (mailSender.sendMail(
            newEmailBean.getRecipient(),
            newEmailBean.getSubject(),
            newEmailBean.getMessage()
        ) ? Response.status(Status.OK) : Response.status(Status.INTERNAL_SERVER_ERROR)).build();
    }
    
    @GET
    @Path("/trash/list")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Email> getTrashList() {
        return emailDao.getTrashedEmails();
    }
    
    @POST
    @Path("/trash")
    public void trashEmail(long id) {
        Email email = emailDao.findById(id);
        if (email == null) {
            throw new IllegalStateException("Email does not exist!");
        }
        
        emailDao.trashEmail(email);
    }
    
    @POST
    @Path("/delete")
    public void deleteEmail(long id) {
        Email email = emailDao.findById(id);
        if (email == null) {
            throw new IllegalStateException("Email does not exist!");
        }
        
        emailDao.delete(email);
    }
    
}
