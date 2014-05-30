package escheduler.controller.listener;

import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;

import escheduler.view.MainView;

public class LoginListener implements ClickListener {

	private MainView mv;
	
	public LoginListener(MainView c) {
		mv = c;
	}
	
	@Override
	public void buttonClick(ClickEvent event) {
		String caption=event.getButton().getCaption();
		if(caption=="Login") {
			mv.openLogin();
		}
		else {
			if(caption=="Submit Login") {
				//Check login values
				mv.login();
			}	
		}
			
	}

}
