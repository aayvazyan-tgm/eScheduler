package escheduler.controller;

import java.util.LinkedList;
import java.util.List;

import escheduler.model.*;

import org.hibernate.*;

/**
 * This class handles all login requests made by the User.
 * 
 * @author Andreas Willinger
 * @version 29.05.2014
 */
public class LoginController 
{
	/**
	 * Checks if the given username and password combination is correct.
	 * 
	 * @param username The supplied unique username
	 * @param password The supplied password
	 * @return true, if user is valid, false if not or no connection to database
	 */
	public boolean login(String username, String password)
	{
		if(username == null || password == null)
			return false;
		
		Session session = SessionManager.getInstance().getHibernateSession();
		
		if(session == null)
			return false;
		
		session.beginTransaction();
		List<User> results = new LinkedList<User>();
		
		try
		{
			Query query = session.getNamedQuery("checkLogin")
					.setString("username", username)
					.setString("password", password);
				
			results = (List<User>)query.list();
		}
		finally
		{
			session.getTransaction().commit();
		}
			
		if(results.size() == 0)
				return false;
			else
				return true;
	}
}
