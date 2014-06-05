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
	 */
	public ControllerProxy(){
		this.loginController=new LoginController();
		this.registerController=new RegisterController();
		this.newEventController=new NewEventController();
		this.invitesController=new InvitesController();
		this.eventsController=new EventsController();
		this.notificationsController=new NotificationsController();
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

