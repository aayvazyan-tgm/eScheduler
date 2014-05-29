package escheduler.controller;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import escheduler.model.*;

/**
 * Manages all newly created Events.
 * 
 * @author Andreas Willinger
 * @version 29.05.2014
 */
public class NewEventController 
{
	/**
	 * Inserts a new Event into the Database
	 * 
	 * @param event The Event to insert into the DB.
	 * @return true, if insertion was successful, false if not (no database connection).
	 */
	public boolean createEvent(Event event) 
	{
		Logger lg=Logger.getLogger("Debug");
		if(event == null)
			return false;
		
		Session session = SessionManager.getInstance().getHibernateSession();
		
		if(session == null)return false; // better safe than sorry
		
		Transaction tx = session.beginTransaction();
		
		try
		{
			session.save(event);
			
			tx.commit();
			
			// notify all invited users about that new event
			// only do that after the event was successfully saved
			NotificationsController nc = new NotificationsController();
			nc.addNotitification(nc.createNotification(NType.EVENT_NEW, null, event));
			
			return true;
		}
		catch(RuntimeException ex)
		{
			try
			{
				tx.rollback();
			}
			catch(RuntimeException ex2)
			{
				lg.error("Error while rolling back Transaction", ex2);
				//TODO: Proper exception handling
			}
			
			return false;
		}
	}
}
