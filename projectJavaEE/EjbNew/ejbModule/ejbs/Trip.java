package ejbs;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Stateless
@LocalBean
@Entity
@Table(name="Trip")
public class Trip implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="tripid")
	public int tid;
	
	@Column(name="fromStat")
	public String fromStation; //fromStation,toStation,avalibleSeats,departureTime,arrivalTime
	
	@Column(name="toStat")
	public String toStation;
	
	@Column(name="avalible")
	public int avalibleSeats;

	@Column(name="detime")
	public String departureTime;
	
	@Column(name="arrtime")
	public String arrivalTime;
	
	
	@ManyToMany(mappedBy = "trips",fetch = FetchType.EAGER)
	private List<User> users;
	
	
	public List<User> retalloflistt() {
		return users;
	}

	public void addUsers(User useradd) {
		users.add(useradd);
	}

	public Trip() {	
		
		users=new ArrayList<User>();
	}
		
	public String getFromStation() {
		return fromStation;
	}
	public void setFromStation(String fromStation) {
		this.fromStation = fromStation;
	}
	public String getToStation() {
		return toStation;
	}
	public void setToStation(String toStation) {
		this.toStation = toStation;
	}
	public int getAvalibleSeats() {
		return avalibleSeats;
	}
	public void setAvalibleSeats(int avalibleSeats) {
		this.avalibleSeats = avalibleSeats;
	}
	public String getDepartureTime() {
		return departureTime;
	}
	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}
	public String getArrivalTime() {
		return arrivalTime;
	}
	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	public void setId(int id) {
		this.tid = id;
	}
		public int getId() {
		return tid;
	}
}