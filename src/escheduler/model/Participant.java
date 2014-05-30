package escheduler.model;

import javax.persistence.*;

import org.hibernate.annotations.Cascade;

/**
 * A Participant is a User who participates in an Event, got a State (invited/participating) and has either already voted or not (null).
 * 
 * @author Andreas Willinger
 * @version 29.05.2014
 */
@NamedQueries({
	@NamedQuery(name = "getInvitesForUser", query = "SELECT Event e FROM Participant p WHERE p.User = :user AND p.status = false")
})
@Entity
public class Participant 
{
	/** The invitation status (true = participating, false = pending). */
	private boolean status;
	
	/** The user. */
	@Id
	@ManyToOne(optional = false)
	private User user;
	
	/** the eventdate the user has voted on. */
	@Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
	@ManyToOne(optional = true)
	private Eventdate eventdate;
	
	/** hibernate should fill this automatically. */
	@Id
	@Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
	@ManyToOne
	private Event event;
	
	public Participant(boolean status, User user, Event event)
	{
		this.status = status;
		this.user = user;
		this.event = event;
	}

	/**
	 * Checks if is status.
	 *
	 * @return true, if is status
	 */
	public boolean isStatus() 
	{
		return status;
	}

	/**
	 * Sets the status.
	 *
	 * @param status the new status
	 */
	public void setStatus(boolean status) 
	{
		this.status = status;
	}

	/**
	 * Gets the user.
	 *
	 * @return the user
	 */
	public User getUser() 
	{
		return user;
	}

	/**
	 * Sets the user.
	 *
	 * @param user the new user
	 */
	public void setUser(User user)
	{
		this.user = user;
	}

	/**
	 * Gets the eventdate.
	 *
	 * @return the eventdate
	 */
	public Eventdate getEventdate() 
	{
		return eventdate;
	}

	/**
	 * Sets the eventdate.
	 *
	 * @param eventdate the new eventdate
	 */
	public void setEventdate(Eventdate eventdate)
	{
		this.eventdate = eventdate;
	}	
}
