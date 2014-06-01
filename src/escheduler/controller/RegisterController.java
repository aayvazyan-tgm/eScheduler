package escheduler.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import escheduler.model.User;

/**
 * This class handles user registrations in the System.
 * 
 * @author Andreas Willinger
 * @version 01.06.2014
 */
public class RegisterController 
{
	/**
	 * Creates a new User in the System, with login capabilities.
	 * 
	 * @param username The unique name of the new user
	 * @param password The password of the new user
	 * @return true, if creation was successful, false, if the user already exists or there was a DB failure.
	 */
	public boolean register(String username, String password) 
	{
		if(username == null || password == null)
			return false;
		
		Logger lg=Logger.getLogger("Debug");
		Session session = SessionManager.getInstance().getHibernateSession();
		
		if(session == null)
			return false;
		
		Transaction tx = session.beginTransaction();
		
		// first, let's check if the user already exists
		Query query = session.getNamedQuery("checkExistance")
				.setString("username", username);
			
		@SuppressWarnings("unchecked")
		List<User> results = (List<User>)query.list();
		
		if(results.size() > 0)
				return false;
		
		// then, actually save the user
		User user = new User(username, password);
		
		try
		{
			session.save(user);
			tx.commit();
			
			return true;
		}
		catch(RuntimeException re)
		{
			re.printStackTrace();
			try
			{
				tx.rollback();
			}
			catch(RuntimeException re2)
			{
				lg.error("Commit/Rollback fehlgeschlagen (Exception). ", re2);
			}
			
			return false;
		}
	}
}
