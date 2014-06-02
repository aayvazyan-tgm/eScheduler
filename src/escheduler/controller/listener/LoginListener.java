package escheduler.controller.listener;

import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;

import escheduler.controller.LoginController;
import escheduler.model.User;
import escheduler.view.MainView;
import escheduler.view.composites.LoginComposite;

/**
 * Listener that handles button clicks from escheduler.view.composite.LoginComposite
 * and checks the value using methods from escheduler.controller.LoginController
 *
 * @author Freudensprung Fabian
 * @version Jun 1, 2014
 */
public class LoginListener implements ClickListener {

	private MainView mv;
	private LoginController lcont;
	
	public LoginListener(MainView c) {
		mv = c;
	}
	
	@Override
	public void buttonClick(ClickEvent event) {
		String caption=event.getButton().getCaption();
		//Checks if the Button pressed was on the LoggedOutComposite or on the LoginComposite by checking the caption
		if(caption=="Login") {
			mv.openLogin();
		}
		else {
			if(caption=="Submit Login") {
				lcont = new LoginController();
				//Extracts the values from the form
				LoginComposite lcomp = (LoginComposite)mv.getContent();
				String pass = lcomp.getPassword().getValue();
				String user = lcomp.getUser().getValue();
				//Checks if username and password match
				if(lcont.login(user, pass)) {
					mv.login(new User(user,pass));
				}
				else {
					lcomp.setError("Login failed!");
				}
			}	
		}
			
	}

}
