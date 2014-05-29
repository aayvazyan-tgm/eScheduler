import org.apache.log4j.*;

/**
 * The main class, that executes the program and Configures the Logger.
 * 
 * @author Ari Ayvazyan
 * @version 29.05.2014
 */
public class Start 
{
	
	/**
	 * The main method, that executes the program and Configures the Logger.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) 
	{
		ConsoleAppender ca=new ConsoleAppender(); //creates a new Console appender for Log4J
		BasicConfigurator.configure(ca); //assign the appender to the Log4J configuration
		Logger lg=Logger.getLogger("Debug");
		lg.log(Level.INFO, "Der Betrieb wird aufgenommen");
		//TODO Execute the Controllers
		
	}
}
