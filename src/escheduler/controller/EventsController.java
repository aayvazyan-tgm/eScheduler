package escheduler.controller;

import java.util.*;

import javassist.bytecode.Descriptor.Iterator;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import escheduler.model.*;

/**
 * 
 * @author Andreas Willinger
 * @version 29.05.2014
 */
public class EventsController 
{
	public boolean fixEvent(Event e, Eventdate d) 
	{
		if(e == null || d == null)
			return false;
		
		Logger lg = Logger.getLogger("Debug");
		
		e = this.getUpdatedEvent(e, "getEventDates");
		if(e == null) return false;
		
		Session session = SessionManager.getInstance().getHibernateSession();
		if(session == null) return false;
		
		// here, a switch happens
		// if the event is a multi-votes per date Event, then delete all other dates
		for(Eventdate date:e.getEventdates())
		{
			if(e.getType() != EType.DATE_MULTIUSER) break;
			
			if((!date.getStart().toString().equals(d.getStart().toString())) 
					&& 
				(!date.getEnd().toString().equals(d.getEnd().toString())))
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
		
		Participant participant = new Participant();
		participant.setEventdate(null);
		participant.setStatus(false);
		participant.setUser(user);
		
		e.getParticipants().add(participant);
		
		try
		{
			session.save(participant);
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
		for(Participant participant:participants)
		{
			if(e.getType() != EType.DATE_SINGLEUSER) break;
			
			Eventdate date = participant.getEventdate();
			if(date == null) continue;
			
			if((date.getStart().toString().equals(eventdate.getStart().toString())) 
					&& 
				(date.getEnd().toString().equals(eventdate.getEnd().toString())))
			{
				// only allow one vote per date
				return false;
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

	public boolean addComment(String comment, User user, Event event) 
	{
		if(event == null || user == null || comment == null || comment.equals(""))
			return false;
		
		Logger lg = Logger.getLogger("Debug");
		
		event = this.getUpdatedEvent(event, "getEventComments");
		if(event == null) return false;
		
		Session session = SessionManager.getInstance().getHibernateSession();
		if(session == null) return false;
		
		Comment c = new Comment();
		c.setAuthor(user);
		c.setDate(new Date());
		c.setEvent(event);
		c.setText(comment);
		event.getComments().add(c);
		
		Transaction tx = session.beginTransaction();
		
		try
		{
			session.save(c);
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

	public boolean removeUser(User user, Event event) 
	{
		if(user == null || event == null)
			return false;
		
		Logger lg = Logger.getLogger("Debug");
		
		event = this.getUpdatedEvent(event, "getEventParticipants");
		
		if(event == null) return false;
		
		// loop trough all participants and delete each one where the supplied user occurs
		ArrayList<Participant> participants = new ArrayList<>(event.getParticipants());
		
		for(Participant p:participants)
		{
			if(p.getUser().getUsername().equals(user.getUsername()))
				event.getParticipants().remove(p);
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

	public boolean deleteComment(Comment comment, Event event) 
	{
		if(comment == null || event == null)
			return false;
		
		Logger lg = Logger.getLogger("Debug");
		
		event = this.getUpdatedEvent(event, "getEventComments");
		if(event == null) return false;
		
		Session session = SessionManager.getInstance().getHibernateSession();
		if(session == null) return false;
		
		// loop trough all comments and delete the supplied one
		for(Comment c:event.getComments())
		{
			if(c.getID() == comment.getID())
			{
				event.getComments().remove(c);
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

	public boolean addDate(Eventdate eventdate, Event event) 
	{
		if(eventdate == null || event == null)
			return false;
		
		Logger lg = Logger.getLogger("Debug");
		
		event = this.getUpdatedEvent(event, "getEventDates");
		if(event == null) return false;
		
		Session session = SessionManager.getInstance().getHibernateSession();
		if(session == null) return false;
		
		// do not allow duplicate dates
		for(Eventdate date:event.getEventdates())
		{
			if((date.getStart().toString() == eventdate.getStart().toString())
					&&
					(date.getEnd().toString() == eventdate.getEnd().toString()))
			{
				return false;
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
				lg.error("Commit/Rollback fehlgeschlagen (Exception)", re2);
			}
			return false;
		}
		return false;
	}

	public boolean editDate(Eventdate eventdate, Event event)
	{
		return false;
	}
	
	public boolean updateEvent(Event e)
	{
		if(e == null)
			return false;
		
		Logger lg = Logger.getLogger("Debug");
		
		Session session = SessionManager.getInstance().getHibernateSession();
		if(session == null) return false;
		
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
				lg.error("Commit/Rollback fehlgeschlagen (Exception)", re2);
			}
			return false;
		}
	}
	
	public boolean deleteEvent(Event e)
	{
		if(e == null)
			return false;
		
		Logger lg = Logger.getLogger("Debug");
		
		Session session = SessionManager.getInstance().getHibernateSession();
		if(session == null) return false;
		
		Transaction tx = session.beginTransaction();
		
		try
		{
			session.delete(e);
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
	
	public User searchUser(String search)
	{
		if(search == null || search.equals(""))
			return null;
		
		Session session = SessionManager.getInstance().getHibernateSession();
		if(session == null) return null;
		
		Query query = session.getNamedQuery("searchUser")
				.setString("q", search);
		
		List<User> users = (List<User>)query.list();
		java.util.Iterator it = users.iterator();
		
		while(it.hasNext())
		{
			Object[] t = (Object[])it.next();
			User user = (User)t[0];
			
			return user;
		}
		return null;
	}
	
	private Event getUpdatedEvent(Event e, String type)
	{
		if(type == null || type.equals(""))
			return null;
		
		Session session = SessionManager.getInstance().getHibernateSession();
		
		if(session == null)
			return null;
		
		// first, let's check if the user already exists
		Query query = session.getNamedQuery(type)
						.setLong("ID", e.getID());
					
		List<Event> results = (List<Event>)query.list();
		java.util.Iterator it = results.iterator();
		
		while(it.hasNext())
		{
			Object[] t = (Object[])it.next();
			Event event = (Event)t[0];
			
			return event;
		}
		
		return null;
	}
}
