package escheduler.model;

import java.util.Collection;

import javax.persistence.*;
import javax.validation.constraints.*;

@NamedQueries({
	@NamedQuery(name = "getEventInformation", query = "FROM Event e WHERE e.ID = :ID"),
	@NamedQuery(name = "getEventParticipants", query = "FROM Event e INNER JOIN e.participants WHERE e.ID = :ID"),
	@NamedQuery(name = "getEventComments", query = "FROM Event e, INNER JOIN e.comments WHERE e.ID = :ID"),
	@NamedQuery(name = "getEventDates", query = "FROM Event e INNER JOIN e.eventdates WHERE e.ID = :ID"),
	@NamedQuery(name = "getEventsForUser", query = "FROM Event e INNER JOIN e.participants p WHERE e.User = :user OR p = :user")
})
@Entity
public class Event 
{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long ID;
	
	@NotNull
	private String name;
	@NotNull
	private String description;
	private boolean votingactive;
	@NotNull
	private User organisator;
	private EType type;
	
	@OneToMany
	private Collection<Participant> participants;
	@OneToMany
	private Collection<Comment> comments;
	@OneToMany
	private Collection<Eventdate> eventdates;
	
	private EntityManager entityManager;
	
	public Long getID()
	{
		return this.ID;
	}
	
	public void setID(Long ID)
	{
		this.ID = ID;
	}
	
	public String getName() 
	{
		return name;
	}
	public void setName(String name) 
	{
		this.name = name;
	}
	public String getDescription() 
	{
		return description;
	}
	public void setDescription(String description) 
	{
		this.description = description;
	}
	public boolean isVotingactive() 
	{
		return votingactive;
	}
	public void setVotingactive(boolean votingactive) 
	{
		this.votingactive = votingactive;
	}
	public User getOrganisator() 
	{
		return organisator;
	}
	public void setOrganisator(User organisator) 
	{
		this.organisator = organisator;
	}
	public EType getType() 
	{
		return type;
	}
	public void setType(EType type) 
	{
		this.type = type;
	}
	public Collection<Participant> getParticipants()
	{
		return participants;
	}
	public void setParticipants(Collection<Participant> participants) 
	{
		this.participants = participants;
	}
	public Collection<Comment> getComments() 
	{
		return comments;
	}
	public void setComments(Collection<Comment> comments) 
	{
		this.comments = comments;
	}
	public Collection<Eventdate> getEventdates()
	{
		return eventdates;
	}
	public void setEventdates(Collection<Eventdate> eventdates)
	{
		this.eventdates = eventdates;
	}
}