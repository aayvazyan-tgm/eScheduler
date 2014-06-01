package escheduler.model;

import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * This class represents a comment posted on an event, identified by:
 * 
 * date, author, event, text.
 *
 * @author Andreas Willinger
 * @version 01.06.2014
 */
@Entity
public class Comment 
{
	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long ID;
	
	/** The date. */
	@NotNull
	private Date date;
	
	/** The comment text itself.. */
	@NotNull
	private String text;
	
	/** The author. */
	@ManyToOne(optional = false)
	private User author;
	
	/** The event, which the comment was posted on. */
	@ManyToOne(optional = false)
	private Event event;
	
	/**
	 * Default Constructor for Hibernate
	 */
	public Comment()
	{
		
	}
	
	/**
	 * Instantiates a new comment.
	 *
	 * @param date the date
	 * @param text the text
	 * @param author the author
	 * @param event the event
	 */
	public Comment(Date date, String text, User author, Event event)
	{
		this.date = date;
		this.text = text;
		this.author = author;
		this.event = event;
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
	 * Sets the id.
	 *
	 * @param ID the new id
	 */
	public void setID(Long ID)
	{
		this.ID = ID;
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
	 * Gets the text.
	 *
	 * @return the text
	 */
	public String getText() 
	{
		return text;
	}

	/**
	 * Sets the text.
	 *
	 * @param text the new text
	 */
	public void setText(String text) 
	{
		this.text = text;
	}

	/**
	 * Gets the author.
	 *
	 * @return the author
	 */
	public User getAuthor()
	{
		return author;
	}

	/**
	 * Sets the author.
	 *
	 * @param author the new author
	 */
	public void setAuthor(User author) 
	{
		this.author = author;
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
