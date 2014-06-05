/**
 * 
 */
package escheduler.controller.listener;

import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

import escheduler.view.MainView;
import escheduler.view.composites.NewEventComposite;

/**
 * Handles Button Clicks in the NewEventComposite to add the selected User to the Form
 * 
 * @author Freudensprung Fabian
 * @version Jun 3, 2014
 *
 */
@SuppressWarnings("serial")
public class AddUserListener implements ClickListener {

	private MainView mv;
	
	/**
	 * Constructor of AddUserListener
	 * 
	 * @param mv MainView containing the Composites
	 */
	public AddUserListener(MainView mv) {
		this.mv = mv;
	}


	/* (non-Javadoc)
	 * @see com.vaadin.ui.Button.ClickListener#buttonClick(com.vaadin.ui.Button.ClickEvent)
	 */
	@Override
	public void buttonClick(ClickEvent event) {
		NewEventComposite nec = (NewEventComposite) mv.getContent();
		nec.addUser();
	}

}
