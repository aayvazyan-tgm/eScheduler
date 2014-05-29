package escheduler.model;

import java.util.Date;

import javax.validation.constraints.*;
import javax.persistence.*;

/**
 * A date proposal on an event, aka. a voting option.
 * 
 * @author Andreas Willinger
 * @version 29.05.2014
 */
@Entity
public class Eventdate 
{
	/** The start. */
	@NotNull
	private Date start;
	
	/** The end. */
	@NotNull
	private Date end;
	
	/** The event. */
	@ManyToOne(optional = false)
	private Event event;

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
