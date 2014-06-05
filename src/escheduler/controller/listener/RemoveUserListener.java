package escheduler.controller.listener;

import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

import escheduler.view.MainView;
import escheduler.view.composites.LoggedInComposite;

/**
 * Handles clicks in the EventDetailView meant to remove an user
 * 
 * @author Freudensprung Fabian
 * @version Jun 5, 2014
 *
 */
public class RemoveUserListener implements ClickListener {

	private MainView mv;
	
	/**
	 * Constructor of RemoveUserListener
	 * 
	 * @param mv MainView containing the Composites
	 */
	public RemoveUserListener(MainView mv) {
		this.mv = mv;
	}
	
	
	/* (non-Javadoc)
	 * @see com.vaadin.ui.Button.ClickListener#buttonClick(com.vaadin.ui.Button.ClickEvent)
	 */
	@Override
	public void buttonClick(ClickEvent event) {
		((LoggedInComposite)mv.getContent()).getEvents().getEventDetail().removeSelectedUser();
	}

}
