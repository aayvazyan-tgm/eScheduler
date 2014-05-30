package escheduler.controller.listener;

import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;

import escheduler.view.MainView;

public class RegisterListener implements ClickListener {

	private MainView mv;
	
	public RegisterListener(MainView c) {
		mv = c;
	}
	
	@Override
	public void buttonClick(ClickEvent event) {
		String caption=event.getButton().getCaption();
		if(caption=="Register") {
			mv.openRegister();
		}
		else {
			if(caption=="Submit Registration") {
				//Check registration values
				mv.register();
			}	
		}
			
	}

}
