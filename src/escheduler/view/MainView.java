package escheduler.view;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import escheduler.model.User;
import escheduler.view.composites.LoggedInComposite;
import escheduler.view.composites.LoggedOutComposite;
import escheduler.view.composites.LoginComposite;
import escheduler.view.composites.RegisterComposite;

/**
 * Vaadin UI that uses the Custom Components from escheduler.view.composites to display 
 * the User Interface
 *
 * @author Freudensprung Fabian
 * @version Jun 1, 2014
 */
@SuppressWarnings("serial")
@Theme("escheduler")
public class MainView extends UI {
	
	private boolean loggedIn = false;
	private User user;

	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = MainView.class)
	public static class Servlet extends VaadinServlet {
	}

	@Override
	protected void init(VaadinRequest request) {
		final VerticalLayout layout = new VerticalLayout();
		layout.setMargin(true);
		setContent(layout);
		layout.setSizeFull();		
		
		LoggedOutComposite composite = new LoggedOutComposite(this);
		layout.addComponent(composite);
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public void openLogin() {
		setContent(new LoginComposite(this));
	}

	public void login(User user) {
		this.user = user;
		loggedIn = true;
		setContent(new LoggedInComposite(this));
	}

	public void register(User user) {
		login(user);
	}

	public void openRegister() {
		setContent(new RegisterComposite(this));
	}

}