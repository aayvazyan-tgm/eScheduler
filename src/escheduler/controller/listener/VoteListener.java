/**
 * 
 */
package escheduler.controller.listener;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

import escheduler.view.MainView;
import escheduler.view.composites.LoggedInComposite;

/**
 * Handles Button-Clicks meant to vote for an Eventdate or
 * fix an Eventdate
 * 
 * @author Freudensprung Fabian
 * @version Jun 5, 2014
 *
 */
public class VoteListener implements ClickListener {

	private MainView mv;
	
	/**
	 * Constructor of Vote Listener
	 * 
	 * @param mv MainView containing the Composites
	 */
	public VoteListener(MainView mv) {
		this.mv = mv;
	}
	
	/**
	 * Handles Button-Clicks
	 */
	@Override
	public void buttonClick(ClickEvent event) {
		Button b = event.getButton();
		String cap = b.getCaption();
		//Checks if the Evendate should be voted for or fixed
		if(cap.equals("Vote")) {
			((LoggedInComposite)mv.getContent()).getEvents().getEventDetail().vote();
		}
		else {
			if(cap.equals("Pick Date")) {
				((LoggedInComposite)mv.getContent()).getEvents().getEventDetail().pickDate();
			}
		}
	}

}
