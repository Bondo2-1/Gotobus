package services;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
//import javax.websocket.server.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ejbs.Station;
import ejbs.User;


@Stateless
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/stations")
@PermitAll
public class StationService {
	
	@PersistenceContext (unitName = "tripbus")
	EntityManager eManager;
	
	
	@GET
	@Path("/listStations")
	public List<Station> listAllStation(){
		
		TypedQuery<Station> query=eManager.createQuery("SELECT e FROM Station e", Station.class);
		List<Station> stations=query.getResultList();
		
			return stations;
	}
	
	
	
	@POST
	@Path("/addstation")
	public String createStation(Station station){
		
			try {
				eManager.persist(station);
				return "success station";
			}
			catch (Exception e) {
				throw new EJBException(e);
			}		
	}
	
	@GET
	@Path("{id}")
	public Station getStation(@PathParam("id") int id){
			
		    return eManager.find(Station.class, id);
					
	}
	
	
	
	
	
}