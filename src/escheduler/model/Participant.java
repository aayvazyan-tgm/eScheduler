package escheduler.model;

import javax.persistence.*;
import javax.validation.constraints.*;

@NamedQueries({
	@NamedQuery(name = "getInvitesForUser", query = "SELECT Event e FROM Participant p WHERE p.User = :user AND p.status = false")
})
@Entity
public class Participant 
{
	private boolean status;
	
	@ManyToOne(optional = false)
	private User user;
	
	@ManyToOne(optional = true)
	private Eventdate eventdate;
	
	@ManyToOne
	private Event event;
	
	@NotNull
	private EntityManager entityManager;

	public boolean isStatus() 
	{
		return status;
	}

	public void setStatus(boolean status) 
	{
		this.status = status;
	}

	public User getUser() 
	{
		return user;
	}

	public void setUser(User user)
	{
		this.user = user;
	}

	public Eventdate getEventdate() 
	{
		return eventdate;
	}

	public void setEventdate(Eventdate eventdate)
	{
		this.eventdate = eventdate;
	}	
}
