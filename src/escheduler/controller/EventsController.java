package escheduler.controller;

import java.util.*;

import org.apache.log4j.Logger;
import org.hibernate.*;

import escheduler.model.*;

/**
 * The main class of this Application.
 * It handles all event-related aspects (except creating new ones), such as:
 * comments, event dates, votes, comments
 * 
 * @author Andreas Willinger
 * @version 29.05.2014
 */
public class EventsController 
{
	/**
	 * Disables voting on a Event.
	 * 
	 * @param e The Event to disable voting on
	 * @param d (multi-vote per event only) The fixed Eventdate
	 * @return true on success, false on failure (database failure, event doesn't exist anymore)
	 */
	public boolean fixEvent(Event e, Eventdate d) 
	{
		if(e == null)
			return false;
		
		Logger lg = Logger.getLogger("Debug");
		
		// refresh the current event data
		e = this.getUpdatedEvent(e, "getEventDates");
		if(e == null) return false;
		
		Session session = SessionManager.getInstance().getHibernateSession();
		if(session == null) return false;
		
		// here, a switch happens
		// if the event allows  multiple votes per date, then delete all other dates
		for(Eventdate date:e.getEventdates())
		{
			if(e.getType() != EType.DATE_MULTIUSER) break;
			if(d == null) break;
			
			if(date.getID() != d.getID())
			{
				e.getEventdates().remove(date);
			}
		}
		
		// same for both types: disable voting
		e.setVotingactive(false);
		
		Transaction tx = session.beginTransaction();
		
		// and finally, update the database
		try
		{
			session.update(e);
			tx.commit();
			
			NotificationsController nc = new NotificationsController();
			nc.addNotitification(nc.createNotification(NType.DATE_FIXED, null, e));
			
			return true;
		}
		catch(RuntimeException re)
		{
			try
			{
				tx.rollback();
			}
			catch(RuntimeException re2)
			{
				lg.error("Commit/Rollback fehlgeschlagen (Exception)", re2);
			}
			return false;
		}
	}
	
	/**
	 * Adds a new User to the Event and sends them an Invite.
	 * 
	 * @param user The user to invite
	 * @param e The event to add the user to
	 * @return true on success, false on failure (database failure, event doesn't exist anymore)
	 */
	public boolean addUser(User user, Event e)
	{
		if(user == null || e == null)
			return false;
		
		Logger lg = Logger.getLogger("Debug");
		
		e = this.getUpdatedEvent(e, "getEventParticipants");
		if(e == null) return false;
		
		// create a new Participant object, and update the database
		Session session = SessionManager.getInstance().getHibernateSession();
		
		if(session == null) return false;
		Transaction tx = session.beginTransaction();
		
		Participant participant = new Participant(false, user, null);
		e.getParticipants().add(participant);
		
		try
		{
			session.update(e);
			tx.commit();
			
			return true;
		}
		catch(RuntimeException re)
		{
			try
			{
				tx.rollback();
			}
			catch(RuntimeException re2)
			{
				lg.error("Commit/Rollback fehlgeschlagen (Exception)", re2);
			}
			return false;
		}
	}
	
	/**
	 * Removes an existing user from the event.
	 * 
	 * @param user The user to remove
	 * @param event The event to remove the user from
	 * @return true on success, false on failure (database failure, user has already accepted the invite, event doesn't exist anymore)
	 */
	public boolean removeUser(User user, Event event) 
	{
		if(user == null || event == null)
			return false;
		
		Logger lg = Logger.getLogger("Debug");
		
		event = this.getUpdatedEvent(event, "getEventParticipants");
		
		if(event == null) return false;
		
		// loop trough all participants and delete each one where the supplied user occurs
		ArrayList<Participant> participants = new ArrayList<>(event.getParticipants());
		
		Participant pc = null;
		for(Participant p:participants)
		{
			if(p.getUser().getUsername().equals(user.getUsername()))
			{
				if(p.isStatus()) return false;
				pc = p;
				event.getParticipants().remove(p);
				break;
			}
		}
		
		// then update the database
		Session session = SessionManager.getInstance().getHibernateSession();
		
		if(session == null) return false;
		
		Transaction tx = session.beginTransaction();
		
		try
		{
			session.update(event);
			tx.commit();
			
			return true;
		}
		catch(RuntimeException re)
		{
			try
			{
				tx.rollback();
			}
			catch(RuntimeException re2)
			{
				lg.error("Commit/Rollback fehlgeschlagen (Exception)", re2);
			}
			return false;
		}
	}
	
