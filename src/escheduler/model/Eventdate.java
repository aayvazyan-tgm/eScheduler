package escheduler.model;

import java.util.Date;

import javax.validation.constraints.*;
import javax.persistence.*;

import org.hibernate.annotations.Cascade;

/**
 * A date proposal on an event, aka. a voting option.
 * 
 * @author Andreas Willinger
 * @version 03.06.2014
 */
@Entity
public class Eventdate 
{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long ID;
	
	/** The start date. */
	@NotNull
	private Date start;
	
	/** The end date. */
	@NotNull
	private Date end;
	
	/** The event (hibernate should fill this automatically). */
	@Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
	@ManyToOne
	private Event event;
	
	/**
	 * Default constructor for Hibernate
	 */
	public Eventdate()
	{
		
	}
	
	/**
	 * Instantiates a new Eventdate
	 * 
	 * @param start the start
	 * @param end the end
	 * @param event the event
	 */
	public Eventdate(Date start, Date end, Event event)
	{
		this.start = start;
		this.end = end;
		this.event = event;
	}
	
	/**
	 * Gets the ID.
	 * 
	 * @return the ID
	 */
	public Long getID()
	{
		return ID;
	}
	
	/**
	 * Sets the ID
	 * 
	 * @param ID the new ID
	 */
	public void setID(Long ID)
	{
		this.ID = ID;
	}
	/**
	 * Gets the start.
	 *
	 * @return the start
	 */
	public Date getStart() 
	{
		return start;
	}

	/**
	 * Sets the start.
	 *
	 * @param start the new start
	 */
	public void setStart(Date start) 
	{
		this.start = start;
	}

	/**
	 * Gets the end.
	 *
	 * @return the end
	 */
	public Date getEnd() 
	{
		return end;
	}

	/**
	 * Sets the end.
	 *
	 * @param end the new end
	 */
	public void setEnd(Date end) 
	{
		this.end = end;
	}

	/**
	 * Gets the event.
	 *
	 * @return the event
	 */
	public Event getEvent() 
	{
		return event;
	}

	/**
	 * Sets the event.
	 *
	 * @param event the new event
	 */
	public void setEvent(Event event) 
	{
		this.event = event;
	}
}
