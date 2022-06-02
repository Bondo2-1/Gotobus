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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Stateless
@LocalBean
@Entity
@Table(name="User")
public class User implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int userId;
	
	@Column(name="name")
	private String userName;
	
	@Column(name="password")
	private String password ;
	
	@Column(name="role")
	private String userRole;
	
	@OneToMany(mappedBy = "userbookstrips",fetch = FetchType.LAZY)
	private List<Notification> notiLists;
	
	
	
	///////
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
		name="userxtrip",
		joinColumns = @JoinColumn(name="userid"),
		inverseJoinColumns = @JoinColumn(name="tripid"))
	private List<Trip> trips;
	
	
	public List<Trip> revaloflist() {
		return trips;
	}

	public void addTrips(Trip triss) {
		trips.add(triss);
	}

	
	  public List<Notification> revallloflisttt() {
		return notiLists;
	}

	public void addnotifications(Notification notis) {
		notiLists.add(notis);
	}

	
	
	public User() {
		trips =new ArrayList<Trip>();
		notiLists=new ArrayList<Notification>();
	}
	
	
	
	public int getUserid() {
		return userId;
	}

	

	public void setUserid(int userid) {
		this.userId = userid;
	}



	public String getUsername() {
		return userName;
	}



	public void setUsername(String userName) {
		this.userName = userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}





	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public String getUserRole() {
		return userRole;
	}



	



	
	
	
	
    
}
