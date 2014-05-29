package escheduler.model;

import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * Notifies the User about changes on Events he participates or owns/manages.
 * 
 * @author Andreas Willinger
 * @version 29.05.2014
 */
@NamedQueries({
	@NamedQuery(name = "FROM Notification n WHERE n.trigger IN (SELECT p.event FROM Participant p WHERE p.user = :user)", query = "")
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
	@NotNull
	private String description;
	
	/** The source. */
	@ManyToOne(optional = false)
	private User source;
	
	/** The trigger. */
	@ManyToOne(optional = false)
	private Event trigger;
	
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
	 * Sets the id.
	 *
	 * @param ID the new id
	 */
	public void setID(Long ID)
	{
		this.ID = ID;
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
	 * Gets the source.
	 *
	 * @return the source
	 */
	public User getSource() 
	{
		return source;
	}

	/**
	 * Sets the source.
	 *
	 * @param source the new source
	 */
	public void setSource(User source)
	{
		this.source = source;
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
	
	

}
