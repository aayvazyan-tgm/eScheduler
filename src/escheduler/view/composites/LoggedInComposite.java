package escheduler.view.composites;

import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.TabSheet;

import escheduler.view.MainView;

/**
 * Custom Vaadin Component that combines everthing a logged in user can do, using
 * EventComposite, InvitationComposite & NotificationComposite
 *
 * @author Freudensprung Fabian
 * @version Jun 1, 2014
 */
public class LoggedInComposite extends CustomComponent {

	private TabSheet mainLayout;	
	private EventComposite events;
	private NotificationComposite notifications;
	private InvitationComposite invitations;
	private MainView mv;
	
	/**
	 * Constructor of LoggedInComposite
	 * 
	 * @param mv 
	 */
	public LoggedInComposite(MainView mv) {
		this.mv=mv;
		buildMainLayout();
		setCompositionRoot(mainLayout);
	}

	/**
	 * Builds the main layout.
	 *
	 * @return the tab sheet
	 */
	private TabSheet buildMainLayout() {
		// common part: create layout
		mainLayout = new TabSheet();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("100%");
		mainLayout.setHeight("100%");
		
		events = new EventComposite(mv);
		mainLayout.addTab(events, "Events", null);
		
		notifications = new NotificationComposite(mv);
		mainLayout.addTab(notifications, "Notifications", null);
		
		invitations = new InvitationComposite(mv);
		mainLayout.addTab(invitations, "Invitations", null);
		
		// top-level component properties
		setWidth("100.0%");
		setHeight("100.0%");
		
		return mainLayout;
	}
	
	
	/**
	 * Gets the events.
	 *
	 * @return the events
	 */
	public EventComposite getEvents() {
		return events;
	}

	/**
	 * Sets the events.
	 *
	 * @param events the new events
	 */
	public void setEvents(EventComposite events) {
		this.events = events;
	}

	/**
	 * Gets the notifications.
	 *
	 * @return the notifications
	 */
	public NotificationComposite getNotifications() {
		return notifications;
	}

	/**
	 * Sets the notifications.
	 *
	 * @param notifications the new notifications
	 */
	public void setNotifications(NotificationComposite notifications) {
		this.notifications = notifications;
	}

	/**
	 * Gets the invitations.
	 *
	 * @return the invitations
	 */
	public InvitationComposite getInvitations() {
		return invitations;
	}

	/**
	 * Sets the invitations.
	 *
	 * @param invitations the new invitations
	 */
	public void setInvitations(InvitationComposite invitations) {
		this.invitations = invitations;
	}
	

}
