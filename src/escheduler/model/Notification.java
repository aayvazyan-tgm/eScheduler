package escheduler.model;

import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.*;

@NamedQueries({
	@NamedQuery(name = "FROM Notification n WHERE n.trigger IN (SELECT p.event FROM Participant p WHERE p.user = :user)", query = "")
})
@Entity
public class Notification 
{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long ID;
	
	@NotNull
	private NType type;

	@NotNull
	private Date date;
	
	@NotNull
	private String description;
	
	@ManyToOne(optional = false)
	private User source;
	
	@ManyToOne(optional = false)
	private Event trigger;

	private EntityManager entityManager;
	
	public Long getID()
	{
		return this.ID;
	}
	
	public void setID(Long ID)
	{
		this.ID = ID;
	}
	
	public NType getType() 
	{
		return type;
	}

	public void setType(NType type) 
	{
		this.type = type;
	}

	public Date getDate() 
	{
		return date;
	}

	public void setDate(Date date) 
	{
		this.date = date;
	}

	public String getDescription() 
	{
		return description;
	}

	public void setDescription(String description) 
	{
		this.description = description;
	}

	public User getSource() 
	{
		return source;
	}

	public void setSource(User source)
	{
		this.source = source;
	}

	public Event getTrigger() 
	{
		return trigger;
	}

	public void setTrigger(Event trigger) {
		this.trigger = trigger;
	}
	
	

}
