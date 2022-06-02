package services;

import java.util.List;
import java.util.Set;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.jws.soap.SOAPBinding.Use;
import javax.persistence.Entity;
import javax.persistence.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Table;
import javax.persistence.TypedQuery;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.sun.org.apache.bcel.internal.generic.Select;

import ejbs.Station;
import ejbs.Trip;
import ejbs.User;


@Stateless
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/users")
@PermitAll
@TransactionManagement(TransactionManagementType.BEAN)
public class UserServices {
	
	@PersistenceContext (unitName = "tripbus")
	EntityManager eManager;
	
	@Inject
	public UserTransaction tx;
	
	
	
	
	@POST
	@Path("/ulogin")
	public boolean userlogin(User user){
		
		TypedQuery<User> query=eManager.createQuery("SELECT e FROM User e", User.class);
		List<User> usersList=query.getResultList();
		
		for(int i=0;i<usersList.size();i++)
		{
			
			if ((user.getUsername().equals(usersList.get(i).getUsername()))&&(user.getPassword().equals(usersList.get(i).getPassword())))
			{
				return true;
			}
		}
		return false;
			
	}
	
	
	@POST
	@Path("/registeruser")
	public String register(User user) throws IllegalStateException, SecurityException, SystemException{
		try {
			
			tx.begin();
			eManager.persist(user);
			tx.commit();
			return "azozz success";
		}
		catch (Exception e) {
			tx.rollback();
			throw new EJBException(e);
		}
	}
	

	
	@GET
	@Path("/listallUsers")
	public List<User> listAllUsers(){
		
		TypedQuery<User> query=eManager.createQuery("SELECT e FROM User e", User.class);
		List<User> users=query.getResultList();
		
			return users;
	} 
	
	
}