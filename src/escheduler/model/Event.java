
package escheduler.model;

import java.util.Collection;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 * An Event, which can be identified by:
 * 
 * A unique ID, name, description, organisator, type, participants and eventdates
 * @author Andreas Willinger
 * @version 03.06.2014
 */
@NamedQueries({
	@NamedQuery(name = "getEventComplete", query = "FROM Event e LEFT JOIN e.participants p LEFT JOIN e.comments c LEFT JOIN e.eventdates d INNER JOIN e.organisator o WHERE e.ID = :ID"),
	@NamedQuery(name = "getEventParticipants", query = "FROM Event e INNER JOIN e.organisator o LEFT JOIN e.participants p WHERE e.ID = :ID"),
	@NamedQuery(name = "getEventDates", query = "FROM Event e LEFT JOIN e.eventdates WHERE e.ID = :ID"),
	@NamedQuery(name = "getEventComments", query = "FROM Event e LEFT JOIN e.comments c WHERE e.ID = :ID"),
	@NamedQuery(name = "getEventsForUser", query = "FROM Event e LEFT JOIN e.participants p WHERE e.organisator.username = :user OR p.user.username = :user"),
	@NamedQuery(name = "getInvitesForUser", query = "FROM Event e LEFT JOIN e.participants p WHERE p.user.username = :username AND p.status = false")
})
@Entity
public class Event 
{
	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long ID;
	
	/** The name. */
	@NotNull
	private String name;
	
	/** description of the event. */
	private String description;
	
	/** is voting active. */
	private boolean votingactive;
	
	/** The organisator. */
	@NotNull
	@ManyToOne(optional = false)
	private User organisator;
	
	/** type: may either be multi-vote (multiple users can vote on a date) or single-vote. */
	private EType type;
	
	/** The participants, each with a user, their vote and invite state. */
	@OneToMany
	@LazyCollection(LazyCollectionOption.FALSE)
	@Cascade({org.hibernate.annotations.CascadeType.ALL})
	private Collection<Participant> participants;
	
	/** The comments. */
	@OneToMany
	@LazyCollection(LazyCollectionOption.FALSE)
	@Cascade({org.hibernate.annotations.CascadeType.ALL})
	private Collection<Comment> comments;
	
	/** The eventdates. */
	@OneToMany
	@LazyCollection(LazyCollectionOption.FALSE)
	@Cascade({org.hibernate.annotations.CascadeType.ALL})
	private Collection<Eventdate> eventdates;
	
	/**
	 * Default constructor for Hibernate
	 */
	public Event()
	{
		
	}
	
	/**
	 * Instantiates a new Event
	 * 
	 * @param name the name
	 * @param organisator the organisator
	 * @param type the type
	 */
	public Event(String name, User organisator, EType type)
	{
		this.name = name;
		this.votingactive = true;
		this.organisator = organisator;
		this.type = type;
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
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() 
	{
		return name;
	}
	
	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) 
	{
		this.name = name;
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
	 * Checks if is votingactive.
	 *
	 * @return true, if is votingactive
	 */
	public boolean isVotingactive() 
	{
		return votingactive;
	}
	
	/**
	 * Sets the votingactive.
	 *
	 * @param votingactive the new votingactive
	 */
	public void setVotingactive(boolean votingactive) 
	{
		this.votingactive = votingactive;
	}
	
	/**
	 * Gets the organisator.
	 *
	 * @return the organisator
	 */
	public User getOrganisator() 
	{
		return organisator;
	}
	
	/**
	 * Sets the organisator.
	 *
	 * @param organisator the new organisator
	 */
	public void setOrganisator(User organisator) 
	{
		this.organisator = organisator;
	}
	
	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public EType getType() 
	{
		return type;
	}
	
	/**
	 * Sets the type.
	 *
	 * @param type the new type
	 */
	public void setType(EType type) 
	{
		this.type = type;
	}
	
	/**
	 * Gets the participants.
	 *
	 * @return the participants
	 */
	public Collection<Participant> getParticipants()
	{
		return participants;
	}
	
	/**
	 * Sets the participants.
	 *
	 * @param participants the new participants
	 */
	public void setParticipants(Collection<Participant> participants) 
	{
		this.participants = participants;
	}
	
	/**
	 * Gets the comments.
	 *
	 * @return the comments
	 */
	public Collection<Comment> getComments() 
	{
		return comments;
	}
	
	/**
	 * Sets the comments.
	 *
	 * @param comments the new comments
	 */
	public void setComments(Collection<Comment> comments) 
	{
		this.comments = comments;
	}
	
	/**
	 * Gets the eventdates.
	 *
	 * @return the eventdates
	 */
	public Collection<Eventdate> getEventdates()
	{
		return eventdates;
	}
	
	/**
	 * Sets the eventdates.
	 *
	 * @param eventdates the new eventdates
	 */
	public void setEventdates(Collection<Eventdate> eventdates)
	{
		this.eventdates = eventdates;
	}
}