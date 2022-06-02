package services;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.annotation.security.PermitAll;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.jws.soap.SOAPBinding.Use;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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

import com.sun.org.apache.xalan.internal.xsltc.compiler.Pattern;

import javax.persistence.*;

import ejbs.Notification;
import ejbs.Station;
import ejbs.Trip;
import ejbs.User;


@Stateless
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/trip")
@PermitAll
@TransactionManagement(TransactionManagementType.BEAN)
public class TripService {
	
	
	@PersistenceContext (unitName = "tripbus")
	EntityManager eManager;
	
	@Inject
	public UserTransaction tx;
	
	
	
	
public static class BookParam{
		
		public int userID;
		public int tripID;
		
		public int getUserId() {
			return userID;
		}
		public void setUserId(int userId) {
			this.userID = userId;
		}
		public int getTripId() {
			return tripID;
		}
		
		public void setTripId(int tripId) {
			this.tripID = tripId;
		}
			
	}




public static class Searchtrip {
	
	
	public String fromdate;	//from date states the date of the starting trip
	
	
	public String todate;	//to date states the date of the starting trip
	
	
	public int fromstation;
	
	
	public int tostation;
	
	public String getFromdate() {
		return fromdate;
	}

	public void setFromdate(String fromdate) {
		this.fromdate = fromdate;
	}

	public String getTodate() {
		return todate;
	}

	public void setTodate(String todate) {
		this.todate = todate;
	}

	public int getFromstation() {
		return fromstation;
	}

	public void setFromtation(int fromstation) {
		this.fromstation = fromstation;
	}

	public int getTostation() {
		return tostation;
	}

	public void setTostation(int tostation) {
		this.tostation = tostation;
	}
}


	
	
	
	
	@POST
	@Path("/addUsertrip")
	public String createtrip(Trip trip) throws IllegalStateException, SecurityException, SystemException {
		
			try {
				tx.begin();
				eManager.persist(trip);
				tx.commit();
				return "success trip";
			}
			catch (Exception e) {
				tx.rollback();
				throw new EJBException(e);
			}
			
	}
	
	
	@POST
	@Path("/searchtrip")
	public List<Trip> searchtrip(Searchtrip setrip){
		Station f =eManager.find(Station.class, setrip.getFromstation());
		Station t =eManager.find(Station.class, setrip.getTostation());
		  
		String fromname =f.getName();
		String toname =t.getName();
		
		String fromdate =setrip.fromdate;
		String todate =setrip.todate;
		
		 List<Trip> list=eManager.createQuery("SELECT e FROM Trip e WHERE e.toStation = :toname AND e.fromStation = :fStat AND e.departureTime >= '"+fromdate+"%'AND e.arrivalTime <='"+todate+"%'", Trip.class)
		 .setParameter("toname", toname)
		 .setParameter("fStat", fromname)
		 .getResultList(); 		 
		 return list; 
	}
	
	
	
	
	
	@GET
	@Path("/listalltrips")
	public List<Trip> listAlltrips(){
		
		TypedQuery<Trip> query=eManager.createQuery("SELECT e FROM Trip e", Trip.class);
		List<Trip> trip=query.getResultList();
		
			return trip;
	}
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	@POST
	@Path("/booktrip")
	public String booktrip(BookParam bookparam ) throws IllegalStateException, SecurityException, SystemException {
		try {
		tx.begin();
		User user=eManager.find(User.class,bookparam.userID);
		Trip trip =eManager.find(Trip.class,bookparam.tripID);
		
		
		
		Date date= new Date();
		SimpleDateFormat formatt=new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		String strdate=formatt.format(date);
		Notification tempnotify=new Notification();
		
		if (trip.avalibleSeats==0)
		{
			tempnotify.messageContent="Sorry, Trip " +trip.fromStation+" to " +trip.toStation +"have no available seats";
			tempnotify.dateMessage=strdate;
			
			eManager.persist(tempnotify);
			user.addnotifications(tempnotify);
			tempnotify.putuser(user);
			
			eManager.merge(user);
			tx.commit();
			return tempnotify.messageContent;
		}
		
		tempnotify.dateMessage=strdate;
		tempnotify.messageContent="you have book a trip from "+trip.fromStation+" to "+trip.toStation +" passed " ;
		eManager.persist(tempnotify);
		
		user.addnotifications(tempnotify);
		tempnotify.putuser(user);
		
		user.addTrips(trip);		
		trip.addUsers(user);
		trip.avalibleSeats=trip.avalibleSeats-1;
		eManager.merge(user);
		eManager.merge(trip);
		
		tx.commit();
		return "paaaass";
		
		}
		catch(Exception e) {
			tx.rollback();
			throw new EJBException(e);
			
		}
	}
	
	@GET
	@Path("/viewusertrip/{id}")
	public List<Trip> viewtrip(@PathParam("id") int id) throws IllegalStateException, SecurityException, SystemException {
		try {
			tx.begin();
			List<Trip> templist=eManager.find(User.class, id).revaloflist();
			tx.commit();
		return templist;
		
		}
		catch(Exception e) {
			tx.rollback();
			return null;
			
		}
	}
	
		
	
	
	
	
		
}




