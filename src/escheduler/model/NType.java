package escheduler.model;

/**
 * The Enum NType defines the type of a Notification.
 * @author Ari Ayvazyan
 * @version 29.05.2014
 */
public enum NType 
{
	/** A date got fixed */
	DATE_FIXED,
	
	/** All invites are accepted. */
	ALL_INVITES_ACCEPTED,
	
	/** A new Invitation to a event */
	EVENT_NEW,
	
	/** The event was edited */
	EVENT_EDIT,
	
	/** The event deleted. */
	EVENT_DELETED
}
