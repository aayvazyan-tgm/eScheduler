package escheduler.controller;

import java.util.*;

import org.apache.log4j.Logger;
import org.hibernate.*;

import escheduler.model.*;

/**
 * All Notifications are handled trough this class.
 * 
 * @author Andreas Willinger
 * @version 29.05.2014
 */
public class NotificationsController
{
	/**
	 * Creates a new Notification object for work.
	 * 
	 * @param type The type of the new notification.
	 * @param target The target (who should see it) of the notification.
	 * @param trigger The trigger (event) which caused it.
	 * @return A new Notification Object
	 */
	public Notification createNotification(NType type, User target, Event trigger)
	{
		return new Notification(type, new Date(), this.getDescription(type), target, trigger);
	}
	
	/**
	 * Adds a new Notification to the System and calls the event listeners.
	 * 
	 * @param notification The new notification to post.
	 * @return true on success, false on database failure
	 */
	public boolean addNotitification(Notification notification)
	{
		if(notification == null)
			return false;
		
		Session session = SessionManager.getInstance().getHibernateSession();
		
		if(session == null)
			return false;
		
		Logger lg = Logger.getLogger("Debug");
		
		// as there are certain types of notifications which get sent to multiple or only single users,
		// we store all of the notification here
		List<Notification> notifications = new ArrayList<>();
		
		// these require only on receiver: organisator (all invites accepted)
		if(notification.getType() == NType.ALL_INVITES_ACCEPTED)
		{
			if(notification.getTarget() == null) return false;
			notifications.add(notification);
		}
		else
		{
			for(Participant participant: notification.getTrigger().getParticipants())
			{
				if(!participant.isStatus()) continue;
				notification.setTarget(participant.getUser());
			}
		}
		
		Transaction tx = session.beginTransaction();
		
		// and finally save all of them to the database
		try
		{
			for(Notification notif: notifications)
			{
				session.save(notif);
			}
			tx.commit();
			
			// notify all listeners
			SessionManager.getInstance().notifyListeners();
			
			return true;
		}
		catch(RuntimeException re1)
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
	 * Marks a single notification as read.
	 * 
	 * @param notification The notification to mark as read.
	 * @return true on success, false on database failure
	 */
	public boolean markAsRead(Notification notification)
	{
		if(notification == null)
			return false;
		
		Session session = SessionManager.getInstance().getHibernateSession();
		
		if(session == null)
			return false;
		
		Logger lg = Logger.getLogger("Debug");
		
		// marks the notification as read
		notification.setRead(true);
		
		Transaction tx = session.beginTransaction();
		
		// and update it in the database
		try
		{
			session.update(notification);
			
			tx.commit();
			return true;
		}
		catch(RuntimeException re1)
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
	 * Gets all Notifications for a User.
	 * 
	 * @param user The user to get the notifications for.
	 * @return A List containing all notifications for that user, otherwise null (database failure)
	 */
	public List<Notification> getNotificationsForUser(User user)
	{
		if(user == null)
			return null;
		
		Session session = SessionManager.getInstance().getHibernateSession();
		
		if(session == null)
			return null;
		
		@SuppressWarnings("unused")
		Logger lg = Logger.getLogger("Debug");
		List<Notification> notifications = new ArrayList<Notification>();
		
		Query query = session.getNamedQuery("getNotificationsForUser").setString("user", user.getUsername());
		
		@SuppressWarnings("unchecked")
		List<Notification> result = query.list();
		@SuppressWarnings("rawtypes")
		Iterator it = result.iterator();
		
		while(it.hasNext())
		{
			Object[] cols = (Object[])it.next();
			if(cols.length == 0) continue;
			
			notifications.add((Notification)cols[0]);
		}
		
		return notifications;
	}
	
	/**
	 * Gets a String representation for an NType Element.
	 * 
	 * @param type The NType Element to get the description for.
	 * @return String representation of the NType, "not found" if the supplied type doesn't exist
	 */
	public String getDescription(NType type)
	{
		switch(type)
		{
			case DATE_FIXED:
				return "A event date has been fixed.";
			case ALL_INVITES_ACCEPTED:
				return "All invites to your event have been accepted!";
			case EVENT_NEW:
				return "You have been invited to join the event.";
			case EVENT_EDIT:
				return "The event was updated.";
			case EVENT_DELETED:
				return "The event was deleted.";
		}
		
		return "not found";
	}
}
