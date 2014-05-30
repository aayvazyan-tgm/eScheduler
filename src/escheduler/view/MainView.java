package escheduler.view;

import javax.servlet.annotation.WebServlet;

import com.google.gwt.user.client.ui.HTML;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import escheduler.view.composites.EventComposite;
import escheduler.view.composites.EventDetailComposite;
import escheduler.view.composites.EventListComposite;
import escheduler.view.composites.InvitationComposite;
import escheduler.view.composites.LoggedInComposite;
import escheduler.view.composites.LoggedOutComposite;
import escheduler.view.composites.NewEventComposite;
import escheduler.view.composites.NotificationComposite;
import escheduler.view.composites.LoginComposite;
import escheduler.view.composites.RegisterComposite;

@SuppressWarnings("serial")
@Theme("escheduler")
public class MainView extends UI {

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

	public void openLogin() {
		setContent(new LoginComposite(this));
	}

	public void login() {
		setContent(new LoggedInComposite(this));
	}

	public void register() {
		setContent(new LoggedInComposite(this));
	}

	public void openRegister() {
		setContent(new RegisterComposite(this));
	}

}