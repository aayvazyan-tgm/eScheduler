package escheduler.view;

import escheduler.model.Notification;

/**
 * Classes implementing this Interface are considered event listeners for new Notifications.
 * 
 * @author Andreas Willinger
 * @version 05.06.2014
 */
public interface IViewElement
{
	/**
	 * Called by the publisher of the Event with the new Notification.
	 * 
	 * @param notification The new Notification
	 */
	void update(Notification notification);
}