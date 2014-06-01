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
	@NamedQuery(name = "getInvitesForUser", query = "SELECT p.event FROM Participant p WHERE p.user = :user AND p.status = false")
})
@Entity
public class Participant 
{
	/**	The primary key */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long ID;
	
	/** The invitation status (true = participating, false = pending). */
	private boolean status;
	
	/** The user. */
	@ManyToOne(optional = false)
	@Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
	private User user;
	
	/** the eventdate the user has voted on. */
	@Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
	@ManyToOne(optional = true)
	private Eventdate eventdate;
	
	/** hibernate should fill this automatically. */
	@ManyToOne
	@Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
	private Event event;
	
	public Participant(boolean status, User user, Event event)
	{
		this.status = status;
		this.user = user;
		this.event = event;
	}
	
	/**
	 * Gets the ID.
	 * 
	 * @return the ID
	 */
	public Long getID()
	{
		return this.ID;
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
