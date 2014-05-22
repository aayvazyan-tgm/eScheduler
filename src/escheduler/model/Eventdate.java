package escheduler.model;

import java.util.Date;

import javax.validation.constraints.*;
import javax.persistence.*;

@Entity
public class Eventdate 
{
	@NotNull
	private Date start;
	
	@NotNull
	private Date end;
	
	@ManyToOne(optional = false)
	private Event event;

	private EntityManager entityManager;

	public Date getStart() 
	{
		return start;
	}

	public void setStart(Date start) 
	{
		this.start = start;
	}

	public Date getEnd() 
	{
		return end;
	}

	public void setEnd(Date end) 
	{
		this.end = end;
	}

	public Event getEvent() 
	{
		return event;
	}

	public void setEvent(Event event) 
	{
		this.event = event;
	}

	public EntityManager getEntityManager() 
	{
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) 
	{
		this.entityManager = entityManager;
	}	
}
