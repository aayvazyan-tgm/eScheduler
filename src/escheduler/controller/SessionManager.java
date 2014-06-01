package escheduler.controller;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import escheduler.view.IViewElement;

/**
 * This class manages the connection between Hibernate (the data) and the application itself.
 * Only allows one instance.
 *
 * @author Ari Ayvazyan
 * @version 01.06.2014
 */
public class SessionManager 
{
	/** The listeners, waiting for a change. */
	private Queue<IViewElement> listeners = new LinkedBlockingQueue<>();
	
	/** Hibernate's Session factory to get the current session */
	private SessionFactory factory = new AnnotationConfiguration().configure().buildSessionFactory();
	
	/** The session manager. */
	private static SessionManager sessionManager;
	
	/**
	 * Instantiates a new session manager.
	 */
	private SessionManager()
	{
		
	}
	
	/**
	 * Gets the Hibernate session.
	 *
	 * @return If factory not null, a Hibernate Session Object, otherwise null.
	 */
	public Session getHibernateSession() 
	{
		return (this.factory == null)?null:this.factory.getCurrentSession();
	}
	
	/**
	 * Adds a listener.
	 *
	 * @param An Element implementing the IViewElement interface.
	 */
	public void addListener(IViewElement view)
	{
		this.listeners.add(view);
	}
	
	/**
	 * Removes a listener.
	 *
	 * @param An Element implementing the IViewElement interface.
	 */
	public void removeListener(IViewElement view)
	{
		for(IViewElement ve:this.listeners)
		{
			if(ve == view) this.listeners.remove(ve);
		}
	}
	
	/**
	 * Notify listeners.
	 */
	public void notifyListeners()
	{
		for(IViewElement ve:this.listeners)
			ve.update();
	}
	
	/**
	 * Gets the single instance of SessionManager.
	 *
	 * @return The current Instance of the Session Manager
	 */
	public static SessionManager getInstance(){
		if(sessionManager==null)
		{
			sessionManager=new SessionManager();
			return sessionManager;
		}else{
			return sessionManager;
		}
	}
}
