package escheduler.controller.listener;

import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

import escheduler.view.MainView;
import escheduler.view.composites.LoggedInComposite;

/**
 * Listener that handles button clicks from escheduler.view.composite.NotificationComposite
 * by moving notifications to the read category and marking them as read 
 * using escheduler.controller.NotificationsController
 *
 * @author Freudensprung Fabian
 * @version Jun 1, 2014
 */
@SuppressWarnings("serial")
public class NotificationReadListener implements ClickListener {

	private MainView mv;
	private Long id;

	/**
	 * Constructor of NotificationReadListener
	 * 
	 * @param mv MainView the Composites are in
	 * @param id specifies the row, the notification is in
	 */
	public NotificationReadListener(MainView mv, Long id) {
		this.mv=mv;
		this.id=id;
	}

	
	/* (non-Javadoc)
	 * @see com.vaadin.ui.Button.ClickListener#buttonClick(com.vaadin.ui.Button.ClickEvent)
	 */
	@Override
	public void buttonClick(ClickEvent event) {
		((LoggedInComposite) mv.getContent()).getNotifications().removeNotification(id);
	}

}
