package escheduler.controller;

/**
 * The Controller Class
 * 
 * 
 * @author Ari Ayvazyan
 * @version 29.05.2014
 */
public class ControllerProxy {
	
	/** The login controller. */
	private LoginController loginController;
	
	/** The register controller. */
	private RegisterController registerController;
	
	/** The new event controller. */
	private NewEventController newEventController;
	
	/** The invites controller. */
	private InvitesController invitesController;
	
	/** The events controller. */
	private EventsController eventsController;
	
	/** The notifications controller. */
	private NotificationsController notificationsController;
	
	/**
	 * Instantiates a new controller proxy.
	 *
	 * @param lg the LoginController
	 * @param rg the RegisterController
	 * @param ne the NewEventController
	 * @param inv the InvitesController
	 * @param ev the EventsController
	 * @param nc the NotificationsController
	 */
	public ControllerProxy(LoginController lg,RegisterController rg, 
			NewEventController ne, InvitesController inv, EventsController ev, 
			NotificationsController nc){
		this.loginController=lg;
		this.registerController=rg;
		this.newEventController=ne;
		this.invitesController=inv;
		this.eventsController=ev;
		this.notificationsController=nc;
	}
	
	/**
	 * Gets the login controller.
	 *
	 * @return the loginController
	 */
	public LoginController getLoginController() {
		return loginController;
	}
	
	/**
	 * Gets the register controller.
	 *
	 * @return the registerController
	 */
	public RegisterController getRegisterController() {
		return registerController;
	}
	
	/**
	 * Gets the new event controller.
	 *
	 * @return the newEventController
	 */
	public NewEventController getNewEventController() {
		return newEventController;
	}
	
	/**
	 * Gets the invites controller.
	 *
	 * @return the invitesController
	 */
	public InvitesController getInvitesController() {
		return invitesController;
	}
	
	/**
	 * Gets the events controller.
	 *
	 * @return the eventsController
	 */
	public EventsController getEventsController() {
		return eventsController;
	}
	
	/**
	 * Gets the notifications controller.
	 *
	 * @return the notificationsController
	 */
	public NotificationsController getNotificationsController() {
		return notificationsController;
	}
}

