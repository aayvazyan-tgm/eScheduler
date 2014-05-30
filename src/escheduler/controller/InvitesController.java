package escheduler.controller;

import java.util.*;

import org.apache.log4j.Logger;
import org.hibernate.*;

import escheduler.model.*;

/**
 * This class handles the accepting/declining of invitations sent by orginsators of events.
 * 
 * @author Andreas Willinger
 * @version 29.05.2014
 */
public class InvitesController
{
	/**
	 * Accepts the invite sent to an event by the organisator.
	 * 
	 * @param user The user who accepts the invite.
	 * @param event The event to accept the invite for.
	 * @return true on success, false on failure (event not found, database failure)
	 */
	public boolean acceptInvite(User user, Event event)
	{
		if(user == null || event == null)
			return false;
		
		Session session = SessionManager.getInstance().getHibernateSession();
		
		if(session == null)
			return false;
		
		Logger lg = Logger.getLogger("Debug");
		
		Participant pc = null;
		
		// loop trough all participants, find the user and set its participation state to true
		for(Participant p: event.getParticipants())
		{
			if(p.getUser().getUsername().equals(user.getUsername()))
			{
				pc = p;
				p.setStatus(true);
				
				break;
			}
		}
		
		Transaction tx = session.beginTransaction();
		
		try
		{
			session.update(event);
			tx.commit();
			
			this.countAccepted(new LinkedList<Participant>(event.getParticipants()), user, event);
			
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
	 * Declines the invite sent to an event by the organisator.
	 * 
	 * @param user The user who rejects the invite.
	 * @param event 
	 * @return
	 */
	public boolean declineInvite(User user, Event event)
	{
		if(user == null || event == null)
			return false;
		
		Session session = SessionManager.getInstance().getHibernateSession();
		
		if(session == null)
			return false;
		
		Logger lg = Logger.getLogger("Debug");
		
		Participant pc = null;
		
		// loop trough all participants, find the user and remove them.
		for(Participant p: event.getParticipants())
		{
			if(p.getUser().getUsername().equals(user.getUsername()))
			{
				pc = p;
				event.getParticipants().remove(p);
				break;
			}
		}
		
		Transaction tx = session.beginTransaction();
		
		try
		{
			session.update(event);
			tx.commit();
			
			this.countAccepted(new LinkedList<Participant>(event.getParticipants()), user, event);
			
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
	 * Gets all pending Invites for a user.
	 * 
	 * @param u The user to get the invites for.
	 * @return A List containing Events to which the user was invited to, or null on failure
	 */
	public List<Event> getInvites(User u)
	{
		if(u == null)
			return null;
		
		Session session = SessionManager.getInstance().getHibernateSession();
		
		if(session == null)
			return null;
		
		// prepare query
		Query query = session.getNamedQuery("getInvitesForUser")
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
	 * Checks whether or not all participants of an event have already accepted their invitation.
	 * If they did, it adds a new notification.
	 * 
	 * @param participants The event's participants
	 * @param lastUser The last user checked (for the notification)
	 * @param trigger The event which is checked (for the notification)
	 */
	private void countAccepted(List<Participant> participants, User lastUser, Event trigger)
	{
		// counts the amount of total accepts invites
		// used to add the all-users-accepted notification.
		int totalAccepted = 1;
		int totalAdded = participants.size();
		
		for(Participant p: participants)
		{
			if(p.isStatus())
				totalAccepted++;
		}
		
		// check if all people have accepted their invites
		if(totalAccepted == totalAdded)
		{
			// and create a new notification if they did
			NotificationsController nc = new NotificationsController();
			nc.addNotitification(nc.createNotification(NType.ALL_INVITES_ACCEPTED, trigger.getOrganisator(), trigger));
		}
	}
}
