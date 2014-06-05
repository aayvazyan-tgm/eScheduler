package escheduler.controller.listener;

import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

import escheduler.controller.EventsController;
import escheduler.controller.InvitesController;
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
	private Long id;
	private InvitesController ic;
	private EventsController ec;
	
	/**
	 * Constructor of InvitstionListener
	 * 
	 * @param mv MainView containing the Composites
	 * @param id specifies the Table-Row the Button is in
	 */
	public InvitationListener(MainView mv, Long id) {
		ic = new InvitesController();
		ec = new EventsController();
		this.mv=mv;
		this.id=id;
	}

	
	/* (non-Javadoc)
	 * @see com.vaadin.ui.Button.ClickListener#buttonClick(com.vaadin.ui.Button.ClickEvent)
	 */
	@Override
	public void buttonClick(ClickEvent event) {
		//Checks if the Accept- / Decline-Button was clicked
		if(event.getButton().getCaption().equals("A")){
			//Saves the Accept of the Invitation in the DB
			ic.acceptInvite(mv.getUser(), ec.getEventById(id));
			((LoggedInComposite)mv.getContent()).getEvents().getEventList().loadEvents();
		}
		else {
			//Saves the Decline of the Invitation in the DB
			ic.declineInvite(mv.getUser(), ec.getEventById(id));
		}
		//Removes the Invitation after saving the change to the DB
		((LoggedInComposite) mv.getContent()).getInvitations().removeInvitation(id);
	}

}