	/**
	 * Casts are Vote made by a user for a specific eventdate.
	 * 
	 * @param e The event on which the user voted on.
	 * @param u The user who voted.
	 * @param eventdate The user's vote choice.
	 * @return true on success, false on failure (database failure, event doesn't exist anymore, multiple votes on a single-vote date)
	 */
	public boolean Vote(Event e, User u, Eventdate eventdate) 
	{
		if(e == null || u == null || eventdate == null)
			return false;
		
		Logger lg = Logger.getLogger("Debug");
		
		e = this.getUpdatedEvent(e, "getEventParticipants");
		if(e == null) return false;
		
		Session session = SessionManager.getInstance().getHibernateSession();
		if(session == null) return false;
		
		ArrayList<Participant> participants = new ArrayList<>(e.getParticipants());
		
		// first, look if others have already voted for the supplied
		// only check if the event is a one-vote-per-date
		if(e.getType() == EType.DATE_SINGLEUSER)
		{
			for(Participant participant:participants)
			{
				Eventdate date = participant.getEventdate();
				if(date == null) continue;
				
				if(date.getID() == eventdate.getID())
				{
					// only allow one vote per date
					return false;
				}
			}
		}
		
		// then cast the vote
		for(Participant participant:participants)
		{
			if(participant.getUser().getUsername().equals(u.getUsername()))
			{
				participant.setEventdate(eventdate);
				break;
			}
		}
		
		// and update the DB
		Transaction tx = session.beginTransaction();
		
		try
		{
			session.update(e);
			tx.commit();
			
			return true;
		}
		catch(RuntimeException re)
		{
			try
			{
				tx.rollback();
			}
			catch(RuntimeException re2)
			{
				lg.error("Error while rolling back transaction.", re2);
			}
			return false;
		}
	}
	
	/**
	 * Adds a new comment to an event.
	 * 
	 * @param comment The comment-String to add.
	 * @param user The user ("owner") who posted it.
	 * @param event The event to add the comment to.
	 * @return true on success, false on failure (database failure, event doesn't exist anymore)
	 */
	public boolean addComment(String comment, User user, Event event) 
	{
		if(event == null || user == null || comment == null || comment.equals(""))
			return false;
		
		Logger lg = Logger.getLogger("Debug");
		
		event = this.getUpdatedEvent(event, "getEventComments");
		if(event == null) return false;
		
		Session session = SessionManager.getInstance().getHibernateSession();
		if(session == null) return false;
		
		// check if user is a part of the event
		boolean isParticipant = false;
		for(Participant participant: event.getParticipants())
		{
			if(participant.getUser().getUsername().equals(user.getUsername()))
				isParticipant = true;
		}
		if(!isParticipant) return false;
		
		// setup a new Comment Object
		Comment c = new Comment(new Date(), comment, user, event);
		event.getComments().add(c);
		
		Transaction tx = session.beginTransaction();
		
		// and save it to the database
		try
		{
			session.update(event);
			tx.commit();
			
			return true;
		}
		catch(RuntimeException re)
		{
			try
			{
				tx.rollback();
			}
			catch(RuntimeException re2)
			{
				lg.error("Error while rolling back transaction.", re2);
			}
			return false;
		}
	}
	
	/**
	 * Removes an existing Comment from the event.
	 * 
	 * @param comment The comment to delete
	 * @param event The event to delete the comment from
	 * @return true on success, false on failure (event doesn't exist anymore, user doesn't have access to delete, database failure)
	 */
	public boolean deleteComment(Comment comment, User user, Event event) 
	{
		if(comment == null || user == null || event == null)
			return false;
		
		Logger lg = Logger.getLogger("Debug");
		
		event = this.getUpdatedEvent(event, "getEventComments");
		if(event == null) return false;
		
		Session session = SessionManager.getInstance().getHibernateSession();
		if(session == null) return false;
		
		if(!event.getOrganisator().getUsername().equals(user.getUsername()) && !comment.getAuthor().getUsername().equals(user.getUsername()))
			return false;
		
		// loop trough all comments, look for the supplied one and delete it.
		for(Comment c:event.getComments())
		{
			if(c.getID() == comment.getID())
			{
				event.getComments().remove(c);
				break;
			}
		}
		
		Transaction tx = session.beginTransaction();
		
		try
		{
			session.update(event);
			
			tx.commit();
			
			return true;
		}
		catch(RuntimeException re)
		{
			try
			{
				tx.rollback();
			}
			catch(RuntimeException re2)
			{
				lg.error("Error while rolling back transaction.", re2);
			}
			return false;
		}
	}
	
	/**
	 * Updates an Event's General Information
	 * 
	 * @param e The event do update
	 * @return true on success, false on failure (event doesn't exist anymore, >1 has accepted their invite, database failure)
	 */
	public boolean updateEvent(Event e)
	{
		if(e == null)
			return false;
		
		Logger lg = Logger.getLogger("Debug");
		
		e = this.getUpdatedEvent(e, "getEventParticipants");
		if(e == null) return false;
		
		// first check if none of the users have yet accepted their invites
		for(Participant participant: e.getParticipants())
		{
			if(participant.isStatus())
				return false;
		}
		
		Session session = SessionManager.getInstance().getHibernateSession();
		if(session == null) return false;
		
		Transaction tx = session.beginTransaction();
		
		try
		{
			session.update(e);
			tx.commit();
			
			NotificationsController nc = new NotificationsController();
			nc.addNotitification(nc.createNotification(NType.EVENT_EDIT, null, e));
			
			return true;
		}
		catch(RuntimeException re)
		{
			try
			{
				tx.rollback();
			}
			catch(RuntimeException re2)
			{
				lg.error("Commit/Rollback fehlgeschlagen (Exception)", re2);
			}
			return false;
		}
	}
	
