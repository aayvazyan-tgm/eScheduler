package escheduler.controller.listener;

import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

import escheduler.view.MainView;
import escheduler.view.composites.LoggedInComposite;

public class DeclineListener implements ClickListener {

	private MainView mv;
	private int id;
	
	public DeclineListener(MainView mv, int id) {
		this.mv=mv;
		this.id=id;
	}

	@Override
	public void buttonClick(ClickEvent event) {
		//Remove from Invitations
		((LoggedInComposite) mv.getContent()).getInvitations().removeInvitation(id);
	}

}
