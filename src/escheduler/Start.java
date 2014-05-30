package escheduler;

import javax.servlet.*;
import org.apache.log4j.*;

/**
 * The main class, that executes the program and Configures the Logger.
 * 
 * @author Ari Ayvazyan
 * @version 30.05.2014
 */
public class Start
	implements ServletContextListener
{	
	/**
	 * @see ServletContextListener#contextInitialized(ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent event)
	{
		ConsoleAppender ca = new ConsoleAppender(); //creates a new Console appender for Log4J
		BasicConfigurator.configure(ca); //assign the appender to the Log4J configuration
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
