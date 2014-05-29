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
 * @version 29.05.2014
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
		
		// first, let's check if the user already exists
		Query query = session.getNamedQuery("checkExistance")
				.setString("username", username);
			
		List<User> results = (List<User>)query.list();
			
		if(results.size() > 0)
				return false;
		
		// then, create a new transaction and actually save the user
		Transaction tx = session.beginTransaction();
		User user = new User(username, password);
		
		try
		{
			session.save(user);
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
				lg.error("Commit/Rollback fehlgeschlagen (Exception). ", re2);
			}
			
			return false;
		}
	}
}
