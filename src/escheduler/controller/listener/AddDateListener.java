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
 * Handles Button Clicks in the NewEventComposite to add the selected Date to the Form
 * 
 * @author Freudensprung Fabian
 * @version Jun 4, 2014
 *
 */
public class AddDateListener implements ClickListener {

	MainView mv;
	
	/**
	 * Constructor of AddDateListener
	 * 
	 * @param mv MainView containing the Composites
	 */
	public AddDateListener(MainView mv) {
		this.mv = mv;
	}

	
	/* (non-Javadoc)
	 * @see com.vaadin.ui.Button.ClickListener#buttonClick(com.vaadin.ui.Button.ClickEvent)
	 */
	@Override
	public void buttonClick(ClickEvent event) {
		Button bu = event.getButton();
		String ca = bu.getCaption();
		//Checks if the Event-Source is the expected Button
		if(ca.equals("Add Date")) {
			NewEventComposite ne = (NewEventComposite) mv.getContent();
			ne.addDate();
		}
	}

}
