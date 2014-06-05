/**
 * 
 */
package escheduler.controller.listener;

import com.vaadin.ui.Component;
import com.vaadin.ui.TabSheet.SelectedTabChangeEvent;
import com.vaadin.ui.TabSheet.SelectedTabChangeListener;

import escheduler.view.MainView;

/**
 * Handles Tab switches to detect if the selected Tab was the Logout-Tab
 * 
 * @author Freudensprung Fabian
 * @version Jun 5, 2014
 *
 */
public class LogoutListener implements SelectedTabChangeListener {

	private MainView mv;
	
	/**
	 * Instantiates a new logout listener.
	 *
	 * @param mv the mv
	 */
	public LogoutListener(MainView mv) {
		this.mv = mv;
	}
	/* (non-Javadoc)
	 * @see com.vaadin.ui.TabSheet.SelectedTabChangeListener#selectedTabChange(com.vaadin.ui.TabSheet.SelectedTabChangeEvent)
	 */
	@Override
	public void selectedTabChange(SelectedTabChangeEvent event) {
		Component c = event.getTabSheet().getSelectedTab();
		if(c.getId().equals("logout")) {
			mv.logOut();
		}
		

	}

}
