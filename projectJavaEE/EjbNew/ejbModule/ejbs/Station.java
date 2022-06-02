package ejbs;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.io.Serializable;
import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Stateless
@LocalBean
@Entity
@Table(name="Station")
public class Station implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="stationid")
	private int sid;
	
	@Column(name="stationname")
	private String sname;
	
	@Column(name="latnum")
	private double latvalue ;
	
	@Column(name="longnum")
	private double longvalue;
	

	public int getId() {
		return sid;
	}
	



	public void setId(int id) {
		this.sid = sid;
	}



	public String getName() {
		return sname;
	}



	public void setName(String name) {
		this.sname = name;
	}



	public double getLatval() {
		return latvalue;
	}



	public void setLatval(double latval) {
		this.latvalue = latval;
	}



	public double getLongval() {
		return longvalue;
	}



	public void setLongval(double longval) {
		this.longvalue = longval;
	}

	
		
    public Station() {
        
    }

	

}
