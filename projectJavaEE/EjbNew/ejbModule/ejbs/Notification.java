package ejbs;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.jws.soap.SOAPBinding.Use;
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
import javax.persistence.Table;

@Stateless
@LocalBean
@Entity
@Table(name="noti")
public class Notification implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="meid")
	private int messageId;
	
	
	
	@Column(name="mestring")
	public String messageContent; 
	
	@Column(name="daofme")
	public String dateMessage;
	
	
	@ManyToOne
	@JoinColumn(name = "userid")
	private User userbookstrips;
	
	 public User retvaloflistt() {
		return userbookstrips;
	}

	public void putuser(User userrset) {
		userbookstrips = userrset;
	}
	 
	 
	
	public Notification() {
		userbookstrips=new User();
	}
	
	


	public void setMessageid(int messageid) {
		this.messageId = messageid;
	}

	
	public String getMessage() {
		return messageContent;
	}

	public void setMessage(String message) {
		this.messageContent = message;
	}

	public String getDateMessage() {
		return dateMessage;
	}

	public void setDateMessage(String dateMessage) {
		this.dateMessage = dateMessage;
	}

	
	
}