	/**
	 * Purges an existing event from the database
	 * 
	 * @param e The event to purge
	 * @return true on success, false on failure (event doesn't exist anymore, database failure)
	 */
	public boolean deleteEvent(Event e)
	{
		if(e == null)
			return false;
		
		Logger lg = Logger.getLogger("Debug");
		
		Session session = SessionManager.getInstance().getHibernateSession();
		if(session == null) return false;
		
		Transaction tx = session.beginTransaction();
		
		// delete it
		try
		{
			session.delete(e);
			tx.commit();
			
			NotificationsController nc = new NotificationsController();
			nc.addNotitification(nc.createNotification(NType.EVENT_DELETED, null, e));
			
			return true;
		}
		catch(RuntimeException re)
		{
			try
			{
				tx.rollback();
			}
			catch(RuntimeException re2)
			{
				lg.error("Commit/Rollback fehlgeschlagen (Exception)", re2);
			}
			return false;
		}
	}
	
	/**
	 * Searches the user in the system by the supplied partial username.
	 * 
	 * @param search The partial (or full) name to search for
	 * @return A User Object containing the partial username, null on failure (user not found, database failure)
	 */
	public User searchUser(String search)
	{
		if(search == null || search.equals(""))
			return null;
		
		Session session = SessionManager.getInstance().getHibernateSession();
		if(session == null) return null;
		
		Query query = session.getNamedQuery("searchUser")
				.setString("q", search);
		
		// first, get all ROWS
		@SuppressWarnings("unchecked")
		List<User> users = (List<User>)query.list();
		@SuppressWarnings("rawtypes")
		Iterator it = users.iterator();
		
		// iterate over all of them
		while(it.hasNext())
		{
			// then, get all columns for each row
			Object[] col = (Object[])it.next();
			if(col.length == 0) continue;
			
			User user = (User)col[0];
			
			return user;
		}
		return null;
	}
	
	/**
	 * Gets all events where a user is participating in or is the owner of.
	 * 
	 * @param u The user to get the events for.
	 * @return A List<Event> if there were events found, null on failure (database failure)
	 */
	public List<Event> getEventsForUser(User u)
	{
		if(u == null)
			return null;
		
		Session session = SessionManager.getInstance().getHibernateSession();
		
		if(session == null)
			return null;
		
		// prepare query
		Query query = session.getNamedQuery("getEventComplete")
						.setString("user", u.getUsername());
		
		// first, get all ROWS
		@SuppressWarnings("unchecked")
		List<Event> results = (List<Event>)query.list();
		@SuppressWarnings("rawtypes")
		Iterator it = results.iterator();
		
		List<Event> events = new ArrayList<Event>();
		
		// iterate over all of them
		while(it.hasNext())
		{
			// then, get all columns for each row
			Object[] col = (Object[])it.next();
			if(col.length == 0) continue;
			Event event = (Event)col[0];
			
			events.add(event);
		}
		
		return events;
	}
	
	/**
	 * Gets an existing event identified by its unique ID.
	 * 
	 * @param ID The ID of the event to fetch.
	 * @return An Event Object on success, null on failure (event not found, database failure)
	 */
	public Event getEventById(Long ID)
	{
		if(ID == null || ID < 0)
			return null;
		
		Session session = SessionManager.getInstance().getHibernateSession();
		
		if(session == null)
			return null;
		
		// prepare query
		Query query = session.getNamedQuery("getEventComplete")
						.setLong("ID", ID);
		
		// first, get all ROWS
		@SuppressWarnings("unchecked")
		List<Event> results = (List<Event>)query.list();
		@SuppressWarnings("rawtypes")
		Iterator it = results.iterator();
		
		// iterate over all of them
		while(it.hasNext())
		{
			// then, get all columns for each row
			Object[] col = (Object[])it.next();
			if(col.length == 0) continue;
			
			Event event = (Event)col[0];
			
			return event;
		}
		
		return null;
	}
	
	/**
	 * Gets the current state of the Event Object in the Database.
	 * Use this every time you want to update/delete data from an event.
	 * 
	 * @param e The event to update
	 * @param type The Query type (look for the namedqueries in the model/Event.java file)
	 * @return An Event Object on success, null on failure (event not found, database failure)
	 */
	private Event getUpdatedEvent(Event e, String type)
	{
		if(type == null || type.equals(""))
			return null;
		
		Session session = SessionManager.getInstance().getHibernateSession();
		
		if(session == null)
			return null;
		
		// prepare query
		Query query = session.getNamedQuery(type)
						.setLong("ID", e.getID());
		
		// first, get all ROWS
		@SuppressWarnings("unchecked")
		List<Event> results = (List<Event>)query.list();
		@SuppressWarnings("rawtypes")
		Iterator it = results.iterator();
		
		// iterate over all of them
		while(it.hasNext())
		{
			// then, get all columns for each row
			Object[] col = (Object[])it.next();
			if(col.length == 0) continue;
			
			Event event = (Event)col[0];
			
			return event;
		}
		
		return null;
	}
}