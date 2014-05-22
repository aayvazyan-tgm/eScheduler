package escheduler.model;

import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.*;

@NamedQueries({
	@NamedQuery(name = "getCommentDetails", query = "SELECT * FROM Comment c WHERE c = :comment")
})
@Entity
public class Comment 
{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long ID;
	@NotNull
	private Date date;
	@NotNull
	private String text;
	@ManyToOne(optional = false)
	private User author;
	@ManyToOne(optional = false)
	private Event event;
	
	private EntityManager entityManager;
	
	public Long getID()
	{
		return this.ID;
	}
	
	public void setID(Long ID)
	{
		this.ID = ID;
	}
	public Date getDate() 
	{
		return date;
	}

	public void setDate(Date date) 
	{
		this.date = date;
	}

	public String getText() 
	{
		return text;
	}

	public void setText(String text) 
	{
		this.text = text;
	}

	public User getAuthor()
	{
		return author;
	}

	public void setAuthor(User author) 
	{
		this.author = author;
	}

	public Event getEvent() 
	{
		return event;
	}

	public void setEvent(Event event) 
	{
		this.event = event;
	}
}
