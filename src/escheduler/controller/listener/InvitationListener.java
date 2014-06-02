package escheduler.controller.listener;

import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

import escheduler.view.MainView;
import escheduler.view.composites.LoggedInComposite;

/**
 * Listener that handles clicks on Accept/Decline Buttons in escheduler.view.composites.InvitationComposite
 * using methods from escheduler.controller.InvitesController 
 *
 * @author Freudensprung Fabian
 * @version Jun 1, 2014
 */
public class InvitationListener implements ClickListener {

	private MainView mv;
	private int id;
	
	public InvitationListener(MainView mv, int id) {
		this.mv=mv;
		this.id=id;
	}

	@Override
	public void buttonClick(ClickEvent event) {
		if(event.getButton().getCaption().equals("A")){
			//Accept Invitation
		}
		else {
			//Decline Invitation
		}
		((LoggedInComposite) mv.getContent()).getInvitations().removeInvitation(id);
	}

}
