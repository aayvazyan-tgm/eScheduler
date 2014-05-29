package escheduler.controller;

import java.util.List;

import escheduler.model.*;

import org.hibernate.*;

/**
 * 
 * @author Andreas Willinger
 * @version 20140529.1
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
		
		Query query = session.getNamedQuery("checkLogin")
				.setString("username", username)
				.setString("password", password);
			
		List<User> results = (List<User>)query.list();
			
		if(results.size() == 0)
				return false;
			else
				return true;
	}
}
