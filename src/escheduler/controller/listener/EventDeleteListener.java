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
 * @author Freudensprung Fabian
 * @version Jun 5, 2014
 *
 */
public class EventDeleteListener implements ClickListener {

	private MainView mv;

	/**
	 * Constructor of EventDeleteListener
	 * 
	 * @param mv MainView, the Composites are in
	 */
	public EventDeleteListener(MainView mv) {
		this.mv = mv;
	}
	
	/* (non-Javadoc)
	 * @see com.vaadin.ui.Button.ClickListener#buttonClick(com.vaadin.ui.Button.ClickEvent)
	 */
	@Override
	public void buttonClick(ClickEvent event) {
		Button b = event.getButton();
		String cap = b.getCaption();
		if(cap.equals("Delete Event")) {
			((LoggedInComposite)mv.getContent()).getEvents().getEventDetail().deleteEvent();
		}

	}

}
