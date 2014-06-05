/**
 * 
 */
package escheduler.controller.listener;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

import escheduler.view.MainView;
import escheduler.view.composites.NewEventComposite;

/**
 * Handles Button Clicks in the NewEventComposite that are meant to
 * remove a previously added Date/User
 * 
 * @author Freudensprung Fabian
 * @version Jun 5, 2014
 *
 */
public class FormRemoveListener implements ClickListener {

	private MainView mv;
	
	/**
	 * Constructor of FormRemoveListener
	 * 
	 * @param mv MainView containing the Composites
	 */
	public FormRemoveListener(MainView mv) {
		this.mv = mv;
	}

	
	/* (non-Javadoc)
	 * @see com.vaadin.ui.Button.ClickListener#buttonClick(com.vaadin.ui.Button.ClickEvent)
	 */
	@Override
	public void buttonClick(ClickEvent event) {
		Button b = event.getButton();
		String cap = b.getCaption();
		//Checks if the Button to delete an User or to delete a Date was clicked
		if(cap.equals("Remove Date")) {
			((NewEventComposite)mv.getContent()).removeSelectedDate();
		}
		else {
			if(cap.equals("Remove User")) {
				((NewEventComposite)mv.getContent()).removeSelectedUser();
			}
		}
	}

}
