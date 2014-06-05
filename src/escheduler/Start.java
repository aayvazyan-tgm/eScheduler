package escheduler;

import javax.servlet.*;
import org.apache.log4j.*;

/**
 * The main class, that executes the program and Configures the Logger.
 * 
 * @author Ari Ayvazyan
 * @version 05.06.2014
 */
public class Start
	implements ServletContextListener
{	
	/**
	 * @see ServletContextListener#contextInitialized(ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent event)
	{
		Logger lg = Logger.getLogger("Debug");
		lg.log(Level.INFO, "Der Betrieb wird aufgenommen");
	}
	
	/**
	 * @see ServletContextListener#contextDestroyed(ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent event)
	{
	}
}
