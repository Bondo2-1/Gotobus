package services;

import java.text.SimpleDateFormat;
import java.util.Date;
//import java.sql.Date;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ejbs.Notification;
import ejbs.Trip;
import ejbs.User;

@Stateless
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/notify")
@PermitAll
@TransactionManagement(TransactionManagementType.BEAN)
public class NotificationService {
	
	@PersistenceContext (unitName = "tripbus")
	EntityManager eManager;
	
	@Inject
	public UserTransaction tx;
	
	@GET
	@Path("/notifshow/{id}")
	public List<Notification> notific(@PathParam("id") int id) throws IllegalStateException, SecurityException, SystemException 
	{
		try {
			tx.begin();
			List<Notification> tmplist=eManager.find(User.class, id).revallloflisttt();
			tx.commit();
			return tmplist;
		}
		catch (Exception e) {
			tx.rollback();
			return null;
		}
	}	
}
