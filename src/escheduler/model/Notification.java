package escheduler.model;

import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * Notifies the User about changes on Events he participates or owns/manages.
 * 
 * @author Andreas Willinger
 * @version 01.06.2014
 */
@NamedQueries({
	@NamedQuery(name = "getNotificationsForUser", query = "FROM Notification n INNER JOIN n.target ta INNER JOIN n.trigger tr WHERE ta.username = :user")
})
@Entity
public class Notification 
{
	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long ID;
	
	/** The type. */
	@NotNull
	private NType type;

	/** The date. */
	@NotNull
	private Date date;
	
	/** The description. */
	private String description;
	
	/** the target */
	@NotNull
	@ManyToOne
	private User target;
	
	/** The trigger. */
	@ManyToOne
	private Event trigger;
	
	/** users who have read this notification **/
	private boolean read;
	
	public Notification(NType type, Date date, String description, User target, Event trigger)
	{
		this.type = type;
		this.date = date;
		this.description = description;
		this.target = target;
		this.trigger = trigger;
	}
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Long getID()
	{
		return this.ID;
	}
	
	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public NType getType() 
	{
		return type;
	}

	/**
	 * Sets the type.
	 *
	 * @param type the new type
	 */
	public void setType(NType type) 
	{
		this.type = type;
	}

	/**
	 * Gets the date.
	 *
	 * @return the date
	 */
	public Date getDate() 
	{
		return date;
	}

	/**
	 * Sets the date.
	 *
	 * @param date the new date
	 */
	public void setDate(Date date) 
	{
		this.date = date;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() 
	{
		return description;
	}

	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) 
	{
		this.description = description;
	}
	
	/**
	 * Gets the target.
	 * 
	 * @return the target 
	 */
	public User getTarget() {
		return target;
	}
	
	/**
	 * Gets the target.
	 * 
	 * @param target the new target
	 */
	public void setTarget(User target) {
		this.target = target;
	}

	/**
	 * Gets the trigger.
	 *
	 * @return the trigger
	 */
	public Event getTrigger() 
	{
		return trigger;
	}

	/**
	 * Sets the trigger.
	 *
	 * @param trigger the new trigger
	 */
	public void setTrigger(Event trigger) {
		this.trigger = trigger;
	}
	
	/**
	 * Gets the read status of this notification.
	 * 
	 * @return the read status of this notification.
	 */
	public boolean getRead()
	{
		return this.read;
	}
	
	/**
	 * Sets the read status of this notification.
	 * 
	 * @param the new read status.
	 */
	public void setRead(boolean read)
	{
		this.read = read;
	}
}
