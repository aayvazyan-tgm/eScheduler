package escheduler.model;

/**
 * Describes the type of events.
 * @author Ari Ayvazyan
 * @version 29.05.2014
 */
public enum EType
{
	
	/** Defines a date that allows more than one users. */
	DATE_MULTIUSER,
	
	/** Defines a date that allows only one user. */
	DATE_SINGLEUSER
}
