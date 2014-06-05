package escheduler.view;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import escheduler.controller.SessionManager;
import escheduler.model.Notification;
import escheduler.model.User;
import escheduler.view.composites.EventComposite;
import escheduler.view.composites.LoggedInComposite;
import escheduler.view.composites.LoggedOutComposite;
import escheduler.view.composites.LoginComposite;
import escheduler.view.composites.NewEventComposite;
import escheduler.view.composites.RegisterComposite;

/**
 * Vaadin UI that uses the Custom Components from escheduler.view.composites to display 
 * the User Interface
 *
 * @author Freudensprung Fabian
 * @version Jun 1, 2014
 */
/**
 * @author ffreu_000
 * @version Jun 5, 2014
 *
 */
@SuppressWarnings("serial")
@Theme("escheduler")
public class MainView extends UI implements IViewElement{
	
	private boolean loggedIn = false;
	private User user;

	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = MainView.class)
	public static class Servlet extends VaadinServlet {
	}

	/**
	 * Initialises the UI on first run
	 */
	@Override
	protected void init(VaadinRequest request) {
		final VerticalLayout layout = new VerticalLayout();
		layout.setMargin(true);
		setContent(layout);
		layout.setSizeFull();		
		
		LoggedOutComposite composite = new LoggedOutComposite(this);
		layout.addComponent(composite);

		SessionManager.getInstance().addListener(this);
	}

	
	/**
	 * Gets the user.
	 *
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * Sets the user.
	 *
	 * @param user the new user
	 */
	public void setUser(User user) {
		this.user = user;
	}
	
	public void openLogin() {
		setContent(new LoginComposite(this));
	}

	public void login(User user) {
		this.user = user;
		setLoggedIn(true);
		setContent(new LoggedInComposite(this));
	}

	/**
	 * Registers a user 
	 *
	 * @param user the user
	 */
	public void register(User user) {
		login(user);
	}

	
	/**
	 * Open register composite
	 */
	public void openRegister() {
		setContent(new RegisterComposite(this));
	}

	/**
	 * open New event composite
	 */
	public void newEvent() {
		setContent(new NewEventComposite(this));
	}

	/**
	 * Checks if is logged in.
	 *
	 * @return the loggedIn
	 */
	public boolean isLoggedIn() {
		return loggedIn;
	}

	/**
	 * Sets the logged in.
	 *
	 * @param loggedIn the loggedIn to set
	 */
	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	
	/* (non-Javadoc)
	 * @see escheduler.view.IViewElement#update(escheduler.model.Notification)
	 */
	@Override
	public void update(Notification notification) {
		if(this.getContent() instanceof LoggedInComposite) {
			LoggedInComposite lic = (LoggedInComposite) this.getContent();
			if(notification.getTarget().getUsername().equals(user.getUsername()) && notification.getRead()==false) {
				lic.getNotifications().addNotification(notification);
			}
			
		}
	}

	/**
	 * opens event composite
	 */
	public void viewEvents() {
		this.setContent(new LoggedInComposite(this));
	}
}